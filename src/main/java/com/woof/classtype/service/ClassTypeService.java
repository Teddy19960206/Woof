package com.woof.classtype.service;

import java.util.List;
import java.util.Set;

import com.woof.classtype.entity.ClassType;
import com.woof.groupcourse.entity.GroupCourse;

public interface ClassTypeService {
	ClassType addClassType(ClassType classType);
	
	ClassType updateClassType(ClassType classType);
	
	void deleteClassType(Integer ctNo);
	
	ClassType findClassTypeByNO(Integer ctNo);
	
	List<ClassType> getAllClassTypes();

	Set<GroupCourse> getGroupCourseByCtNo(Integer ctNo);


}
