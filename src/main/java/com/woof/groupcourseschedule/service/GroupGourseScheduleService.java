package com.woof.groupcourseschedule.service;

import com.woof.groupcourse.entity.GroupCourse;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.trainer.entity.Trainer;

import java.sql.Date;
import java.util.List;

public interface GroupGourseScheduleService {


    void registrationSchedule(Integer gcsNo);

    int updateSchedule(Integer gcsNo, GroupCourse groupCourse , Trainer trainer , Date gcsStart , Date gcsEnd , Integer minLimit , Integer maxLimit , Integer count , Integer gcsPrice , Integer gcsStatus);
    GroupCourseSchedule findByGcsNo(Integer GcsNo);
    List<GroupCourseSchedule> getAll();
    List<GroupCourseSchedule> getGroupScheduleByCtNo(Integer ctNo);

}
