package com.woof.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormService;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormServiceImpl;

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
				deleteMember(req, res);
				return;
			case "query":
				processQuery(req, res);
				return;
			default:
				forwardPath = "/frontend/member/selectmember.jsp";
			}
			// 來自memberlogin.jsp的請求
			if ("memberlogin".equals(action)) {
				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				req.setAttribute("errorMsgs", errorMsgs);
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memberNo = req.getParameter("memberNo");

				if (memberNo == null || (memberNo.trim()).length() == 0) {
					errorMsgs.put("memberemail", "會員帳號:請勿空白");
				}

				String memberpassword = req.getParameter("memberpassword");

				if (memberpassword == null || (memberpassword.trim()).length() == 0) {
					errorMsgs.put("memberpassword", "會員密碼:請勿空白");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/memberlogin.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
			}
		}
		req.getRequestDispatcher(forwardPath).forward(req, res);
	}

	private void processQuery(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Member member = memberService.findMemberByNo(req.getParameter("memNo"));

		res.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

		String str = gson.toJson(member);
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
		member.setMomoPoint(Integer.valueOf(req.getParameter("momoPoint")));
		member.setTotalClass(Integer.valueOf(req.getParameter("totalClass")));
		member.setMemStatus(Integer.valueOf(req.getParameter("memStatus")));
		System.out.println(req.getParameter("memAddress") + "=============================");
		System.out.println(member.getMemAddress() + "----------------------------");

		memberService.addMember(member);
		// 導到指定的URL 頁面上 把請求回應都帶過去
		String url = req.getContextPath() + "/frontend/member/list_all_member.jsp";
		req.setCharacterEncoding("UTF-8");
		res.sendRedirect(url);
//		RequestDispatcher rd = req.getRequestDispatcher(url);
//		rd.forward(req, res);
	}

	private void updateMember(HttpServletRequest req, HttpServletResponse res)
			throws ParseException, IOException, ServletException {
		Member member = new Member();
//		req.setAttribute(getServletName(), member);

		
		member.setMemNo(req.getParameter("memNo"));
		member.setMemName(req.getParameter("memName"));
		member.setMemGender(req.getParameter("memGender"));
		member.setMemEmail(req.getParameter("memEmail"));
		member.setMemPassword(req.getParameter("memPassword"));
		member.setMemTel(req.getParameter("memTel"));
		member.setMemAddress(req.getParameter("memAddress"));
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
//		res.sendRedirect(req.getContextPath() + "/frontend/member/list_all_member.jsp");
		// 導到指定的URL 頁面上 把請求回應都帶過去
		System.out.println(req.getParameter("memNo") + "================");

//		req.setCharacterEncoding("UTF-8");
//		res.setCharacterEncoding("UTF-8");
//		req.setAttribute("memNo", req.getParameter("memNo"));
		String url = req.getContextPath() + "/frontend/member/list_all_member.jsp";
		res.sendRedirect(url);
//		res.setContentType("text/html; charset=UTF-8");
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/member/updatemember.jsp");
//		dispatcher.forward(req, res);
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
//		            req.setAttribute("error", "Invalid member number provided.");
//		            RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/member/errorPage.jsp");
//		            dispatcher.forward(req, res);
//		            return; // 由於已經進行了轉發，所以返回以終止後續的代碼執行
		        }

		    } else {
		        req.setAttribute("error", "Invalid member number provided.");
		        RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/member/errorPage.jsp");
		        dispatcher.forward(req, res);
		        return;
		    }
		    // 設置編碼和轉發到指定的JSP頁面
		    req.setCharacterEncoding("UTF-8");
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/member/list_one_member.jsp");
		    dispatcher.forward(req, res);

		} catch (Exception e) {
		    // 處理其他潛在錯誤
		    e.printStackTrace();

		}
	}

	private void deleteMember(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getParameter("memNo");
		memberService.deleteMember(req.getParameter("memNo"));
		// 導到指定的URL 頁面上 把請求回應都帶過去
		String url = req.getContextPath() + "/frontend/member/list_all_member.jsp";
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
