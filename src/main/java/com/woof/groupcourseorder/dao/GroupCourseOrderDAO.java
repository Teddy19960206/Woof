package com.woof.groupcourseorder.dao;

import com.woof.groupcourseorder.entity.GroupCourseOrder;

import java.util.List;

public interface GroupCourseOrderDAO {
    int insert(GroupCourseOrder groupCourseOrder);
    void update(GroupCourseOrder groupCourseOrder);
    void updateStatus(Integer gcoNo , Integer status);
    void modifyAllOrderByGcsNo(Integer gcsNo);
    void modifyAllOrderBySchedule(Integer gcsNo , Integer gcoStatus);
    GroupCourseOrder findByGcoNo(Integer gcoNo);
    List<GroupCourseOrder> getAll();
    List<GroupCourseOrder> getAllByMember(String memberNo);
    List<GroupCourseOrder> getAll(Integer groupClass , Integer status , String memNo , Integer currentPage);
    List<GroupCourseOrder> getByDate(Integer year , Integer month);
    List<GroupCourseOrder> getAllMember(Integer scheduleNo);
    List<GroupCourseOrder> getAllBySchedule(Integer gcsNo);
    long getTotal(Integer groupClass , Integer status , String memNo);

}
