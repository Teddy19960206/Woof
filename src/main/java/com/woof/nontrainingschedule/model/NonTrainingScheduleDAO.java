package com.woof.nontrainingschedule.model;

import java.util.List;

public interface NonTrainingScheduleDAO {

	void insert (NonTrainingScheduleVO nonTrainingScheduleVO);
	void update (NonTrainingScheduleVO nonTrainingScheduleVO);
	void delete (NonTrainingScheduleVO nonTrainingScheduleVO);
	
	NonTrainingScheduleVO findByNtsNo(Integer ntsNo);
	List<NonTrainingScheduleVO> getAll();
}
