package com.woof.promotionactivity.service;

import java.util.List;

import org.hibernate.Session;

import com.woof.promotionactivity.dao.PromotionActivityDAO;
import com.woof.promotionactivity.dao.PromotionActivityDAOImpl;
import com.woof.promotionactivity.entity.PromotionActivity;
import com.woof.util.HibernateUtil;

public class PromotionActivityServiceImpl implements PromotionActivityService{

	private PromotionActivityDAO dao;

	public PromotionActivityServiceImpl() {
		dao = new PromotionActivityDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public PromotionActivity addPromotionActivity(PromotionActivity promotionActivity) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		if (dao.insert(promotionActivity) == 1) {

			session.getTransaction().commit();
			return promotionActivity;
		}
		session.getTransaction().rollback();
		return null;
	}

	@Override
	public PromotionActivity updatePromotionActivity(PromotionActivity promotionActivity) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		int result = dao.update(promotionActivity);
		if (result == 1) {

			session.getTransaction().commit();
			return promotionActivity;
		}
		session.getTransaction().rollback();

		return null;
	}

//	@Override
//	public void deletePromotionActivity(Integer paNo) {
//		// TODO Auto-generated method stub
//		
//	}
	      
	@Override
	public PromotionActivity findPromotionActivityByPaNo(Integer paNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		return null;
	}

	@Override
	public List<PromotionActivity> getAllPromotionActivity() {
		//filter已經有寫所以這邊可以省略
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

//		session.beginTransaction();
		List<PromotionActivity> promotionActivityList = dao.getAll();
//		session.getTransaction().commit();

		// TODO Auto-generated method stub
		return promotionActivityList;
			  
	}

}
