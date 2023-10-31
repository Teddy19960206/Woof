package com.woof.member.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/login.do")
public class LoginCl extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 中文亂碼解決方法
		res.setContentType("text/html;charset=gb2312");
//防止非法登入    得到session
		HttpSession httpSession = req.getSession(true);
		// 修改session的存在時間為20s
		httpSession.setMaxInactiveInterval(20);
		httpSession.setAttribute("pass", "ok");
		// 來自memberlogin.jsp的請求
		if ("memberlogin".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 獲取使用者名稱的賬號和密碼
			String u = null;
			// 針對jsp 其username為username1
			u = req.getParameter("memberaccount");
			if (u == null || (u.trim()).length() == 0) {
				errorMsgs.put("memberaccount", "會員帳號:請勿空白");
			}
			String p = null;
			p = req.getParameter("memberpassword");
			if (p == null || (p.trim()).length() == 0) {
				errorMsgs.put("memberpassword", "會員密碼:請勿空白");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/memberlogin.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
			}
				// 得到提交的驗證碼
				String code = req.getParameter("code");
				// 獲取session驗證碼
				HttpSession session = req.getSession();
				String randStr = (String) session.getAttribute("randStr");
				res.setCharacterEncoding("gb2312");
				// 當賬號資訊和驗證碼輸入正確時才可以訪問
				try {
					// 啟動mysql驅動器
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root",
							"password");
					String sql = "select * from MEMBER where MEM_NO=? and MEM_PASSWORD=?";
					PreparedStatement preparedStatement = con.prepareStatement(sql);
					preparedStatement.setString(1, u);
					preparedStatement.setString(2, p);
					ResultSet rs = preparedStatement.executeQuery();
					if (!rs.next()) {

						res.getWriter().println("<a href=LoginServlet.jsp>抱歉：帳號或密碼錯誤，請注意核實資訊重新輸入</a>");
						return;
					} else {

						// 獲取到
						if (code.equals(randStr)) {

							String keep = req.getParameter("keep");
							// 勾選了兩週內免登陸選項
							if (keep != null) {

								// 建立cookie
								Cookie cookie1 = new Cookie("username", u);
								Cookie cookie2 = new Cookie("password", p);
								// 設定關聯路徑
								cookie1.setPath(req.getContextPath());
								cookie2.setPath(req.getContextPath());
								// 設定cookie的消亡時間 兩週
								cookie1.setMaxAge(2 * 7 * 24 * 60 * 60);
								cookie1.setMaxAge(2 * 7 * 24 * 60 * 60);
								// 把cookie資訊寫給瀏覽器
								res.addCookie(cookie1);
								res.addCookie(cookie2);
							}
							// 跳轉到歡迎介面
							res.sendRedirect("welcome.jsp?username=" + u + " &password=" + p);
						}
						rs.close();
						preparedStatement.close();
					}
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		this.doGet(req, res);
	}
}
