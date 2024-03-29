package com.woof.groupcourseschedule.service;

import com.woof.groupcourse.entity.GroupCourse;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.trainer.entity.Trainer;

import java.sql.Date;
import java.util.List;

public interface GroupGourseScheduleService {


    GroupCourseSchedule addSchedule(GroupCourse groupCourse , Trainer trainer , Date startDate , Date endDate , Integer minLimit , Integer maxLimit , Integer regCount, Integer price , Integer gcsStatus , String delayReason , GroupCourseSchedule relatedGcsNo);
    void registrationSchedule(Integer gcsNo);
    void cancelSchedule(Integer gcsNo);
    int updateSchedule(Integer gcsNo, GroupCourse groupCourse , Trainer trainer , Date gcsStart , Date gcsEnd , Integer minLimit , Integer maxLimit , Integer regCount , Integer gcsPrice , Integer gcsStatus , String gcsDelayReason , GroupCourseSchedule relatedGcsNo);
    void updateStatus(Integer status , Integer gcsNo);
    GroupCourseSchedule findByGcsNo(Integer GcsNo);
    List<GroupCourseSchedule> getAll();
    List<GroupCourseSchedule> getAll(Integer classType , Integer status , Integer currentPage);
    List<GroupCourseSchedule> getGroupScheduleByCtNo(Integer ctNo);
    List<GroupCourseSchedule> getListSchedule(Integer classType , Integer status);
    List<GroupCourseSchedule> getOffSchedule();
    List<GroupCourseSchedule> getAllUpSchedule();
    List<GroupCourseSchedule> getAllReview();
    List<GroupCourseSchedule> getAllConfirmSchedule();
    int getPageTotal(Integer classType ,Integer status);

}
