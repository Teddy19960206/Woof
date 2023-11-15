package com.woof.privatetrainingappointmentform.dao;

import java.util.List;

import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;

public interface PrivateTrainingAppointmentFormDAO {

	int insert(PrivateTrainingAppointmentForm privateTrainingAppointmentForm);
	
	int update(PrivateTrainingAppointmentForm privateTrainingAppointmentForm);
	
	int delete(PrivateTrainingAppointmentForm privateTrainingAppointmentForm);
	
	PrivateTrainingAppointmentForm findByPtaNo(Integer ptaNo);
	
	List<PrivateTrainingAppointmentForm> findByMemNo(String memNo , int currentPage);
	
	long getTotalMember(String memberNo);
	
	List<PrivateTrainingAppointmentForm> findByTrainerNo(Integer trainerNo , int currentPage);
	
	long getTotalTrainer(Integer trainerNo);
	
	List<PrivateTrainingAppointmentForm> getAll();
	
	List<PrivateTrainingAppointmentForm> getAll(int currentPage);
	
	long getTotal();
	
	List<PrivateTrainingAppointmentForm> getByMemName(String memName , int currentPage);
	
	long getTotalByMemName(String memberNo);
	
	List<PrivateTrainingAppointmentForm> getByBoth(String memName , Integer trainerNo ,int currentPage);
	
	long getTotalByBoth(String memName , Integer trainerNo);
}
