package com.woof.groupscheduledetail.service;

import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.trainer.entity.Trainer;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public interface GroupScheduleDetailService {

    void modify(Integer gcsdNo, Trainer trainer , Date classDate);

    void add(GroupCourseSchedule groupCourseSchedule , Trainer trainer , Set<Date> classDate);

    void delete(Integer gcsd);

    GroupScheduleDetail findByGcsd(Integer gcsdNo);

    List<GroupScheduleDetail> getAll();

    List<GroupScheduleDetail> getByGroupSchedule(Integer gcsNo);

    List<Object[]> getByTrainer(Integer trainerNo);

    List<GroupScheduleDetail> getDetailByDate(Integer year , Integer month);


}
