package com.woof.nontrainingschedule.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
import java.util.List;

import com.woof.nontrainingschedule.dao.NonTrainingScheduleDAO;
import com.woof.nontrainingschedule.dao.NonTrainingScheduleDAOImpl;
import com.woof.nontrainingschedule.entity.NonTrainingSchedule;
import com.woof.trainer.entity.Trainer;
import com.woof.util.HibernateUtil;

public class NonTrainingScheduleServiceImpl implements NonTrainingScheduleService{

	private NonTrainingScheduleDAO dao;

	public NonTrainingScheduleServiceImpl() {
		dao = new NonTrainingScheduleDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public int addNts(Trainer trainer, Date ntsDate) {
		NonTrainingSchedule nonTrainingSchedule = new NonTrainingSchedule();
		nonTrainingSchedule.setTrainer(trainer);
		nonTrainingSchedule.setNtsDate(ntsDate);
		dao.insert(nonTrainingSchedule);
		return 1;
	}

	@Override
	public int updateNts(Integer ntsNo, Trainer trainer, Date ntsDate) {
		NonTrainingSchedule nonTrainingSchedule = new NonTrainingSchedule();
		nonTrainingSchedule.setNtsNo(ntsNo);
		nonTrainingSchedule.setTrainer(trainer);
		nonTrainingSchedule.setNtsDate(ntsDate);
		dao.update(nonTrainingSchedule);
		return 1;
	}

	@Override
	public int deleteNts(Integer ntsNo) {
		NonTrainingSchedule nonTrainingSchedule = new NonTrainingSchedule();
		nonTrainingSchedule.setNtsNo(ntsNo);
		dao.delete(nonTrainingSchedule);
		return 1;
	}

	@Override
	public NonTrainingSchedule findNtsByNtsNo(Integer ntsNo) {
		return dao.findByNtsNo(ntsNo);
	}

	@Override
	public List<NonTrainingSchedule> findNtsByTrainerNo(Integer trainerNo) {
		return dao.findByTrainerNo(trainerNo);
	}

	@Override
	public List<NonTrainingSchedule> findNtsByNtsDate(Date ntsDate) {
		return dao.findByNtsDate(ntsDate);
	}

	@Override
	public List<NonTrainingSchedule> getAllNonTrainingSchedules() {
		return dao.getAll();
	}

	@Override
	public List<NonTrainingSchedule> getAllNTSs(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
}
