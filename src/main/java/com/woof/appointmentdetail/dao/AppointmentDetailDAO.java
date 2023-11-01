package com.woof.appointmentdetail.dao;

import java.sql.Timestamp;
import java.util.List;

import com.woof.appointmentdetail.entity.AppointmentDetail;

public interface AppointmentDetailDAO {

	int insert(AppointmentDetail appointmentDetail);
	
	int update(AppointmentDetail appointmentDetail);
	
	AppointmentDetail findByAdNo(Integer adNo);	
	
	List<AppointmentDetail> findByPtaNo(Integer ptaNo);	
	
	List<AppointmentDetail> findByAppTime(Timestamp appTime);	
	
	List<AppointmentDetail> getAll();
	
	List<AppointmentDetail> getAll(int currentPage);
	
	long getTotal();
}
