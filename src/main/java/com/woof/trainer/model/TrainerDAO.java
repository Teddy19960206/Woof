package com.woof.trainer.model;

import java.util.List;

public interface TrainerDAO {
	void insert(TrainerVO trainerVO);

	void update(TrainerVO trainerVO);

	void delete(TrainerVO trainerVO);

	TrainerVO findByTrainerNo(Integer trainerNo);

	List<TrainerVO> getAll();
}
