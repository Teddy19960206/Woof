package com.woof.promotionactivity.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import com.woof.promotionactivity.entity.PromotionActivity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;



public class PromotionActivityDAOImpl implements PromotionActivityDAO{
	
	private SessionFactory factory;

	public PromotionActivityDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(PromotionActivity entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public int update(PromotionActivity entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer paNo) {
		PromotionActivity promotionActivity = getSession().get(PromotionActivity.class, paNo);
		if(promotionActivity != null) {
			getSession().delete(promotionActivity);
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public PromotionActivity findByPaNo(Integer paNo) {
		return getSession().get(PromotionActivity.class, paNo);
	}

	@Override
	public List<PromotionActivity> getAll() {
		return getSession().createQuery("FROM PromotionActivity" , PromotionActivity.class).list();
	}

	@Override
	public List<PromotionActivity> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("from PromotionActivity", PromotionActivity.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from PromotionActivity", Long.class).uniqueResult();
	}
  
}
