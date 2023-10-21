package com.woof.product.controller;

import java.io.IOException;
import java.util.*;
import com.woof.product.entity.Product;
import com.woof.product.service.ProductService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Product")
public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

  req.setCharacterEncoding("UTF-8");
  String action = req.getParameter("action");

  if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp??��?��??

   List<String> errorMsgs = new LinkedList<String>();
   // Store this set in the request scope, in case we need to
   // send the ErrorPage view.
   req.setAttribute("errorMsgs", errorMsgs);

   /***************************
    * 1.接收請求參數 - 輸入格式的錯誤處理
    **********************/
   String str = req.getParameter("prodNo");
   if (str == null || (str.trim()).length() == 0) {
    errorMsgs.add("請輸入員工編號");
   }
   // Send the use back to the form, if there were errors
   if (!errorMsgs.isEmpty()) {
    RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
    failureView.forward(req, res);
    return;// 程式中斷
   }

   Integer prodNo = null;
   try {
	   prodNo = Integer.valueOf(str);
   } catch (Exception e) {
    errorMsgs.add("員工編號格式不正確");
   }
   // Send the use back to the form, if there were errors
   if (!errorMsgs.isEmpty()) {
    RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
    failureView.forward(req, res);
    return;// 程式中斷
   }

   /***************************
    * 2.開始查詢資料
    *****************************************/
   ProductService productSvc = new ProductService();
   Product product = productSvc.getOneProduct(prodNo);
   if (product == null) {
    errorMsgs.add("查無資料");
   }
   // Send the use back to the form, if there were errors
   if (!errorMsgs.isEmpty()) {
    RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
    failureView.forward(req, res);
    return;// 程式中斷
   }

   /***************************
    * 3.查詢完成,準備轉交(Send the Success view)
    *************/
   req.setAttribute("product", product); // 資料庫取出的empVO物件,存入req
   String url = "/product/listOneEmp.jsp";
   RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
   successView.forward(req, res);

  }
	}
}