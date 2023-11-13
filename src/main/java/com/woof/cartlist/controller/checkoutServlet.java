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
                    String prodNo = request.getParameter("prodNo");

                    System.out.println(prodNo);
                    
                    // 從 Redis 中獲取購物車
                    cartJson = jedis.get(memNo);
                    if (cartJson != null) {
                        Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();
                        List<Map<String, Object>> cart = new Gson().fromJson(cartJson, type);

                        // 移除特定商品
                        cart.removeIf(item -> item.get("prodNo").equals(prodNo));

                        // 更新 Redis 中的購物車
                        cartJson = new Gson().toJson(cart);
                        jedis.set(memNo, cartJson);
                    }
                    response.getWriter().write("Item deleted");
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
