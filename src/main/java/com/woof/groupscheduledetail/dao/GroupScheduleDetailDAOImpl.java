package com.woof.groupscheduledetail.dao;

import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.trainer.entity.Trainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import org.hibernate.query.NativeQuery;
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

    @Override
    public List<GroupScheduleDetail> getByGroupSchedule(Integer gcsNo) {
//        String sql = "SELECT * FROM GROUP_COURSE_SCHEDULE_DETAIL AS gcsd WHERE GCS_NO = :gcsNo";
//        NativeQuery query = getSession().createNativeQuery(sql)
//                .setParameter("gcsNo" , gcsNo)
//                .addEntity(GroupScheduleDetail.class);
//
//        List results = query.list();

        String hql = "FROM GroupScheduleDetail AS gcsd JOIN FETCH gcsd.trainer WHERE gcsd.groupCourseSchedule.gcsNo = :gcsNo";
        Query<GroupScheduleDetail> query = getSession().createQuery(hql, GroupScheduleDetail.class).setParameter("gcsNo", gcsNo);
        List<GroupScheduleDetail> results = query.list();

        return results;
    }

    @Override
    public List<GroupScheduleDetail> getAll() {
        return getSession().createQuery("FROM GroupScheduleDetail " , GroupScheduleDetail.class).list();
    }
}
