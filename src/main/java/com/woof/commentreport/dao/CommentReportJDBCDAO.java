package com.woof.commentreport.dao;

import java.util.List;

import com.woof.commentreport.entity.CommentReport;

public interface CommentReportJDBCDAO {
	
	void insert (CommentReport commentReportVO);
	void update (CommentReport commentReportVO);
	void delete (CommentReport commentReportVO);
	
	CommentReport findByCrNo(Integer crNo);
	List<CommentReport> getAll();

}