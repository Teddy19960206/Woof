package com.woof.classorder.service;

import java.util.List;

import com.woof.appointmentdetail.entity.AppointmentDetail;

public interface AppointmentDetailService {
	AppointmentDetail addAppointmentDetail(AppointmentDetail appointmentDetail);
	AppointmentDetail updateAppointmentDetail(AppointmentDetail appointmentDetail);
	void deleteAppointmentDetail(Integer adNo);
	AppointmentDetail findAppointmentDetailByNo(Integer adNo);
	List<AppointmentDetail> getAllAppointmentDetails();
	
}
