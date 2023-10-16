package com.woof.groupcourse.service;

import com.woof.groupcourse.dao.GroupCourseDAO;
import com.woof.groupcourse.dao.GroupCourseDAOImpl;
import com.woof.groupcourse.entity.GroupCourse;
import com.woof.util.HibernateUtil;

public class GroupCourseServiceImpl implements GroupCourseService{

    private GroupCourseDAO dao;

    public GroupCourseServiceImpl(){
        dao = new GroupCourseDAOImpl(HibernateUtil.getSessionFactory());
    }

    @Override
    public int addGroupCourse(GroupCourse groupCourse) {

        if (dao.insert(groupCourse) == 1){
            return 1;
        }
        return 0;
    }

    @Override
    public GroupCourse findGroupCourseByNo(Integer gcNo) {
        GroupCourse groupCourse = dao.findbyGcNo(gcNo);
        return groupCourse;
    }

    public byte[] getPictureById(Integer gcNo){
        return findGroupCourseByNo(gcNo).getCoursePhoto();
    }

}
