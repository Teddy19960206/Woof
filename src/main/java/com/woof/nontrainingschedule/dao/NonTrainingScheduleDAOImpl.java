package com.woof.nontrainingschedule.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.nontrainingschedule.entity.NonTrainingSchedule;

public class NonTrainingScheduleDAOImpl implements NonTrainingScheduleDAO{

	private SessionFactory factory;

	public NonTrainingScheduleDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(NonTrainingSchedule nonTrainingSchedule) {
		return (Integer) getSession().save(nonTrainingSchedule);
	}

	@Override
	public int update(NonTrainingSchedule nonTrainingSchedule) {
		try {
			getSession().update(nonTrainingSchedule);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public NonTrainingSchedule findByNtsNo(Integer ntsNo) {
		return getSession().get(NonTrainingSchedule.class, ntsNo);
	}

	@Override
	public List<NonTrainingSchedule> getAll() {
		return getSession().createQuery("FROM NonTrainingSchedule", NonTrainingSchedule.class).list();
	}
}
