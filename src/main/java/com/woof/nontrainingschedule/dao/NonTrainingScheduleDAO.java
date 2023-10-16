package com.woof.nontrainingschedule.dao;

import java.util.List;

import com.woof.nontrainingschedule.entity.NonTrainingSchedule;

public interface NonTrainingScheduleDAO {

	void insert (NonTrainingSchedule nonTrainingScheduleVO);
	void update (NonTrainingSchedule nonTrainingScheduleVO);
	void delete (NonTrainingSchedule nonTrainingScheduleVO);
	
	NonTrainingSchedule findByNtsNo(Integer ntsNo);
	List<NonTrainingSchedule> getAll();
}
