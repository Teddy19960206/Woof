package com.woof.faq.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.faq.entity.Faq;
import com.woof.faq.service.FaqService;
import com.woof.faq.service.FaqServiceImpl;

@WebServlet("/faq")
public class FaqServlet extends HttpServlet {

	private FaqService faqService;

	@Override
	public void init() throws ServletException {
		faqService = new FaqServiceImpl();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "getAll":
			forwardPath = getAll(req, resp);
			break;
		case "addfaq":
			forwardPath = addfaq(req, resp);
			break;

		default:
			forwardPath = "/backend/faq/getAll.jsp";
		}

		resp.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, resp);

	}

	private String getAll(HttpServletRequest req, HttpServletResponse resp) {

		List<Faq> all = faqService.getAllFaq();
		req.setAttribute("all", all);

		return "/backend/faq/getAll.jsp";
	}
	
	private String addfaq(HttpServletRequest req, HttpServletResponse resp) {
		
		String faqClass = req.getParameter("faqClass");
		String faqTitle = req.getParameter("faqTitle");
		String faqContent = req.getParameter("faqContent");

		System.out.println("qqqqq");
		
		int result = (Integer)faqService.addFaq(faqClass, faqTitle, faqContent);
	    
	    if (result > 0) {
	        return "1"; // FAQ添加成功
	    } else {
	        return "-1"; // FAQ添加失败
	    }

	}

}
