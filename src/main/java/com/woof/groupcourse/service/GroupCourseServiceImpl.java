package com.woof.groupcourse.service;

import com.woof.AppService;
import com.woof.classtype.entity.ClassType;
import com.woof.groupcourse.dao.GroupCourseDAO;
import com.woof.groupcourse.dao.GroupCourseDAOImpl;
import com.woof.groupcourse.entity.GroupCourse;
import com.woof.skill.entity.Skill;
import com.woof.util.HibernateUtil;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.util.List;



public class GroupCourseServiceImpl implements GroupCourseService , AppService<String> {


    private GroupCourseDAO dao;

    public GroupCourseServiceImpl(){
        dao = new GroupCourseDAOImpl(HibernateUtil.getSessionFactory());
    }


    @Override
    public int modify(Integer gcNo, Skill skill, ClassType classType, byte[] coursePhoto, String courseContent, Integer courseStatus) {

        GroupCourse groupCourse = new GroupCourse();
        groupCourse.setGcNo(gcNo);
        groupCourse.setSkill(skill);
        groupCourse.setClassType(classType);
        if (coursePhoto !=null){
            groupCourse.setCoursePhoto(coursePhoto);
        }else {      groupCourse.setCoursePhoto(null);

        }

        groupCourse.setCourseContent(courseContent);
        groupCourse.setCourseStatus(courseStatus);

        dao.update(groupCourse);

        return 1;
    }

    @Override
    public int addGroupCourse(Skill skill, ClassType classType, byte[] coursePhoto, String courseContent) {
        GroupCourse groupCourse = new GroupCourse();
        groupCourse.setSkill(skill);
        groupCourse.setClassType(classType);
        groupCourse.setCoursePhoto(coursePhoto);
        groupCourse.setCourseContent(courseContent);
        dao.insert(groupCourse);
        return 1;
    }

    @Override
    public int deletePhoto(Integer gcNo) {
        return dao.deletePhoto(gcNo);
    }

    @Override
    public GroupCourse findGroupCourseByNo(Integer gcNo) {
        GroupCourse groupCourse = dao.findbyGcNo(gcNo);
        return groupCourse;
    }

    @Override
    public List<GroupCourse> getAllbyCtNo(Integer ctNo) {
        return dao.getGroupCourseByCtNo(ctNo);
    }

    @Override
    public List<GroupCourse> getAllGroupCourse() {
        return dao.getAll();
    }

    @Override
    public List<GroupCourse> getAllGroupCourse(Integer classType , Integer status , Integer currentPage) {
        return dao.getAll(classType , status , currentPage);
    }

//    @Override
//    public List<GroupCourse> getAllGroupCourse(int currentPage) {
//        return dao.getAll(currentPage);
//    }

//    @Override
//    public int getPageTotal() {
//        long total = dao.getTotal();
//
//        int pageQty  = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
//        return pageQty;
//    }


    public int getPageTotal(Integer classType , Integer status){
        long total = dao.getTotal(classType , status);

        int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));

        return pageQty;
    }

    public byte[] getPhotoById(String gcNoStr){

        Integer gcNo = Integer.valueOf(gcNoStr);

        return findGroupCourseByNo(gcNo).getCoursePhoto();
    }




}
