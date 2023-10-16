package com.woof.appointmentdetail.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.appointmentdetail.entity.AppointmentDetail;

public class AppointmentDetailDAOImpl implements AppointmentDetailDAO{

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

//	@Override
//	public int delete(Integer adNo) {
//		AppointmentDetail appointmentDetail = getSession().get(AppointmentDetail.class, adNo);
//		if(appointmentDetail != null) {
//			getSession().delete(appointmentDetail);
//			return 1;
//		}else {
//			return -1;
//		}
//	}

	@Override
	public AppointmentDetail findByAdNo(Integer adNo) {
		return getSession().get(AppointmentDetail.class, adNo);
	}

	@Override
	public List<AppointmentDetail> getAll() {
		return getSession().createQuery("FROM AppointmentDetail" , AppointmentDetail.class).list();
	}
}
