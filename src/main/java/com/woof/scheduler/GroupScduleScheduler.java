package com.woof.scheduler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@WebServlet(value = "/groupScheduler" , loadOnStartup = 1)
public class GroupScduleScheduler extends HttpServlet {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private Timer timer;
    private GroupGourseScheduleService groupGourseScheduleService = new GroupCourseScheduleServiceImpl();
    private List<GroupCourseSchedule> groupCourseScheduleList = null;

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




//               尋找已下架的課程，並到截止日期 + 1 day 時。
//               先尋找有報名該訂單，並尚未付款的會員，將該會員的訂單狀態變成 已取消 ，並 該課程報名人數 -1
//               自動判斷是否滿足最低人數


//               滿足最低人數 ： 狀態改變 ->  status ： 確定開課(2)

//               未滿最低人數 ： 狀態改變 ->  status ： 審核中 (6)  並儲存到redis通知管理員做後續處理

                String json = gson.toJson("");
                Jedis jedis = JedisUtil.getResource();
//                jedis.set("")

//               尋找上架的課程，並到截止日期時，自動下架
                List<GroupCourseSchedule> updateGroupSchedule = null;
                groupCourseScheduleList = groupGourseScheduleService.getAllUpSchedule();
                for (GroupCourseSchedule groupCourseSchedule : groupCourseScheduleList){
                    if (groupCourseSchedule.getGcsEnd().getTime() <= Calendar.getInstance().getTimeInMillis()){
                        groupGourseScheduleService.updateStatus(groupCourseSchedule.getGcsNo());
                    }
                }

                System.out.println("");
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
