package com.woof.classtype.model;

import java.util.List;

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
	public int insert(ClassTypeVO entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public int update(ClassTypeVO entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer ctno) {
		ClassTypeVO classTypeVO = getSession().get(ClassTypeVO.class, ctno);
		if(classTypeVO != null) {
			getSession().delete(classTypeVO);
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public ClassTypeVO findbyCtNo(Integer ctNo) {
		return getSession().get(ClassTypeVO.class, ctNo);
	}

	@Override
	public List<ClassTypeVO> getAll() {
		return getSession().createQuery("from CLASS_TYPE" , ClassTypeVO.class).list();
	}
	
	
		
    
}
