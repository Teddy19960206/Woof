package com.woof.groupcourse.dao;

import com.woof.groupcourse.entity.GroupCourse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class GroupCourseDAOImpl implements GroupCourseDAO{

    private SessionFactory factory;

    public GroupCourseDAOImpl(SessionFactory factory){
        this.factory = factory;
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }

    @Override
    public int insert(GroupCourse groupCourse) {
        return (Integer) getSession().save(groupCourse);
    }

    @Override
    public int update(GroupCourse groupCourse) {
        try{
            getSession().update(groupCourse);
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public GroupCourse findbyGcNo(Integer gcNo) {
        return getSession().get(GroupCourse.class,gcNo);
    }

    @Override
    public List<GroupCourse> getAll() {
        return null;
    }
}
