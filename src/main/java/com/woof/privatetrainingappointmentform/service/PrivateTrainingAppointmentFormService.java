package com.woof.privatetrainingappointmentform.service;

import java.util.List;

import com.woof.member.entity.Member;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.trainer.entity.Trainer;

public interface PrivateTrainingAppointmentFormService {
	
	PrivateTrainingAppointmentForm findPrivateTrainingAppointmentFormByPtaNo(Integer ptaNo);
	
	List<PrivateTrainingAppointmentForm> getAllPrivateTrainingAppointmentForms();

	int addPrivateTrainingAppointmentForm(Member member, Trainer trainer, Integer ptaClass);

	int updatePrivateTrainingAppointmentForm(Integer ptaNo, Member member, Trainer trainer,
			Integer ptaClass);
	
	int deletePrivateTrainingAppointmentForm(Integer ptaNo);
	
	List<PrivateTrainingAppointmentForm> getAllPTAFs(int currentPage);

	int getPageTotal();
	
	List<PrivateTrainingAppointmentForm> findPrivateTrainingAppointmentFormByMemNo(String memNo , int currentPage);
	
	int getPageTotal2(String memNo);
	
	List<PrivateTrainingAppointmentForm> findPrivateTrainingAppointmentFormByTrainerNo(Integer trainerNo , int currentPage);
	
	int getPageTotal3(Integer trainerNo);
}
