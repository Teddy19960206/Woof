package com.woof.groupscheduledetail.service;

import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.trainer.entity.Trainer;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public interface GroupScheduleDetailService {

    int modify(Integer gcsdNo, Trainer trainer , Date classDate , GroupCourseSchedule groupCourseSchedule);

    int add(GroupCourseSchedule groupCourseSchedule , Trainer trainer , Set<Date> classDate);

    int delete(Integer gcsd);

    List<GroupScheduleDetail> getAll();

    List<GroupScheduleDetail> getByGroupSchedule(Integer gcsNo);

}