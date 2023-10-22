package com.woof.groupscheduledetail.dao;

import com.woof.groupscheduledetail.entity.GroupScheduleDetail;

import java.util.List;

public interface GroupScheduleDetailDAO {
    int insert(GroupScheduleDetail groupScheduleDetail);
    int update(GroupScheduleDetail groupScheduleDetail);
    int delete(GroupScheduleDetail groupScheduleDetail);

    GroupScheduleDetail findByGcsd(Integer gcsd);

    List<GroupScheduleDetail> getByGroupSchedule(Integer gcsNo);
    List<GroupScheduleDetail> getAll();
}
