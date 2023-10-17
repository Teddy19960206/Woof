package com.woof.commentreport.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.commentreport.service.CommentReportService;
import com.woof.commentreport.service.CommentReportServiceImpl;


@WebServlet("/commentreport")
public class CommentReportServlet extends HttpServlet {

private CommentReportService commentReportService;
	
	@Override
	public void init() throws ServletException{
		commentReportService = new CommentReportServiceImpl();
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String appointmentdetail = req.getParameter("Type");

        resp.getWriter().println(appointmentdetail);
    }
	
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        //PrintWriter writer = resp.getWriter();
        //System.out.println("123");
        System.out.println(commentReportService.getAllCommentReports());
        
    }
}
