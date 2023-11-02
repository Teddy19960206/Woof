package com.woof.appointmentdetail.controller;

import com.woof.appointmentdetail.entity.AppointmentDetail;
import com.woof.appointmentdetail.service.AppointmentDetailService;
import com.woof.appointmentdetail.service.AppointmentDetailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/appointmentdetail/*")
@MultipartConfig
public class AppointmentDetailServlet extends HttpServlet {
	
	private AppointmentDetailService appointmentDetailService;
	
	@Override
	public void init() throws ServletException{
		appointmentDetailService = new AppointmentDetailServiceImpl();
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		String forwardPath = "";
		if (action != null) {
			switch (action) {
			case "getdetail":
				getByPtaNo(req, resp);
				forwardPath = "/frontend/appointmentdetail/appointmentDetail_get.jsp";
				break;
			case "gettoadd":
				beforeAdd(req, resp);
				forwardPath = "/frontend/appointmentdetail/appointmentDetail_add.jsp";
				break;
			case "gettoupdate":
//				beforeUpdate(req, resp);
				forwardPath = "/frontend/appointmentdetail/appointmentDetail_update.jsp";
				break;
			default:
				forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp";
			}
		} else {
			forwardPath = "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp";
		}
		req.getRequestDispatcher(forwardPath).forward(req, resp);		
        resp.getWriter().println(action);
    }
	
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    
    private void getByPtaNo(HttpServletRequest req, HttpServletResponse res) {
    	
    	String ptaNoStr = req.getParameter("ptaNo");
    	Integer ptaNo = Integer.parseInt(ptaNoStr);
    	List<AppointmentDetail> appointmentDetails = appointmentDetailService.findAdByPtaNo(ptaNo);
    	req.setAttribute("appointmentDetails",appointmentDetails);
    	req.setAttribute("ptaNo",ptaNo);
    }
    
    private void beforeAdd(HttpServletRequest req, HttpServletResponse res) {
 
    	Integer ptaNo = Integer.parseInt(req.getParameter("ptaNo"));
    	req.setAttribute("ptaNo",ptaNo); 
    }
    
//    private void beforeUpdate(HttpServletRequest req, HttpServletResponse res) {
//    	
//    	Integer ptaNo = Integer.parseInt(req.getParameter("ptaNo"));
//    	req.setAttribute("ptaNo",ptaNo); 
//    }
    
    
}
