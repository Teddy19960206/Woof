package com.woof.cartlist.controller;

import java.io.IOException;
import java.lang.reflect.Type;
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

@WebServlet("/checkout")
public class checkoutServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String action = request.getParameter("action");
		Jedis jedis = null;

		String memNo = request.getParameter("memNo");
		System.out.println("印出來的會員帳號" + memNo);

		try {
			jedis = new Jedis("localhost", 6379);
			String cartJson = null; // 移到這裡

			String prodNo = request.getParameter("prodNo");
			System.out.println("商品" + prodNo);

			switch (action) {

			case "getCart":

				// 從 Redis 獲取當前購物車
				cartJson = jedis.get(memNo);
				if (cartJson == null) {
					cartJson = "[]";
				}

				// 顯示為什麼在前端跑不出來
				System.out.println("redis cartJson: " + cartJson);

				// 設置響應
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(cartJson);

				break;

			case "deleteItem":

				cartJson = jedis.get(memNo);
				if (cartJson != null) {
					Type type = new TypeToken<List<Map<String, Object>>>() {
					}.getType();
					List<Map<String, Object>> cart = new Gson().fromJson(cartJson, type);

					// 移除特定商品
					cart.removeIf(item -> item.get("prodNo").equals(prodNo));

					// 更新 Redis 中的購物車
					cartJson = new Gson().toJson(cart);
					jedis.set(memNo, cartJson);
				}
				response.getWriter().write("Item deleted");
				break;

			case "decreaseQuantity":

				int newQuantity = Integer.parseInt(request.getParameter("quantity"));

				System.out.println("減少數量顯示" + newQuantity);

				cartJson = jedis.get(memNo);
				if (cartJson != null) {
					Type type = new TypeToken<List<Map<String, Object>>>() {
					}.getType();
					List<Map<String, Object>> cart = new Gson().fromJson(cartJson, type);

					for (Map<String, Object> item : cart) {
						if (item.get("prodNo").equals(prodNo)) {
							int currentQuantity = ((Number) item.get("quantity")).intValue();
							if (currentQuantity > 1) {
								item.put("quantity", newQuantity);
							}
							break;
						}
					}

					cartJson = new Gson().toJson(cart);
					jedis.set(memNo, cartJson);
				}

				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				response.getWriter().write(cartJson);
				break;

			case "increaseQuantity":

				int addQuantity = Integer.parseInt(request.getParameter("quantity"));
				
				System.out.println("增加數量顯示" + addQuantity);
				// 增加數量的邏輯
				cartJson = jedis.get(memNo);
				if (cartJson != null) {
					Type type = new TypeToken<List<Map<String, Object>>>() {
					}.getType();
					List<Map<String, Object>> cart = new Gson().fromJson(cartJson, type);

					for (Map<String, Object> item : cart) {
						if (item.get("prodNo").equals(prodNo)) {
							int currentQuantity = ((Number) item.get("quantity")).intValue();
							if (currentQuantity >= 1) {
								item.put("quantity", addQuantity);
							}
							break;
						}
					}
						cartJson = new Gson().toJson(cart);
						jedis.set(memNo, cartJson);
					}

					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json");
					response.getWriter().write(cartJson);

				break;

			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
			}
		} catch (Exception e) {

			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		} finally {

			if (jedis != null) {
				jedis.close();
			}
		}
	}


}
