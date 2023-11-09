package com.woof.commentreport.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.commentreport.service.CommentReportService;
import com.woof.commentreport.service.CommentReportServiceImpl;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerService;
import com.woof.trainer.service.TrainerServiceImpl;
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
				addCommentReport(req, res);
				forwardPath = "/frontend/commentreport/commentReport_add.jsp";
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
    
    private void addCommentReport(HttpServletRequest req, HttpServletResponse res) {
    	
    	
			
    }
}
