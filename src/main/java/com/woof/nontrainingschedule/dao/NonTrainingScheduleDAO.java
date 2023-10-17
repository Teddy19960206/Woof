package com.woof.nontrainingschedule.dao;

import java.util.List;
import com.woof.nontrainingschedule.entity.NonTrainingSchedule;

public interface NonTrainingScheduleDAO {

	int insert(NonTrainingSchedule nonTrainingSchedule);

	int update(NonTrainingSchedule nonTrainingSchedule);

	NonTrainingSchedule findByNtsNo(Integer ntsNo);

	List<NonTrainingSchedule> getAll();
}
