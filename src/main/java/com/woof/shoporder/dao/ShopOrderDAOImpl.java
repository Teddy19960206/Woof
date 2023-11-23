package com.woof.shoporder.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
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

	//模糊比對
	@Override
	public List<ShopOrder> findByMemNo(String memNo, int currentPage) {
		int startIndex = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM ShopOrder s WHERE s.member.memNo LIKE :memNo ORDER BY s.shopOrderNo DESC", ShopOrder.class)
		        .setParameter("memNo", "%" + memNo + "%")
		        .setFirstResult(startIndex) // 設定起始索引
		        .setMaxResults(PAGE_MAX_RESULT) // 設定最大結果數
		        .getResultList();
	}
	
	//模糊比對
	@Override
	public long getTotalMember(String memNo) {
		long a = getSession()
				.createQuery("SELECT COUNT(*) from ShopOrder s WHERE s.member.memNo LIKE :memNo", Long.class)
		        .setParameter("memNo", "%" + memNo + "%")
				// 回傳他有幾個
				.uniqueResult();
		System.out.println(a+"=============================================================");
		return a;
	}
	
	@Override
	public List<ShopOrder> getOrdersByMember(String memNo) {
            return getSession().createQuery("FROM ShopOrder s WHERE s.member.memNo = :memNo ORDER BY shopOrderNo DESC", ShopOrder.class)
                    .setParameter("memNo", memNo)
                    .list();
        
    }
}
