package com.woof.groupscheduledetail.dao;

import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.trainer.entity.Trainer;

import java.sql.Date;
import java.util.List;

public interface GroupScheduleDetailDAO {
    int insert(List<GroupScheduleDetail> groupScheduleDetailSet);
    int update(Integer gcsdNo, Trainer trainer, Date classDate);
    int delete(GroupScheduleDetail groupScheduleDetail);
    GroupScheduleDetail findByGcsd(Integer gcsd);
    GroupScheduleDetail getMaxDate(Integer gcsNo) ;
    List<Object[]> getByTrainer(Integer trainerNo);
    List<GroupScheduleDetail> getByGroupSchedule(Integer gcsNo);
    List<GroupScheduleDetail> getAll();
    List<GroupScheduleDetail> getByDate(Integer year  , Integer month);
    List<GroupScheduleDetail> getByDate(Integer year  , Integer month , Integer trainerNo);
}
