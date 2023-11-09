package com.woof.administrator.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

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
				res.sendRedirect(req.getContextPath() + "/frontend/administrator/logout1.jsp");
				System.out.println(session1.getId() + "刪除");
				String user = (String) session1.getAttribute("user");
				if (user == null) {
					System.out.println("User is not in session.");
				}
				return;
			// =================登入===============================//
			case "administratorlogout1":
				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				req.setAttribute("errorMsgs", errorMsgs);
				// 從請求中獲取會員編號和密碼
				String adminNoStr = req.getParameter("adminNo");
				String adminPassword = req.getParameter("adminPassword");

				try {
					// 驗證帳號和密碼不為空
					if (adminNoStr != null && !adminNoStr.trim().isEmpty() && adminPassword != null
							&& !adminPassword.trim().isEmpty()) {
						// 使用administratorService根據會員編號查找會員
						Administrator administrator = administratorService.findAdministratorByAdminNo(adminNoStr);

						// 檢查會員是否存在以及密碼是否匹配
						if (administrator != null && administrator.getAdminPassword().equals(adminPassword)) {
							// 登入成功，將會員信息設置到session中
							HttpSession session = req.getSession();
							session.setAttribute("Administrator", administrator);
							

							// 轉發到登入成功頁面或者其他操作
							res.sendRedirect(req.getContextPath() + "/backend/index.html");
						} else {
							// 登入失敗，設置錯誤信息並轉發到登入頁面
							req.setAttribute("loginError", "帳號或密碼不正確");
							RequestDispatcher dispatcher = req
									.getRequestDispatcher("/frontend/administrator/logout1.jsp");
							dispatcher.forward(req, res);
						}
					} else {
						// 帳號或密碼為空，設置錯誤信息並轉發到登入頁面
						req.setAttribute("loginError", "帳號和密碼都不能為空");
						RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/administrator/logout1.jsp");
						dispatcher.forward(req, res);
					}
				} catch (Exception e) {
					// 處理其他潛在錯誤
					e.printStackTrace();
					req.setAttribute("error", "系統錯誤，請聯絡系統管理員。");
					RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/administrator/errorPage.jsp");
					dispatcher.forward(req, res);
				}
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}
}
