package com.woof.groupcourseschedule.service;

import com.woof.groupcourse.entity.GroupCourse;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.trainer.entity.Trainer;

import java.sql.Date;
import java.util.List;

public interface GroupGourseScheduleService {


    GroupCourseSchedule addSchedule(GroupCourse groupCourse , Trainer trainer , Date startDate , Date endDate , Integer minLimit , Integer maxLimit , Integer price , String delayReason , GroupCourseSchedule relatedGcsNo);
    void registrationSchedule(Integer gcsNo);
    int updateSchedule(Integer gcsNo, GroupCourse groupCourse , Trainer trainer , Date gcsStart , Date gcsEnd , Integer minLimit , Integer maxLimit , Integer regCount , Integer gcsPrice , Integer gcsStatus , String gcsDelayReason , GroupCourseSchedule relatedGcsNo);
    GroupCourseSchedule findByGcsNo(Integer GcsNo);
    List<GroupCourseSchedule> getAll();
    List<GroupCourseSchedule> getGroupScheduleByCtNo(Integer ctNo);

    List<GroupCourseSchedule> getListSchedule(Integer classType , Integer status);

}
