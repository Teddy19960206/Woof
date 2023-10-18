package com.woof.latestnews.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.woof.latestnews.service.LatestNewsService;
import com.woof.latestnews.service.LatestNewsServiceImpl;

@WebServlet("/latestNews")
public class LatestNewsServlet extends HttpServlet {

	private LatestNewsService latestNewsService;

	@Override
	public void init() throws ServletException {
		latestNewsService = new LatestNewsServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String latestnewsType = req.getParameter("LatestNews");

		res.getWriter().println(latestnewsType);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		super.doGet(req, res);
//		PrintWriter writer = res.getWriter();
//		System.out.println("123");
//		System.out.println(latestNewsService.getAllLatestNews());
		doPost(req,res);
	}
}

