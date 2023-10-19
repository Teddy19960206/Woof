package com.woof.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.faq.entity.Faq;
import com.woof.util.Util;

public class FaqDAOImpl implements FaqDAO{
	
	private SessionFactory factory;

	public FaqDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(Faq faq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Faq faq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Faq faq) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Faq findByFaqNo(Integer faqNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Faq> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
    	
   	
	
	
}
