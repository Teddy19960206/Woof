package com.woof.groupcourseschedule.dao;

import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

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
    public int insert(GroupCourseSchedule groupCourseSchedule) {
        return (Integer) getSession().save(groupCourseSchedule);
    }

    @Override
    public int update(GroupCourseSchedule groupCourseSchedule) {
        getSession().merge(groupCourseSchedule);
        return 1;
    }

    @Override
    public void updateCount(Integer gcsNo , Integer regCount) {
        String sql = "UPDATE group_course_schedule SET regCount = ? WHERE GCS_NO = ?";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter(1, regCount + 1);
        query.setParameter(2, gcsNo);
        int result = query.executeUpdate();
        System.out.println(result);
    }

    @Override
    public GroupCourseSchedule findByGcsNo(Integer gcsNo) {
        return getSession().get(GroupCourseSchedule.class , gcsNo);
    }

    @Override
    public List<GroupCourseSchedule> getAll() {
        return getSession().createQuery("FROM GroupCourseSchedule" , GroupCourseSchedule.class).list();
    }

    @Override
    public List<GroupCourseSchedule> getAllbyClassType(Integer ctNo) {

        String sql = "SELECT gcs" +
                " FROM GROUP_COURSE_SCHEDULE AS gcs INNER JOIN GROUP_COURSE AS gc ON gcs.GC_NO = gc.GC_NO WHERE gc.CT_NO = :ctNo";

        NativeQuery query = getSession().createNativeQuery(sql)
            .setParameter("ctNo", ctNo)
                .addEntity(GroupCourseSchedule.class);

        List<GroupCourseSchedule> results = query.list();

//        String hql = "select gcs FROM GroupCourseSchedule gcs JOIN gcs.groupCourse gc WHERE gc.classType = :ctNo";
//        Query<GroupCourseSchedule> query = getSession().createQuery(hql, GroupCourseSchedule.class);
//        query.setParameter("ctNo", classType);
//        List<GroupCourseSchedule> results = query.list();

        return results;
    }
}
