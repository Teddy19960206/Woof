package com.woof.classtype.dao;

import java.util.List;
import java.util.Set;

import com.woof.classtype.entity.ClassType;
import com.woof.groupcourse.entity.GroupCourse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ClassTypeDAOImpl implements ClassTypeDAO {
	
	private SessionFactory factory;
	
	public ClassTypeDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	public Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int insert(ClassType classType) {
		return (Integer) getSession().save(classType);
	}

	@Override
	public int update(ClassType classType) {
		try {
			getSession().update(classType);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer ctno) {
		ClassType classType = getSession().get(ClassType.class, ctno);
		if(classType != null) {
			getSession().delete(classType);
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public ClassType findbyCtNo(Integer ctNo) {
		return getSession().get(ClassType.class, ctNo);
	}

	@Override
	public List<ClassType> getAll() {
		return getSession().createQuery("FROM ClassType" , ClassType.class).list();
	}

	@Override
	public Set<GroupCourse> getGroupCourseByClassNo(Integer classNo) {
		return getSession().get(ClassType.class , classNo).getGroupCourses();
	}
}
