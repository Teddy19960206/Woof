package com.woof.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;

@WebServlet("/member/*")
@MultipartConfig
//一個 servlet 實體對應一個 service 實體
public class MemberServlet extends HttpServlet {

	private MemberServiceImpl memberService;

	@Override
	public void init() throws ServletException {
		memberService = new MemberServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		res.getWriter().println(action);

		int secondSlashIndex = action.indexOf('/', 2);
		Integer result = null;
		if (secondSlashIndex > 0) {
			result = Integer.valueOf(action.substring(secondSlashIndex + 1));
		}

		String forwardPath = "";

		switch (action) {
		case "/addpage":
//            預先載入可選擇的選項
			forwardPath = getSelectInfo(req, res);
			break;
		case "/addmember":
//            正式增加Member資料
			addMember(req, res);
			return;
		case "/modified":
//           正式修改資料
			modified(req, res);
			return;
		case "/getall":
			forwardPath = getAllmembers(req, res);
			return;
		default:
//           進入edit畫面先進行讀取要修改的檔案
			if (action.startsWith("/edit/")) {
				getSelectInfo(req, res);
				forwardPath = edit(req, res, result);
			} else {
				forwardPath = "/member/selectmember.jsp";
			}
			req.getRequestDispatcher(forwardPath).forward(req, res);
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}


	private String getAllmembers(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8");

		String member = req.getParameter("member");

		List<Member> memberList = null;

		if (member != null) {
			if ("0".equals(member)) {
				memberList = memberService.getAllMembers();
			}
		} else {
//	            異常判斷
		}

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(memberList);
		res.setContentType("application/json;charset=UTF-8");

		res.getWriter().write(json);
		return json;
	}
	private String getSelectInfo(HttpServletRequest req, HttpServletResponse res) {

		MemberService memberservice = new MemberServiceImpl();
		java.util.List<Member> allMember = memberService.getAllMembers();
		req.setAttribute("members", allMember);
		return "/frontend/member/selectmember.jsp";
	}

	private void addMember(HttpServletRequest req, HttpServletResponse res) {

	}
//
	private String edit(HttpServletRequest req, HttpServletResponse res, Integer id) {
		return null;
//		Member member = member.findMemberByNo(id);
//		req.setAttribute("member", member);
//		return "/frontend/member/editMember.jsp";
	}
//
	private void modified(HttpServletRequest req, HttpServletResponse res) throws IOException {
//		Integer memNo = Integer.valueOf(req.getParameter("memberNo"));
//		byte[] bytes = null;
//
//		Part filePart = req.getPart("photo");
//		if (filePart != null && filePart.getSize() > 0) {
//
//			bytes = PartParsebyte.partToByteArray(filePart);
//
//		} else {
//			bytes = memberService.getPhotoById(Integer.valueOf(req.getParameter("groupCourseNo")));
//		}
//
//		String content = req.getParameter("content");
//
//		Integer status = Integer.valueOf(req.getParameter("status"));
//
//		int result = memberService.modify(memNo, content, content, bytes, content, content, content, content, null, status, result, result);
//
//		if (result == 1) {
//			System.out.println("更新成功");
//		} else {
//			System.out.println("更新失敗");
//		}
//
//		res.sendRedirect(req.getServletContext().getContextPath() + "/frontend/member/list_all_member.jsp");
//
	}

}
