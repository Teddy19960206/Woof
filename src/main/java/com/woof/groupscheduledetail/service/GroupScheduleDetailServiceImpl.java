package com.woof.groupscheduledetail.service;


import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupscheduledetail.dao.GroupScheduleDetailDAO;
import com.woof.groupscheduledetail.dao.GroupScheduleDetailDAOImpl;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.trainer.entity.Trainer;
import com.woof.util.HibernateUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GroupScheduleDetailServiceImpl implements GroupScheduleDetailService{

    private GroupScheduleDetailDAO dao;

    public GroupScheduleDetailServiceImpl(){
        dao = new GroupScheduleDetailDAOImpl(HibernateUtil.getSessionFactory());
    }
    @Override
    public void modify(Integer gcsdNo, Trainer trainer, Date classDate) {
        dao.update(gcsdNo , trainer , classDate);
    }

    @Override
    public void add(GroupCourseSchedule groupCourseSchedule,Trainer trainer , Set<Date> classDate) {

        List<GroupScheduleDetail> groupScheduleDetails = new ArrayList<>();
        for (Date date : classDate){
            GroupScheduleDetail groupDetail = new GroupScheduleDetail();
            groupDetail.setTrainer(groupCourseSchedule.getTrainer());
            groupDetail.setClassDate(date);
            groupDetail.setGroupCourseSchedule(groupCourseSchedule);
            groupScheduleDetails.add(groupDetail);
        }
        dao.insert(groupScheduleDetails);
    }

    @Override
    public void delete(Integer gcsdNo) {
        dao.delete(dao.findByGcsd(gcsdNo));
    }

    @Override
    public GroupScheduleDetail findByGcsd(Integer gcsdNo) {
        return dao.findByGcsd(gcsdNo);
    }

    @Override
    public List<GroupScheduleDetail> getAll() {
        return dao.getAll();
    }

    @Override
    public List<GroupScheduleDetail> getByGroupSchedule(Integer gcsNo) {
        return dao.getByGroupSchedule(gcsNo);
    }

    @Override
    public List<Object[]> getByTrainer(Integer trainerNo) {
        return dao.getByTrainer(trainerNo);
    }

    @Override
    public List<GroupScheduleDetail> getDetailByDate(Integer year, Integer month) {
        return dao.getByDate(year , month);
    }
}
