package com.woof.appointmentdetail.service;

import java.util.List;

import org.hibernate.Session;

import com.woof.appointmentdetail.dao.AppointmentDetailDAO;
import com.woof.appointmentdetail.dao.AppointmentDetailDAOImpl;
import com.woof.appointmentdetail.entity.AppointmentDetail;
import com.woof.util.HibernateUtil;

public class AppointmentDetailServiceImpl implements AppointmentDetailService{
	
	private AppointmentDetailDAO dao;
	
	public AppointmentDetailServiceImpl() {
		dao = new AppointmentDetailDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public AppointmentDetail addAppointmentDetail(AppointmentDetail appointmentDetail) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		if (dao.insert(appointmentDetail) == 1){

			session.getTransaction().commit();
			return appointmentDetail;
		}
		session.getTransaction().rollback();
		return null;
	}

	@Override
	public AppointmentDetail updateAppointmentDetail(AppointmentDetail appointmentDetail) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		int result = dao.update(appointmentDetail);
		if (result == 1){

			session.getTransaction().commit();
			return appointmentDetail;
		}
		session.getTransaction().rollback();

		return null;
	}

//	@Override
//	public void deleteAppointmentDetail(Integer adNo) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public AppointmentDetail findAppointmentDetailByNo(Integer adNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		return null;
	}

	@Override
	public List<AppointmentDetail> getAllAppointmentDetails() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		List<AppointmentDetail> appointmentDetailList = dao.getAll();
		session.getTransaction().commit();

		// TODO Auto-generated method stub
		return appointmentDetailList;
	}
	

}
