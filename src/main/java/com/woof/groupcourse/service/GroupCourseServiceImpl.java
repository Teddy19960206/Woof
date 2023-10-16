package com.woof.groupcourse.service;

import com.woof.AppService;
import com.woof.classtype.entity.ClassType;
import com.woof.groupcourse.dao.GroupCourseDAO;
import com.woof.groupcourse.dao.GroupCourseDAOImpl;
import com.woof.groupcourse.entity.GroupCourse;
import com.woof.skill.entity.Skill;
import com.woof.util.HibernateUtil;

public class GroupCourseServiceImpl implements GroupCourseService , AppService {

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
        }else {
            groupCourse.setCoursePhoto(null);
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
    public GroupCourse findGroupCourseByNo(Integer gcNo) {
        GroupCourse groupCourse = dao.findbyGcNo(gcNo);
        return groupCourse;
    }

    public byte[] getPhotoById(Integer gcNo){
        return findGroupCourseByNo(gcNo).getCoursePhoto();
    }

}
