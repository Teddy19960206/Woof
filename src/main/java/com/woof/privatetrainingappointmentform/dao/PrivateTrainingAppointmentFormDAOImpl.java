package com.woof.privatetrainingappointmentform.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

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

	@Override
	public List<PrivateTrainingAppointmentForm> getByMemName(String memName, int currentPage) {
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm WHERE member.memName LIKE :name",PrivateTrainingAppointmentForm.class)
				.setParameter("name", "%" + memName + "%")
				.setMaxResults(PAGE_MAX_RESULT)
				.getResultList();
		

	}

	@Override
	public long getTotalByMember(String memberName) {
		return getSession().createQuery("SELECT COUNT(*) from PrivateTrainingAppointmentForm  WHERE member.memName LIKE :name", Long.class)
				.setParameter("memberName", memberName)
				.uniqueResult();
	}

}
