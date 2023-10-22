package com.woof.groupscheduledetail.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
    public int insert(GroupScheduleDetail groupScheduleDetail) {
        return (Integer) getSession().save(groupScheduleDetail);
    }

    @Override
    public int update(GroupScheduleDetail groupScheduleDetail) {
        getSession().merge(groupScheduleDetail);
        return 1;
    }

    @Override
    public int delete(GroupScheduleDetail groupScheduleDetail) {
        getSession().delete(groupScheduleDetail);
        return 1;
    }

    @Override
    public GroupScheduleDetail findByGcsd(Integer gcsd) {
        return getSession().get(GroupScheduleDetail.class , gcsd);
    }

    @Override
    public List<GroupScheduleDetail> getByGroupSchedule(Integer gcsNo) {
        Query query = getSession().createQuery("FROM GroupScheduleDetail WHERE GroupCourseSchedule.gcsNo = :gcsNo");
        query.setParameter("gcsNo" , gcsNo);
        List<GroupScheduleDetail> results = query.list();

        return results;
    }

    @Override
    public List<GroupScheduleDetail> getAll() {
        return getSession().createQuery("FROM GroupScheduleDetail " , GroupScheduleDetail.class).list();
    }
}
