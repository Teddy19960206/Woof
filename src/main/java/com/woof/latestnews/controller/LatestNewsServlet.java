package com.woof.latestnews.controller;

import com.alibaba.fastjson2.JSONObject;
import com.google.gson.JsonObject;

import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.woof.latestnews.service.LatestNewsService;
import com.woof.latestnews.service.LatestNewsServiceImpl;
import com.woof.latestnews.entity.*;

@WebServlet("/latestNews.do")
@MultipartConfig
public class LatestNewsServlet extends HttpServlet {
		private LatestNewsService latestNewsService;

		@Override
		public void init() throws ServletException {
			latestNewsService = new LatestNewsServiceImpl();
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
			//前端的action對應到哪個 執行哪個
			switch (action) {
			case "add":
				processAdd(req, res);
				break;
			case "update":
				processUpdate(req, res);
				break;
			case "del":
				processDelete(req, res);
				break;
			case "query":
				processQuery(req, res);
			default:
			}
		}

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);
		}
		
		private void processQuery(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
			//找對應的aminno
			LatestNews ln = latestNewsService.findLatestNewsByLnNo(Integer.parseInt(req.getParameter("LN_NO")));
			
			res.setCharacterEncoding("UTF-8");
			//用json把值裝起來
			String str1 = JSONObject.toJSONString(ln);
			//回應給前端
			PrintWriter out = res.getWriter();
			out.write(str1);
		}
		
		private void processDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
			latestNewsService.deleteLatestNews(Integer.parseInt(req.getParameter("LN_NO")));
		}
		
		private void processUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
			LatestNews ln = new LatestNews();
			//把資料給前端
			int lnNo = Integer.parseInt(req.getParameter("LN_NO"));
			ln.setLnNo(lnNo);
//			ln.setLnNo (req.getParameter(Integer.parseInt("LN_NO")));
			ln.setLnTitle (req.getParameter("LN_Title"));
			ln.setLnContent(req.getParameter("LN_CONTENT"));
			String lnTimeStr = req.getParameter("LN_TIME"); // 獲取 LN_TIME 參數的值
			java.sql.Timestamp lnTime = null;

			if (lnTimeStr != null && !lnTimeStr.isEmpty()) {
			    // 嘗試將字符串轉換為 Timestamp
			    try {
			        lnTime = java.sql.Timestamp.valueOf(lnTimeStr);
			    } catch (IllegalArgumentException e) {
			        // 如果轉換失敗，你可以處理錯誤或執行其他操作
			        e.printStackTrace(); // 這裡僅是一個簡單的示例，你可以選擇處理錯誤更適當的方式
			    }
			}
			ln.setLnTime(lnTime);
			
			
			
			latestNewsService.updateLatestNews(ln);
			//導到指定的URL 頁面上 把請求回應都帶過去
//			String url = "/frontend/latestNews/latestNews.jsp";
//			RequestDispatcher rd =  req.getRequestDispatcher(url);
//			rd.forward(req, res);
			String url = req.getContextPath()+"/frontend/latestNews/latestNews.jsp";
			res.sendRedirect(url);
		}

		private void processAdd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
			LatestNews ln = new LatestNews();
			//把資料給前端
			int lnNo = Integer.parseInt(req.getParameter("LN_NO"));
			ln.setLnNo(lnNo);
//			ln.setLnNo (req.getParameter(Integer.parseInt("LN_NO")));
			ln.setLnTitle (req.getParameter("LN_Title"));
			ln.setLnContent(req.getParameter("LN_CONTENT"));
			String lnTimeStr = req.getParameter("LN_TIME"); // 獲取 LN_TIME 參數的值
			java.sql.Timestamp lnTime = null;

			if (lnTimeStr != null && !lnTimeStr.isEmpty()) {
			    // 嘗試將字符串轉換為 Timestamp
			    try {
			        lnTime = java.sql.Timestamp.valueOf(lnTimeStr);
			    } catch (IllegalArgumentException e) {
			        // 如果轉換失敗，你可以處理錯誤或執行其他操作
			        e.printStackTrace(); // 這裡僅是一個簡單的示例，你可以選擇處理錯誤更適當的方式
			    }
			}
			ln.setLnTime(lnTime);
			
		
			// 取得圖片 
			// 開串流
			Part p1 = req.getPart("LN_PHOTO");
			InputStream input1 = p1.getInputStream();
			byte[] photo1 = new byte[input1.available()];
			input1.read(photo1);
			input1.close();
			ln.setLnPhoto(photo1);
			
			
			latestNewsService.addLatestNews(ln);
			
		
			//導到指定的URL 頁面上 把請求回應都帶過去
			String url = req.getContextPath()+"/frontend/latestNews/latestNews.jsp";
			res.sendRedirect(url);
		}

	}
