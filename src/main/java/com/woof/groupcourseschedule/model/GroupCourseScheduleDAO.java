package com.woof.groupcourseschedule.model;


import java.util.List;

public interface GroupCourseScheduleDAO {

    void insert(GroupCourseScheduleVO groupCourseScheduleVO);

    void update(GroupCourseScheduleVO groupCourseScheduleVO);

    GroupCourseScheduleVO findByGcsNo(Integer gcsNo);

    List<GroupCourseScheduleVO> getAll();
}
