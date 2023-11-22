package com.woof.commentreport.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.classorder.entity.ClassOrder;
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
			getSession().merge(commentReport);
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
		return getSession().createQuery("FROM CommentReport ORDER BY crDate DESC", CommentReport.class).list();
	}

	@Override
	public List<CommentReport> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM CommentReport ORDER BY crDate DESC", CommentReport.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from CommentReport", Long.class).uniqueResult();
	}

	@Override
	public int updateStatusByPta(Integer ptaNo, Integer crStatus) {
		return getSession().createQuery("UPDATE CommentReport c SET c.crStatus = :crStatus WHERE c.privateTrainingAppointmentForm.ptaNo = :ptaNo")
					.setParameter("ptaNo", ptaNo)
					.setParameter("crStatus", crStatus)
					.executeUpdate();
	}
	
	
}
