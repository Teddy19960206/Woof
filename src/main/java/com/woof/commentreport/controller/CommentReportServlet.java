package com.woof.commentreport.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
    
    private void addCommentReport(HttpServletRequest req, HttpServletResponse res) throws ParseException {
    	
    	Integer ptaNo = Integer.valueOf(req.getParameter("ptano"));
    	PrivateTrainingAppointmentFormService privateTrainingAppointmentFormService = new PrivateTrainingAppointmentFormServiceImpl();
    	PrivateTrainingAppointmentForm pta = privateTrainingAppointmentFormService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo);
    	String comment = req.getParameter("comment");
    	Integer crStatus = 0;
    	
    	Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String crDateStr = dateFormat.format(now);
		Date parsedDate = dateFormat.parse(crDateStr);
		Timestamp crDate = new Timestamp(parsedDate.getTime());
		
		int result = commentReportService.addCommentReport(pta, comment, crStatus, crDate);
		if (result == 1) {
			System.out.println("檢舉成功");
			req.setAttribute("successMessage", "檢舉成功");
		} else {
			System.out.println("檢舉失敗");
			req.setAttribute("errorMessage", "檢舉失敗");
		}	
    }
}
