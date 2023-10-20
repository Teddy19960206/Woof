package com.woof.classtype.service;

import java.util.List;
import java.util.Set;

import com.woof.classtype.entity.ClassType;
import com.woof.groupcourse.entity.GroupCourse;

public interface ClassTypeService {
	void addClassType(String className);
	
	ClassType updateClassType(Integer ctNo , String ctName);
	
	int deleteClassType(Integer ctNo);
	
	ClassType findClassTypeByNo(Integer ctNo);
	
	List<ClassType> getAllClassTypes();

	Set<GroupCourse> getGroupCourseByCtNo(Integer ctNo);


}
