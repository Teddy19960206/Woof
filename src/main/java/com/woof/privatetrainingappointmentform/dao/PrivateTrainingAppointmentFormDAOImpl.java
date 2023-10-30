package com.woof.privatetrainingappointmentform.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

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

	public int delete(PrivateTrainingAppointmentForm privateTrainingAppointmentForm) {
		getSession().delete(privateTrainingAppointmentForm);
		return 1;
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
	
	@Override
	public List<PrivateTrainingAppointmentForm> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm", PrivateTrainingAppointmentForm.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from PrivateTrainingAppointmentForm", Long.class).uniqueResult();
	}

	@Override
	public List<PrivateTrainingAppointmentForm> findByMemNo(String memNo) {
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm WHERE member.memNo = :memNo", PrivateTrainingAppointmentForm.class)
				.setParameter("memNo", memNo)
				.list();
	}

	@Override
	public List<PrivateTrainingAppointmentForm> findByTrainerNo(Integer trainerNo) {
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm WHERE trainer.trainerNo = :trainerNo", PrivateTrainingAppointmentForm.class)
				.setParameter("trainerNo", trainerNo)
				.list();
	}
	
}
