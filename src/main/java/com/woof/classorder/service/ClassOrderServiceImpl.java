package com.woof.classorder.service;

import java.util.List;

import org.hibernate.Session;

import com.woof.classorder.dao.ClassOrderDAOImpl;
import com.woof.classorder.entity.ClassOrder;
import com.woof.classorder.dao.ClassOrderDAO;
import com.woof.util.HibernateUtil;

public class ClassOrderServiceImpl implements ClassOrderService{

	private ClassOrderDAO dao;
	
	public ClassOrderServiceImpl() {
		dao = new ClassOrderDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public ClassOrder addClassOrder(ClassOrder classOrder) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		if (dao.insert(classOrder) == 1){

			session.getTransaction().commit();
			return classOrder;
		}
		session.getTransaction().rollback();
		return null;
	}

	@Override
	public ClassOrder updateClassOrder(ClassOrder classOrder) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		int result = dao.update(classOrder);
		if (result == 1){

			session.getTransaction().commit();
			return classOrder;
		}
		session.getTransaction().rollback();

		return null;
	}

	@Override
	public ClassOrder findClassOrderByNo(Integer adNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		return null;
	}

	@Override
	public List<ClassOrder> getAllClassOrders() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		List<ClassOrder> classOrderList = dao.getAll();
		session.getTransaction().commit();

		// TODO Auto-generated method stub
		return classOrderList;
	}
}
