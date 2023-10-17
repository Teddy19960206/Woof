package com.woof.commentreport.service;

import java.util.List;

import com.woof.commentreport.entity.CommentReport;

public interface CommentReportService {

	CommentReport addCommentReport(CommentReport commentReport);
	
	CommentReport updateCommentReport(CommentReport commentReport);

	CommentReport findCommentReportByCrNo(Integer crNo);
	
	List<CommentReport> getAllCommentReports();
}
