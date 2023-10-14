package com.woof.promotionactivity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

import com.woof.promotionactivity.entity.PromotionActivity;
import com.woof.util.Util;

public class PromotionActivityDAOImpl implements PromotionActivityDAO{
	
	private SessionFactory factory;

	public PromotionActivityDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(PromotionActivity PromotionActivity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(PromotionActivity PromotionActivity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer paNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PromotionActivity findByPaNo(Integer paNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromotionActivity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromotionActivity> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromotionActivity> getAll(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
    
}
