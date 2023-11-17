package com.woof.classorder.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.classorder.entity.ClassOrder;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;

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
		
		return (int)getSession().save(classOrder);
			
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

	@Override
	public List<ClassOrder> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM ClassOrder", ClassOrder.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from ClassOrder", Long.class).uniqueResult();
	}

	@Override
	public List<ClassOrder> getByMemNo(String memNo, int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM ClassOrder c WHERE c.member.memNo = :memNo", ClassOrder.class)
				.setParameter("memNo", memNo)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotalByMemNo(String memNo) {
		return getSession().createQuery("SELECT COUNT(*) from ClassOrder c WHERE c.member.memNo = :memNo", Long.class)
				.setParameter("memNo", memNo)
				.uniqueResult();
	}
	
}
