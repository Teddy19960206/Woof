package com.woof.appointmentdetail.service;

import java.sql.Date;
import java.util.List;

import com.woof.appointmentdetail.entity.AppointmentDetail;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;

public interface AppointmentDetailService {
	AppointmentDetail findAdByAdNo(Integer adNo);

	AppointmentDetail getOneByDate(Integer trainerNo , Date date);
	
	int addAd(PrivateTrainingAppointmentForm pta , Date appTime , Integer appStatus);
	
	int updateAd(Integer adNo , PrivateTrainingAppointmentForm pta , Date appTime , Integer appStatus);
	
	int deleteAd(Integer adNo);

	int addAll(PrivateTrainingAppointmentForm pta, List<Date> appTime, Integer appStatus);
	
	List<AppointmentDetail> getAllAppointmentDetails();
	
	List<AppointmentDetail> getAds(int currentPage);
	
	int getPageTotal();
	
	List<AppointmentDetail> findAdByPtaNo(Integer ptaNo);
	
	List<AppointmentDetail> findAdByAppTime(Date appTime);
	
	long getTotalByPtaNo(Integer ptaNo);
	
	List<AppointmentDetail> getAllUpdateStatus();
	
	void updateComplete(List<AppointmentDetail> list);
	
}
