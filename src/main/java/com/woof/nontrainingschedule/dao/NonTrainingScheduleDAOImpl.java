package com.woof.nontrainingschedule.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.nontrainingschedule.entity.NonTrainingSchedule;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

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
		try {
			getSession().save(nonTrainingSchedule);
			return 1;
		} catch (Exception e) {
			return -1;
		}
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
		try {
			getSession().delete(nonTrainingSchedule);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public List<NonTrainingSchedule> findByTrainerNo(Integer trainerNo ,Integer year , Integer month, int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM NonTrainingSchedule WHERE trainer.trainerNo = :trainerNo and YEAR(ntsDate) = :year and MONTH(ntsDate) = :month", NonTrainingSchedule.class)
				.setParameter("trainerNo", trainerNo)
				.setParameter("year", year)
				.setParameter("month", month)			
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	
	@Override
	public long getTotalByTrainerNo(Integer trainerNo , Integer year , Integer month) {
		return getSession().createQuery("select count(*) from NonTrainingSchedule  where trainer.trainerNo = :trainerNo and YEAR(ntsDate) = :year and MONTH(ntsDate) = :month" , Long.class)
				.setParameter("trainerNo", trainerNo)
				.setParameter("year", year)
				.setParameter("month", month)
				.uniqueResult();
	}

	@Override
	public List<NonTrainingSchedule> findByNtsDate(Integer year  , Integer month) {
		return getSession().createQuery("FROM NonTrainingSchedule  WHERE YEAR(ntsDate) = :year and MONTH(ntsDate) = :month", NonTrainingSchedule.class)
				.setParameter("year", year)
				.setParameter("month", month)
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

	public List<Date> getAllByDate(Integer year , Integer month , Integer trainerNo){

		String sql = "SELECT NTS_DATE FROM non_training_schedule WHERE TRAINER_NO = ? AND YEAR(NTS_DATE) = ? AND MONTH(NTS_DATE) = ?";
		NativeQuery sqlQuery = getSession().createSQLQuery(sql);
		sqlQuery.setParameter(1 , trainerNo);
		sqlQuery.setParameter(2 , year);
		sqlQuery.setParameter(3 , month);

		return sqlQuery.list();

	}

	public void deleteByDate(Integer trainerNo , Date date ){
		String hql = "DELETE NonTrainingSchedule WHERE trainer.trainerNo = :trainerNo AND ntsDate = :date";
		Query query = getSession().createQuery(hql);
		query.setParameter("trainerNo" , trainerNo);
		query.setParameter("date" , date);
		query.executeUpdate();
	}
}
