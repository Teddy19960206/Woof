package com.woof.trainer.dao;

import java.util.List;


import com.woof.trainer.entity.TrainerVO;

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
	int insert(TrainerVO trainerVO);

	 int update(TrainerVO trainerVO);

	 int delete(Integer trainerNo);

	 TrainerVO getById(Integer trainerNo);

	 List<TrainerVO> getAll();

	 long getTotal();
}
