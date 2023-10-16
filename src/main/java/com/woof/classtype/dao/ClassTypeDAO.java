package com.woof.classtype.dao;

import com.woof.classtype.entity.ClassType;
import com.woof.groupcourse.entity.GroupCourse;

import java.util.List;
import java.util.Set;

public interface ClassTypeDAO {

    int insert(ClassType classType);

    int update(ClassType classType);

    int delete(Integer ctno);

    ClassType findbyCtNo(Integer ctNo);

    List<ClassType> getAll();

    Set<GroupCourse> getGroupCourseByClassNo(Integer classNo);
}
