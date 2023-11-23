package com.woof.privatetrainingappointmentform.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.woof.appointmentdetail.entity.AppointmentDetailDTO;
import com.woof.member.entity.Member;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.trainer.entity.Trainer;

public interface PrivateTrainingAppointmentFormService {
	
	PrivateTrainingAppointmentForm findPrivateTrainingAppointmentFormByPtaNo(Integer ptaNo);
	
	List<PrivateTrainingAppointmentForm> getAllPrivateTrainingAppointmentForms();

	PrivateTrainingAppointmentForm addPrivateTrainingAppointmentForm(Member member, Trainer trainer, Integer ptaClass);

	int updatePrivateTrainingAppointmentForm(Integer ptaNo, Member member, Trainer trainer,
			Integer ptaClass, String ptaComment,Timestamp commentTime, Timestamp commentUpTime);
	
	int deletePrivateTrainingAppointmentForm(Integer ptaNo);
	
	List<PrivateTrainingAppointmentForm> getAllPTAFs(int currentPage);

	int getPageTotal();
	
//	List<PrivateTrainingAppointmentForm> findPrivateTrainingAppointmentFormByMemNo(String memNo , int currentPage);
	
//	int getPageTotal2(String memNo);
	
	List<PrivateTrainingAppointmentForm> findPrivateTrainingAppointmentFormByTrainerNo(Integer trainerNo , int currentPage);
	
	int getPageTotal3(Integer trainerNo);

	List<Date> getByTrainer(Integer year , Integer month , Integer trainerNo);
	
	List<PrivateTrainingAppointmentForm> findPrivateTrainingAppointmentFormByMemNo(String memNo , int currentPage);
	
	int getPageTotal4(String memNo);
	
	List<PrivateTrainingAppointmentForm> findByBoth(String memNo , Integer trainerNo , int currentPage);
	
	int getPageTotal5(String memNo , Integer trainerNo);

	List<PrivateTrainingAppointmentForm> getAppointmentByMemNo(String memNo);
	
	List<PrivateTrainingAppointmentForm> getNonReportedComment(Integer trainerNo);
	
	boolean canComment(Integer ptaNo);
}
