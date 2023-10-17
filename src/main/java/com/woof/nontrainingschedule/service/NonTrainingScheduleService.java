package com.woof.nontrainingschedule.service;

import java.util.List;

import com.woof.nontrainingschedule.entity.NonTrainingSchedule;

public interface NonTrainingScheduleService {

	NonTrainingSchedule addNonTrainingSchedule(NonTrainingSchedule nonTrainingSchedule);

	NonTrainingSchedule updateNonTrainingSchedule(NonTrainingSchedule nonTrainingSchedule);

	NonTrainingSchedule findNonTrainingScheduleByNtsNo(Integer ntsNo);

	List<NonTrainingSchedule> getAllNonTrainingSchedules();
}
