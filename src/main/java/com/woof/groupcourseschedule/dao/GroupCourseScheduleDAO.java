package com.woof.groupcourseschedule.dao;


import com.woof.groupcourseschedule.entity.GroupCourseSchedule;

import java.util.List;

public interface GroupCourseScheduleDAO {

    void insert(GroupCourseSchedule groupCourseSchedule);

    void update(GroupCourseSchedule groupCourseSchedule);

    GroupCourseSchedule findByGcsNo(Integer gcsNo);

    List<GroupCourseSchedule> getAll();
}