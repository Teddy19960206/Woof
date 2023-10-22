package com.woof.groupscheduledetail.dao;

import com.woof.groupscheduledetail.entity.GroupScheduleDetail;

import java.util.List;
import java.util.Set;

public interface GroupScheduleDetailDAO {
    int insert(List<GroupScheduleDetail> groupScheduleDetailSet);
    int update(GroupScheduleDetail groupScheduleDetail);
    int delete(GroupScheduleDetail groupScheduleDetail);

    GroupScheduleDetail findByGcsd(Integer gcsd);

    List<GroupScheduleDetail> getByGroupSchedule(Integer gcsNo);
    List<GroupScheduleDetail> getAll();
}
