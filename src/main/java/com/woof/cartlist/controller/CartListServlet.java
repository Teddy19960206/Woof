package com.woof.cartlist.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.member.entity.Member;

import redis.clients.jedis.Jedis;

@WebServlet("/cartlist")
public class CartListServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        Jedis jedis = null;

        try {
            // 初始化 Jedis 並連接到 Redis
            jedis = new Jedis("localhost", 6379);

            if ("getCart".equals(action)) {
           	
            	Member member = (Member) request.getSession(false).getAttribute("member");
            	if (member == null) {
            	    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "用戶未登入");
            	    return;
            	}
            	String memNo = member.getMemNo();
                // 從 Redis 獲取當前購物車
                String cartJson = jedis.get(memNo);
                
                if (cartJson == null) {
                    cartJson = "[]"; // 如果購物車為空，返回空的 JSON 數組
                }

                //顯示為什麼在前端跑不出來
                System.out.println("redis cartJson: " + cartJson);
                
                
                
                // 設置響應
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(cartJson); // 傳回購物車的 JSON
  
            }
        } catch (Exception e) {
            // 處理錯誤
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } finally {
            // 確保 jedis 在使用後被關閉
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
