package com.woof.privatetrainingappointmentform.service;

import java.util.List;

import com.woof.member.entity.Member;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.trainer.entity.Trainer;

public interface PrivateTrainingAppointmentFormService {
	
	PrivateTrainingAppointmentForm addPrivateTrainingAppointmentForm(PrivateTrainingAppointmentForm privateTrainingAppointmentForm);

	
	PrivateTrainingAppointmentForm updatePrivateTrainingAppointmentForm(PrivateTrainingAppointmentForm privateTrainingAppointmentForm);
	
	PrivateTrainingAppointmentForm findPrivateTrainingAppointmentFormByPtaNo(Integer ptaNo);
	
	List<PrivateTrainingAppointmentForm> getAllPrivateTrainingAppointmentForms();

	int addPrivateTrainingAppointmentForm(Member member, Trainer trainer, Integer ptaClass);
}
