package com.woof.privatetrainingappointmentform.service;

import java.util.List;

import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;

public interface PrivateTrainingAppointmentFormService {
	PrivateTrainingAppointmentForm addPrivateTrainingAppointmentForm(PrivateTrainingAppointmentForm privateTrainingAppointmentForm);
	
	PrivateTrainingAppointmentForm updatePrivateTrainingAppointmentForm(PrivateTrainingAppointmentForm privateTrainingAppointmentForm);
	
	PrivateTrainingAppointmentForm findPrivateTrainingAppointmentFormByNo(Integer ptaNo);
	
	List<PrivateTrainingAppointmentForm> getAllPrivateTrainingAppointmentForms();
}