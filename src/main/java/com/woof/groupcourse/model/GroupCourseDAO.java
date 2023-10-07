package com.woof.groupcourse.model;


import java.util.List;

public interface GroupCourseDAO {

    void insert(GroupCourseVO groupCourseVO);

    void update(GroupCourseVO groupCourseVO);

    void delete(GroupCourseVO groupCourseVO);

    GroupCourseVO findbyCtNo(Integer ctNo);

    List<GroupCourseVO> getAll();
}
