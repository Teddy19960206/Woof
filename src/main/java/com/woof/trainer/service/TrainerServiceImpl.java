package com.woof.trainer.service;

import java.util.List;

import org.hibernate.Session;

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
			int result = dao.update(trainer);
			if (result == 1) {

				session.getTransaction().commit();
				return trainer;
			}
			session.getTransaction().rollback();

			return null;
		}

//		@Override
//		public void deleteAdministrator(Integer adminNo) {
//			// TODO Auto-generated method stub
//			
//		}
		      
		@Override
		public Trainer findTrainerByTrainerNo(Integer trainerNo) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			return null;
		}

		@Override
		public List<Trainer> getAllTrainer() {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List<Trainer> allTrainer = dao.getAll();
			session.getTransaction().commit();

			return allTrainer;
				  
		}

	}


