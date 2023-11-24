package com.woof.administrator.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.mindrot.jbcrypt.BCrypt;

import com.woof.administrator.service.AdministratorServiceImpl;
import com.woof.administrator.entity.Administrator;
import com.woof.administrator.service.AdministratorService;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/logout1")
public class LoginServlet extends HttpServlet {

	private AdministratorService administratorService;

	@Override
	public void init() throws ServletException {
		administratorService = new AdministratorServiceImpl();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 中文亂碼解決方法
		res.setContentType("text/html;charset=UTF-8");
		String action1 = req.getParameter("action1");
		// =======================登出============================//

		if (action1 != null) {
			switch (action1) {
			case "administratorlogout":

				HttpSession session1 = req.getSession();

				session1.removeAttribute("administrator");
				res.sendRedirect(req.getContextPath() + "/frontend/administratorlogin/logout1.jsp");
				System.out.println(session1.getId() + "刪除");
				String user = (String) session1.getAttribute("administrator");
				if (user == null) {
					System.out.println("User is not in session.");
				}
				return;
			// =================登入===============================//
			case "administratorlogout1":
				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				req.setAttribute("errorMsgs", errorMsgs);
				// 從請求中獲取管理員no和密碼
				String adminNoStr = req.getParameter("adminNo");
				String adminPassword = req.getParameter("adminPassword");

				try {
					// 驗證帳號和密碼不為空
					if (adminNoStr != null && !adminNoStr.trim().isEmpty() && adminPassword != null
							&& !adminPassword.trim().isEmpty()) {
						// 使用administratorService根據管理員no查找會員
						Administrator administrator = administratorService.findAdministratorByAdminNo(adminNoStr);
						System.out.println(administrator);
						if (administrator == null) {
							errorMsgs.put("loginError1", "帳號未註冊");
						}  else {
		                    String encryptedPassword = administrator.getAdminPassword(); // 從資料庫獲取加密後的密碼
		                    if (BCrypt.checkpw(adminPassword, encryptedPassword)) {
		                        // 密碼匹配，處理登錄成功的邏輯
		                        HttpSession session2 = req.getSession();
		                        session2.setAttribute("administrator", administrator);
		                        res.sendRedirect(req.getContextPath() + "/backend/index.jsp");
		                    } else {
		                        errorMsgs.put("loginError", "帳號密碼有誤");
		                    }
						}
					
							
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("errorMsgs", errorMsgs);
							req.getRequestDispatcher("frontend/administratorlogin/logout1.jsp").forward(req, res);
						}
					} 
						}
					catch (Exception e) {
					e.printStackTrace();
					 errorMsgs.put("loginError1", "帳號密碼有誤");
					req.getRequestDispatcher("frontend/administratorlogin/logout1.jsp").forward(req, res);
					}
					return;
			
			}
		}
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}
}
