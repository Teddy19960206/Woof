package com.woof.commentreport.model;

import java.util.List;

public interface CommentReportDAO {
	
	void insert (CommentReportVO commentReportVO);
	void update (CommentReportVO commentReportVO);
	void delete (CommentReportVO commentReportVO);
	
	CommentReportVO findByCrNo(Integer crNo);
	List<CommentReportVO> getAll();

}