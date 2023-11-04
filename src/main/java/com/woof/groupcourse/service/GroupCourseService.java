package com.woof.groupcourse.service;

import com.woof.classtype.entity.ClassType;
import com.woof.groupcourse.entity.GroupCourse;
import com.woof.skill.entity.Skill;

import java.util.List;

public interface GroupCourseService {

    int modify(Integer gcNo, Skill skill, ClassType classType , byte[] coursePhoto , String courseContent , Integer courseStatus);

    int addGroupCourse(Skill skill, ClassType classType , byte[] coursePhoto , String courseContent);

    int deletePhoto(Integer gcNo);

    GroupCourse findGroupCourseByNo(Integer gcNo);

    List<GroupCourse> getAllbyCtNo(Integer ctNo);

    List<GroupCourse> getAllGroupCourse();

    List<GroupCourse> getAllGroupCourse(int currentPage);

    int getPageTotal();

    int getPageTotal(Integer classType , Integer status);


}
