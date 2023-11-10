package com.woof.classorder.service;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import com.woof.classorder.dao.ClassOrderDAOImpl;
import com.woof.classorder.entity.ClassOrder;
import com.woof.member.entity.Member;
import com.woof.classorder.dao.ClassOrderDAO;
import com.woof.util.HibernateUtil;

public class ClassOrderServiceImpl implements ClassOrderService{

	private ClassOrderDAO dao;
	
	public ClassOrderServiceImpl() {
		dao = new ClassOrderDAOImpl(HibernateUtil.getSessionFactory());
	}

	

	@Override
	public int addClassOrder(Member member, Integer coBc, Integer coPaymentMethod, Integer coSmmp, Timestamp coTime,
			Integer coStatus, Integer actualAmount) {
		
		ClassOrder classOrder = new ClassOrder();
		classOrder.setMember(member);
		classOrder.setCoBc(coBc);
		classOrder.setCoPaymentMethod(coPaymentMethod);
		classOrder.setCoSmmp(coSmmp);
		classOrder.setCoTime(coTime);
		classOrder.setCoStatus(coStatus);
		classOrder.setActualAmount(actualAmount);
		return dao.insert(classOrder);
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
	public ClassOrder findClassOrderByCoNo(Integer coNo) {
		return dao.findByCoNo(coNo);
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
