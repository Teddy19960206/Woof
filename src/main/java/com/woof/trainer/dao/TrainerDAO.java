package com.woof.trainer.dao;

import java.util.List;


import com.woof.trainer.entity.Trainer;

public interface TrainerDAO {
//	void insert(TrainerVO trainerVO);
//
//	void update(TrainerVO trainerVO);
//
//	void delete(TrainerVO trainerVO);
//
//	TrainerVO findByTrainerNo(Integer trainerNo);
//
//	List<TrainerVO> getAll();
	int insert(Trainer trainerVO);

	 int update(Trainer trainerVO);

	 int delete(Integer trainerNo);

	 Trainer getById(Integer trainerNo);

	 List<Trainer> getAll();

	 long getTotal();
}
