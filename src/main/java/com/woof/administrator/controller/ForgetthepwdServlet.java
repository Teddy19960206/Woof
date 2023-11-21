package com.woof.administrator.controller;



	import java.io.IOException;
	import java.util.LinkedHashMap;
	import java.util.List;
	import java.util.Map;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.MultipartConfig;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

import com.woof.administrator.entity.Administrator;
import com.woof.administrator.service.AdministratorService;
import com.woof.administrator.service.AdministratorServiceImpl;
import com.woof.util.MailService;

	import redis.clients.jedis.Jedis;

	@WebServlet("/forgetthepwd.do")
	@MultipartConfig
	public class ForgetthepwdServlet  extends HttpServlet {
		private AdministratorService administratorService;

		@Override
		public void init() throws ServletException {
			administratorService = new AdministratorServiceImpl();
		}

		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

			// 中文亂碼解決方法
			res.setContentType("text/html;charset=UTF-8");
			String action = req.getParameter("action");
			// =================傳mail================================//
			if ("reset".equals(action)) {
				MailService mailService = new MailService();
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				List<Administrator> administrators = administratorService.getAllAdministrators();
				String adminemail = req.getParameter("adminEmail");
				boolean isEmailRegistered = false;
				for(Administrator admin :  administrators) {
					if(admin.getAdminEmail().equals(adminemail)) {
						isEmailRegistered = true;
				        break;
					}
				}
				if(!isEmailRegistered) {
				    errorMsgs.put("adminEmail", "該信箱未註冊過");
				}
				if(!errorMsgs.isEmpty()) {
					
					req.getRequestDispatcher("frontend/administrator/forgotpwd.jsp").forward(req, res);
				    return;
				}
				// 生成令牌
				String token = generateToken(); // 實現這個方法來生成令牌
				String realURL = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
				// 創建重置連結
				String resetLink = realURL + req.getContextPath()
						+ "/frontend/administrator/changepwd.jsp?action=reset&adminEmail=" + adminemail +"&token=" + token;
				// 將令牌保存到某處，例如數據庫，與用戶的郵箱地址關聯
				// 連接到 Redis
				try (Jedis jedis = new Jedis("localhost", 6379)) {
					// 保存令牌到 Redis，與郵箱地址關聯
					jedis.set(adminemail, token);
					// 設置令牌的過期時間，例如 30 分鐘
					jedis.expire(adminemail, 30 * 60);
					// ... 發送郵件的代碼 ...
				} catch (Exception e) {
					e.printStackTrace();
					// 處理 Redis 連接或操作異常
				}
				// 發送郵件
				mailService.sendMail(adminemail, "忘記密碼", MailService.passwordResetEmail(resetLink));

				// 導到指定的URL 頁面上 把請求回應都帶過去
				String url = req.getContextPath() + "/frontend/administrator/validemail.jsp";
				res.sendRedirect(url);
			}
			// ================更改密碼======================//
			if ("changepwd".equals(action)) {
				String adminEmail = req.getParameter("adminEmail");
				String tokenFromRequest = req.getParameter("token");
				String adminPassword = req.getParameter("adminPassword");
				String confirmPassword = req.getParameter("confirmadminPassword");
				// 新增: 比較 memPassword 和 confirmPassword 是否一致
			    if (!adminPassword.equals(confirmPassword)) {
			        // 如果不一致，設置錯誤訊息並重定向到錯誤頁面或顯示錯誤
			        req.setAttribute("errorMsg", "密碼和確認密碼不匹配");
			        req.getRequestDispatcher("/frontend/administrator/changepwd.jsp").forward(req, res);
			        return;
			    }
				System.out.println(adminEmail + "/ adminEmail");
				System.out.println(adminPassword + "/ adminPassword");
				System.out.println(tokenFromRequest+ "/ tokenFromRequest");
				// 連接到 Redis
				String tokenFromRedis = null;
				if (adminEmail != null && !adminEmail.isEmpty()) {
					try (Jedis jedis = new Jedis("localhost", 6379)) {
					    tokenFromRedis = jedis.get(adminEmail); // 正確獲取並賦值
					} catch (Exception e) {
					    e.printStackTrace();
					    return;
					}
				}else {
				    // 處理 mememail 為空的情況
					System.out.println("adminemail is null");
				}
				
				if (tokenFromRequest != null && tokenFromRedis != null && tokenFromRequest.equals(tokenFromRedis)) {
					// 令牌有效，繼續執行更改密碼的操作
					Administrator admin = administratorService.findAdministratorByEmail(adminEmail);
					System.out.println(admin+ " / admin");
					admin.setAdminPassword(adminPassword);
					administratorService.updateAdministrator(admin);
					// 導到指定的URL 頁面上 把請求回應都帶過去
					req.getRequestDispatcher("/frontend/administrator/changepwdsucess.jsp").forward(req, res);
				} else {
					req.getRequestDispatcher("/frontend/administrator/errorPage.jsp").forward(req, res);
					return;
				}
			}
		}

		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			this.doPost(req, res);
		}

		// 實現生成令牌的方法
		private String generateToken() {
			// 實現令牌生成邏輯，例如使用 UUID
			return java.util.UUID.randomUUID().toString();
		}
	}

