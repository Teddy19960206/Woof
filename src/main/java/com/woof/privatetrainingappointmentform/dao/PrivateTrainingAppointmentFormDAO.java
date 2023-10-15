package com.woof.privatetrainingappointmentform.dao;

import java.util.List;

import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;

public interface PrivateTrainingAppointmentFormDAO {

	void insert (PrivateTrainingAppointmentForm privateTrainingAppointmentFormVO);
	void update (PrivateTrainingAppointmentForm privateTrainingAppointmentFormVO);
	void delete (PrivateTrainingAppointmentForm privateTrainingAppointmentFormVO);
	
	PrivateTrainingAppointmentForm findByPtaNo(Integer ptaNo);
	List<PrivateTrainingAppointmentForm> getAll();
}
