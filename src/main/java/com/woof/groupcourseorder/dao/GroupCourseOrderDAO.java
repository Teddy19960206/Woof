package com.woof.groupcourseorder.dao;

import com.woof.groupcourseorder.entity.GroupCourseOrder;

import java.util.List;

public interface GroupCourseOrderDAO {
    void insert(GroupCourseOrder groupCourseOrder);

    void update(GroupCourseOrder groupCourseOrder);

    GroupCourseOrder findByGcoNo(Integer gcoNo);

    List<GroupCourseOrder> getAll();

    List<GroupCourseOrder> getByDate(Integer year , Integer month);
}
