package com.woof.groupcourse.model;


import java.util.List;

public interface GroupCourseDAO {

    void insert(GroupCourse groupCourse);

    void update(GroupCourse groupCourse);

    void delete(GroupCourse groupCourse);

    GroupCourse findbyCtNo(Integer ctNo);

    List<GroupCourse> getAll();
}
