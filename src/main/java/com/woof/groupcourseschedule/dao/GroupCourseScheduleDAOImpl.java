package com.woof.groupcourseschedule.dao;

import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import java.util.List;

public class GroupCourseScheduleDAOImpl implements GroupCourseScheduleDAO{

    private SessionFactory factory;

    public GroupCourseScheduleDAOImpl(SessionFactory factory){
        this.factory = factory;
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }

    @Override
    public void insert(GroupCourseSchedule groupCourseSchedule) {

    }

    @Override
    public void update(GroupCourseSchedule groupCourseSchedule) {

    }

    @Override
    public GroupCourseSchedule findByGcsNo(Integer gcsNo) {
        return getSession().get(GroupCourseSchedule.class,gcsNo);
    }

    @Override
    public List<GroupCourseSchedule> getAll() {
        return getSession().createQuery("FROM GroupCourseSchedule" , GroupCourseSchedule.class).list();
    }

    @Override
    public List<GroupCourseSchedule> getAllbyClassType(Integer ctNo) {

        String sql = "SELECT gcs.GCS_NO , gcs.GC_NO , gcs.TRAINER_NO , gcs.GCS_START , gcs.GCS_END , gcs.MIN_LIMIT ,gcs.MAX_LIMIT , gcs.COUNT , gcs.GCS_PRICE , GCS_STATUS" +
                " FROM GROUP_COURSE_SCHEDULE AS gcs INNER JOIN GROUP_COURSE AS gc ON gcs.GC_NO = gc.GC_NO WHERE gc.CT_NO = :ctNo";
        NativeQuery query = getSession().createNativeQuery(sql)
            .setParameter("ctNo", ctNo) // 設置參數
                .addEntity(GroupCourseSchedule.class);
        List<GroupCourseSchedule> results = query.list();

//        String hql = "select gcs FROM GroupCourseSchedule gcs JOIN gcs.groupCourse gc WHERE gc.classType = :ctNo";
//        Query<GroupCourseSchedule> query = getSession().createQuery(hql, GroupCourseSchedule.class);
//        query.setParameter("ctNo", classType);
//        List<GroupCourseSchedule> results = query.list();
//
//        System.out.println(results);
        return results;
    }
}
