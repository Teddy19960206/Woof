package com.woof.cartlist.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/cartlist")
public class CartListServlet extends HttpServlet {
	// 到時候加入會員使用複合鍵的Map
	// Map<String, Map<String, Integer>> cart = new HashMap<>();
	
    private Map<Object, Integer> cart = new HashMap<>();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        	
        
        if ("add".equals(action)) {
            // 添加商品到購物車
            String prodNo = request.getParameter("prodNo");
            String prodName = request.getParameter("prodName");
            double prodPrice = Double.parseDouble(request.getParameter("prodPrice"));

            // 從登入的會員獲取會員帳號
            // String memNo = request.getParameter("memNo"); 
            
            // 檢查購物車是否已經包含相同的商品，如果是，增加數量
            if (cart.containsKey(prodName)) {
                cart.put(prodName, cart.get(prodName) + 1);
            } else {
                cart.put(prodName, 1);
            }

            System.out.println("購物車有"+cart);
            
            response.setContentType("text/html; charset=UTF-8");
            // 更新購物車中的數量
            int totalItems = cart.values().stream().mapToInt(Integer::intValue).sum();

            System.out.println("一共有"+totalItems+"件");
            
            Gson gson = new Gson();
            String totalItemsJson = gson.toJson(totalItems);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            //console那邊可以看到
            response.getWriter().write(totalItemsJson);
            
        
  
        }
    }
}
