package com.woof.privatetrainingappointmentform.model;

import java.util.List;

public interface PrivateTrainingAppointmentFormDAO {

	void insert (PrivateTrainingAppointmentFormVO privateTrainingAppointmentFormVO);
	void update (PrivateTrainingAppointmentFormVO privateTrainingAppointmentFormVO);
	void delete (Integer ptaNo);
	
	PrivateTrainingAppointmentFormVO findByPtaNo(Integer ptaNo);
	List<PrivateTrainingAppointmentFormVO> getAll();
}
