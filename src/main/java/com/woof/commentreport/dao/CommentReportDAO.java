package com.woof.commentreport.dao;

import java.util.List;

import com.woof.commentreport.entity.CommentReport;

public interface CommentReportDAO {
	
	int insert(CommentReport commentReport);
	
	int update(CommentReport commentReport);

	List<CommentReport> getAll();
	
	List<CommentReport> getAll(int currentPage);
	
	long getTotal();
	
	int updateStatusByPta(Integer ptaNo , Integer crStatus);
	
	CommentReport findByCrNo(Integer crNo);
	
	
	
	int delete(CommentReport commentReport);

	
}
