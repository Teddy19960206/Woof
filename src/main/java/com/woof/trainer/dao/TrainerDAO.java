package com.woof.trainer.dao;

import java.util.List;
import java.util.Set;

import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
import com.woof.skill.entity.Skill;
import com.woof.trainer.entity.Trainer;

public interface TrainerDAO {
//	void insert(Trainer trainerVO);
//
//	void update(Trainer trainerVO);
//
//	void delete(Trainer trainerVO);
//
//	Trainer findByTrainerNo(Integer trainerNo);

//	List<Trainer> getAll();
//	
	int insert(Trainer trainer);

	int update(Trainer trainer);

	int delete(Integer trainerNo);

	Set<Skill> getSkillsList(Integer trainerNo);

	List<Trainer> getAll();

	Trainer findBytrainerNo(Integer trainerNo);

	Set<GroupScheduleDetail> groupScheduleDetails(Integer trainerNo);

	Trainer getByAdmin(String adminNo);
	
	List<Trainer> getAll(int currentPage);
	
	long getTotal();
}
