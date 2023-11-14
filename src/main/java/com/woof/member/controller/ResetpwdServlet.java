package com.woof.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.util.MailService;

import redis.clients.jedis.Jedis;
@WebServlet("/resetPassword.do")
public class ResetpwdServlet extends HttpServlet{
	private MemberService memberService;

	@Override
	public void init() throws ServletException {
		memberService = new MemberServiceImpl();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 中文亂碼解決方法
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		//=================傳mail================================//
		if ("reset".equals(action)) {
            MailService mailService = new MailService();
            String mememail = req.getParameter("memEmail");

            // 生成令牌
            String token = generateToken(); // 實現這個方法來生成令牌

            // 創建重置連結
            String resetLink = req.getRequestURL() + "?action=reset&token=" + token;

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

            // 重定向到登錄頁面或確認頁面
            String url = req.getContextPath() + "/frontend/member/login/login.jsp";
            res.sendRedirect(url);
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