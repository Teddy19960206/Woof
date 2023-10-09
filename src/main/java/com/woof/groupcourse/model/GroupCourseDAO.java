package com.woof.groupcourse.model;


import java.util.List;

public interface GroupCourseDAO {

    void insert(GroupCourseVO groupCourseVO);

    void update(GroupCourseVO groupCourseVO);

    GroupCourseVO findbyGcNo(Integer gcNo);

    List<GroupCourseVO> getAll();
}
