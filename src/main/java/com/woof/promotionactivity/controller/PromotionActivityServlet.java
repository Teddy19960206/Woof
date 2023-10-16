package com.woof.promotionactivity.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.promotionactivity.service.PromotionActivityService;
import com.woof.promotionactivity.service.PromotionActivityServiceImpl;

@WebServlet("/promotionactivity")
public class PromotionActivityServlet extends HttpServlet {

	private PromotionActivityService promotionActivityService;

	@Override
	public void init() throws ServletException {
		promotionActivityService = new PromotionActivityServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String promotionactivity = req.getParameter("Type");

		res.getWriter().println(promotionactivity);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);

		// PrintWriter writer = resp.getWriter();
		// System.out.println("123");
		System.out.println(promotionActivityService.getAllPromotionActivity());

	}
}
