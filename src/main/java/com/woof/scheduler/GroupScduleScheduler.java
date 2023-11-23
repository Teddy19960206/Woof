package com.woof.scheduler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.groupcourseorder.entity.GroupCourseOrder;
import com.woof.groupcourseorder.service.GroupCourseOrderService;
import com.woof.groupcourseorder.service.GroupCourseOrderServiceImpl;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupcourseschedule.service.GroupCourseScheduleServiceImpl;
import com.woof.groupcourseschedule.service.GroupGourseScheduleService;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.groupscheduledetail.service.GroupScheduleDetailService;
import com.woof.groupscheduledetail.service.GroupScheduleDetailServiceImpl;
import com.woof.appointmentdetail.entity.AppointmentDetail;
import com.woof.appointmentdetail.entity.AppointmentDetailDTO;
import com.woof.appointmentdetail.service.AppointmentDetailService;
import com.woof.appointmentdetail.service.AppointmentDetailServiceImpl;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormService;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormServiceImpl;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.util.HibernateUtil;
import com.woof.util.JedisUtil;
import com.woof.util.JsonIgnoreExclusionStrategy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//@WebServlet(value = "/groupScheduler" , loadOnStartup = 1)
public class GroupScduleScheduler extends HttpServlet {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4); // 根據需要調整線程池大小
    private GroupGourseScheduleService groupGourseScheduleService = new GroupCourseScheduleServiceImpl();

    private GroupCourseOrderService groupCourseOrderService = new GroupCourseOrderServiceImpl();

    private GroupScheduleDetailService groupScheduleDetailService = new GroupScheduleDetailServiceImpl();

    private AppointmentDetailService appointmentDetailService = new AppointmentDetailServiceImpl();

    private PrivateTrainingAppointmentFormService privateTrainingAppointmentFormService = new PrivateTrainingAppointmentFormServiceImpl();
   
    private Gson gson = new GsonBuilder()
            .setExclusionStrategies()
            .addSerializationExclusionStrategy(new JsonIgnoreExclusionStrategy(true))
            .addDeserializationExclusionStrategy(new JsonIgnoreExclusionStrategy(false))
            .setDateFormat("yyyy-MM-dd")
            .create();

    @Override
    public void init() throws ServletException {

        Runnable[] tasks = {

//              1. 尋找審核中的課程截止日期過一天後，是否滿足開課條件
//              2. 會先尋找報名開課程的會員訂單，是否都已付款
//              3. 若未付款狀態，則取消該訂單，並報名人數-1

//              若滿足開課條件，則狀態變成確定開課，並賦予回饋有購買該課程的會員 毛毛幣 為原價格的 3%
//              若不滿足，則狀態不變，但會存到 redis 由後台管理員做後續處理

                ()->{
                    Session currentSession = sessionFactory.getCurrentSession();
                    try{
                        currentSession.beginTransaction();
                        Jedis jedis = JedisUtil.getResource();


//                      尋找審核中的課程，並到截止日期 + 1 day 時。
                        List<GroupCourseSchedule> allReview = groupGourseScheduleService.getAllReview();
//                      先尋找有報名該訂單，並尚未付款的會員，將該會員的訂單狀態變成 (已取消) ，並 該課程報名人數 -1
                        for (GroupCourseSchedule groupCourseSchedule : allReview){
                            List<GroupCourseOrder> allOrder = groupCourseOrderService.getAllBySchedule(groupCourseSchedule.getGcsNo());
                            for (GroupCourseOrder groupCourseOrder : allOrder){
//                              尋找 status : 尚未付款(0) 的訂單，訂單狀態變為 status : 已取消(3)
//                               並且該課程報名人數 -1
                                if (groupCourseOrder.getGcoStatus() == 0){
                                    groupCourseOrderService.modify(groupCourseOrder.getGcoNo() , 3);
                                    groupGourseScheduleService.cancelSchedule(groupCourseSchedule.getGcsNo());
                                }
                            }

//                          判斷是否滿足最低人數
                            if (groupCourseSchedule.getRegCount() >= groupCourseSchedule.getMinLimit()){
//                              滿足最低人數 ： 狀態改變 ->  status ： 確定開課(2)
                                groupGourseScheduleService.updateStatus(2 , groupCourseSchedule.getGcsNo());
//                              有購買該課程的會員 毛毛幣 為原價格的 1 / 10
                                List<GroupCourseOrder> orders = groupCourseOrderService.getOrderBySchedule(groupCourseSchedule.getGcsNo());
                                for (GroupCourseOrder order : orders){
                                    Member member = order.getMember();
                                    new MemberServiceImpl().updateMemberPoints(member.getMemNo() , (int) (member.getMomoPoint() + Math.floor(groupCourseSchedule.getGcsPrice() * 0.03)));
                                }
                            }else {
//                              沒滿最低人數 ： 狀態改變 ->  不變  並儲存到 redis 通知管理員做後續處理
                                jedis.hset("schedules", groupCourseSchedule.getGcsNo().toString(), gson.toJson(groupCourseSchedule));
                            }
                        }
                        jedis.close();
                        currentSession.getTransaction().commit();
                    }catch (Exception e){
                        e.printStackTrace();
                        currentSession.getTransaction().rollback();;
                    }
                },


//              尋找小於今日的已上架課程，課程狀態改變為 審核中

                () ->{
                    Session currentSession = sessionFactory.getCurrentSession();
                    try{
                        currentSession.beginTransaction();
                        //  尋找上架的Schedule，並到截止日期時，狀態變成 status : 審核中 (6)
                        List<GroupCourseSchedule> groupCourseScheduleList = groupGourseScheduleService.getAllUpSchedule();
                        for (GroupCourseSchedule groupCourseSchedule : groupCourseScheduleList) {
                            groupGourseScheduleService.updateStatus(6, groupCourseSchedule.getGcsNo());
                        }
                        currentSession.getTransaction().commit();
                    }catch (Exception e){
                        e.printStackTrace();
                        currentSession.getTransaction().rollback();;
                    }
                },

//              尋找確認開課的課程，若已上完所有課程，則 訂單狀態 與 課程狀態 改變為已結束

//              1. 尋找確認開課狀態的課程
//              2. 並且尋找所有上課日期小於今日的日期，改變成 已結束

                ()->{
                    Session currentSession = sessionFactory.getCurrentSession();
                    try{
                        currentSession.beginTransaction();
//                      尋找Schedule 確認開課 (2) 後 該課程的所有上課時間 都已經小於現在時間時， 狀態改變成 5 (已結束)
                        List<GroupCourseSchedule> allConfirmSchedule = groupGourseScheduleService.getAllConfirmSchedule();

                        Calendar today = Calendar.getInstance();
                        today.set(Calendar.HOUR_OF_DAY, 0);
                        today.set(Calendar.MINUTE, 0);
                        today.set(Calendar.SECOND, 0);
                        today.set(Calendar.MILLISECOND, 0);

//                        allConfirmSchedule.stream()
//                                .map(groupCourseSchedule ->  groupScheduleDetailService.getMaxDate(groupCourseSchedule.getGcsNo()))
//                                .filter(groupScheduleDetail -> groupScheduleDetail != null)
//                                .filter(groupScheduleDetail -> groupScheduleDetail.getClassDate().before(today.getTime()))
//                                .forEach(groupCourseSchedule -> groupCourseOrderService.finishOrder(groupCourseSchedule.getGcsNo()));


                        for (GroupCourseSchedule groupCourseSchedule : allConfirmSchedule) {
//                          且報名該課程的人 order ，狀態改變成 4 (已完成)

                            GroupScheduleDetail maxDate = groupScheduleDetailService.getMaxDate(groupCourseSchedule.getGcsNo());
                            if (maxDate != null && maxDate.getClassDate().before(today.getTime())) {
                                groupCourseOrderService.finishOrder(groupCourseSchedule.getGcsNo());
                            }
                        }

                        currentSession.getTransaction().commit();
                    }catch (Exception e){
                        e.printStackTrace();
                        currentSession.getTransaction().rollback();;
                    }
                },
                
                // 日期一到 變成不能取消預約 (把取消預約按鈕不能按)
                ()->{
                	Session currentSession = sessionFactory.getCurrentSession();
                    try{
                        currentSession.beginTransaction();
                        
                        List<AppointmentDetail> allAppointments = appointmentDetailService.getAllUpdateStatus();
                        
//                        System.out.println(allAppointments);
                        appointmentDetailService.updateComplete(allAppointments);

                        currentSession.getTransaction().commit();
                    }catch (Exception e){
                        e.printStackTrace();
                        currentSession.getTransaction().rollback();;
                    }
                }
                
        };


        for (Runnable task : tasks)
            executorService.scheduleWithFixedDelay(task , 0 , Long.parseLong(getInitParameter("timer")), TimeUnit.SECONDS);

    }

    @Override
    public void destroy() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException ie) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt(); //中斷
            }
        }
    }
}
