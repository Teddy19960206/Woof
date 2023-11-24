package com.woof.shoporder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ecpay.payment.integration.domain.AioCheckOutOneTime;
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

import ecpay.payment.integration.AllInOne;

@WebServlet("/shoporder/*")
public class ShopOrdeServlet extends HttpServlet {

	private ShopOrderService shopOrderService;
	private ShopOrderDetailService shopOrderDetailService;
	public static AllInOne all;
	
	@Override
	public void init() throws ServletException {
		super.init();
		shopOrderService = new ShopOrderServiceImpl();
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

		case "getAll":
			forwardPath = getAll(request, response);
			break;

		case "updateshoporder":
			updateshoporder(request, response);
			return;

		case "addshoporder":
			addshoporder(request, response);
			return;

//			會員單一查詢
		case "getByMemNo":
			forwardPath = getByMemNo(request, response);
			break;

		case "updateecpay":
			updateecpay(request, response);
			return;
			
		// 全部不成立會跑到這邊
		default:
			forwardPath = "/backend/index.jsp";
		}

		response.setContentType("text/html; charset=UTF-8");
//		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);

	}

	private String getAll(HttpServletRequest request, HttpServletResponse response) {

		String page = request.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);

		// 會卡暫存視情況隱藏
//		if (request.getSession().getAttribute("faqPageQty") == null) {
		int shopOrderPageQty = shopOrderService.getPageTotal();
		request.getSession().setAttribute("shopOrderPageQty", shopOrderPageQty);
//		}

		List<ShopOrder> all = shopOrderService.getAllShopOrder(currentPage);

		request.setAttribute("all", all);
		request.setAttribute("currentPage", currentPage);

		return "/backend/shoporder/getAllshoporder.jsp";
	}

	private void updateshoporder(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int shopOrderNo = Integer.parseInt(request.getParameter("shopOrderNo"));

		System.out.println(shopOrderNo + "商成訂單編號===============================");

		String memNo = request.getParameter("memNo");
		MemberService memberService = new MemberServiceImpl();
		Member member = memberService.findMemberByNo(memNo);

		String prodOrderDatefront = request.getParameter("prodOrderDate");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parsedDate = null; // 初始化為 null
		try {
			parsedDate = dateFormat.parse(prodOrderDatefront);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp prodOrderDate = new Timestamp(parsedDate.getTime());

		int payMethod = Integer.parseInt(request.getParameter("payMethod"));
		Boolean shipMethod = Boolean.parseBoolean(request.getParameter("shipMethod"));
		String recName = request.getParameter("recName");
		String recMobile = request.getParameter("recMobile");
		String recAddress = request.getParameter("recAddress");
		Boolean hasReturn = Boolean.parseBoolean(request.getParameter("hasReturn"));
		int moCoin = Integer.parseInt(request.getParameter("moCoin"));
		int orderTotalPrice = Integer.parseInt(request.getParameter("orderTotalPrice"));
		int actualPrice = Integer.parseInt(request.getParameter("actualPrice"));
		int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));

		System.out.println(orderStatus + "訂單狀態=================================");
		System.out.println(hasReturn);

		int result = shopOrderService.updateShopOrder(shopOrderNo, member, prodOrderDate, payMethod, shipMethod,
				orderStatus, recName, recMobile, recAddress, hasReturn, moCoin, orderTotalPrice, actualPrice);

//		request.setAttribute("result", result);	
		System.out.println(result);

		if (result > 0) {
			System.out.println("更新成功");
		} else {
			System.out.println("更新失敗");
		}

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write("{\"message\": \"修改成功\"}");
	}

	private void addshoporder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String memNo = request.getParameter("memNo");

		System.out.println("======================================" + memNo);

		MemberService memberService = new MemberServiceImpl();
		Member member = memberService.findMemberByNo(memNo);

		System.out.println("======================================" + member);

		java.util.Date now = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String coTimeStr = dateFormat.format(now);
		java.util.Date parsedDate = null; // 初始化為 null
		try {
			parsedDate = dateFormat.parse(coTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Timestamp prodOrderDate = new Timestamp(parsedDate.getTime());

		int payMethod = Integer.parseInt(request.getParameter("payment"));
		request.setAttribute("payMethod", payMethod);
		int orderStatus = (payMethod == 1) ? 4 : 0; //付款方式 0:信用卡 1:匯款 2:綠界
		

		
		request.getSession().setAttribute("orderStatus", orderStatus);

		Boolean shipMethod = Boolean.parseBoolean(request.getParameter("shipMethod"));
		System.out.println(shipMethod + "===============================================");

		String recName = request.getParameter("memName");
		String recMobile = request.getParameter("phone");
		String recAddress = request.getParameter("address");
		Boolean hasReturn = false;
		request.getSession().setAttribute("hasReturn", hasReturn);

		// 前端傳過來的毛毛幣使用數量
		int moCoin = Integer.parseInt(request.getParameter("inputSmmp"));

		System.out.println(moCoin + "毛毛幣==========================================");

		// 計算新的毛毛幣餘額，不可以變負數
		int currentMoCoins = member.getMomoPoint();

		System.out.println(currentMoCoins + "會員擁有毛毛幣==========================================");

		int newMoCoins = Math.max(currentMoCoins - moCoin, 0);
		// 更新會員的毛毛幣餘額
		memberService.updateMemberPoints(memNo, newMoCoins);

		int orderTotalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		
		String actualPriceStr = request.getParameter("totalAfterCoins");
		int actualPrice = Integer.parseInt(actualPriceStr);
		
		
		
		int savedOrderNo = shopOrderService.addShopOrder(member, prodOrderDate, payMethod, shipMethod, orderStatus,
				recName, recMobile, recAddress, hasReturn, moCoin, orderTotalPrice, actualPrice);

		if(payMethod == 2) {
			
            // 獲取協議（http或https）
            String scheme = request.getScheme();
            // 獲取主機名
            String host = request.getServerName();
            // 獲取端口號
            int port = request.getServerPort();
            // 構建完整的URL
            String fullURL = scheme + "://" + host + ":" + port;

            all = new AllInOne("");

            AioCheckOutOneTime obj = new AioCheckOutOneTime();
            obj.setMerchantTradeNo(formatOrderId(savedOrderNo));
            obj.setMerchantTradeDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            obj.setTotalAmount(actualPriceStr);
            obj.setTradeDesc("寵毛導師：商成訂單付款成功");
            obj.setItemName("狗罐頭");
            
            // 有異常導回首頁
            obj.setReturnURL(fullURL+request.getContextPath()+"/index.jsp");
            obj.setNeedExtraPaidInfo("N");
            obj.setRedeem("N");

//            要重新更新訂單狀態
            obj.setClientBackURL(fullURL+request.getContextPath()+"/shoporder/ecpay?action=updateecpay&orderno=" + savedOrderNo);
			
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(all.aioCheckOut(obj , null));
            
			return;
		}
		
		
		
		// 如果有確定進入資料庫會有流水編號，再去找流水編號的值，顯示在jsp
		var result = shopOrderService.findByShopOrderNo(savedOrderNo);
//		 資料給下一個jsp
		request.setAttribute("result", result);

		if (savedOrderNo > 0) {
//		    return 1; // 訂單新增成功
			System.out.println("訂單新增成功");
			request.setAttribute("orderSuccess", true);

			try (Jedis jedis = new Jedis("localhost", 6379)) {
				// 從 Redis 獲取購物車數據
				String cartJson = jedis.get(memNo);

				// 解析購物車數據
				Type listType = new TypeToken<List<Map<String, Object>>>() {
				}.getType();
				List<Map<String, Object>> cartItems = new Gson().fromJson(cartJson, listType);

				for (Map<String, Object> item : cartItems) {
					// 從map中取出商品信息
					int prodNo = Integer.parseInt((String) item.get("prodNo"));
					int orderAmount = ((Double) item.get("quantity")).intValue();
					int prodPrice = ((Double) item.get("prodPrice")).intValue();
					int hasReturned = 0;
					BigDecimal discountRate = BigDecimal.valueOf(0.00);

					System.out.println(prodNo + "===============================================");
					System.out.println(orderAmount + "===============================================");
					System.out.println(prodPrice + "===============================================");

					// 保存訂單明細
					int saveshoporderdetail = shopOrderDetailService.addShopOrderDetail(savedOrderNo, prodNo,
							orderAmount, prodPrice, hasReturned, discountRate);

					var result2 = shopOrderService.findByShopOrderNo(saveshoporderdetail);
//	       			資料給下一個jsp
					request.setAttribute("result2", result2);
				}

				// 清除 Redis 中的購物車數據
				jedis.del(memNo);
			} catch (Exception e) {

				e.printStackTrace();

			}

		} else {
//	        return -1; // 訂單新增失败
			System.out.println("新增失敗");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/cartlist/finishorder.jsp");
		dispatcher.forward(request, response);
		
	}

	private String getByMemNo(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String memNo = request.getParameter("memNo");
		String page = request.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		int shopOrderPageQty2 = shopOrderService.getPageTotal2(memNo);
		request.getSession().setAttribute("shopOrderPageQty2", shopOrderPageQty2);

		List<ShopOrder> shopOrders = shopOrderService.findByMemNo(memNo, currentPage);

		System.out.println(shopOrders);

		request.setAttribute("shopOrders", shopOrders);
		request.setAttribute("currentPage", currentPage);

		return "/backend/shoporder/getOneshoporder.jsp";
	}

	
	
//  轉換訂單編號
  public String formatOrderId(long orderId) {
      return "woofShop" + String.format("%08d", orderId);
  }
  
  private void updateecpay(HttpServletRequest request, HttpServletResponse response) throws IOException {
	  
//	  Member member = (Member) request.getSession(false).getAttribute("member");
	  Integer orderno = Integer.valueOf(request.getParameter("orderno"));
	  	  
	  ShopOrder shoporder = shopOrderService.findByShopOrderNo(orderno);
//	  shoporder.setOrderStatus(5);
	  
	  shopOrderService.updateShopOrder(shoporder.getShopOrderNo(), shoporder.getMember(), shoporder.getProdOrderDate(), shoporder.getPayMethod(), shoporder.getShipMethod(), 5, shoporder.getRecName(), shoporder.getRecMobile(), shoporder.getRecAddress(), shoporder.getHasReturn(), shoporder.getMoCoin(), shoporder.getOrderTotalPrice(), shoporder.getActualPrice());
	  
	  response.setContentType("text/html;charset=UTF-8");
	  response.sendRedirect(request.getContextPath()+"/index.jsp");
  }
  
}
