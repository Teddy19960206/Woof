package com.woof.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
		if (action != null) {
			switch (action) {
			case "/addmember":
//            正式增加Member資料
				try {
					addMember(req, res);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "/update":
//           正式修改資料
				updatemember(req, res);
				break;
			case "/getall":
				forwardPath = getAllmembers(req, res);
				break;
			case "/getone":
				getOne(req, res);
				forwardPath = "/frontend/member/list_one_member.jsp";
				break;
			case "/delete":
				Delete(req, res);
				forwardPath = "/frontend/member/list_all_member.jsp";

			default:
//           進入edit畫面先進行讀取要修改的檔案
				if (action.startsWith("/edit/")) {
					getSelectInfo(req, res);
					forwardPath = edit(req, res, result);
				} else {
					forwardPath = "/member/selectmember.jsp";
				}
				break;
			}
			req.getRequestDispatcher(forwardPath).forward(req, res);

			res.setContentType("text/html; charset=UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
			dispatcher.forward(req, res);
		}
	}

	private void getOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 從請求中取得 memNo
		String memNoStr = req.getParameter("memNo");
		String memNo = null;

		try {
			memNo = memNoStr;
		} catch (NumberFormatException e) {
			// 可以進行異常處理，例如轉發到錯誤頁面
			req.setAttribute("errorMessage", "無效的會員編號");
			req.getRequestDispatcher("/member/errorPage.jsp").forward(req, res);
			return;
		}

		// 呼叫 Service 方法取得 Member 資料
		Member member = memberService.findMemberByNo(memNo);

		if (member == null) {
			// 沒有找到會員，可以進行相應的錯誤處理
			req.setAttribute("errorMessage", "找不到指定的會員");
			req.getRequestDispatcher("/member/errorPage.jsp").forward(req, res);
			return;
		}

		// 將 Member 資料設定為請求的屬性
		req.setAttribute("member", member);

		// 轉發到適當的 JSP 頁面以顯示 Member 資料
		req.getRequestDispatcher("/frontend/member/list_one_member.jsp").forward(req, res);
	}

	private void updatemember(HttpServletRequest req, HttpServletResponse res) {
		Integer memNo = Integer.valueOf(req.getParameter("memberNo"));
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

	private void Delete(HttpServletRequest req, HttpServletResponse res) {
//		req.getParameter("memNo");
//		memberService.deleteMember(Integer.valueOf(req.getParameter("memNo")));
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

	private void addMember(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException {
//		req.setCharacterEncoding("UTF-8");
//		String memName = req.getParameter("memName");
//		String memGender = req.getParameter("memGender");
//		String memEmail = req.getParameter("memEmail");
//		String memPassword = req.getParameter("memPassword");
//		String memTel = req.getParameter("memTel");
//		String memAddress = req.getParameter("memAddress");
//		// java.sql的日期寫法
//		Date date = null;
//		try {
//			date = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("memBd"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		String memBd = req.getParameter("memBd"); // 取决于您的HTML表单中的字段名称
//
//		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd"); // 根据您的日期格式进行调整
//		java.util.Date parsedDate = format.parse(memBd);
//		java.sql.Date memBd1 = new java.sql.Date(parsedDate.getTime());
//		Integer momoPoint = Integer.valueOf(req.getParameter("momoPoint"));
//		Integer totalClass = Integer.valueOf(req.getParameter("totalClass"));
//		Integer memStatus = Integer.valueOf(req.getParameter("memStatus"));
//		memberService.addMember(memName, memGender, memEmail,memPassword, memTel,memAddress,memBd1, momoPoint, totalClass, memStatus);
//		int result;
//			result = -1;
//			if (result == 1) {
//				System.out.println("新增成功");
//				req.setAttribute("successMessage", "新增成功");
//			} else {
//				System.out.println("新增失敗");
//				req.setAttribute("errorMessage", "新增失敗");
//			}
//			res.sendRedirect(req.getContextPath() + "/frontend/member/list_all_member.jsp");
		}

//
	private String edit(HttpServletRequest req, HttpServletResponse res, Integer id) {
		return null;
//		Member member = member.findMemberByNo(id);
//		req.setAttribute("member", member);
//		return "/frontend/member/editMember.jsp";
	}
}
