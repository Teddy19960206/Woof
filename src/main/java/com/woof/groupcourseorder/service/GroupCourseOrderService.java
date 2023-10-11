package com.woof.groupcourseorder.service;

import com.woof.groupcourseorder.model.*;

public class GroupCourseOrderService {
	
	private GroupCourseOrderDAO groupCourseOrderDAO;
	
	public GroupCourseOrderService() {
		groupCourseOrderDAO = new GroupCourseOrderDAOImpl();
		
	}

}
