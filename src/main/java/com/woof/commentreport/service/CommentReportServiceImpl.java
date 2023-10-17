package com.woof.commentreport.service;

import java.util.List;

import org.hibernate.Session;

import com.woof.commentreport.dao.CommentReportDAO;
import com.woof.commentreport.dao.CommentReportDAOImpl;
import com.woof.commentreport.entity.CommentReport;
import com.woof.util.HibernateUtil;

public class CommentReportServiceImpl implements CommentReportService{

	private CommentReportDAO dao;
	
	public CommentReportServiceImpl() {
		dao = new CommentReportDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public CommentReport addCommentReport(CommentReport commentReport) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		if (dao.insert(commentReport) == 1){

			session.getTransaction().commit();
			return commentReport;
		}
		session.getTransaction().rollback();
		return null;
	}

	@Override
	public CommentReport updateCommentReport(CommentReport commentReport) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		int result = dao.update(commentReport);
		if (result == 1){

			session.getTransaction().commit();
			return commentReport;
		}
		session.getTransaction().rollback();

		return null;
	}

	@Override
	public CommentReport findCommentReportByNo(Integer adNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		return null;
	}

	@Override
	public List<CommentReport> getAllCommentReports() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		List<CommentReport> commentReportList = dao.getAll();
		session.getTransaction().commit();

		// TODO Auto-generated method stub
		return commentReportList;
	}
	
}
