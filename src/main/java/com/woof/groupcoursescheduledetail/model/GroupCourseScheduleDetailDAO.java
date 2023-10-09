package com.woof.groupcoursescheduledetail.model;

import java.util.List;

public interface GroupCourseScheduleDetailDAO {
    void insert(GroupCourseScheduleDetailVO groupCourseScheduleDetailVO);
    void update(GroupCourseScheduleDetailVO groupCourseScheduleDetailVO);

    void delete(GroupCourseScheduleDetailVO groupCourseScheduleDetailVO);

    GroupCourseScheduleDetailVO findByGcsd(Integer gcsd);

    List<GroupCourseScheduleDetailVO> getAll();
}
