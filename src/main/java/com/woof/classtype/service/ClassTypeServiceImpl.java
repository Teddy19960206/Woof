package com.woof.classtype.service;

import java.util.List;
import java.util.Set;
import com.woof.classtype.dao.ClassTypeDAO;
import com.woof.classtype.dao.ClassTypeDAOImpl;
import com.woof.classtype.entity.ClassType;
import com.woof.groupcourse.entity.GroupCourse;
import com.woof.util.HibernateUtil;

public class ClassTypeServiceImpl implements ClassTypeService{
	
	private ClassTypeDAO dao;
	
	public ClassTypeServiceImpl() {
		dao = new ClassTypeDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public void addClassType(String className) {

		ClassType classType = new ClassType();
		classType.setCtName(className);
		int result = dao.insert(classType);

		if (result == 1){
			System.out.println("新增成功");
		}else{
			System.out.println("新增失敗");
		}

	}

	@Override
	public ClassType updateClassType(Integer ctNo , String ctName) {

		ClassType classType = new ClassType();
		classType.setCtNo(ctNo);
		classType.setCtName(ctName);

		int result = dao.update(classType);
		if (result == 1){
			return classType;
		}
		return null;
	}

	@Override
	public int deleteClassType(Integer ctNo) {
			return dao.delete(ctNo);
	}

	@Override
	public ClassType findClassTypeByNo(Integer ctNo) {
		
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
