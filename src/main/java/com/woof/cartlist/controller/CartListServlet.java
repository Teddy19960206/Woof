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
import redis.clients.jedis.Jedis;

@WebServlet("/cartlist")
public class CartListServlet extends HttpServlet {
	private Jedis jedis = new Jedis("localhost", 6379);

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String action = request.getParameter("action");

		if ("add".equals(action)) {
//			HttpSession session = request.getSession();
//			String memNo = (String) session.getAttribute("memNo"); // 假設會員編號已經存儲在會話中
			String memNo = "member1";
//			if (memNo == null) {
//				// 處理未登入狀態
//				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "用戶未登入");
//				return;
//			}

			// 從 Redis 獲取當前購物車
			String cartJson = jedis.get(memNo);
			List<Map<String, Object>> cart;
			if (cartJson != null) {
				cart = new Gson().fromJson(cartJson, new TypeToken<List<Map<String, Object>>>() {
				}.getType());
			} else {
				cart = new ArrayList<>();
			}

			// 添加商品到購物車
			String prodNo = request.getParameter("prodNo");
			String prodName = request.getParameter("prodName");
			double prodPrice = Double.parseDouble(request.getParameter("prodPrice"));
			boolean exists = false;

			// 檢查購物車是否已經包含該商品
			for (Map<String, Object> item : cart) {
				if (prodNo.equals(item.get("prodNo"))) {
					int quantity = (int) Double.parseDouble(item.get("quantity").toString());
					item.put("quantity", quantity + 1); // 增加數量
					exists = true;
					break;
				}
			}

			if (!exists) {
				Map<String, Object> newItem = new HashMap<>();
				newItem.put("prodNo", prodNo);
				newItem.put("prodName", prodName);
				newItem.put("quantity", 1);
				newItem.put("prodprice", prodPrice);
				cart.add(newItem);
			}

			// 將更新後的購物車轉換為JSON字符串並存回Redis
			cartJson = new Gson().toJson(cart);
			jedis.set(memNo, cartJson);

			// 設置響應
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(cartJson); // 傳回購物車的JSON
		}
	}

	@Override
	public void destroy() {

		jedis.close(); // 確保在 Servlet 被銷毀時關閉 Jedis 客戶端
		super.destroy();
	}
}