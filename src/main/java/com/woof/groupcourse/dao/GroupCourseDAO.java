package com.woof.groupcourse.dao;


import com.woof.groupcourse.entity.GroupCourse;

import java.util.List;

public interface GroupCourseDAO {

    int insert(GroupCourse groupCourse);

    int update(GroupCourse groupCourse);

    GroupCourse findbyGcNo(Integer gcNo);

    List<GroupCourse> getGroupCourseByCtNo(Integer ctNo);

    List<GroupCourse> getAll();
}
