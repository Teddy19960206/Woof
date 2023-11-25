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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.woof.latestnews.service.LatestNewsService;
import com.woof.latestnews.service.LatestNewsServiceImpl;
import com.woof.util.PartParsebyte;
import com.woof.administrator.entity.Administrator;
import com.woof.administrator.service.AdministratorService;
import com.woof.administrator.service.AdministratorServiceImpl;
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
				break;
			case "getone":
				getOne(req, res);
				break;
			default:
			}
		}
		private void getOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

			LatestNewsService latestNewsService = new LatestNewsServiceImpl();

			try {

				req.setCharacterEncoding("UTF-8");
			

				// 獲取特定成員
				String lnNoStr = req.getParameter("LN_NO");
				System.out.println(lnNoStr);

				if (lnNoStr != null && !lnNoStr.trim().isEmpty()) {
					try {

						LatestNews latestNews=latestNewsService.findLatestNewsByLnNo(Integer.parseInt(lnNoStr));
						req.setAttribute("latestNews", latestNews);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else {
					req.setAttribute("error", "Invalid latestNews number provided.");
					RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/administrator/errorPage.jsp");
					dispatcher.forward(req, res);
					return;
				}
				// 設置編碼和轉發到指定的JSP頁面
				req.setCharacterEncoding("UTF-8");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/latestnews/listonelatestnews.jsp");
				dispatcher.forward(req, res);

			} catch (Exception e) {
				// 處理其他潛在錯誤
				e.printStackTrace();

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
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			List<LatestNews> latestNews = latestNewsService.getAllLatestNews(); 
			LatestNews ln = new LatestNews();
		
			
			 
			String lnNoStr = req.getParameter("LN_NO");
		    if (lnNoStr != null && !lnNoStr.isEmpty()) {
		        ln.setLnNo(Integer.parseInt(lnNoStr));
		    }
			    
			    
			    String lnTitle = req.getParameter("LN_TITLE");
				if (lnTitle == null || lnTitle.trim().length() == 0) {
				    errorMsgs.put("LN_TITLE", "消息標題請勿空白");
				}else if(lnTitle.length() >40) { 
					errorMsgs.put("LN_TITLE","長度不得超過40");
		        }
				
				String lnContent = req.getParameter("LN_CONTENT");
				if (lnContent == null || lnContent.trim().length() == 0) {
				    errorMsgs.put("LN_CONTENT", "消息內容請勿空白");
				}else if(lnContent.length() >300) { 
					errorMsgs.put("LN_CONTENT","長度不得超過300");
		        }
				
				if (!errorMsgs.isEmpty()){
					req.getSession().setAttribute("errorMsgs", errorMsgs);
					String contextPath = req.getContextPath();
					res.sendRedirect(contextPath + "/backend/latestnews/latestNewsUpdate.jsp?lnNo=" + lnNoStr);

			    return;
//			    刪除錯誤訊息
				}else {
					req.getSession().removeAttribute("errorMsgs");
				}
			    // 設置標題和內容
		
			    ln.setLnTitle(req.getParameter("LN_TITLE"));
			    ln.setLnContent(req.getParameter("LN_CONTENT"));

			    // 處理時間
			    String lnTimeStr1 = req.getParameter("LN_TIME"); 
			    Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date parseDate;
				java.sql.Timestamp lnTime;
				String lnTimeStr = dateFormat.format(now);
					
					Date parsedDate = null;
					try {
						parsedDate = dateFormat.parse(lnTimeStr);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lnTime = new Timestamp(parsedDate.getTime());
					System.out.println(lnTime);
					ln.setLnTime(lnTime);
					req.setAttribute("lnTime", lnTime);
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
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			List<LatestNews> latestNews = latestNewsService.getAllLatestNews();
			LatestNews ln = new LatestNews();
			//把資料給前端
			String lnTitle = req.getParameter("LN_TITLE");
			if (lnTitle == null || lnTitle.trim().length() == 0) {
			    errorMsgs.put("LN_TITLE", "消息標題請勿空白");
			}else if(lnTitle.length() >40) { 
				errorMsgs.put("LN_TITLE","長度不得超過40");
	        }
			
			String lnContent = req.getParameter("LN_CONTENT");
			if (lnContent == null || lnContent.trim().length() == 0) {
			    errorMsgs.put("LN_CONTENT", "消息內容請勿空白");
			}else if(lnContent.length() >300) { 
				errorMsgs.put("LN_CONTENT","長度不得超過300");
	        }
			
			if (!errorMsgs.isEmpty()){
				req.getSession().setAttribute("errorMsgs", errorMsgs);
				req.getRequestDispatcher("backend/latestnews/latestNewsAdd.jsp").forward(req, res);

		    return;
//		    刪除錯誤訊息
			}else {
				req.getSession().removeAttribute("errorMsgs");
			}
			
//			ln.setLnNo (req.getParameter(Integer.parseInt("LN_NO")));
			ln.setLnTitle (req.getParameter("LN_TITLE"));
			ln.setLnContent(req.getParameter("LN_CONTENT"));
			
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date parseDate;
			java.sql.Timestamp lnTime;
			String lnTimeStr = dateFormat.format(now);
				
				Date parsedDate = null;
				try {
					parsedDate = dateFormat.parse(lnTimeStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lnTime = new Timestamp(parsedDate.getTime());
				System.out.println(lnTime);
				ln.setLnTime(lnTime);
				req.setAttribute("lnTime", lnTime);
				
			
			
			
		
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
