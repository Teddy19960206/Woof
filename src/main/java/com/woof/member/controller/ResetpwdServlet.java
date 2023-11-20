package com.woof.member.controller;

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

import org.mindrot.jbcrypt.BCrypt;

import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.util.MailService;

import redis.clients.jedis.Jedis;

@WebServlet("/resetPassword.do")
@MultipartConfig
public class ResetpwdServlet extends HttpServlet {
	private MemberService memberService;

	@Override
	public void init() throws ServletException {
		memberService = new MemberServiceImpl();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 中文亂碼解決方法
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		// =================傳mail================================//
		if ("reset".equals(action)) {
			MailService mailService = new MailService();
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			List<Member> members = memberService.getAllMembers();
			String mememail = req.getParameter("memEmail");
			
			boolean isEmailRegistered = false;
			for(Member mem :  members) {
				if(mem.getMemEmail().equals(mememail)) {
					isEmailRegistered = true;
			        break;
				}
				}
			if(!isEmailRegistered) {
			    errorMsgs.put("memEmail", "該信箱未註冊過");
			}
			req.setAttribute("errorMsgs", errorMsgs);
			if(!errorMsgs.isEmpty()) {
				req.getRequestDispatcher("frontend/member/login/forgotpwd.jsp").forward(req, res);
			    return;
			}
			// 生成令牌
			String token = generateToken(); // 實現這個方法來生成令牌
			String realURL = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
			// 創建重置連結
			String resetLink = realURL + req.getContextPath()
					+ "/frontend/member/login/changepwd.jsp?action=reset&memEmail=" + mememail +"&token=" + token;
			// 將令牌保存到某處，例如數據庫，與用戶的郵箱地址關聯
			// 連接到 Redis
			try (Jedis jedis = new Jedis("localhost", 6379)) {
				// 保存令牌到 Redis，與郵箱地址關聯
				jedis.set(mememail, token);
				// 設置令牌的過期時間，例如 30 分鐘
				jedis.expire(mememail, 30 * 60);
				// ... 發送郵件的代碼 ...
			} catch (Exception e) {
				e.printStackTrace();
				// 處理 Redis 連接或操作異常
			}
			// 發送郵件
			mailService.sendMail(mememail, "忘記密碼", MailService.passwordResetEmail(resetLink));

			// 導到指定的URL 頁面上 把請求回應都帶過去
//			String url = req.getContextPath() + "/frontend/member/login/forgotpwdmail.jsp";
//			res.sendRedirect(url);
			req.getRequestDispatcher("frontend/member/login/forgotpwdmail.jsp").forward(req, res);
		}
		// ================更改密碼======================//
		if ("changepwd".equals(action)) {
			String memEmail = req.getParameter("memEmail");
			String tokenFromRequest = req.getParameter("token");
			String memPassword = req.getParameter("memPassword");
			String confirmPassword = req.getParameter("confirmMemPassword");
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			 // 加密密碼
		    String encryptedPassword = BCrypt.hashpw(memPassword, BCrypt.gensalt());
			// 新增: 比較 memPassword 和 confirmPassword 是否一致
		    if (!memPassword.equals(confirmPassword)) {
		    	req.setAttribute("errorMsgs", errorMsgs);
		        // 如果不一致，設置錯誤訊息並重定向到錯誤頁面或顯示錯誤
		        errorMsgs.put("memPassword","密碼不一致");
		        req.getRequestDispatcher("/frontend/member/login/changepwd.jsp").forward(req, res);
		        return;
		    }
			System.out.println(memEmail + "/ memEmail");
			System.out.println(memPassword + "/ memPassword");
			System.out.println(tokenFromRequest+ "/ tokenFromRequest");
			// 連接到 Redis
			String tokenFromRedis = null;
			if (memEmail != null && !memEmail.isEmpty()) {
				try (Jedis jedis = new Jedis("localhost", 6379)) {
				    tokenFromRedis = jedis.get(memEmail); // 正確獲取並賦值
				} catch (Exception e) {
				    e.printStackTrace();
				    return;
				}
			}else {
			    // 處理 mememail 為空的情況
				System.out.println("mememail is null");
			}
			
			if (tokenFromRequest != null && tokenFromRedis != null && tokenFromRequest.equals(tokenFromRedis)) {
				// 令牌有效，繼續執行更改密碼的操作
				Member member = memberService.findMemberByEmail(memEmail);
				System.out.println(member+ " / member");
				member.setMemPassword(encryptedPassword);
//				member.setMemPassword(memPassword);
				memberService.updateMember(member);
				// 導到指定的URL 頁面上 把請求回應都帶過去
				req.getRequestDispatcher("/frontend/member/login/changepwdsucess.jsp").forward(req, res);
			} else {
				req.getRequestDispatcher("/frontend/member/login/errorPage.jsp").forward(req, res);
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