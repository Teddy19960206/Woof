package com.woof.appointmentdetail.controller;

import com.woof.appointmentdetail.entity.AppointmentDetail;
import com.woof.appointmentdetail.service.AppointmentDetailService;
import com.woof.appointmentdetail.service.AppointmentDetailServiceImpl;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormService;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			case "add":
				add(req, resp);
				return;
			case "gettoupdate":
				forwardPath = "/frontend/appointmentdetail/appoindmentDetail_update.jsp";
				break;
			case "update":
				update(req, resp);
				return;
			case "delete":
				delete(req, resp);
				return;
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
    
    private void add(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	
    	Integer ptaNo = Integer.parseInt(req.getParameter("ptaNo"));
    	PrivateTrainingAppointmentFormService ptaService =  new PrivateTrainingAppointmentFormServiceImpl();
    	PrivateTrainingAppointmentForm pta = ptaService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo);
    	
    	
    	String selectedDateStr = req.getParameter("date");
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(selectedDateStr);// 將字串轉換為 java.util.Date
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // 將 java.util.Date 轉換為 java.sql.Date
    	
		Integer appStatus = Integer.parseInt(req.getParameter("appStatus"));
		
		int result = appointmentDetailService.addAd(pta , sqlDate , appStatus);
		
		if (result == 1) {
			System.out.println("新增成功");
			req.setAttribute("successMessage", "新增成功");
		} else {
			System.out.println("新增失敗");
			req.setAttribute("errorMessage", "新增失敗");
		}
		res.sendRedirect(req.getContextPath() + "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp");
	}
    private void update(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	
    	Integer adNo = Integer.parseInt(req.getParameter("adNo"));
    	
    	Integer ptaNo = Integer.parseInt(req.getParameter("ptaNo"));
    	PrivateTrainingAppointmentFormService ptaService =  new PrivateTrainingAppointmentFormServiceImpl();
    	PrivateTrainingAppointmentForm pta = ptaService.findPrivateTrainingAppointmentFormByPtaNo(ptaNo);
    	
    	
    	String selectedDateStr = req.getParameter("date");
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	
    	java.util.Date parsedDate = null;
    	try {
    		parsedDate = dateFormat.parse(selectedDateStr);// 將字串轉換為 java.util.Date
    	} catch (ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} 
    	java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // 將 java.util.Date 轉換為 java.sql.Date
    	
    	Integer appStatus = Integer.parseInt(req.getParameter("appStatus"));
    	
    	int result = appointmentDetailService.updateAd(adNo , pta , sqlDate , appStatus);
    	
    	if (result == 1) {
    		System.out.println("更新成功");
    		req.setAttribute("successMessage", "更新成功");
    	} else {
    		System.out.println("更新失敗");
    		req.setAttribute("errorMessage", "更新失敗");
    	}
    	res.sendRedirect(req.getContextPath() + "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp");
    }
    
    private void delete(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	
    	Integer adNo = Integer.parseInt(req.getParameter("adNo"));
    	int result = appointmentDetailService.deleteAd(adNo);
    	if (result == 1) {
    		System.out.println("刪除成功");
    		req.setAttribute("successMessage", "刪除成功");
    	} else {
    		System.out.println("刪除失敗");
    		req.setAttribute("errorMessage", "刪除失敗");
    	}
    	res.sendRedirect(req.getContextPath() + "/frontend/privatetrainingappointmentform/privateTrainingAppointmentForm.jsp");

    }
}
