package com.woof.commentreport.service;

import java.sql.Timestamp;
import java.util.List;

import com.woof.commentreport.entity.CommentReport;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;


public interface CommentReportService {

	int addCommentReport(PrivateTrainingAppointmentForm privateTrainingAppointmentForm,
			String crContext, Integer crStatus, Timestamp crDate);

	int updateCommentReport(CommentReport commentReport);

	int deleteCommentReport(CommentReport commentReport);

	CommentReport findCommentReportByCrNo(Integer crNo);

	List<CommentReport> getAllCommentReports();
}
