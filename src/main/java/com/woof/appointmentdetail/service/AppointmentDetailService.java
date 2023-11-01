package com.woof.appointmentdetail.service;

import java.sql.Timestamp;
import java.util.List;

import com.woof.appointmentdetail.entity.AppointmentDetail;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;

public interface AppointmentDetailService {
	AppointmentDetail findAdByAdNo(Integer adNo);
	
	int addAd(PrivateTrainingAppointmentForm pta , Timestamp appTime , Integer appStatus , String appVenue);
	
	int updateAd(Integer adNo , PrivateTrainingAppointmentForm pta , Timestamp appTime , Integer appStatus , String appVenue);
	
	List<AppointmentDetail> getAllAppointmentDetails();
	
	List<AppointmentDetail> getAds(int currentPage);
	
	int getPageTotal();
	
	List<AppointmentDetail> findAdByPtaNo(Integer ptaNo);
	
	List<AppointmentDetail> findAdByAppTime(Timestamp appTime);
	
}
