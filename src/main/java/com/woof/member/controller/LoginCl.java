package com.woof.member.controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/LoginCl")
public class LoginCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 中文亂碼解決方法
		response.setContentType("text/html;charset=gb2312");
//防止非法登入    得到session
		HttpSession httpSession = request.getSession(true);
		// 修改session的存在時間為20s
		httpSession.setMaxInactiveInterval(20);
		httpSession.setAttribute("pass", "ok");
		// 獲取使用者名稱的賬號和密碼
		String u = null;
		// 針對jsp 其username為username1
		u = request.getParameter("username1");
		String p = null;
		p = request.getParameter("passwd");
		// 得到提交的驗證碼
		String code = request.getParameter("code");
		// 獲取session驗證碼
		HttpSession session = request.getSession();
		String randStr = (String) session.getAttribute("randStr");
		response.setCharacterEncoding("gb2312");
		// 當賬號資訊和驗證碼輸入正確時才可以訪問
		try {

			// 啟動mysql驅動器
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");
			String sql = "select * from usert where MEM_NO=? and MEM_PASSWORD=?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, u);
			preparedStatement.setString(2, p);
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next()) {

				response.getWriter().println("<a href=LoginServlet.jsp>抱歉：帳號或密碼錯誤，請注意核實資訊重新輸入</a>");
				return;
			} else {

				// 獲取到
				if (code.equals(randStr)) {

					String keep = request.getParameter("keep");
					// 勾選了兩週內免登陸選項
					if (keep != null) {

						// 建立cookie
						Cookie cookie1 = new Cookie("username", u);
						Cookie cookie2 = new Cookie("password", p);
						// 設定關聯路徑
						cookie1.setPath(request.getContextPath());
						cookie2.setPath(request.getContextPath());
						// 設定cookie的消亡時間 兩週
						cookie1.setMaxAge(2 * 7 * 24 * 60 * 60);
						cookie1.setMaxAge(2 * 7 * 24 * 60 * 60);
						// 把cookie資訊寫給瀏覽器
						response.addCookie(cookie1);
						response.addCookie(cookie2);
					}
					// 跳轉到歡迎介面
					response.sendRedirect("welcome.jsp?uname=" + u + " &password=" + p);
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}
}
