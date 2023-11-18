package com.woof.groupcourse.dao;


import com.woof.groupcourse.entity.GroupCourse;

import java.util.List;

public interface GroupCourseDAO {

    int insert(GroupCourse groupCourse);

    int update(GroupCourse groupCourse);

    int deletePhoto(Integer gcNo);

    GroupCourse findbyGcNo(Integer gcNo);

    List<GroupCourse> getGroupCourseByCtNo(Integer ctNo);

    List<GroupCourse> getAll();

    List<GroupCourse> getByStatus(Integer status);

    long getTotal(Integer classType , Integer status);

    List<GroupCourse> getAll(Integer classType , Integer status , int currentPage);
}
