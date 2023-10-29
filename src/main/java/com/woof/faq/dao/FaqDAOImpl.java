package com.woof.faq.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.faq.entity.Faq;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;


public class FaqDAOImpl implements FaqDAO {

	private SessionFactory factory;

	public FaqDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(Faq faq) {
		return (Integer) getSession().save(faq);
	}

	@Override
	public int update(Faq faq) {
		try {
			getSession().update(faq);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer faqNo) {
		Faq faq = getSession().get(Faq.class, faqNo);
		if (faq != null) {
			getSession().delete(faq);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Faq findByFaqNo(Integer faqNo) {
		return getSession().get(Faq.class, faqNo);
	}

	@Override
	public List<Faq> getAll() {
		return getSession().createQuery("FROM Faq", Faq.class).list();
	}
	
	
	@Override
	public List<Faq> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM Faq", Faq.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from Faq", Long.class).uniqueResult();
	}
	

}
