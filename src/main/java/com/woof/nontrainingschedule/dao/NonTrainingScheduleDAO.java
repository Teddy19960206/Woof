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
	
	List<NonTrainingSchedule> findByTrainerNo(Integer trainerNo);
	
	List<NonTrainingSchedule> findByNtsDate(Date ntsDate);
	
	List<NonTrainingSchedule> getAll(int currentPage);
	
	long getTotal();
}
