package com.woof.privatetrainingappointmentform.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.util.HibernateUtil;

public class PrivateTrainingAppointmentFormDAOImpl implements PrivateTrainingAppointmentFormDAO {

	private SessionFactory factory;

	public PrivateTrainingAppointmentFormDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(PrivateTrainingAppointmentForm privateTrainingAppointmentForm) {
		

		return (Integer) getSession().save(privateTrainingAppointmentForm);
	}

	@Override
	public int update(PrivateTrainingAppointmentForm privateTrainingAppointmentForm) {
		try {
			getSession().update(privateTrainingAppointmentForm);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public PrivateTrainingAppointmentForm findByPtaNo(Integer ptaNo) {
		return getSession().get(PrivateTrainingAppointmentForm.class, ptaNo);
	}

	@Override
	public List<PrivateTrainingAppointmentForm> getAll() {
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm", PrivateTrainingAppointmentForm.class)
				.list();
	}

}
