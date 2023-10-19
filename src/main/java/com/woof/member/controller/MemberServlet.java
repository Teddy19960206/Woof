package com.woof.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {

	private MemberService memberService;

	@Override
	public void init() throws ServletException {
		memberService = new MemberServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String memberType = req.getParameter("Member");

		res.getWriter().println(memberType);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		super.doGet(req, res);
//		PrintWriter writer = res.getWriter();
//		System.out.println("123");
//		System.out.println(memberService.getAllMembers());
		doPost(req,res);
	}
}
//