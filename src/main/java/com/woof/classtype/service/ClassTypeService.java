package com.woof.classtype.service;

import java.util.List;

import com.woof.classtype.entity.ClassType;

public interface ClassTypeService {
	ClassType addClassType(ClassType classType);
	
	ClassType updateClassType(ClassType classType);
	
	void deleteClassType(Integer ctNo);
	
	ClassType findClassTypeByNO(Integer ctNo);
	
	List<ClassType> getAllClassTypes();
}
