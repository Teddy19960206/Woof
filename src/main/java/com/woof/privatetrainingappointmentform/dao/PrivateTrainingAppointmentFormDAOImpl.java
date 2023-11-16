package com.woof.privatetrainingappointmentform.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.groupcourseorder.entity.GroupCourseOrder;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;


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
		try {
			getSession().save(privateTrainingAppointmentForm);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int update(PrivateTrainingAppointmentForm privateTrainingAppointmentForm) {
		try {
			getSession().merge(privateTrainingAppointmentForm);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	public int delete(PrivateTrainingAppointmentForm privateTrainingAppointmentForm) {
		try {
			getSession().delete(privateTrainingAppointmentForm);
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
	public List<PrivateTrainingAppointmentForm> findByMemNo(String memNo , int currentPage) {
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm p WHERE p.member.memNo = :memNo", PrivateTrainingAppointmentForm.class)
				.setParameter("memNo", memNo)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotalMember(String memNo) {
		return getSession().createQuery("SELECT COUNT(*) from PrivateTrainingAppointmentForm p WHERE p.member.memNo = :memNo", Long.class)
				.setParameter("memNo", memNo)
				.uniqueResult();
	}
	
	@Override
	public List<PrivateTrainingAppointmentForm> findByTrainerNo(Integer trainerNo , int currentPage) {
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm WHERE trainer.trainerNo = :trainerNo", PrivateTrainingAppointmentForm.class)
				.setParameter("trainerNo", trainerNo)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}
	
	@Override
	public long getTotalTrainer(Integer trainerNo) {
		return getSession().createQuery("SELECT COUNT(*) from PrivateTrainingAppointmentForm  WHERE trainer.trainerNo = :trainerNo", Long.class)
				.setParameter("trainerNo", trainerNo)
				.uniqueResult();
	}
	
	public List<Date> getByTrainer(Integer year , Integer month , Integer trainerNo){

		String sql = "SELECT ad.APP_TIME " +
				"FROM PRIVATE_TRAINING_APPOINTMENT_FORM pta " +
				"JOIN APPOINTMENT_DETAIL ad ON pta.PTA_NO = ad.PTA_NO " +
				"WHERE pta.TRAINER_NO = :trainerNo AND " +
				"MONTH(ad.APP_TIME) = :month AND " +
				"YEAR(ad.APP_TIME) = :year";

		NativeQuery query = getSession().createNativeQuery(sql);
		query.setParameter("trainerNo", trainerNo);
		query.setParameter("month", month);
		query.setParameter("year", year);
		List<Date> dates = query.list();

		return dates;
	}



	@Override
	public List<PrivateTrainingAppointmentForm> getByMemName(String memName, int currentPage) {
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm p WHERE p.member.memName LIKE :memName",PrivateTrainingAppointmentForm.class)
				.setParameter("memName", "%" + memName + "%")
				.setMaxResults(PAGE_MAX_RESULT)
				.getResultList();
		

	}

	@Override
	public long getTotalByMemName(String memName) {
		long totalCount = getSession()
		        .createQuery("SELECT COUNT(*) from PrivateTrainingAppointmentForm p WHERE p.member.memName LIKE :memName", Long.class)
		        .setParameter("memName", "%" + memName + "%")
		        .uniqueResult();

//		    System.out.println("===============================Total count for member " + memName + " is: " + totalCount); // 在這裡印出值

		    return totalCount;
	}

	@Override
	public List<PrivateTrainingAppointmentForm> getByBoth(String memName, Integer trainerNo, int currentPage) {
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm p WHERE p.trainer.trainerNo = :trainerNo AND p.member.memName LIKE :memName",PrivateTrainingAppointmentForm.class)
				.setParameter("trainerNo", trainerNo)
				.setParameter("memName", "%" + memName + "%")
				.setMaxResults(PAGE_MAX_RESULT)
				.getResultList();
	}

	@Override
	public long getTotalByBoth(String memName, Integer trainerNo) {
		return getSession().createQuery("SELECT COUNT(*) FROM PrivateTrainingAppointmentForm p WHERE p.trainer.trainerNo = :trainerNo AND p.member.memName LIKE :memName", Long.class)
				.setParameter("trainerNo", trainerNo)
				.setParameter("memName", "%" + memName + "%")
		        .uniqueResult();
	}
	
}
