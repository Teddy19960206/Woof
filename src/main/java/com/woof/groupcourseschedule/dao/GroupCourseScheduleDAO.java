package com.woof.groupcourseschedule.dao;

import com.woof.groupcourse.entity.GroupCourse;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.trainer.entity.Trainer;

import java.sql.Date;
import java.util.List;

public interface GroupCourseScheduleDAO {

    void insert(GroupCourseSchedule groupCourseSchedule);

    int update(GroupCourseSchedule groupCourseSchedule);

    void updateCount(Integer gcsNo , Integer count);

    GroupCourseSchedule findByGcsNo(Integer gcsNo);

    List<GroupCourseSchedule> getAll();


    List<GroupCourseSchedule> getAllbyClassType(Integer ctNo);
}
