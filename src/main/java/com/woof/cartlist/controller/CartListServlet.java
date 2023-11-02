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
	
    private Map<String, Integer> cart = new HashMap<>();

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
            
            //console那邊可以看到
            response.getWriter().write("商品已加到購物車");
            response.setHeader("cartItemCount", String.valueOf(totalItems)); // 更新購物車數量的標頭信息
        
//         // 更新購物車中的數量
//            int totalItems = cart.values().stream().mapToInt(Integer::intValue).sum();
//
//            // 創建一個JSON對象，包含購物車數量
//            Map<String, Integer> responseMap = new HashMap<>();
//            responseMap.put("cartItemCount", totalItems);
//
//            // 將JSON轉換為字串
//            String jsonResponse = new Gson().toJson(responseMap);
//
//            // 設置響應的內容類型為JSON
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//
//            // 寫入JSON響應
//            response.getWriter().write(jsonResponse);
        
        }
    }
}
