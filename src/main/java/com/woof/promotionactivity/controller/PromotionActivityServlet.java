package com.woof.promotionactivity.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.promotionactivity.entity.PromotionActivity;
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter("action");
		if ("getAllPromotionActivity".equals(action)) {

			// 獲取所有促銷活動的資訊
			List<PromotionActivity> promotionActivityList = promotionActivityService.getAllPromotionActivity();

			// 將促銷活動的資訊儲存到 request 屬性中
			req.setAttribute("promotionActivityList", promotionActivityList);

			// 跳轉到 /promotionactivity.jsp 頁面        (下面是路徑)
			req.getRequestDispatcher("/backend/promotionactivity/getAllPA.jsp").forward(req, resp);

			// PrintWriter writer = resp.getWriter();
            // System.out.println("123");
            // System.out.println(promotionActivityService.getAllPromotionActivity());

		}
	}
}
