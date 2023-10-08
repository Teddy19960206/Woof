package com.woof.privatetrainingappointmentform.model;

import java.util.List;

public interface PrivateTrainingAppointmentFormDAO {

	void insert (PrivateTrainingAppointmentFormDAO privateTrainingAppointmentFormDAO);
	void update (PrivateTrainingAppointmentFormDAO privateTrainingAppointmentForm);
	void delete (Integer ptaNo);
	
	PrivateTrainingAppointmentFormVO findByPtaNo(Integer ptaNo);
	List<PrivateTrainingAppointmentFormVO> getAll();
}
