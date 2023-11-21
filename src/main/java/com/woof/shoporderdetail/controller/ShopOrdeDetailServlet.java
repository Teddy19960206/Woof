package com.woof.shoporderdetail.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import redis.clients.jedis.Jedis;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.woof.faq.entity.Faq;
import com.woof.faq.service.FaqService;
import com.woof.faq.service.FaqServiceImpl;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.shoporder.entity.ShopOrder;
import com.woof.shoporder.service.ShopOrderService;
import com.woof.shoporder.service.ShopOrderServiceImpl;
import com.woof.shoporderdetail.service.ShopOrderDetailService;
import com.woof.shoporderdetail.service.ShopOrderDetailServiceImpl;


@WebServlet("/shoporderdetail/*")
public class ShopOrdeDetailServlet extends HttpServlet {

	private ShopOrderDetailService shopOrderDetailService;

	@Override
	public void init() throws ServletException {
		 super.init();
		 shopOrderDetailService = new ShopOrderDetailServiceImpl();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String forwardPath = "";

		switch (action) {
			
//			會員單一查詢
		case "getByMemNo":
			forwardPath = getByMemNo(request, response);
			break;

		// 全部不成立會跑到這邊
		default:
			forwardPath = "/backend/index.jsp";
		}

		response.setContentType("text/html; charset=UTF-8");
//		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);		
		dispatcher.forward(request, response);

	}


private String getByMemNo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	
		return "/backend/shoporder/getOneshoporder.jsp";
	}

}
