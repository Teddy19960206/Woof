package com.woof.cartlist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.woof.member.entity.Member;

import redis.clients.jedis.Jedis;

@WebServlet("/cart/*")
public class CartServlet extends HttpServlet {
	private Jedis jedis = new Jedis("localhost", 6379);

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String action = request.getParameter("action");
		System.out.println("動作為action = " + action);

		String forwardPath = "";
		
		HttpSession session = request.getSession();
		Object member = session.getAttribute("member");
//		Member member = (Member) request.getSession(false).getAttribute("member");
//		String memNo = member.getMemNo();
		
		System.out.println(member);
		
		if (member == null) {
	        // 用戶未登入，導向登入頁面
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "用戶未登入，請登入唷");
		    return;
		}
		
		switch (action) {
		
		case "getTotalQuantity":
			getTotalQuantity(request, response);
			break;
			
		case "add":
			add(request, response);
			break;
			
		default:

			forwardPath = "/frontend/index.jsp";
			break;
		}
	}

	private void getTotalQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Member member = (Member) request.getSession(false).getAttribute("member");
		
		if (member == null) {
	        // 用戶未登入，返回 totalQuantity 為 0 的 JSON
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write("{\"totalQuantity\": 0}");
	        return;
	    }
		
		// 有登入，redis找資料
		String memNo = member.getMemNo();
		String cartJson = jedis.get(memNo);
		
		List<Map<String, Object>> cart;
		if (cartJson != null) {
			cart = new Gson().fromJson(cartJson, new TypeToken<List<Map<String, Object>>>() {
			}.getType());
		} else {
			cart = new ArrayList<>();
		}

		int totalQuantity = cart.stream().mapToInt(item -> (int) Double.parseDouble(item.get("quantity").toString()))
				.sum();

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("totalQuantity", totalQuantity);

		String responseJson = new Gson().toJson(responseMap);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(responseJson);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Member member = (Member) request.getSession(false).getAttribute("member");
		String memNo = member.getMemNo();
		
		// 從 Redis 獲取當前購物車
		String cartJson = jedis.get(memNo);
		List<Map<String, Object>> cart;
		if (cartJson != null) {
			// 轉成物件
			cart = new Gson().fromJson(cartJson, new TypeToken<List<Map<String, Object>>>() {
			}.getType());
		} else {
			cart = new ArrayList<>();
		}

		// 添加商品到購物車
		String prodNo = request.getParameter("prodNo");
		String prodName = request.getParameter("prodName");
		double prodPrice = Double.parseDouble(request.getParameter("prodPrice"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		boolean exists = false;
		
		System.out.println(prodName);
		System.out.println(quantity);

		// 檢查購物車是否已經包含該商品
		for (Map<String, Object> item : cart) {
			if (prodNo.equals(item.get("prodNo"))) {
				double existingQuantityDouble = (double) item.get("quantity");
		        int existingQuantity = (int) existingQuantityDouble;
		        item.put("quantity", existingQuantity + quantity); // 增加數量
		        exists = true;
		        break;
			}
		}

		if (!exists) {
			Map<String, Object> newItem = new HashMap<>();
			newItem.put("prodNo", prodNo);
			newItem.put("prodName", prodName);
		    newItem.put("quantity", quantity); // 使用傳入的數量
			newItem.put("prodPrice", prodPrice);
			cart.add(newItem);
		}

		// 將更新後的購物車轉換為JSON字符串並存回Redis
		cartJson = new Gson().toJson(cart);
		jedis.set(memNo, cartJson);

		// 計算購物車中所有商品的總數量
		int totalQuantity = cart.stream().mapToInt(item -> (int) Double.parseDouble(item.get("quantity").toString()))
				.sum();

		// 創建一個包含總數量的 Map
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("totalQuantity", totalQuantity);    
	    responseMap.put("message", "商品已成功加入購物車！"); 
		responseMap.put("cart", cart);

		// 將 Map 轉換為 JSON 字符串
		String responseJson = new Gson().toJson(responseMap);

		// 設置響應
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(responseJson); // 傳回包含總數量的 JSON
	}

	@Override
	public void destroy() {
		jedis.close();
		super.destroy();
	}
}