package com.woof.classtype.service;

import java.util.List;
import java.util.Set;

import com.woof.AppService;
import com.woof.classtype.dao.ClassTypeDAO;
import com.woof.classtype.dao.ClassTypeDAOImpl;
import com.woof.classtype.entity.ClassType;
import com.woof.groupcourse.entity.GroupCourse;
import com.woof.util.HibernateUtil;
import org.hibernate.Session;

public class ClassTypeServiceImpl implements ClassTypeService{
	
	private ClassTypeDAO dao;
	
	public ClassTypeServiceImpl() {
		dao = new ClassTypeDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public ClassType addClassType(ClassType classType) {

		if (dao.insert(classType) == 1){

			return classType;
		}
		return null;
	}

	@Override
	public ClassType updateClassType(ClassType classType) {

		int result = dao.update(classType);
		if (result == 1){

			return classType;
		}
		return null;
	}

	@Override
	public void deleteClassType(Integer ctNo) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public ClassType findClassTypeByNO(Integer ctNo) {
		
		ClassType classType = dao.findbyCtNo(ctNo);
		
		return classType;
	}

	@Override
	public List<ClassType> getAllClassTypes() {
		
		List<ClassType> classTypeList = dao.getAll();


		return classTypeList;
	}

	@Override
	public Set<GroupCourse> getGroupCourseByCtNo(Integer ctNo) {

		Set<GroupCourse> groupCourseSet = dao.getGroupCourseByClassNo(ctNo);

		return groupCourseSet;
	}

}
