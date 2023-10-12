package com.woof.classtype.service;

import java.util.List;

import com.woof.classtype.model.ClassTypeVO;

public interface ClassTypeService {
	ClassTypeVO addClassType(ClassTypeVO classType);
	
	ClassTypeVO updateClassType(ClassTypeVO classType);
	
	void deleteClassType(Integer ctNo);
	
	ClassTypeVO findClassTypeByNO(Integer ctNo);
	
	List<ClassTypeVO> getAllClassTypes();
}
