package com.woof.groupscheduledetail.service;

import com.woof.groupcourse.service.GroupCourseServiceImpl;
import com.woof.groupcourseschedule.dao.GroupCourseScheduleDAO;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.groupscheduledetail.dao.GroupScheduleDetailDAO;
import com.woof.groupscheduledetail.dao.GroupScheduleDetailDAOImpl;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.trainer.entity.Trainer;
import com.woof.util.HibernateUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupScheduleDetailServiceImpl implements GroupScheduleDetailService{

    private GroupScheduleDetailDAO dao;

    public GroupScheduleDetailServiceImpl(){
        dao = new GroupScheduleDetailDAOImpl(HibernateUtil.getSessionFactory());
    }
    @Override
    public int modify(Integer gcsdNo, Trainer trainer, Date classDate , GroupCourseSchedule groupCourseSchedule) {
        GroupScheduleDetail groupScheduleDetail = new GroupScheduleDetail();
        groupScheduleDetail.setGcsdNo(gcsdNo);
        groupScheduleDetail.setTrainer(trainer);
        groupScheduleDetail.setClassDate(classDate);
        groupScheduleDetail.setGroupCourseSchedule(groupCourseSchedule);

        dao.update(groupScheduleDetail);

        return 1;
    }

    @Override
    public int add(GroupCourseSchedule groupCourseSchedule,Trainer trainer , Set<Date> classDate) {

        List<GroupScheduleDetail> groupScheduleDetails = new ArrayList<>();
        for (Date date : classDate){
            GroupScheduleDetail groupDetail = new GroupScheduleDetail();
            groupDetail.setTrainer(groupCourseSchedule.getTrainer());
            groupDetail.setClassDate(date);
            groupDetail.setGroupCourseSchedule(groupCourseSchedule);

            groupScheduleDetails.add(groupDetail);


        }
        System.out.println(groupScheduleDetails);
        dao.insert(groupScheduleDetails);

        return 1;
    }

    @Override
    public int delete(Integer gcsdNo) {

        GroupScheduleDetail byGcsd = dao.findByGcsd(gcsdNo);
        return dao.delete(byGcsd);
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
}
