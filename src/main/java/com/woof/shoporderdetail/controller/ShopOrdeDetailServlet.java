//package com.woof.shoporderdetail.controller;
//
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.List;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.woof.shoporder.service.ShopOrderService;
//import com.woof.shoporder.service.ShopOrderServiceImpl;
//import com.woof.shoporderdetail.service.ShopOrderDetailService;
//import com.woof.shoporderdetail.service.ShopOrderDetailServiceImpl;
//
//@WebServlet("/shoporderdetail/*")
//public class ShopOrdeDetailServlet extends HttpServlet {
//
//	private ShopOrderDetailService shopOrderDetailService;
//
//	@Override
//	public void init() throws ServletException {
//		shopOrderDetailService = new ShopOrderDetailServiceImpl();
//	}
//
//	@Override
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		doPost(request, response);
//	}
//
//	@Override
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		request.setCharacterEncoding("UTF-8");
//		String action = request.getParameter("action");
//		String forwardPath = "";
//
//		switch (action) {
//
//		case "addshoporderdetail":
//			forwardPath = addshoporderdetail(request, response);
//			break;
//			
////		case "getAll":
////			forwardPath = getAll(request, response);
////			break;
////
////		case "updatehoporder":
////			forwardPath = updateshoporder(request, response);
////			break;
////			
////			會員單一查詢
////		case "getByMemNo":
////			forwardPath = getByMemNo(request, response);
////			break;
//
//		// 全部不成立會跑到這邊
//		default:
//			forwardPath = "/backend/index.jsp";
//		}
//
//		response.setContentType("text/html; charset=UTF-8");
//		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
//		dispatcher.forward(request, response);
//
//	}
//
//	private String addshoporderdetail(HttpServletRequest request, HttpServletResponse response) {
//
//		int payMethod = Integer.parseInt(request.getParameter("payment"));
//		
//		System.out.println("======================================" + payMethod);
//		
//		Boolean shipMethod = Boolean.parseBoolean(request.getParameter("shipMethod"));
//
//		System.out.println("======================================" + shipMethod);
//		
//		int orderStatus = 0;
//		request.getSession().setAttribute("orderStatus", orderStatus);
//
//		String recName = request.getParameter("memName");
//		
//		System.out.println("======================================" + recName);
//
//		String recMobile = request.getParameter("phone");
//		
//		System.out.println("======================================" + recMobile);
//		
//		String recAddress = request.getParameter("address");
//		
//		System.out.println("======================================" + recAddress);
//
//		Boolean hasReturn = false;
//		request.getSession().setAttribute("hasReturn", hasReturn);
//
//		
//		int moCoin = Integer.parseInt(request.getParameter("inputSmmp"));
//
//		System.out.println("======================================" + moCoin);
//		
//		int orderTotalPrice = Integer.parseInt(request.getParameter("totalPrice"));
//		int actualPrice = Integer.parseInt(request.getParameter("totalAfterCoins"));
//
//		
//		int saved = (Integer) shopOrderService.addShopOrder(member, prodOrderDate, payMethod, shipMethod, orderStatus,
//				recName, recMobile, recAddress, hasReturn, moCoin, orderTotalPrice, actualPrice);
//		// 如果有確定進入資料庫會有流水編號，再去找流水編號的值，顯示在jsp
//		var result = shopOrderService.findByShopOrderNo(saved);
//
//		
//		
//		if (saved > 0) {
////		    return 1; // 訂單新增成功
//			System.out.println("訂單新增成功");
//		} else {
////	        return -1; // 訂單新增失败
//			System.out.println("新增失敗");
//		}
//
//		// 資料給下一個jsp
//		request.setAttribute("result", result);
//		return "/frontend/cartlist/finishorder.jsp";
//	}
//	
//	
////	private String getAll(HttpServletRequest request, HttpServletResponse response) {
////
////		String page = request.getParameter("page");
////		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
////
////		// 會卡暫存視情況隱藏
////		if (request.getSession().getAttribute("faqPageQty") == null) {
////			int shopOrderPageQty = shopOrderService.getPageTotal();
////			request.getSession().setAttribute("shopOrderPageQty", shopOrderPageQty);
////		}
////
////		List<ShopOrder> all = shopOrderService.getAllShopOrder(currentPage);
////
////		request.setAttribute("all", all);
////		request.setAttribute("currentPage", currentPage);
////
////		return "/backend/shoporder/getAllshoporder.jsp";
////	}
////
////	private String updateshoporder(HttpServletRequest request, HttpServletResponse response) {
////
////		int shopOrderNo = Integer.parseInt(request.getParameter("shopOrderNo"));
////
////		String memNo = request.getParameter("memNo");
////		MemberService memberService = new MemberServiceImpl();
////		Member member = memberService.findMemberByNo(memNo);
////
////		String prodOrderDatefront = request.getParameter("prodOrderDate");
////		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////		Date parsedDate = null; // 初始化為 null
////		try {
////			parsedDate = dateFormat.parse(prodOrderDatefront);
////		} catch (ParseException e) {
////			e.printStackTrace();
////		}
////		Timestamp prodOrderDate = new Timestamp(parsedDate.getTime());
////
////		int payMethod = Integer.parseInt(request.getParameter("payMethod"));
////		Boolean shipMethod = Boolean.parseBoolean(request.getParameter("shipMethod"));
////		String recName = request.getParameter("recName");
////		String recMobile = request.getParameter("recMobile");
////		String recAddress = request.getParameter("recAddress");
////		Boolean hasReturn = Boolean.parseBoolean(request.getParameter("hasReturn"));
////		int moCoin = Integer.parseInt(request.getParameter("moCoin"));
////		int orderTotalPrice = Integer.parseInt(request.getParameter("orderTotalPrice"));
////		int actualPrice = Integer.parseInt(request.getParameter("actualPrice"));
////		int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
////
////		System.out.println(shopOrderNo);
////		System.out.println(orderStatus);
////		System.out.println(hasReturn);
////
////		int result = shopOrderService.updateShopOrder(shopOrderNo, member, prodOrderDate, payMethod, shipMethod,
////				orderStatus, recName, recMobile, recAddress, hasReturn, moCoin, orderTotalPrice, actualPrice);
////
//////		request.setAttribute("result", result);	
//////		System.out.println(result);
////
////		if (result > 0) {
////			System.out.println("更新成功");
////		} else {
////			System.out.println("更新失敗");
////		}
////
////		response.setContentType("application/json;charset=UTF-8");
////		return "/backend/shoporder/getAllshoporder.jsp";
////	}
//
//	
////private String getByMemNo(HttpServletRequest request, HttpServletResponse response) throws IOException {
////		
////		String memNo = request.getParameter("memNo");
////		String page = request.getParameter("page");
////		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
////		int shopOrderPageQty2 = shopOrderService.getPageTotal2(memNo);
////		request.getSession().setAttribute("shopOrderPageQty2", shopOrderPageQty2);
////		
////		List<ShopOrder> shopOrders = shopOrderService.findByMemNo(memNo , currentPage); 
////
////		System.out.println(shopOrders);
////		
////		request.setAttribute("shopOrders", shopOrders);
////		request.setAttribute("currentPage", currentPage);
////		
////		return "/backend/shoporder/getOneshoporder.jsp";
////	}
//
//}
