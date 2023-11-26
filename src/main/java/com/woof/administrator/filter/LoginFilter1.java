package com.woof.administrator.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import com.woof.administrator.entity.Administrator;
import com.woof.member.entity.Member;
@WebFilter("/administrator/*")
public class LoginFilter1 implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】

		Object admin = session.getAttribute("administrator");

		if (admin  == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/frontend/administratorlogin/logout1.jsp");
			return;
		} else {
			// 檢查管理員是否被停權
			if (((Administrator) admin).getAdminStatus() == 2||((Administrator) admin).getAdminStatus() == 0) {
				// 如果管理員被停權，重定向到停權通知頁面
				res.sendRedirect(req.getContextPath() + "/frontend/administratorlogin/adminsuspend.jsp");
				return;
			} else {
				chain.doFilter(request, response);
			}
		}
	}
}