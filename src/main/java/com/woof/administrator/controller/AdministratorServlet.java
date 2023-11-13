package com.woof.administrator.controller;

import com.woof.administrator.service.AdministratorService;
import com.woof.administrator.service.AdministratorServiceImpl;
import com.woof.administrator.service.AdministratorService;
import com.woof.administrator.service.AdministratorServiceImpl;
import com.alibaba.fastjson2.JSONObject;
import com.google.gson.JsonObject;
import com.woof.administrator.entity.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/administrator.do")
@MultipartConfig
public class AdministratorServlet extends HttpServlet {

	private AdministratorService administratorService;

	@Override
	public void init() throws ServletException {
		administratorService = new AdministratorServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//前端的action對應到哪個 執行哪個
		switch (action) {
		case "add":
			processAdd(req, res);
			break;
			
		case "update":
			processUpdate(req, res);
			break;
		case "update2":
			processUpdate2(req, res);
			break;
		case "del":
			processDelete(req, res);
			break;
		case "query":
			processQuery(req, res);
	
		default:
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	private void processQuery(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		//找對應的aminno
		Administrator admin = administratorService.findAdministratorByAdminNo((req.getParameter("ADMIN_NO")));
		
		res.setCharacterEncoding("UTF-8");
		//用json把值裝起來
		String str = JSONObject.toJSONString(admin);
		//回應給前端
		PrintWriter out = res.getWriter();
		out.write(str);
	}
	
	private void processDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		administratorService.deleteAdministrator(req.getParameter("ADMIN_NO"));
	}
	
	private void processUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		Administrator admin = new Administrator();
		//把資料給前端
		admin.setAdminNo (req.getParameter("ADMIN_NO"));
		System.out.println(admin);
		admin.setAdminName (req.getParameter("ADMIN_NAME"));
		admin.setAdminGender(req.getParameter("ADMIN_GENDER"));
		admin.setAdminEmail (req.getParameter("ADMIN_EMAIL"));
		admin.setAdminPassword (req.getParameter("ADMIN_PASSWORD"));
		admin.setAdminTel (req.getParameter("ADMIN_TEL"));
		admin.setAdminAddress (req.getParameter("ADMIN_ADDRESS"));
		Date date1 = null ;
		try {
			date1 = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("ADMIN_BD"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
		admin.setAdminBd(sqlDate1);
		admin.setEmergencyContactName(req.getParameter("EMERGENCY_CONTACTNAME"));
		admin.setEmergencyContactel(req.getParameter("EMERGENCY_CONTACTEL"));
		//java.sql的日期寫法
		Date date = null ;
		try {
			date = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("ADMIN_HD"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		admin.setAdminHd(sqlDate);
		
		Date date2 = null ;
		try {
			date2 = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("ADMIN_RD"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
		admin.setAdminRd(sqlDate2);
		admin.setAdminStatus(Integer.valueOf(req.getParameter("ADMIN_STATUS")));
		admin.setAdminVerifyStatus(Integer.valueOf(req.getParameter("ADMIN_VERIFY_STATUS")));
		admin.setAdminFuncName(Integer.valueOf(req.getParameter("ADMIN_FUNC_NAME")));
		administratorService.updateAdministrator(admin);
		//導到指定的URL 頁面上 把請求回應都帶過去
//		String url = "/frontend/administrator/administrator.jsp";
//		RequestDispatcher rd =  req.getRequestDispatcher(url);
//		rd.forward(req, res);
		String url = req.getContextPath()+"/frontend/administrator/administrator.jsp";
		res.sendRedirect(url);
	}
private void processUpdate2(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		Administrator admin = new Administrator();
		//把資料給前端
		admin.setAdminNo (req.getParameter("ADMIN_NO"));
		admin.setAdminName (req.getParameter("ADMIN_NAME"));
		
		admin.setAdminGender(req.getParameter("ADMIN_GENDER"));
		admin.setAdminEmail (req.getParameter("ADMIN_EMAIL"));
		admin.setAdminPassword (req.getParameter("ADMIN_PASSWORD"));
		admin.setAdminTel (req.getParameter("ADMIN_TEL"));
		admin.setAdminAddress (req.getParameter("ADMIN_ADDRESS"));
		Date date4 = null ;
		try {
			date4 = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("ADMIN_BD"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate4 = new java.sql.Date(date4.getTime());
		admin.setAdminBd(sqlDate4);
		admin.setEmergencyContactName(req.getParameter("EMERGENCY_CONTACTNAME"));
		admin.setEmergencyContactel(req.getParameter("EMERGENCY_CONTACTEL"));
		System.out.println(admin);

		administratorService.updateAdministrator2(admin);
		//導到指定的URL 頁面上 把請求回應都帶過去

//		String url = "/frontend/administrator/administratorcenter.jsp";
//		RequestDispatcher rd =  req.getRequestDispatcher(url);
//		rd.forward(req, res);
		String url = req.getContextPath()+"/frontend/administrator/administratorcenter.jsp";
		res.sendRedirect(url);
	}

	private void processAdd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		List<Administrator> administrators = administratorService.getAllAdministrators();
//		
		Administrator admin = new Administrator();
		String adminemail = req.getParameter("adminEmail");
		String adminNo = req.getParameter("adminNo");
//		
//		
		for(Administrator admin1 :  administrators) {
			System.out.println("---------");
			System.out.println(admin1.getAdminEmail());
			System.out.println(adminemail);
			System.out.println(admin1.getAdminEmail().equals(adminemail));
			if(admin1.getAdminNo().equals(adminNo)) {
				errorMsgs.put("adminNo","此帳號已註冊，請重新輸入");
			}
			if(admin1.getAdminEmail().equals(adminemail)) {
				errorMsgs.put("adminEmail", "不能使用該信箱");
			}
			if(!errorMsgs.isEmpty()) {
				break;
			}
		}
		
		if(!errorMsgs.isEmpty()) {
			
			req.getRequestDispatcher("frontend/administrator/administratorAdd.jsp").forward(req, res);
		    return;
		}
		/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		if (adminNo == null || adminNo.trim().length() == 0) {
			errorMsgs.put("adminNo","管理員帳號請勿空白");
		}
		
		String adminname = req.getParameter("ADMIN_NAME");
		String adminnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
		if (adminname == null || adminname.trim().length() == 0) {
			errorMsgs.put("ADMIN_NAME","管理員姓名: 請勿空白");
		} else if(!adminname.trim().matches(adminnameReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("ADMIN_NAME","管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在1到50之間");
        }
		
		String admingender = req.getParameter("ADMIN_GENDER").trim();
		if (admingender == null || admingender.trim().length() == 0) {
			errorMsgs.put("ADMIN_GENDER","管理員性別請勿空白");
		}
		
		if (adminemail == null || adminemail.trim().length() == 0) {
			errorMsgs.put("adminEmail","管理員信箱請勿空白");
		}
		
		String adminpwd = req.getParameter("ADMIN_PASSWORD").trim();
		if (adminpwd == null || adminpwd.trim().length() == 0) {
			errorMsgs.put("ADMIN_PASSWORD","管理員密碼請勿空白");
		}
		  String admintel1 = "^09\\d{8}$";
		String admintel = req.getParameter("ADMIN_TEL").trim();
		if (admintel == null || admintel.trim().length() == 0) {
			errorMsgs.put("ADMIN_TEL","管理員電話請勿空白");
		} else if(!admintel.trim().matches(admintel1)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("ADMIN_TEL","管理員電話: 只能是09開頭且總長度為10");
        }
		
		String adminaddress = req.getParameter("ADMIN_ADDRESS").trim();
		if (adminaddress == null || adminaddress.trim().length() == 0) {
			errorMsgs.put("ADMIN_ADDRESS","管理員地址請勿空白");
		}
		String emergencyContactName = req.getParameter("EMERGENCY_CONTACTNAME");
		String emergencyContactNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
		if (adminname == null || adminname.trim().length() == 0) {
			errorMsgs.put("EMERGENCY_CONTACTNAME","緊急聯絡人: 請勿空白");
		} else if(!adminname.trim().matches(adminnameReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("EMERGENCY_CONTACTNAME","緊急聯絡人姓名: 只能是中、英文字母、數字和_ , 且長度必需在1到50之間");
        }
		String emergencyContactel1 = "^09\\d{8}$";
		String emergencyContactel = req.getParameter("EMERGENCY_CONTACTEL").trim();
		if (emergencyContactel == null || emergencyContactel.trim().length() == 0) {
			errorMsgs.put("EMERGENCY_CONTACTEL","緊急聯絡人電話請勿空白");
		} else if(!emergencyContactel.trim().matches(emergencyContactel1)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("EMERGENCY_CONTACTEL","緊急聯絡人電話: 只能是09開頭且總長度為10");
        }

//		 Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()){
			req.getRequestDispatcher("frontend/administrator/administratorAdd.jsp").forward(req, res);
	    return;
		}
		
		
		//把資料給前端
		admin.setAdminNo(adminNo);
		admin.setAdminName (req.getParameter("ADMIN_NAME"));
		admin.setAdminGender(req.getParameter("ADMIN_GENDER"));
		admin.setAdminEmail (adminemail);
		admin.setAdminPassword (req.getParameter("ADMIN_PASSWORD"));
		admin.setAdminTel (req.getParameter("ADMIN_TEL"));
		admin.setAdminAddress (req.getParameter("ADMIN_ADDRESS"));
		Date date1 = null ;
		try {
			date1 = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("ADMIN_BD"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
		admin.setAdminBd(sqlDate1);
		admin.setEmergencyContactName(req.getParameter("EMERGENCY_CONTACTNAME"));
		admin.setEmergencyContactel(req.getParameter("EMERGENCY_CONTACTEL"));
		//java.sql的日期寫法
		Date date = null ;
		try {
			date = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("ADMIN_HD"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		admin.setAdminHd(sqlDate);
		Date date2 = null ;
		try {
			date2 = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("ADMIN_RD"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
		admin.setAdminRd(sqlDate2);
		admin.setAdminStatus(Integer.valueOf(req.getParameter("ADMIN_STATUS")));
		// 取得圖片 
		// 開串流
		Part p = req.getPart("ADMIN_PHOTO");
		InputStream input = p.getInputStream();
		byte[] photo = new byte[input.available()];
		input.read(photo);
		input.close();
		admin.setAdminPhoto(photo);
		admin.setAdminFuncName(Integer.valueOf(req.getParameter("ADMIN_FUNC_NAME")));
		
		administratorService.addAdministrator(admin);
		
	
		//導到指定的URL 頁面上 把請求回應都帶過去
		String url = req.getContextPath()+"/frontend/administrator/addSuccessfully.jsp";
		res.sendRedirect(url);
	}

}