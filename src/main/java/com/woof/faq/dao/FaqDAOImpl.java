package com.woof.faq.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.faq.entity.Faq;


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

}
