package com.woof.appointmentdetail.dao;

import java.util.List;
import java.util.Map;

import com.woof.appointmentdetail.entity.AppointmentDetail;

public interface AppointmentDetailDAO {

	int insert(AppointmentDetail appointmentDetail);
	
	int update(AppointmentDetail appointmentDetail);
	
//	int delete(Integer adNo);
	
	AppointmentDetail findByAdNo(Integer adNo);
	
	List<AppointmentDetail> getAll();
}
