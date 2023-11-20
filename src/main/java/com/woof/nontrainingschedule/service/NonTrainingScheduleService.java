package com.woof.nontrainingschedule.service;

import java.sql.Date;
import java.util.List;

import com.woof.nontrainingschedule.entity.NonTrainingSchedule;
import com.woof.trainer.entity.Trainer;

public interface NonTrainingScheduleService {

	int addNts(Trainer trainer,Date ntsDate);
	
	int updateNts(Integer ntsNo, Trainer trainer ,Date ntsDate);
	
	int deleteNts(Integer ntsNo);

	NonTrainingSchedule findNtsByNtsNo(Integer ntsNo);
	
	List<NonTrainingSchedule> findNtsByTrainerNo(Integer trainerNo , Integer year , Integer month, int currentPage);
	
	int getPageTotal2(Integer trainerNo , Integer year , Integer month);
	
	List<NonTrainingSchedule> findNtsByNtsDate(Integer year, Integer month);
	
	int getPageTotal3(Date ntsDate);

	List<NonTrainingSchedule> getAllNonTrainingSchedules();
	
	List<NonTrainingSchedule> getAllNTSs(int currentPage);
	
	int getPageTotal();

	List<Date> getAllByTrainer(Integer year , Integer month , Integer trainerNo);

	void deleteByDate(Integer trainerNo , Date date );

	
}
