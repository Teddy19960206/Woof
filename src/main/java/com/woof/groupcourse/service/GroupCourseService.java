package com.woof.groupcourse.service;

import java.util.List;

import com.woof.groupcourse.model.*;

public class GroupCourseService {
	private GroupCourseDAO groupCourseDAO;
	
	public GroupCourseService(){
		groupCourseDAO = new GroupCourseDAOImpl();
	}
	
	public GroupCourseVO addGroupCourse(Integer skillNo , Integer classType ,byte[] coursePhoto ,String courseContent , Integer courseStatus) {
		
		GroupCourseVO groupCourseVO = new GroupCourseVO();
		groupCourseVO.setSkillNo(skillNo);
		groupCourseVO.setClassType(classType);
		groupCourseVO.setCoursePhoto(coursePhoto);
		groupCourseVO.setCourseContent(courseContent);
		groupCourseVO.setCourseStatus(courseStatus);
		
		groupCourseDAO.insert(groupCourseVO);
		
		return groupCourseVO;
	}
	
	public void updateGroupCourse(Integer gcNo ,Integer skillNo , Integer classType ,byte[] coursePhoto ,String courseContent , Integer courseStatus) {
		
		GroupCourseVO groupCourseVO = new GroupCourseVO();
		
		groupCourseVO.setSkillNo(skillNo);
		groupCourseVO.setClassType(classType);
		groupCourseVO.setCoursePhoto(coursePhoto);
		groupCourseVO.setCourseContent(courseContent);
		groupCourseVO.setCourseStatus(courseStatus);
		
		groupCourseDAO.update(groupCourseVO);
	}
	
	public GroupCourseVO getOneGroupCourse(Integer gcNo) {
		return groupCourseDAO.findbyGcNo(gcNo);
	}
	
	public List<GroupCourseVO> getAll(){
		return groupCourseDAO.getAll();
	}
	
	
}
