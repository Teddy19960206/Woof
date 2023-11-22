package com.woof.commentreport.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import com.woof.commentreport.dao.CommentReportDAO;
import com.woof.commentreport.dao.CommentReportDAOImpl;
import com.woof.commentreport.entity.CommentReport;
import com.woof.member.entity.Member;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.trainer.entity.Trainer;
import com.woof.util.HibernateUtil;

public class CommentReportServiceImpl implements CommentReportService{

	private CommentReportDAO dao;
	
	public CommentReportServiceImpl() {
		dao = new CommentReportDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public int addCommentReport(PrivateTrainingAppointmentForm privateTrainingAppointmentForm,
			String crContext, Integer crStatus, Timestamp crDate) {
		CommentReport commentReport = new CommentReport();
		commentReport.setPrivateTrainingAppointmentForm(privateTrainingAppointmentForm);
		commentReport.setCrContext(crContext);
		commentReport.setCrStatus(crStatus);
		commentReport.setCrDate(crDate);
		
		return dao.insert(commentReport);
	}

	@Override
	public void updateCrStatus(Integer crNo, Integer crStatus) {
		dao.findByCrNo(crNo).setCrStatus(crStatus);
	}

	@Override
	public int deleteCommentReport(CommentReport commentReport) {
		return dao.delete(commentReport);
	}

	@Override
	public CommentReport findCommentReportByCrNo(Integer crNo) {
		
		return dao.findByCrNo(crNo);
	}

	@Override
	public List<CommentReport> getAllCommentReports() {
		
		List<CommentReport> commentReportList = dao.getAll();

		return commentReportList;
	}

	@Override
	public List<CommentReport> getAllCRs(int currentPage) {
		return dao.getAll(currentPage);	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	@Override
	public void updateStatus(Integer ptaNo, Integer crStatus) {
		dao.updateStatusByPta(ptaNo , crStatus);
	}
	
}
