package com.woof.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.util.MailService;
@WebServlet("/resetPassword.do")
public class ResetpwdServlet extends HttpServlet{
	private MemberService memberService;

	@Override
	public void init() throws ServletException {
		memberService = new MemberServiceImpl();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 中文亂碼解決方法
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		//=================傳mail================================//
		if(action !=null) {
			switch(action){
			case "reset":
		MailService mailService = new MailService();
		Member member = new Member();
		String mememail = req.getParameter("memEmail");
		mailService.sendMail(mememail,"忘記密碼", MailService.passwordResetEmail(req.getRequestURL()+"?action=reset&member="+member));
		System.out.println(req.getRequestURL()+"22222");
					// 導到指定的URL 頁面上 把請求回應都帶過去
					String url = req.getContextPath() + "/frontend/member/login/login.jsp";
					req.setCharacterEncoding("UTF-8");
					res.sendRedirect(url);
			}
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		this.doPost(req, res);
	}
}
