package com.woof.trainer.dao;

import java.util.List;

import com.woof.promotionactivity.entity.PromotionActivity;
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

//	int delete(Integer trainerNo);

	Trainer findByTrainerNo(Integer trainerNo);

	List<Trainer> getAll();
}
