package com.woof.commentreport.dao;

import java.util.List;

import com.woof.commentreport.entity.CommentReport;

public interface CommentReportDAO {
	
	int insert(CommentReport commentReport);
	
	int update(CommentReport commentReport);
	
	CommentReport findByCrNo(Integer crNo);
	
	List<CommentReport> getAll();
}
