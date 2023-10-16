package com.woof.groupcourse.service;

import com.woof.groupcourse.entity.GroupCourse;

public interface GroupCourseService {


    int addGroupCourse(GroupCourse groupCourse);

    GroupCourse findGroupCourseByNo(Integer gcNo);

    byte[] getPictureById(Integer gcNo);
}
