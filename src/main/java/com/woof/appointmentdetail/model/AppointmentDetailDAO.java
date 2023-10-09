package com.woof.appointmentdetail.model;

import java.util.List;

public interface AppointmentDetailDAO {

	void insert (AppointmentDetailVO appointmentDetailVO);
	void update (AppointmentDetailVO appointmentDetailVO);
	void delete(AppointmentDetailVO appointmentDetailVO);
	
	AppointmentDetailVO findByAdNo(Integer adNo);
	List<AppointmentDetailVO> getAll();
}
