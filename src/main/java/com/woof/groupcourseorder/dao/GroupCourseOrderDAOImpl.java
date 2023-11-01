package com.woof.groupcourseorder.dao;

import com.woof.groupcourseorder.entity.GroupCourseOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class GroupCourseOrderDAOImpl implements GroupCourseOrderDAO{

    private SessionFactory factory;

    public GroupCourseOrderDAOImpl(SessionFactory factory){
        this.factory = factory;
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }

    @Override
    public void insert(GroupCourseOrder groupCourseOrder) {
        getSession().save(groupCourseOrder);
    }

    @Override
    public void update(GroupCourseOrder groupCourseOrder) {
        getSession().update(groupCourseOrder);
    }

    @Override
    public GroupCourseOrder findByGcoNo(Integer gcoNo) {
        GroupCourseOrder groupCourseOrder = getSession().get(GroupCourseOrder.class, gcoNo);
        return groupCourseOrder;
    }

    @Override
    public List<GroupCourseOrder> getAll() {
        return getSession()
                .createQuery("FROM GroupCourseOrder ", GroupCourseOrder.class)
                .list();

    }

    @Override
    public List<GroupCourseOrder> getByDate(Integer year , Integer month) {
        String hql = "FROM GroupCourseOrder WHERE YEAR(GroupCourseOrder.gcoDate) = :year and month(GroupCourseOrder .gcoDate) = :month";
        Query query = getSession().createQuery(hql);
        query.setParameter("year" , year);
        query.setParameter("month" , month);
        List<GroupCourseOrder> resultList = query.list();

        return resultList;
    }
}
