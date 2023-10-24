package com.woof.trainer.service;

import java.util.List;
import java.util.Set;

import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import org.hibernate.Session;

import com.woof.trainer.dao.TrainerDAOImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.dao.TrainerDAO;
import com.woof.trainer.dao.TrainerDAOImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.dao.TrainerDAO;
import com.woof.util.HibernateUtil;

public class TrainerServiceImpl  implements TrainerService{
	
		
	private TrainerDAO dao;

	public TrainerServiceImpl() {
		dao = new TrainerDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public Trainer addTrainer(Trainer trainer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			if (dao.insert(trainer) == 1) {
				session.getTransaction().commit();
				return trainer;
			}
			session.getTransaction().rollback();
		return null;
	}

	@Override
	public Trainer updateTrainer(Trainer trainer) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			if (dao.update(trainer) == 1) {
				session.getTransaction().commit();
				return trainer;
			}
			session.getTransaction().rollback();
		return null;
	}

	@Override
	public void deleteTrainer(Integer trainerNo) {
		
	}

	@Override
	public Trainer findTrainerByTrainerNo(Integer trainerNo) {
		Trainer trainer = dao.findBytrainerNo(trainerNo);
		return trainer;
		}

	@Override
	public List<Trainer> getAllTrainers() {
			List<Trainer> trainerList = dao.getAll();
			return trainerList;
	}

	@Override
	public Set<GroupScheduleDetail> getGroupDetail(Integer trainerNo) {
		return dao.groupScheduleDetails(trainerNo);
	}
}



