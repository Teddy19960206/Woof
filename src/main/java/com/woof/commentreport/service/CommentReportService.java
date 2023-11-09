package com.woof.commentreport.service;

import java.sql.Timestamp;
import java.util.List;

import com.woof.commentreport.entity.CommentReport;
import com.woof.member.entity.Member;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.trainer.entity.Trainer;

public interface CommentReportService {

	int addCommentReport(PrivateTrainingAppointmentForm privateTrainingAppointmentForm,
			String crContext, Integer crStatus, Timestamp crDate);

	int updateCommentReport(CommentReport commentReport);

	int deleteCommentReport(CommentReport commentReport);

	CommentReport findCommentReportByCrNo(Integer crNo);

	List<CommentReport> getAllCommentReports();
}
