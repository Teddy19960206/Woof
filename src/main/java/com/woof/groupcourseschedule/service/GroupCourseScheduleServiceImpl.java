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
    public GroupCourseSchedule addSchedule(GroupCourse groupCourse, Trainer trainer, Date startDate, Date endDate, Integer minLimit, Integer maxLimit, Integer price , String delayReason , GroupCourseSchedule relatedGcsNo) {
        GroupCourseSchedule groupCourseSchedule = new GroupCourseSchedule();
        groupCourseSchedule.setGroupCourse(groupCourse);
        groupCourseSchedule.setTrainer(trainer);
        groupCourseSchedule.setGcsStart(startDate);
        groupCourseSchedule.setGcsEnd(endDate);
        groupCourseSchedule.setMinLimit(minLimit);
        groupCourseSchedule.setMaxLimit(maxLimit);
        groupCourseSchedule.setGcsPrice(price);
        groupCourseSchedule.setGcsDelayReason(delayReason);
        groupCourseSchedule.setRelatedGcsNo(relatedGcsNo);

        dao.insert(groupCourseSchedule);

        return groupCourseSchedule;
    }



    @Override
    public void registrationSchedule(Integer gcsNo) {
        GroupCourseSchedule schedule = findByGcsNo(gcsNo);
        dao.updateCount(gcsNo, schedule.getRegCount());
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
    public List<GroupCourseSchedule> getOffSechedule() {
        return dao.getOffStatus();
    }

    @Override
    public int getPageTotal(Integer classType, Integer status) {
        long total = dao.getTotal(classType , status);

        int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));

        return pageQty;
    }
}
