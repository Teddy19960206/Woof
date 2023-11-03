package com.woof.appointmentdetail.service;

import java.sql.Date;
import java.util.List;

import com.woof.appointmentdetail.entity.AppointmentDetail;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;

public interface AppointmentDetailService {
	AppointmentDetail findAdByAdNo(Integer adNo);
	
	int addAd(PrivateTrainingAppointmentForm pta , Date appTime , Integer appStatus);
	
	int updateAd(Integer adNo , PrivateTrainingAppointmentForm pta , Date appTime , Integer appStatus);
	
	List<AppointmentDetail> getAllAppointmentDetails();
	
	List<AppointmentDetail> getAds(int currentPage);
	
	int getPageTotal();
	
	List<AppointmentDetail> findAdByPtaNo(Integer ptaNo);
	
	List<AppointmentDetail> findAdByAppTime(Date appTime);
	
}
