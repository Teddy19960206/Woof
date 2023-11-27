package com.woof.trainer.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.util.List;
import java.util.Set;

import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.skill.entity.Skill;
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
			if (dao.insert(trainer) == 1) {
				return trainer;
			}
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

	@Override
	public Set<Skill> getTrainerSkills(Integer trainerNo) {
		return dao.getSkillsList(trainerNo);
	}

	@Override
	public Trainer getByAdmin(String adminNo) {
		return dao.getByAdmin(adminNo);
	}

	@Override
	public List<Trainer> getAllTrainers2(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
	
}



