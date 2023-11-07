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
		admin.setAdminName (req.getParameter("ADMIN_NAME"));
		admin.setAdminGender(req.getParameter("ADMIN_GENDER"));
		admin.setAdminEmail (req.getParameter("ADMIN_EMAIL"));
		admin.setAdminPassword (req.getParameter("ADMIN_PASSWORD"));
		admin.setAdminTel (req.getParameter("ADMIN_TEL"));
		admin.setAdminAddress (req.getParameter("ADMIN_ADDRESS"));
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
		admin.setAdminStatus(Integer.valueOf(req.getParameter("ADMIN_STATUS")));
		administratorService.updateAdministrator(admin);
		//導到指定的URL 頁面上 把請求回應都帶過去
//		String url = "/frontend/administrator/administrator.jsp";
//		RequestDispatcher rd =  req.getRequestDispatcher(url);
//		rd.forward(req, res);
		String url = req.getContextPath()+"/frontend/administrator/administrator.jsp";
		res.sendRedirect(url);
	}

	private void processAdd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		Administrator admin = new Administrator();
		//把資料給前端
		admin.setAdminNo(req.getParameter("ADMIN_NO"));
		admin.setAdminName (req.getParameter("ADMIN_NAME"));
		admin.setAdminGender(req.getParameter("ADMIN_GENDER"));
		admin.setAdminEmail (req.getParameter("ADMIN_EMAIL"));
		admin.setAdminPassword (req.getParameter("ADMIN_PASSWORD"));
		admin.setAdminTel (req.getParameter("ADMIN_TEL"));
		admin.setAdminAddress (req.getParameter("ADMIN_ADDRESS"));
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
		admin.setAdminStatus(Integer.valueOf(req.getParameter("ADMIN_STATUS")));
		// 取得圖片 
		// 開串流
		Part p = req.getPart("ADMIN_PHOTO");
		InputStream input = p.getInputStream();
		byte[] photo = new byte[input.available()];
		input.read(photo);
		input.close();
		admin.setAdminPhoto(photo);
		
		
		administratorService.addAdministrator(admin);
		
		
		//導到指定的URL 頁面上 把請求回應都帶過去
		String url = req.getContextPath()+"/frontend/administrator/administrator.jsp";
		res.sendRedirect(url);
	}

}