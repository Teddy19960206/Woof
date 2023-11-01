package com.woof.appointmentdetail.dao;

import java.util.List;

import com.woof.appointmentdetail.entity.AppointmentDetail;

public interface AppointmentDetailDAO {

	int insert(AppointmentDetail appointmentDetail);
	
	int update(AppointmentDetail appointmentDetail);
	
	AppointmentDetail findByAdNo(Integer adNo);	
	
	List<AppointmentDetail> findByAppTime(String appTime);	
	
	List<AppointmentDetail> getAll();
	
	List<AppointmentDetail> getAll(int currentPage);
	
	long getTotal();
}
