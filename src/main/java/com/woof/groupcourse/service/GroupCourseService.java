package com.woof.groupcourse.service;

import com.woof.classtype.entity.ClassType;
import com.woof.groupcourse.entity.GroupCourse;
import com.woof.skill.entity.Skill;

public interface GroupCourseService {


    int addGroupCourse(Skill skill, ClassType classType , byte[] coursePhoto , String courseContent);

    GroupCourse findGroupCourseByNo(Integer gcNo);

}
