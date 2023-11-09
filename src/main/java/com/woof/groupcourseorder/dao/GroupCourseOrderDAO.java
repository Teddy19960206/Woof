package com.woof.groupcourseorder.dao;

import com.woof.groupcourseorder.entity.GroupCourseOrder;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;

import java.util.List;

public interface GroupCourseOrderDAO {
    int insert(GroupCourseOrder groupCourseOrder);

    void update(GroupCourseOrder groupCourseOrder);

    GroupCourseOrder findByGcoNo(Integer gcoNo);

    List<GroupCourseOrder> getAll();

    List<GroupCourseOrder> getAll(Integer groupClass , Integer status , String memNo , Integer currentPage);

    long getTotal(Integer groupClass , Integer status , String memNo);

    List<GroupCourseOrder> getByDate(Integer year , Integer month);

    List<GroupCourseOrder> getAllMember(Integer scheduleNo);

    void updateStatus(Integer gcoNo , Integer status);
}
