package com.woof.groupscheduledetail.dao;


import com.woof.groupcourseorder.entity.GroupCourseOrder;
import com.woof.trainer.entity.Trainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.sql.Date;
import java.util.List;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import org.hibernate.query.Query;

public class GroupScheduleDetailDAOImpl implements GroupScheduleDetailDAO{

    private SessionFactory factory;

    public GroupScheduleDetailDAOImpl(SessionFactory factory){
        this.factory = factory;
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }
    @Override
    public int insert(List<GroupScheduleDetail> groupScheduleDetailSet) {
        for (GroupScheduleDetail groupScheduleDetail : groupScheduleDetailSet){
            getSession().persist(groupScheduleDetail);
        }
        getSession().flush();
        return 1;
    }

    @Override
    public int update(Integer gcsdNo, Trainer trainer, Date classDate) {
        GroupScheduleDetail groupScheduleDetail = getSession().get(GroupScheduleDetail.class, gcsdNo);
        groupScheduleDetail.setTrainer(trainer);
        groupScheduleDetail.setClassDate(classDate);
        return 1;
    }

    @Override
    public int delete(GroupScheduleDetail groupScheduleDetail) {
        getSession().delete(groupScheduleDetail);
        return 1;
    }

    @Override
    public GroupScheduleDetail findByGcsd(Integer gcsdNo) {
        return getSession().get(GroupScheduleDetail.class , gcsdNo);
    }

    public GroupScheduleDetail getMaxDate(Integer gcsNo) {
        String hql = "FROM GroupScheduleDetail gcsd WHERE gcsd.groupCourseSchedule.gcsNo = :gcsNo ORDER BY gcsd.classDate DESC";
        Query query = getSession().createQuery(hql);
        query.setParameter("gcsNo" , gcsNo);
        query.setMaxResults(1);
        List<GroupScheduleDetail> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<Object[]> getByTrainer(Integer trainerNo) {
        String hql = "SELECT gcDetail.classDate, skill.skillName , ct.ctName " +
                "FROM Trainer as t " +
                "JOIN t.groupScheduleDetailSet as gcDetail " +
                "JOIN gcDetail.groupCourseSchedule as gcSchedule " +
                "JOIN gcSchedule.groupCourse as gc " +
                "JOIN gc.skill as skill " +
                "JOIN gc.classType as ct " +
                "WHERE t.trainerNo = :trainerNo";

        Query<Object[]> query = getSession()
                .createQuery(hql , Object[].class)
                .setParameter("trainerNo",trainerNo);

        return query.list();
    }

    @Override
    public List<GroupScheduleDetail> getByGroupSchedule(Integer gcsNo) {
//        String sql = "SELECT * FROM GROUP_COURSE_SCHEDULE_DETAIL AS gcsd WHERE GCS_NO = :gcsNo";
//        NativeQuery query = getSession().createNativeQuery(sql)
//                .setParameter("gcsNo" , gcsNo)
//                .addEntity(GroupScheduleDetail.class);
//
//        List results = query.list();

        String hql = "FROM GroupScheduleDetail AS gcsd JOIN FETCH gcsd.trainer WHERE gcsd.groupCourseSchedule.gcsNo = :gcsNo";
        Query<GroupScheduleDetail> query = getSession()
                .createQuery(hql, GroupScheduleDetail.class)
                .setParameter("gcsNo", gcsNo);
        List<GroupScheduleDetail> results = query.list();

        return results;
    }

    @Override
    public List<GroupScheduleDetail> getAll() {
        return getSession().createQuery("FROM GroupScheduleDetail " , GroupScheduleDetail.class).list();
    }

    @Override
    public List<GroupScheduleDetail> getByDate(Integer year  , Integer month) {
//      根據schedule的狀態 若為 下架(0)  已取消 (3) 延期(4) 則不抓取該上課日期

        String hql  = "FROM GroupScheduleDetail gcsd WHERE YEAR(classDate) = :year AND MONTH(classDate) = :month AND " +
                "gcsd.groupCourseSchedule.gcsStatus NOT IN (0, 3, 4)";
        Query query = getSession().createQuery(hql, GroupScheduleDetail.class);
        query.setParameter("year" , year);
        query.setParameter("month" , month);

        return query.list();
    }

    @Override
    public List<GroupScheduleDetail> getByDate(Integer year, Integer month, Integer trainerNo) {
//      根據schedule的狀態 若為 下架(0)  已取消 (3) 延期(4) 則不抓取該上課日期
        String hql  = "FROM GroupScheduleDetail gcsd WHERE YEAR(classDate) = :year AND MONTH(classDate) = :month AND gcsd.trainer.trainerNo = :trainerNo AND " +
                " gcsd.groupCourseSchedule.gcsStatus NOT IN (0, 3 , 4)";
        Query query = getSession().createQuery(hql, GroupScheduleDetail.class);
        query.setParameter("year" , year);
        query.setParameter("month" , month);
        query.setParameter("trainerNo" , trainerNo);
        return query.list();
    }


}
