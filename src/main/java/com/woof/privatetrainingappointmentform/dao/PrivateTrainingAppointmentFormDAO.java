package com.woof.privatetrainingappointmentform.dao;

import java.util.List;

import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;

public interface PrivateTrainingAppointmentFormDAO {

	int insert(PrivateTrainingAppointmentForm privateTrainingAppointmentForm);
	
	int update(PrivateTrainingAppointmentForm privateTrainingAppointmentForm);
	
	int delete(PrivateTrainingAppointmentForm privateTrainingAppointmentForm);
	
	PrivateTrainingAppointmentForm findByPtaNo(Integer ptaNo);
	
	List<PrivateTrainingAppointmentForm> getAll();
	
	List<PrivateTrainingAppointmentForm> getAll(int currentPage);
	
	long getTotal();
}
