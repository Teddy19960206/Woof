package com.woof.trainer.service;

import java.util.List;
import java.util.Set;

import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.skill.entity.Skill;
import com.woof.trainer.entity.Trainer;


public interface TrainerService {

	Trainer addTrainer(Trainer trainer);

	Trainer updateTrainer(Trainer trainer);

	void deleteTrainer(Integer trainerNo);

	Trainer findTrainerByTrainerNo(Integer trainerNo);

	List<Trainer> getAllTrainers();

	Set<GroupScheduleDetail> getGroupDetail(Integer trainerNo);

	Set<Skill> getTrainerSkills(Integer trainerNo);

}
