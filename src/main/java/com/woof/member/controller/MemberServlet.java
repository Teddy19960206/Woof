package com.woof.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.util.MailService;

@WebServlet("/member.do")
@MultipartConfig
//一個 servlet 實體對應一個 service 實體
public class MemberServlet extends HttpServlet {

	private MemberService memberService;

	@Override
	public void init() throws ServletException {
		memberService = new MemberServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		String forwardPath = "";
		if (action != null) {
			switch (action) {
			case "add":
//            正式增加Member資料
				try {
					addMember(req, res);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return;
			case "update":
//           正式修改資料
				try {
					updateMember(req, res);
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();

				}
				return;
			case "getall":
				forwardPath = getAllmembers(req, res);
				break;
			case "getone":
				getOne(req, res);
				return;
			case "delete":
				delete(req, res);
				return;
			case "query":
				processQuery(req, res);
				return;
				
			default:
				forwardPath = "/backend/member/selectmember.jsp";
			}
		}
		req.getRequestDispatcher(forwardPath).forward(req, res);
	}
	private void processQuery(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Member member = memberService.findMemberByNo(req.getParameter("memNo"));
		byte[]photoByte = memberService.getPhotoById(req.getParameter("memNo"));

		res.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd")
				.create();

		String str = gson.toJson(member);
		// 將 JSON 字符串轉換為 UTF-8 編碼的字節數組
		byte[] photoBytes = str.getBytes(StandardCharsets.UTF_8);
//		System.out.println(str);
		// 回應給前端
		PrintWriter out = res.getWriter();
		out.write(str);
	}

	private void addMember(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ParseException, ServletException {
		
		Member member = new Member();
		// 把資料給前端
		member.setMemNo(req.getParameter("memNo"));
		member.setMemName(req.getParameter("memName"));
		member.setMemGender(req.getParameter("memGender"));
		member.setMemEmail(req.getParameter("memEmail"));
		member.setMemPassword(req.getParameter("memPassword"));
		member.setMemTel(req.getParameter("memTel"));
		member.setMemAddress(req.getParameter("memAddress"));
		// java.sql的日期寫法
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("memBd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		member.setMemBd(sqlDate);
		//	插入圖片
		  Part p = req.getPart("memPhoto");
		  InputStream input = p.getInputStream();
		  byte[] photo = new byte[input.available()];
		  input.read(photo);
		  input.close();
		  member.setMemPhoto(photo);
        
		member.setMomoPoint(Integer.valueOf(req.getParameter("momoPoint")));
		member.setTotalClass(Integer.valueOf(req.getParameter("totalClass")));
		member.setMemStatus(Integer.valueOf(req.getParameter("memStatus")));
		System.out.println(req.getParameter("memAddress") + "=============================");
		System.out.println(member.getMemAddress() + "----------------------------");
		System.out.println(req.getParameter("memEmail") + "=============================");
		System.out.println(req.getParameter("memPhoto")+"1111111");
		
		try {
			memberService.addMember(member);
//			MailService mailService = new MailService();
//			mailService.sendMail(to, subject, MailService.valid(req.getRequestURL()+"?action=valid&member="+member));
			System.out.println(req.getRequestURL()+"11111");
			// 導到指定的URL 頁面上 把請求回應都帶過去
			String url = req.getContextPath() + "/backend/member/list_all_member.jsp";
			req.setCharacterEncoding("UTF-8");
			res.sendRedirect(url);
		} catch (Exception e) {
			if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				// Handle the exception
				e.printStackTrace(); // This is for logging purpose
				String errorMsg = "Email already exists! Please use another email.";
				req.setAttribute("errorMessage", errorMsg);
				req.getRequestDispatcher("/backend/member/errorPage.jsp").forward(req, res);
			} else {
				// Handle other exceptions if necessary
				throw e; // or redirect to a general error page
			}
		}
	}

	private void updateMember(HttpServletRequest req, HttpServletResponse res)
			throws ParseException, IOException, ServletException {
		Member member = new Member();
		member.setMemNo(req.getParameter("memNo"));
		member.setMemName(req.getParameter("memName"));
		member.setMemGender(req.getParameter("memGender"));
		member.setMemEmail(req.getParameter("memEmail"));
		member.setMemPassword(req.getParameter("memPassword"));
		member.setMemTel(req.getParameter("memTel"));
		member.setMemAddress(req.getParameter("memAddress"));
		//	插入圖片
		  Part p = req.getPart("memPhoto");
		  InputStream input = p.getInputStream();
		  byte[] photo = new byte[input.available()];
		  input.read(photo);
		  input.close();
		  member.setMemPhoto(photo);
        //生日
		String memBdString = req.getParameter("memBd");

		if (memBdString != null && !memBdString.isEmpty()) {
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(memBdString);
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				member.setMemBd(sqlDate);
			} catch (ParseException e) {
				e.printStackTrace();
				req.setAttribute("dateError", "The provided date is invalid.");
			}
		} else {
			req.setAttribute("memBd=null", memBdString);
		}
		member.setMomoPoint(Integer.valueOf(req.getParameter("momoPoint")));
		member.setTotalClass(Integer.valueOf(req.getParameter("totalClass")));
		member.setMemStatus(Integer.valueOf(req.getParameter("memStatus")));
		memberService.updateMember(member);
		// 導到指定的URL 頁面上 把請求回應都帶過去
		System.out.println(req.getParameter("memNo") + "================");
		String url = req.getContextPath() + "/backend/member/list_all_member.jsp";
		res.sendRedirect(url);
	}

	private void getOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		MemberService memberService = new MemberServiceImpl();

		try {

			req.setCharacterEncoding("UTF-8");
			// 獲取所有成員
//			List<Member> allMembers = memberService.getAllMembers();
//			req.setAttribute("Members", allMembers);

			// 獲取特定成員
			String memNoStr = req.getParameter("memNo");

			if (memNoStr != null && !memNoStr.trim().isEmpty()) {
				try {

					Member member = memberService.findMemberByNo(memNoStr);
					req.setAttribute("member", member);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}

			} else {
				req.setAttribute("error", "Invalid member number provided.");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/member/errorPage.jsp");
				dispatcher.forward(req, res);
				return;
			}
			// 設置編碼和轉發到指定的JSP頁面
			req.setCharacterEncoding("UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/member/list_one_member.jsp");
			dispatcher.forward(req, res);

		} catch (Exception e) {
			// 處理其他潛在錯誤
			e.printStackTrace();

		}
	}

	private void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getParameter("memNo");
		memberService.deletePhoto(req.getParameter("memNo"));
		// 導到指定的URL 頁面上 把請求回應都帶過去
		String url = req.getContextPath() + "/backend/member/list_all_member.jsp";
		req.setCharacterEncoding("UTF-8");
		res.sendRedirect(url);
	}

	private String getAllmembers(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8");

		String member = req.getParameter("member");

		List<Member> memberList = null;

		if (member != null) {
			if ("0".equals(member)) {
				memberList = memberService.getAllMembers();
			}
		} else {
//	            異常判斷
		}

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(memberList);
		res.setContentType("application/json;charset=UTF-8");

		res.getWriter().write(json);
		return json;
	}
}
