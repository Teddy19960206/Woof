package com.woof.appointmentdetail.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.appointmentdetail.entity.AppointmentDetail;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;

public class AppointmentDetailDAOImpl implements AppointmentDetailDAO {

	private SessionFactory factory;

	public AppointmentDetailDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(AppointmentDetail appointmentDetail) {
		try {
			getSession().save(appointmentDetail);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int update(AppointmentDetail appointmentDetail) {
		try {
			getSession().update(appointmentDetail);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	
	@Override
	public int delete(AppointmentDetail appointmentDetail) {
		try {
			getSession().delete(appointmentDetail);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public AppointmentDetail findByAdNo(Integer adNo) {
		return getSession().get(AppointmentDetail.class, adNo);
	}

	@Override
	public List<AppointmentDetail> getAll() {
		return getSession().createQuery("FROM AppointmentDetail", AppointmentDetail.class).list();
	}

	@Override
	public List<AppointmentDetail> findByAppTime(Date appTime) {
		return getSession().createQuery("FROM AppointmentDetail a WHERE a.appTime = :appTime", AppointmentDetail.class)
				.setParameter("appTime", appTime)
				.list();
	}

	@Override
	public List<AppointmentDetail> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM AppointmentDetail", AppointmentDetail.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from AppointmentDetail", Long.class).uniqueResult();
	}

	@Override
	public List<AppointmentDetail> findByPtaNo(Integer ptaNo) {
		return getSession().createQuery("FROM AppointmentDetail a WHERE a.privateTrainingAppointmentForm.ptaNo = :ptaNo", AppointmentDetail.class)
				.setParameter("ptaNo", ptaNo)
				.list();
	}

	@Override
	public long getTotalByPtaNo(Integer ptaNo) {
		return getSession().createQuery("select count(*) from AppointmentDetail a where a.privateTrainingAppointmentForm.ptaNo = :ptaNo", Long.class)
				.setParameter("ptaNo", ptaNo)
				.uniqueResult()
				.longValue();
	}
	
	
}
