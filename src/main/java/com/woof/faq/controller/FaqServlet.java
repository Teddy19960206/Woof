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

@WebServlet("/faq/*")
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
			
		case "getAll2":
			forwardPath = getAll2(req, resp);
			break;	

		case "addfaq":
			forwardPath = addfaq(req, resp);
			break;

		case "updatefaq":
			forwardPath = updatefaq(req, resp);
			break;

		case "deletefaq":
			forwardPath = deletefaq(req, resp);
			break;

		default:
			forwardPath = "/backend/index.jsp";
		}

		resp.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, resp);

	}

	private String getAll(HttpServletRequest req, HttpServletResponse resp) {

//		List<Faq> all = faqService.getAllFaq();
//		req.setAttribute("all", all);
		
		String page = req.getParameter("page");
		int currentPage = (page == null) ? 1 : Integer.parseInt(page);
		
//		if (req.getSession().getAttribute("faqPageQty") == null) {
			int faqPageQty = faqService.getPageTotal();
			req.getSession().setAttribute("faqPageQty", faqPageQty);
//		}
		
		List<Faq> all = faqService.getAllFaq(currentPage);
		
		req.setAttribute("all", all);
		req.setAttribute("currentPage", currentPage);
		
		return "/backend/faq/getAll.jsp";
	}

	
	private String getAll2(HttpServletRequest req, HttpServletResponse resp) {

		List<Faq> all = faqService.getAllFaq();
		
		req.setAttribute("all", all);		
		
		return "/frontend/faqfront/faqfront.jsp";
	}
	
	
	private String addfaq(HttpServletRequest req, HttpServletResponse resp) {

		String faqClass = req.getParameter("faqClass");
		String faqTitle = req.getParameter("faqTitle");
		String faqContent = req.getParameter("faqContent");

		// 如果有確定進入資料庫會有流水編號，再去找流水編號的值，顯示在jsp
		int saved = (Integer) faqService.addFaq(faqClass, faqTitle, faqContent);
		var result = faqService.findByFaqNo(saved);

		if (saved > 0) {
//		    return 1; // FAQ添加成功
	        System.out.println("新增成功");
	    } else {
//	        return -1; // FAQ添加失败
	        System.out.println("新增失敗");
	    }
		
		req.setAttribute("result", result);
		
		return "/backend/faq/addfaq.jsp";
	}

	private String updatefaq(HttpServletRequest req, HttpServletResponse resp) {

		int faqNo = Integer.parseInt(req.getParameter("faqNo"));
		String faqClass = req.getParameter("faqClass");
		String faqTitle = req.getParameter("faqTitle");
		String faqContent = req.getParameter("faqContent");

		int result = faqService.updateFaq(faqNo, faqClass, faqTitle, faqContent);		
//		req.setAttribute("result", result);
		
//		System.out.println(result);

		if (result > 0) {
			System.out.println("更新成功");
		} else {
			System.out.println("更新失敗");
		}
		
		resp.setContentType("application/json;charset=UTF-8");
		return "/backend/faq/faqfirst.jsp";
	}

	private String deletefaq(HttpServletRequest req, HttpServletResponse resp) {

		Integer faqNo = Integer.parseInt(req.getParameter("faqNo"));
		// 檢查 FAQ 編號是否有效
		if (faqNo == null || faqNo <= 0) {
			System.out.println("無效的 FAQ 編號");
			return null;
		}

		int result = faqService.deleteFaq(faqNo);

		if (result == 1) {
			System.out.println("刪除成功");

		} else {
			System.out.println("刪除失敗");
		}

		return "/faq?action=getAll";
	}

}
