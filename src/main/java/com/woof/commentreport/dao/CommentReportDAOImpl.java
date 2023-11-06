package com.woof.commentreport.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.commentreport.entity.CommentReport;

public class CommentReportDAOImpl implements CommentReportDAO{

	private SessionFactory factory;
	
	public CommentReportDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(CommentReport commentReport) {
		try {
			getSession().save(commentReport);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int update(CommentReport commentReport) {
		try {
			getSession().update(commentReport);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(CommentReport commentReport) {
		try {
			getSession().delete(commentReport);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public CommentReport findByCrNo(Integer crNo) {
		return getSession().get(CommentReport.class, crNo);
	}

	@Override
	public List<CommentReport> getAll() {
		return getSession().createQuery("FROM CommentReport", CommentReport.class).list();
	}
	
	
}
