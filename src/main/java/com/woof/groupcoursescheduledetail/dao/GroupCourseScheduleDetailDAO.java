package com.woof.groupcoursescheduledetail.dao;

import com.woof.groupcoursescheduledetail.entity.GroupCourseScheduleDetail;

import java.util.List;

public interface GroupCourseScheduleDetailDAO {
    void insert(GroupCourseScheduleDetail groupCourseScheduleDetail);
    void update(GroupCourseScheduleDetail groupCourseScheduleDetail);

    void delete(GroupCourseScheduleDetail groupCourseScheduleDetail);

    GroupCourseScheduleDetail findByGcsd(Integer gcsd);

    List<GroupCourseScheduleDetail> getAll();
}
