package com.woof.privatetrainingappointmentform.service;

import java.sql.Timestamp;
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
	
	int insertComment(Integer ptaNo, Member member, Trainer trainer,
			Integer ptaClass, String ptaComment, Timestamp commentTime);
	
	int updateComment(Integer ptaNo, Member member, Trainer trainer,
			Integer ptaClass, String ptaComment,Timestamp commentTime, Timestamp commentUpTime);
	
	int deletePrivateTrainingAppointmentForm(Integer ptaNo);
	
	List<PrivateTrainingAppointmentForm> getAllPTAFs(int currentPage);

	int getPageTotal();
	
	List<PrivateTrainingAppointmentForm> findPrivateTrainingAppointmentFormByMemNo(String memNo , int currentPage);
	
	int getPageTotal2(String memNo);
	
	List<PrivateTrainingAppointmentForm> findPrivateTrainingAppointmentFormByTrainerNo(Integer trainerNo , int currentPage);
	
	int getPageTotal3(Integer trainerNo);
	
	List<PrivateTrainingAppointmentForm> getPTAByMemberAndTrainer(String memNo , Integer trainerNo , int currentPage);
	
	int getPageTotal(String memNo,Integer trainerNo);
	
}
