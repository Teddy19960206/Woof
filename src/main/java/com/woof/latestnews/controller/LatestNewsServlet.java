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
import com.woof.util.PartParsebyte;
import com.woof.administrator.entity.Administrator;
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
			//找對應的
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
			 String lnNoStr = req.getParameter("LN_NO");
			    if (lnNoStr != null && !lnNoStr.isEmpty()) {
			        ln.setLnNo(Integer.parseInt(lnNoStr));
			    }
			    // 設置標題和內容
			    ln.setLnTitle(req.getParameter("LN_TITLE"));
			    ln.setLnContent(req.getParameter("LN_CONTENT"));

			    // 處理時間
			    String lnTimeStr1 = req.getParameter("LN_TIME"); 
			    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    try {
			        Date parseDate1 = dateFormat1.parse(lnTimeStr1);
			        java.sql.Timestamp lnTime1 = new java.sql.Timestamp(parseDate1.getTime());
			        ln.setLnTime(lnTime1);
			    } catch (ParseException e) {
			        e.printStackTrace();
			    }
			    Part p = req.getPart("LN_PHOTO");
		        InputStream input = p.getInputStream();
		        byte[] photo = new byte[input.available()];
		        input.read(photo);
		        input.close();
		        ln.setLnPhoto(photo);
 

			    System.out.println(ln);

			    latestNewsService.updateLatestNews(ln);

			    // 重定向到指定的URL
			    String url = req.getContextPath()+"/backend/latestnews/latestNews.jsp";
			    res.sendRedirect(url);
			}

		private void processAdd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
			LatestNews ln = new LatestNews();
			//把資料給前端
		
//			ln.setLnNo (req.getParameter(Integer.parseInt("LN_NO")));
			ln.setLnTitle (req.getParameter("LN_TITLE"));
			ln.setLnContent(req.getParameter("LN_CONTENT"));
			String lnTimeStr = req.getParameter("LN_TIME"); // 獲取 LN_TIME 參數的值
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parseDate;
			java.sql.Timestamp lnTime;
			try {
				parseDate = dateFormat.parse(lnTimeStr);
				lnTime = new java.sql.Timestamp(parseDate.getTime());
				ln.setLnTime(lnTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
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
			String url = req.getContextPath()+"/backend/latestnews/latestNews.jsp";
			res.sendRedirect(url);
		}

	}
