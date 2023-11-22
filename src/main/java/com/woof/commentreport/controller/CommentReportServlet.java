package com.woof.commentreport.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.commentreport.entity.CommentReport;
import com.woof.commentreport.service.CommentReportService;
import com.woof.commentreport.service.CommentReportServiceImpl;

import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormService;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormServiceImpl;


@WebServlet("/commentreport/*")
public class CommentReportServlet extends HttpServlet {

private CommentReportService commentReportService;
	
	@Override
	public void init() throws ServletException{
		commentReportService = new CommentReportServiceImpl();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		String forwardPath = "";
		if (action != null) {
			switch (action) {
			case "report":
				try {
					addCommentReport(req, res);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				forwardPath = "/frontend/commentreport/commentReport_add.jsp";
				return;
			case "getAll":
				getAll(req,res);
				forwardPath = "/backend/commentReport/commentReport.jsp";
				break;
			case "update":
				updateStatus(req,res);
				getAll(req,res);
				forwardPath = "/backend/commentReport/commentReport.jsp";
				break;
			case "updateStatus":
				updateStatus(req,res);
				getAll(req,res);
				forwardPath = "/backend/commentReport/commentReport.jsp";
				break;
			default:
				forwardPath = "/frontend/commentreport/commentReport.jsp";
			}
			req.getRequestDispatcher(forwardPath).forward(req, res);
			res.getWriter().println(action);
		}
	}
	
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doPost(req, res);
    }
    
    private void addCommentReport(HttpServletRequest req, HttpServletResponse res) throws ParseException, IOException {
    	
    	Integer ptaNo = Integer.valueOf(req.getParameter("ptano"));
    	PrivateTrainingAppointmentFormService privateTrainingAppointmentFormService = new PrivateTrainingAppointmentFormServiceImpl();
    	PrivateTrainingAppointmentForm pta = privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo);
    	String comment = req.getParameter("comment");
    	//  0:待處理 1:檢舉通過 2:檢舉未通過
    	Integer crStatus = 0;
    	
    	Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String crDateStr = dateFormat.format(now);
		Date parsedDate = dateFormat.parse(crDateStr);
		Timestamp crDate = new Timestamp(parsedDate.getTime());
		
		int result = commentReportService.addCommentReport(pta, comment, crStatus, crDate);
		if (result == 1) {
			System.out.println("檢舉成功");
//			req.setAttribute("successMessage", "檢舉成功");
		} else {
			System.out.println("檢舉失敗");
//			req.setAttribute("errorMessage", "檢舉失敗");
		}
		String message = (result == 1) ? "success" : "fail";
		res.sendRedirect(req.getContextPath() + "/trainer/getAll?result=" + message);
    }
    
    private void getAll(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
			int CRPageQty = commentReportService.getPageTotal();
			request.getSession().setAttribute("CRPageQty", CRPageQty);
		List<CommentReport> allCommentReports = commentReportService.getAllCRs(currentPage);


		request.setAttribute("commentReports", allCommentReports);
		request.setAttribute("currentPage", currentPage);

	}
    private void update(HttpServletRequest request, HttpServletResponse response) {
    	
    	Integer crNo = Integer.valueOf(request.getParameter("crNo"));
    	Integer crStatus = Integer.valueOf(request.getParameter("crStatus"));
    	commentReportService.updateCrStatus(crNo, crStatus);
    }
    
    private void updateStatus(HttpServletRequest request, HttpServletResponse response) {
    	String a = request.getParameter("crNo");
    	Integer crNo = Integer.valueOf(a);
    	Integer ptaNo = commentReportService.findCommentReportByCrNo(crNo)
    			.getPrivateTrainingAppointmentForm()
    			.getPtaNo();
    	Integer crStatus = Integer.valueOf(request.getParameter("crStatus"));
    	commentReportService.updateStatus(ptaNo, crStatus);
    }
    
    
}
