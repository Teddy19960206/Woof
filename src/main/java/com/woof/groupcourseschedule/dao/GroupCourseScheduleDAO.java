package com.woof.groupcourseschedule.dao;


import com.woof.groupcourse.entity.GroupCourse;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;

import java.util.List;

public interface GroupCourseScheduleDAO {

    int insert(GroupCourseSchedule groupCourseSchedule);

    int update(GroupCourseSchedule groupCourseSchedule);

    void updateCount(Integer gcsNo , Integer regCount);

    void updateStatus(Integer gcsNo);

    GroupCourseSchedule findByGcsNo(Integer gcsNo);

    List<GroupCourseSchedule> getAll();

    List<GroupCourseSchedule> getAll(Integer classType , Integer status , Integer currentPage);

    List<GroupCourseSchedule> getListSchedule(Integer classType , Integer status);

    List<GroupCourseSchedule> getUpStatus();


    List<GroupCourseSchedule> getAllbyClassType(Integer ctNo);

    List<GroupCourseSchedule> getOffStatus();


    long getTotal(Integer classType , Integer status);
}
