package com.woof.appointmentdetail.dao;

import java.util.List;

import com.woof.appointmentdetail.entity.AppointmentDetail;

public interface AppointmentDetailJDBCDAO {

	void insert (AppointmentDetail appointmentDetailVO);
	void update (AppointmentDetail appointmentDetailVO);
	void delete(AppointmentDetail appointmentDetailVO);
	
	AppointmentDetail findByAdNo(Integer adNo);
	List<AppointmentDetail> getAll();
}