package com.woof.nontrainingschedule.dao;

import java.sql.Date;
import java.util.List;
import com.woof.nontrainingschedule.entity.NonTrainingSchedule;

public interface NonTrainingScheduleDAO {

	int insert(NonTrainingSchedule nonTrainingSchedule);

	int update(NonTrainingSchedule nonTrainingSchedule);
	
	int delete(NonTrainingSchedule nonTrainingSchedule);

	NonTrainingSchedule findByNtsNo(Integer ntsNo);

	List<NonTrainingSchedule> getAll();
	
	List<NonTrainingSchedule> findByTrainerNo(Integer trainerNo , int currentPage);
	
	long getTotalByTrainerNo(int trainerNo);
	
	List<NonTrainingSchedule> findByNtsDate(Integer year  , Integer month);
	
	long getTotalByNtsDate(Date ntsDate);
	
	List<NonTrainingSchedule> getAll(int currentPage);
	
	long getTotal();
}
