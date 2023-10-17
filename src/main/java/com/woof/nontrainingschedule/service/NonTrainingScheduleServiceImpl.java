package com.woof.nontrainingschedule.service;

import java.util.List;

import org.hibernate.Session;

import com.woof.nontrainingschedule.dao.NonTrainingScheduleDAO;
import com.woof.nontrainingschedule.dao.NonTrainingScheduleDAOImpl;
import com.woof.nontrainingschedule.entity.NonTrainingSchedule;
import com.woof.util.HibernateUtil;

public class NonTrainingScheduleServiceImpl implements NonTrainingScheduleService{

	private NonTrainingScheduleDAO dao;

	public NonTrainingScheduleServiceImpl() {
		dao = new NonTrainingScheduleDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public NonTrainingSchedule addNonTrainingSchedule(NonTrainingSchedule nonTrainingSchedule) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		if (dao.insert(nonTrainingSchedule) == 1) {

			session.getTransaction().commit();
			return nonTrainingSchedule;
		}
		session.getTransaction().rollback();
		return null;
	}

	@Override
	public NonTrainingSchedule updateNonTrainingSchedule(NonTrainingSchedule nonTrainingSchedule) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		int result = dao.update(nonTrainingSchedule);
		if (result == 1) {

			session.getTransaction().commit();
			return nonTrainingSchedule;
		}
		session.getTransaction().rollback();

		return null;
	}

	@Override
	public NonTrainingSchedule findNonTrainingScheduleByNo(Integer ntsNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		return null;
	}

	@Override
	public List<NonTrainingSchedule> getAllNonTrainingSchedules() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		session.beginTransaction();
		List<NonTrainingSchedule> nonTrainingScheduleList = dao.getAll();
		session.getTransaction().commit();

		// TODO Auto-generated method stub
		return nonTrainingScheduleList;
	}
}
