package com.woof.trainer.service;

import java.util.List;

import com.woof.trainer.entity.Trainer;


public interface TrainerService {

	Trainer addTrainer(Trainer trainer);

	Trainer updateTrainer(Trainer trainer);

	void deleteTrainer(Integer trainerNo);

	Trainer findTrainerByTrainerNo(Integer trainerNo);

	List<Trainer> getAllTrainers();

}
