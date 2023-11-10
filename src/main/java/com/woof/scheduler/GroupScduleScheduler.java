package com.woof.scheduler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.groupcourseorder.entity.GroupCourseOrder;
import com.woof.groupcourseorder.service.GroupCourseOrderService;
import com.woof.groupcourseorder.service.GroupCourseOrderServiceImpl;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupcourseschedule.service.GroupCourseScheduleServiceImpl;
import com.woof.groupcourseschedule.service.GroupGourseScheduleService;
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

@WebServlet(value = "/groupScheduler" , loadOnStartup = 1)
public class GroupScduleScheduler extends HttpServlet {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private Timer timer;
    private GroupGourseScheduleService groupGourseScheduleService = new GroupCourseScheduleServiceImpl();

    private GroupCourseOrderService groupCourseOrderService = new GroupCourseOrderServiceImpl();

    private Gson gson = new GsonBuilder()
            .setExclusionStrategies()
            .addSerializationExclusionStrategy(new JsonIgnoreExclusionStrategy(true))
            .addDeserializationExclusionStrategy(new JsonIgnoreExclusionStrategy(false))
            .setDateFormat("yyyy-MM-dd")
            .create();

    @Override
    public void init() throws ServletException {
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
            Session currentSession = sessionFactory.getCurrentSession();
            try{
                currentSession.beginTransaction();
                System.out.println("執行排成器");
                Jedis jedis = JedisUtil.getResource();

                Set<GroupCourseSchedule> groupCourseScheduleSet = new HashSet<>();

//               尋找審核中的課程，並到截止日期 + 1 day 時。
                List<GroupCourseSchedule> allReview = groupGourseScheduleService.getAllReview();
//               先尋找有報名該訂單，並尚未付款的會員，將該會員的訂單狀態變成 已取消 ，並 該課程報名人數 -1
                for (GroupCourseSchedule groupCourseSchedule : allReview){
                    List<GroupCourseOrder> allOrder = groupCourseOrderService.getAllBySchedule(groupCourseSchedule.getGcsNo());
                    for (GroupCourseOrder groupCourseOrder : allOrder){
//                        尋找 status : 尚未付款(0) 的訂單，訂單狀態變為 status : 已取消(3)
//                        並且該課程報名人數 -1
                        if (groupCourseOrder.getGcoStatus() == 0){
                            groupCourseOrderService.modify(groupCourseOrder.getGcoNo() , 3);
                            groupGourseScheduleService.cancelSchedule(groupCourseSchedule.getGcsNo());
                        }
                    }
//                  判斷是否滿足最低人數

//                  滿足最低人數 ： 狀態改變 ->  status ： 確定開課(2)
                    if (groupCourseSchedule.getRegCount() >= groupCourseSchedule.getMinLimit()){
                        groupGourseScheduleService.updateStatus(2 , groupCourseSchedule.getGcsNo());
                    }else {
//                      滿最低人數 ： 狀態改變 ->  不變  並儲存到 redis 通知管理員做後續處理
                        jedis.sadd("schedule:",groupCourseSchedule.toString());
                    }
                }


//               尋找上架的課程，並到截止日期時，狀態變成 status : 審核中 (6)
                List<GroupCourseSchedule> groupCourseScheduleList = groupGourseScheduleService.getAllUpSchedule();
                for (GroupCourseSchedule groupCourseSchedule : groupCourseScheduleList){
                    groupGourseScheduleService.updateStatus(6 , groupCourseSchedule.getGcsNo());
                }
                jedis.close();
                currentSession.getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
                currentSession.getTransaction().rollback();;
            }
            }
        };

        timer.scheduleAtFixedRate(timerTask , Timestamp.valueOf(LocalDateTime.now()) , 60 * 1000);

    }

    @Override
    public void destroy() {
        timer.cancel();
    }
}
