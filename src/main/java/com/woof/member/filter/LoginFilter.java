package com.woof.member.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;

@WebFilter("/member/*")
public class LoginFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		System.out.println("======================================#######有進去");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object member = session.getAttribute("member");
		
		System.out.println(member);
		
		if (member == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/frontend/member/login/login.jsp");
			return;
		} else {
			// 檢查會員是否被停權
			if (((Member) member).getMemStatus() == 0) {
				// 如果會員被停權，重定向到停權通知頁面
				res.sendRedirect(req.getContextPath() + "/frontend/member/login/membersuspend.jsp");
				return;
			} else {
				chain.doFilter(request, response);
			}
		}
	}
}