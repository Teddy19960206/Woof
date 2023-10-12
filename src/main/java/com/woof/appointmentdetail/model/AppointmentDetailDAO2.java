package com.woof.appointmentdetail.model;

import java.util.List;
import java.util.Map;

public interface AppointmentDetailDAO2 {

	int insert(AppointmentDetailVO entity);
	
	int update(AppointmentDetailVO entity);
	
	int delete(Integer adNo);
	
	AppointmentDetailVO getByAdNo(Integer adNo);
	
	List<AppointmentDetailVO> getAll();
	
	List<AppointmentDetailVO> getByCompositeQuery(Map<String, String> map);
	
	long getTotal();
}
