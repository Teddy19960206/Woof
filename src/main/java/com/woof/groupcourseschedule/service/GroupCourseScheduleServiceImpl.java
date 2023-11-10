package com.woof.groupcourseschedule.service;

import com.woof.groupcourse.entity.GroupCourse;
import com.woof.groupcourseschedule.dao.GroupCourseScheduleDAO;
import com.woof.groupcourseschedule.dao.GroupCourseScheduleDAOImpl;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.trainer.entity.Trainer;
import com.woof.util.HibernateUtil;

import java.sql.Date;
import java.util.List;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

public class GroupCourseScheduleServiceImpl implements GroupGourseScheduleService{

    private GroupCourseScheduleDAO dao;

    public GroupCourseScheduleServiceImpl(){
        dao = new GroupCourseScheduleDAOImpl(HibernateUtil.getSessionFactory());
    }

    @Override
    public GroupCourseSchedule addSchedule(GroupCourse groupCourse, Trainer trainer, Date startDate, Date endDate, Integer minLimit, Integer maxLimit,Integer regCount, Integer price, Integer gcsStatus , String delayReason , GroupCourseSchedule relatedGcsNo) {
        GroupCourseSchedule groupCourseSchedule = new GroupCourseSchedule();
        groupCourseSchedule.setGroupCourse(groupCourse);
        groupCourseSchedule.setTrainer(trainer);
        groupCourseSchedule.setGcsStart(startDate);
        groupCourseSchedule.setGcsEnd(endDate);
        groupCourseSchedule.setMinLimit(minLimit);
        groupCourseSchedule.setMaxLimit(maxLimit);
        groupCourseSchedule.setRegCount(regCount);
        groupCourseSchedule.setGcsPrice(price);
        groupCourseSchedule.setGcsStatus(gcsStatus);
        groupCourseSchedule.setGcsDelayReason(delayReason);
        groupCourseSchedule.setRelatedGcsNo(relatedGcsNo);

        dao.insert(groupCourseSchedule);

        return groupCourseSchedule;
    }



    @Override
    public void registrationSchedule(Integer gcsNo) {
        dao.updateCount(gcsNo, findByGcsNo(gcsNo).getRegCount() + 1);
    }

    @Override
    public void cancelSchedule(Integer gcsNo){
        dao.updateCount(gcsNo , findByGcsNo(gcsNo).getRegCount() - 1);
    }

    @Override
    public int updateSchedule(Integer gcsNo, GroupCourse groupCourse , Trainer trainer , Date gcsStart , Date gcsEnd , Integer minLimit
            , Integer maxLimit , Integer regCount , Integer gcsPrice , Integer gcsStatus , String gcsDelayReason , GroupCourseSchedule relatedGcsNo) {
        GroupCourseSchedule groupCourseSchedule = new GroupCourseSchedule();
        groupCourseSchedule.setGcsNo(gcsNo);
        groupCourseSchedule.setGroupCourse(groupCourse);
        groupCourseSchedule.setTrainer(trainer);
        groupCourseSchedule.setGcsStart(gcsStart);
        groupCourseSchedule.setGcsEnd(gcsEnd);
        groupCourseSchedule.setMinLimit(minLimit);
        groupCourseSchedule.setMaxLimit(maxLimit);
        groupCourseSchedule.setRegCount(regCount);
        groupCourseSchedule.setGcsPrice(gcsPrice);
        groupCourseSchedule.setGcsStatus(gcsStatus);
        groupCourseSchedule.setGcsDelayReason(gcsDelayReason);
        groupCourseSchedule.setRelatedGcsNo(relatedGcsNo);


        return  dao.update(groupCourseSchedule);
    }

    @Override
    public void updateStatus(Integer status , Integer gcsNo) {
        dao.updateStatus(status ,gcsNo);
    }

    public GroupCourseSchedule findByGcsNo(Integer GcsNo){
        return dao.findByGcsNo(GcsNo);
    }
    @Override
    public List<GroupCourseSchedule> getAll() {
        return dao.getAll();
    }

    @Override
    public List<GroupCourseSchedule> getAll(Integer classType, Integer status, Integer currentPage) {
        return dao.getAll(classType , status , currentPage);
    }

    @Override
    public List<GroupCourseSchedule> getGroupScheduleByCtNo(Integer ctNo) {
        return dao.getAllbyClassType(ctNo);
    }

    @Override
    public List<GroupCourseSchedule> getListSchedule(Integer classType, Integer status) {
        return dao.getListSchedule(classType , status);
    }

    @Override
    public List<GroupCourseSchedule> getOffSchedule() {
        return dao.getOffStatus();
    }

    @Override
    public List<GroupCourseSchedule> getAllUpSchedule() {
        return dao.getUpStatus();
    }

    @Override
    public List<GroupCourseSchedule> getAllReview() {
        return dao.getReviewSchedule();
    }

    @Override
    public int getPageTotal(Integer classType, Integer status) {
        long total = dao.getTotal(classType , status);

        return (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
    }
}
