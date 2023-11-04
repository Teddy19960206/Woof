package com.woof.nontrainingschedule.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
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

	@Override
	public int delete(NonTrainingSchedule nonTrainingSchedule) {
		getSession().delete(nonTrainingSchedule);
		return 1;
	}

	@Override
	public List<NonTrainingSchedule> findByTrainerNo(Integer trainerNo , int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM NonTrainingSchedule WHERE trainer.trainerNo = :trainerNo", NonTrainingSchedule.class)
				.setParameter("trainerNo", trainerNo)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	
	@Override
	public long getTotalByTrainerNo(int trainerNo) {
		return getSession().createQuery("select count(*) from NonTrainingSchedule  where trainer.trainerNo = :trainerNo" , Long.class)
				.setParameter("trainerNo", trainerNo)
				.uniqueResult();
	}

	@Override
	public List<NonTrainingSchedule> findByNtsDate(Date ntsDate , int currentPage) {
		return getSession().createQuery("FROM NonTrainingSchedule  WHERE ntsDate = :ntsDate", NonTrainingSchedule.class)
				.setParameter("ntsDate", ntsDate)
				.list();
	}

	
	@Override
	public long getTotalByNtsDate(Date ntsDate) {
		return getSession().createQuery("select count(*) from NonTrainingSchedule  where ntsDate = :ntsDate" , Long.class)
				.setParameter("ntsDate", ntsDate)
				.uniqueResult();
	}

	@Override
	public List<NonTrainingSchedule> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM NonTrainingSchedule", NonTrainingSchedule.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from NonTrainingSchedule", Long.class).uniqueResult();
	}
	
}
