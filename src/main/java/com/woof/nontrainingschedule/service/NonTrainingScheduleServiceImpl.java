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
		return dao.insert(nonTrainingSchedule);
	}

	@Override
	public int updateNts(Integer ntsNo, Trainer trainer, Date ntsDate) {
		NonTrainingSchedule nonTrainingSchedule = new NonTrainingSchedule();
		nonTrainingSchedule.setNtsNo(ntsNo);
		nonTrainingSchedule.setTrainer(trainer);
		nonTrainingSchedule.setNtsDate(ntsDate);
		return dao.update(nonTrainingSchedule);
	}

	@Override
	public int deleteNts(Integer ntsNo) {
		NonTrainingSchedule nonTrainingSchedule = new NonTrainingSchedule();
		nonTrainingSchedule.setNtsNo(ntsNo);
		return dao.delete(nonTrainingSchedule);
	}

	@Override
	public NonTrainingSchedule findNtsByNtsNo(Integer ntsNo) {
		return dao.findByNtsNo(ntsNo);
	}

	@Override
	public List<NonTrainingSchedule> findNtsByTrainerNo(Integer trainerNo , int currentPage) {
		return dao.findByTrainerNo(trainerNo, currentPage);
	}

	@Override
	public int getPageTotal2(Integer trainerNo) {
		long total = dao.getTotalByTrainerNo(trainerNo);
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
	
	@Override
	public List<NonTrainingSchedule> findNtsByNtsDate(Integer year, Integer month) {
		return dao.findByNtsDate(year, month);
	}

	@Override
	public int getPageTotal3(Date ntsDate) {
		long total = dao.getTotalByNtsDate(ntsDate);
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
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
