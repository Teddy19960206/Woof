package com.woof.shoporder.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.shoporder.entity.ShopOrder;


public class ShopOrderDAOImpl implements ShopOrderDAO {

	private SessionFactory factory;

	public ShopOrderDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(ShopOrder shopOrder) {
		return (Integer) getSession().save(shopOrder);
	}

	@Override
	public int update(ShopOrder shopOrder) {
		try {
			getSession().update(shopOrder);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

//	@Override
//	public int delete(Integer shopOrderNo) {
//		Faq faq = getSession().get(Faq.class, shopOrderNo);
//		if (faq != null) {
//			getSession().delete(faq);
//			return 1;
//		} else {
//			return -1;
//		}
//	}

	@Override
	public ShopOrder findByShopOrderNo(Integer shopOrderNo) {
		return getSession().get(ShopOrder.class, shopOrderNo);
	}

	@Override
	public List<ShopOrder> getAll() {
		return getSession().createQuery("FROM ShopOrder", ShopOrder.class).list();
	}
	
	
	@Override
	public List<ShopOrder> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM ShopOrder", ShopOrder.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from ShopOrder", Long.class).uniqueResult();
	}
	

}
