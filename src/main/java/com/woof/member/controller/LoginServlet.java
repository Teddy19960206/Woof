package com.woof.member.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private MemberService memberService;

	@Override
	public void init() throws ServletException {
		memberService = new MemberServiceImpl();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 中文亂碼解決方法
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		// =======================登出============================//
		
		if (action != null) {
			switch (action) {
			case "memberlogout":
				
			HttpSession session = req.getSession();

			session.removeAttribute("user");
			res.sendRedirect(req.getContextPath() + "/frontend/member/login/login.jsp");
			System.out.println(session.getId() + "刪除");
			String user = (String) session.getAttribute("user");
			if (user == null) {
				System.out.println("User is not in session.");
			}
			return;
			// =================登入===============================//
			case "memberlogin":
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			 // 從請求中獲取會員編號和密碼
		    String memNoStr = req.getParameter("memNo");
		    String memPassword = req.getParameter("memPassword");

		    try {
		        // 驗證帳號和密碼不為空
		        if (memNoStr != null && !memNoStr.trim().isEmpty() && memPassword != null && !memPassword.trim().isEmpty()) {
		            // 使用memberService根據會員編號查找會員
		            Member member = memberService.findMemberByNo(memNoStr);

		            // 檢查會員是否存在以及密碼是否匹配
		            if (member != null && member.getMemPassword().equals(memPassword)) {
		                // 登入成功，將會員信息設置到session中
		                HttpSession session1 = req.getSession();
		                session1.setAttribute("member", member);
		                
		                // 這裡調用service來獲取點數
		                Integer momopoints = memberService.getMemberPoints(member.getMemNo());
		                // 將點數添加到session中
		                session1.setAttribute("momoPoints", momopoints);
		                

		                // 轉發到登入成功頁面或者其他操作
		                res.sendRedirect(req.getContextPath() + "/frontend/member/login/welcome.jsp");
		            } else {
		                // 登入失敗，設置錯誤信息並轉發到登入頁面
		                req.setAttribute("loginError", "帳號或密碼不正確");
		                RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/member/login/login.jsp");
		                dispatcher.forward(req, res);
		            }
		        } else {
		            // 帳號或密碼為空，設置錯誤信息並轉發到登入頁面
		            req.setAttribute("loginError", "帳號和密碼都不能為空");
		            RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/member/login/login.jsp");
		            dispatcher.forward(req, res);
		        }
		    } catch (Exception e) {
		        // 處理其他潛在錯誤
		        e.printStackTrace();
		        req.setAttribute("error", "系統錯誤，請聯絡系統管理員。");
		        RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/member/login/errorPage.jsp");
		        dispatcher.forward(req, res);
		    }
			}
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}
}
