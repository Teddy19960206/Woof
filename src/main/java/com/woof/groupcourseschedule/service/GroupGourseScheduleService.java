package com.woof.groupcourseschedule.service;

import com.woof.groupcourseschedule.entity.GroupCourseSchedule;

import java.util.List;

public interface GroupGourseScheduleService {



    List<GroupCourseSchedule> getAll();
    List<GroupCourseSchedule> getGroupScheduleByCtNo(Integer ctNo);

}
