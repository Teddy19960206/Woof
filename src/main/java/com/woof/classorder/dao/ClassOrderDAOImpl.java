package com.woof.classorder.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.classorder.entity.ClassOrder;

public class ClassOrderDAOImpl implements ClassOrderDAO{

	private SessionFactory factory;
	
	public ClassOrderDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(ClassOrder classOrder) {
		try {
			getSession().save(classOrder);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public ClassOrder findByCoNo(Integer coNo) {
		return getSession().get(ClassOrder.class, coNo);
	}

	@Override
	public int update(ClassOrder classOrder) {
		try {
			getSession().merge(classOrder);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	
	@Override
	public List<ClassOrder> getAll() {
		return getSession().createQuery("FROM ClassOrder", ClassOrder.class).list();
	}
}
