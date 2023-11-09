package com.woof.shoporder.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.faq.entity.Faq;
import com.woof.faq.service.FaqService;
import com.woof.faq.service.FaqServiceImpl;
import com.woof.shoporder.entity.ShopOrder;
import com.woof.shoporder.service.ShopOrderService;
import com.woof.shoporder.service.ShopOrderServiceImpl;

@WebServlet("/shoporder/*")
public class ShopOrdeServlet extends HttpServlet {

	private ShopOrderService shopOrderService;

	@Override
	public void init() throws ServletException {
		shopOrderService = new ShopOrderServiceImpl();
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

		case "getAll":
			forwardPath = getAll(request, response);
			break;

//		case "addfaq":
//			forwardPath = addfaq(request, response);
//			break;
//
//		case "updatefaq":
//			forwardPath = updatefaq(request, response);
//			break;

		//全部不成立會跑到這邊	
		default:
			forwardPath = "/backend/index.jsp";
		}

		response.setContentType("text/html; charset=UTF-8");
	    RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
	    dispatcher.forward(request, response);

	}

	private String getAll(HttpServletRequest request , HttpServletResponse response) {

//		List<Faq> all = faqService.getAllFaq();
//		request.setAttribute("all", all);
		
		String page = request.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		
//		if (request.getSession().getAttribute("faqPageQty") == null) {
			int shopOrderPageQty = shopOrderService.getPageTotal();
			request.getSession().setAttribute("fshopOrderPageQty", shopOrderPageQty);
//		}
		
		List<ShopOrder> all = shopOrderService.getAllShopOrder(currentPage);
		
		request.setAttribute("all", all);
		request.setAttribute("currentPage", currentPage);
		
		return "/backend/faq/getAll.jsp";
	}
//
//	private String addfaq(HttpServletrequestuest request, HttpServletresponseonse response) {
//
//		String faqClass = request.getParameter("faqClass");
//		String faqTitle = request.getParameter("faqTitle");
//		String faqContent = request.getParameter("faqContent");
//
//		// 如果有確定進入資料庫會有流水編號，再去找流水編號的值，顯示在jsp
//		int saved = (Integer) faqService.addFaq(faqClass, faqTitle, faqContent);
//		var result = faqService.findByFaqNo(saved);
//
//		if (saved > 0) {
////		    return 1; // FAQ添加成功
//	        System.out.println("新增成功");
//	    } else {
////	        return -1; // FAQ添加失败
//	        System.out.println("新增失敗");
//	    }
//		
//		request.setAttribute("result", result);
//		
//		return "/backend/faq/addfaq.jsp";
//	}
//
//	private String updatefaq(HttpServletrequestuest request, HttpServletresponseonse response) {
//
//		int faqNo = Integer.parseInt(request.getParameter("faqNo"));
//		String faqClass = request.getParameter("faqClass");
//		String faqTitle = request.getParameter("faqTitle");
//		String faqContent = request.getParameter("faqContent");
//
//		int result = faqService.updateFaq(faqNo, faqClass, faqTitle, faqContent);		
////		request.setAttribute("result", result);
//		
////		System.out.println(result);
//
//		if (result > 0) {
//			System.out.println("更新成功");
//		} else {
//			System.out.println("更新失敗");
//		}
//		
//		response.setContentType("application/json;charset=UTF-8");
//		return "/backend/faq/faqfirst.jsp";
//	}

	

}
