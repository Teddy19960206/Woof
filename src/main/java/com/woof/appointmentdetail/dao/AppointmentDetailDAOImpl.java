package com.woof.appointmentdetail.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.appointmentdetail.entity.AppointmentDetail;

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
		return (Integer) getSession().save(appointmentDetail);
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
	public AppointmentDetail findByAdNo(Integer adNo) {
		return getSession().get(AppointmentDetail.class, adNo);
	}

	@Override
	public List<AppointmentDetail> getAll() {
		return getSession().createQuery("FROM AppointmentDetail", AppointmentDetail.class).list();
	}

	@Override
	public List<AppointmentDetail> findByAppTime(String appTime) {
		return getSession().createQuery("FROM AppointmentDetail a WHERE a.appTime = :appTime", AppointmentDetail.class).list();
	}

	@Override
	public List<AppointmentDetail> getAll(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
