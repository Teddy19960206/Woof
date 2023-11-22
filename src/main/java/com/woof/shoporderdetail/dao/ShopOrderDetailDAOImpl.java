package com.woof.shoporderdetail.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.woof.shoporderdetail.entity.ShopOrderDetail;
import org.hibernate.query.Query;


public class ShopOrderDetailDAOImpl implements ShopOrderDetailDAO {

	private SessionFactory factory;

	public ShopOrderDetailDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(ShopOrderDetail shopOrderDetail) {
		return (Integer) getSession().save(shopOrderDetail);
	}

//	@Override
//	public int update(ShopOrderDetail shopOrderDetail) {
//		try {
//			getSession().update(shopOrderDetail);
//			return 1;
//		} catch (Exception e) {
//			return -1;
//		}
//	}

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

//	@Override
//	public List<ShopOrderDetail> findOneShopOrderNoDetail(Integer shopOrderNo) {
//		return getSession()
//		        .createQuery("FROM ShopOrderDetail WHERE shopOrderNo = :shopOrderNo", ShopOrderDetail.class)
//		        .setParameter("shopOrderNo", shopOrderNo)
//		        .list();
//	}

	@Override
	public List<Object> findOneShopOrderNoDetailObj(Integer shopOrderNo) {
		String hql = "SELECT p.PROD_NAME , s.ORDER_AMOUNT  ,s.PROD_PRICE  FROM shop_order_detail  s "
				+ "join product p on s.PROD_NO = p.PROD_NO "
				+ "where s.SHOP_ORDER_NO = :shopOrderNo";
		Query query = getSession().createNativeQuery(hql);
		query.setParameter("shopOrderNo", shopOrderNo);
		
	    return query.getResultList();
	}

	
	
	@Override
	public List<ShopOrderDetail> getAll() {
		return getSession().createQuery("FROM ShopOrderDetail", ShopOrderDetail.class).list();
	}
	
	
//	@Override
//	public List<ShopOrder> getAll(int currentPage) {
//		int first = (currentPage - 1) * PAGE_MAX_RESULT;
//		return getSession().createQuery("FROM ShopOrder", ShopOrder.class)
//				.setFirstResult(first)
//				.setMaxResults(PAGE_MAX_RESULT)
//				.list();
//	}
//
//	@Override
//	public long getTotal() {
//		return getSession().createQuery("select count(*) from ShopOrder", Long.class).uniqueResult();
//	}

//	@Override
//	public List<ShopOrderDetail> findByShopOrderNo(Integer shopOrderNo , int currentPage) {
//		int startIndex = (currentPage - 1) * PAGE_MAX_RESULT;
//		return getSession().createQuery("FROM ShopOrder s WHERE s.member.memNo = :memNo", ShopOrder.class)
//		        .setParameter("shopOrderNo", shopOrderNo)
//		        .setFirstResult(startIndex) // 設定起始索引
//		        .setMaxResults(PAGE_MAX_RESULT) // 設定最大結果數
//		        .list();
//	}
//
//	@Override
//	public long getTotalMember(String memNo) {
//		long a = getSession().createQuery("SELECT COUNT(*) from ShopOrder s WHERE s.member.memNo = :memNo", Long.class)
//				.setParameter("memNo", memNo)
//				// 回傳他有幾個
//				.uniqueResult();
//		System.out.println(a+"=============================================================");
//		return a;
//	}
	
	
}
