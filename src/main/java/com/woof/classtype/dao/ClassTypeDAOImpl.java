package com.woof.classtype.dao;

import java.util.List;

import com.woof.classtype.entity.ClassType;
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
	public int insert(ClassType entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public int update(ClassType entity) {
		try {
			getSession().update(entity);
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
	
	
		
    
}
