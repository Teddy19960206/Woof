package com.woof.appointmentdetail.dao;

import java.sql.Date;
import java.util.List;

import com.woof.appointmentdetail.entity.AppointmentDetail;

public interface AppointmentDetailDAO {

	int insert(AppointmentDetail appointmentDetail);
	
	int update(AppointmentDetail appointmentDetail);

	int delete(AppointmentDetail appointmentDetail);
	int addAll(List<AppointmentDetail> appointmentDetails);
	
	AppointmentDetail findByAdNo(Integer adNo);	
	
	List<AppointmentDetail> findByPtaNo(Integer ptaNo);	
	
	List<AppointmentDetail> findByAppTime(Date appTime);	
	
	List<AppointmentDetail> getAll();
	
	List<AppointmentDetail> getAll(int currentPage);
	
	long getTotal();
	
	long getTotalByPtaNo(Integer ptaNo);
}
