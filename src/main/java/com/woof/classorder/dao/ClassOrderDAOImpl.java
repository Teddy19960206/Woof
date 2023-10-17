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
		return (Integer) getSession().save(classOrder);
	}

	@Override
	public int update(ClassOrder classOrder) {
		try {
			getSession().update(classOrder);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public ClassOrder findByAdNo(Integer adNo) {
		return getSession().get(ClassOrder.class, adNo);
	}

	@Override
	public List<ClassOrder> getAll() {
		return getSession().createQuery("FROM ClassOrder", ClassOrder.class).list();
	}
}
