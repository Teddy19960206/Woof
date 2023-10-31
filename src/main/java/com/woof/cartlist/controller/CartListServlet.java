//package com.woof.cartlist.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.gson.Gson;
//
//@WebServlet("/cartlist/*")
//public class CartListServlet extends HttpServlet {
//	
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//
//        // 模擬商品數據
//        String productId = request.getParameter("productId");
//        String productName = request.getParameter("productName");
//        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
//
//        // 獲取購物車數據（存儲在Session中）
//        List<CartItem> cart = (List<CartItem>) request.getSession().getAttribute("cart");
//
//        if (cart == null) {
//            cart = new ArrayList<>();
//        }
//
//        // 將商品添加到購物車
//        cart.add(new CartItem(productId, productName, productPrice));
//        request.getSession().setAttribute("cart", cart);
//
//        // 這裡也可以將購物車數據存儲到Redis中
//
//        // 返回購物車內容
//        out.print(new Gson().toJson(cart));
//    }
//}
