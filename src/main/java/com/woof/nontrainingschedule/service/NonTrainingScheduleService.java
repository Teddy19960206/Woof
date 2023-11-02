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
	
	List<NonTrainingSchedule> findNtsByTrainerNo(Integer trainerNo);
	
	List<NonTrainingSchedule> findNtsByNtsDate(Date ntsDate);

	List<NonTrainingSchedule> getAllNonTrainingSchedules();
	
	List<NonTrainingSchedule> getAllNTSs(int currentPage);
	
	int getPageTotal();
}
