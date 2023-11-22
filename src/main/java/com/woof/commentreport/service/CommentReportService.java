package com.woof.commentreport.service;

import java.sql.Timestamp;
import java.util.List;

import com.woof.commentreport.entity.CommentReport;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;


public interface CommentReportService {

	int addCommentReport(PrivateTrainingAppointmentForm privateTrainingAppointmentForm,
			String crContext, Integer crStatus, Timestamp crDate);

	void updateCrStatus(Integer crNo , Integer crStatus);

	List<CommentReport> getAllCommentReports();
	
	List<CommentReport> getAllCRs(int currentPage);
	
	int getPageTotal();
	
	
	
	int deleteCommentReport(CommentReport commentReport);

	CommentReport findCommentReportByCrNo(Integer crNo);

}
