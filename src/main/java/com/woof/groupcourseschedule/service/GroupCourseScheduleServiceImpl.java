package com.woof.groupcourseschedule.service;

import com.woof.groupcourseschedule.dao.GroupCourseScheduleDAO;
import com.woof.groupcourseschedule.dao.GroupCourseScheduleDAOImpl;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.util.HibernateUtil;

import java.util.List;

public class GroupCourseScheduleServiceImpl implements GroupGourseScheduleService{

    private GroupCourseScheduleDAO dao;

    public GroupCourseScheduleServiceImpl(){
        dao = new GroupCourseScheduleDAOImpl(HibernateUtil.getSessionFactory());
    }

    @Override
    public List<GroupCourseSchedule> getAll() {
        return dao.getAll();
    }

    @Override
    public List<GroupCourseSchedule> getGroupScheduleByCtNo(Integer ctNo) {


        return dao.getAllbyClassType(ctNo);
    }
}
