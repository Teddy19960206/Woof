package com.woof.privatetrainingappointmentform.service;

import java.util.List;

import org.hibernate.Session;

import com.woof.privatetrainingappointmentform.dao.PrivateTrainingAppointmentFormDAO;
import com.woof.privatetrainingappointmentform.dao.PrivateTrainingAppointmentFormDAOImpl;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.util.HibernateUtil;

public class PrivateTrainingAppointmentFormServiceImpl implements PrivateTrainingAppointmentFormService{

	private PrivateTrainingAppointmentFormDAO dao;
	
	public PrivateTrainingAppointmentFormServiceImpl() {
		dao = new PrivateTrainingAppointmentFormDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public PrivateTrainingAppointmentForm addPrivateTrainingAppointmentForm(
			PrivateTrainingAppointmentForm privateTrainingAppointmentForm) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		if (dao.insert(privateTrainingAppointmentForm) == 1){

			session.getTransaction().commit();
			return privateTrainingAppointmentForm;
		}
		session.getTransaction().rollback();
		return null;
	}

	@Override
	public PrivateTrainingAppointmentForm updatePrivateTrainingAppointmentForm(
			PrivateTrainingAppointmentForm privateTrainingAppointmentForm) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		int result = dao.update(privateTrainingAppointmentForm);
		if (result == 1){

			session.getTransaction().commit();
			return privateTrainingAppointmentForm;
		}
		session.getTransaction().rollback();

		return null;
	}

	@Override
	public PrivateTrainingAppointmentForm findPrivateTrainingAppointmentFormByNo(Integer ptaNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		return null;
	}

	@Override
	public List<PrivateTrainingAppointmentForm> getAllPrivateTrainingAppointmentForms() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		List<PrivateTrainingAppointmentForm> privateTrainingAppointmentFormList = dao.getAll();
		session.getTransaction().commit();

		// TODO Auto-generated method stub
		return privateTrainingAppointmentFormList;
	}
	
	
}