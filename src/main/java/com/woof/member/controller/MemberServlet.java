package com.woof.member.controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.util.MailService;
import com.woof.util.PartParsebyte;

@WebServlet("/member.do")
@MultipartConfig
//一個 servlet 實體對應一個 service 實體
public class MemberServlet extends HttpServlet {

	private MemberService memberService;

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

		String forwardPath = "";
		if (action != null) {
			switch (action) {
			case "add":
//            正式增加Member資料
				try {
					addMember(req, res);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return;
			case "update":
//           正式修改資料
				try {
					updateMember(req, res);
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();

				}
				return;
			case "getall":
				forwardPath = getAllmembers(req, res);
				break;
			case "getone":
				getOne(req, res);
				return;
			case "delete":
				delete(req, res);
				return;
			case "query":
				processQuery(req, res);
				return;
				
			default:
				forwardPath = "/backend/member/selectmember.jsp";
			}
		}
		req.getRequestDispatcher(forwardPath).forward(req, res);
	}
	private void processQuery(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Member member = memberService.findMemberByNo(req.getParameter("memNo"));
		byte[]photoByte = memberService.getPhotoById(req.getParameter("memNo"));

		res.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd")
				.create();

		String str = gson.toJson(member);
		// 將 JSON 字符串轉換為 UTF-8 編碼的字節數組
		byte[] photoBytes = str.getBytes(StandardCharsets.UTF_8);
//		System.out.println(str);
		// 回應給前端
		PrintWriter out = res.getWriter();
		out.write(str);
	}

	private void addMember(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ParseException, ServletException {
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		List<Member> members = memberService.getAllMembers();
		
		Member member = new Member();
		String mememail = req.getParameter("memEmail");
		String memNo = req.getParameter("memNo");
		
		
		for(Member mem :  members) {
			if(mem.getMemNo().equals(memNo)) {
				errorMsgs.put("memNo","此帳號已註冊，請重新輸入");
			}
			if(mem.getMemEmail().equals(mememail)) {
				errorMsgs.put("memEmail", "不能使用該信箱");
			}
			if(!errorMsgs.isEmpty()) {
				break;
			}
		}
		
		if(!errorMsgs.isEmpty()) {
			
			req.getRequestDispatcher("frontend/member/login/addmember.jsp").forward(req, res);
		    return;
		}
		/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		if (memNo == null || memNo.trim().length() == 0) {
			errorMsgs.put("memNo","會員帳號請勿空白");
		}
		
		String memname = req.getParameter("memName");
		String memnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_\s)]{1,50}$";
		if (memname == null || memname.trim().length() == 0) {
			errorMsgs.put("memName","會員姓名: 請勿空白");
		} else if(!memname.trim().matches(memnameReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("memName","會員姓名: 只能是中、英文字母、數字、空格和_ , 且長度必需在1到50之間");
        }
		
		String memgender = req.getParameter("memGender").trim();
		if (memgender == null || memgender.trim().length() == 0) {
			errorMsgs.put("memGender","會員性別請勿空白");
		}
		
		if (mememail == null || mememail.trim().length() == 0) {
			errorMsgs.put("memEmail","會員信箱請勿空白");
		}
		
		String mempwd = req.getParameter("memPassword").trim();
		if (mempwd == null || mempwd.trim().length() == 0) {
			errorMsgs.put("memPassword","會員密碼請勿空白");
		}
		
		String memtel = req.getParameter("memTel").trim();
		if (memtel == null || memtel.trim().length() == 0) {
			errorMsgs.put("memTel","會員電話請勿空白");
		} 
		
		String memaddress = req.getParameter("memAddress").trim();
		if (memaddress == null || memaddress.trim().length() == 0) {
			errorMsgs.put("memAddress","會員地址請勿空白");
		}

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()){
			req.getRequestDispatcher("backend/member/addmember.jsp").forward(req, res);
	    return;
		}
		// 把資料給前端
		member.setMemNo(req.getParameter("memNo"));
		member.setMemName(req.getParameter("memName"));
		member.setMemGender(req.getParameter("memGender"));
		member.setMemEmail(req.getParameter("memEmail"));
		member.setMemPassword(req.getParameter("memPassword"));
		member.setMemTel(req.getParameter("memTel"));
		member.setMemAddress(req.getParameter("memAddress"));
		// java.sql的日期寫法
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("memBd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		member.setMemBd(sqlDate);
		//	插入圖片
		  Part p = req.getPart("memPhoto");
		  InputStream input = p.getInputStream();
		  byte[] photo = new byte[input.available()];
		  input.read(photo);
		  input.close();
		member.setMemPhoto(photo);
		member.setMomoPoint(Integer.valueOf(req.getParameter("momoPoint")));
		member.setTotalClass(Integer.valueOf(req.getParameter("totalClass")));
		member.setMemStatus(Integer.valueOf(req.getParameter("memStatus")));
		
		try {
			memberService.addMember(member);
			// 導到指定的URL 頁面上 把請求回應都帶過去
			String url = req.getContextPath() + "/backend/member/list_all_member.jsp";
			req.setCharacterEncoding("UTF-8");
			res.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateMember(HttpServletRequest req, HttpServletResponse res)
			throws ParseException, IOException, ServletException {
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		Member member = new Member();
		String mememail = req.getParameter("memEmail");
		String memNo = req.getParameter("memNo");
		/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		if (memNo == null || memNo.trim().length() == 0) {
			errorMsgs.put("memNo","會員帳號請勿空白");
		}
		
		String memname = req.getParameter("memName");
		String memnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_\s)]{1,50}$";
		if (memname == null || memname.trim().length() == 0) {
			errorMsgs.put("memName","會員姓名: 請勿空白");
		} else if(!memname.trim().matches(memnameReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("memName","會員姓名: 只能是中、英文字母、數字、空格和_ , 且長度必需在1到50之間");
        }
		
		String memgender = req.getParameter("memGender").trim();
		if (memgender == null || memgender.trim().length() == 0) {
			errorMsgs.put("memGender","會員性別請勿空白");
		}
		
		if (mememail == null || mememail.trim().length() == 0) {
			errorMsgs.put("memEmail","會員信箱請勿空白");
		}
		
		String mempwd = req.getParameter("memPassword").trim();
		if (mempwd == null || mempwd.trim().length() == 0) {
			errorMsgs.put("memPassword","會員密碼請勿空白");
		}
		
		String memtel = req.getParameter("memTel").trim();
		if (memtel == null || memtel.trim().length() == 0) {
			errorMsgs.put("memTel","會員電話請勿空白");
		} 
		
		String memaddress = req.getParameter("memAddress").trim();
		if (memaddress == null || memaddress.trim().length() == 0) {
			errorMsgs.put("memAddress","會員地址請勿空白");
		}

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()){
			req.getRequestDispatcher("backend/member/updatemember.jsp").forward(req, res);
	    return;
		}
		//=======================================================//
		member.setMemNo(req.getParameter("memNo"));
		member.setMemName(req.getParameter("memName"));
		member.setMemGender(req.getParameter("memGender"));
		member.setMemEmail(req.getParameter("memEmail"));
		member.setMemPassword(req.getParameter("memPassword"));
		member.setMemTel(req.getParameter("memTel"));
		member.setMemAddress(req.getParameter("memAddress"));
		Part p = req.getPart("memPhoto");
		byte[] bytes = null;
		
		if (p != null && p.getSize() > 0) {
		
	        bytes = PartParsebyte.partToByteArray(p);
		}else {
			bytes = memberService.getPhotoById(req.getParameter("memNo"));
		}
		member.setMemPhoto(bytes);
        //生日
		String memBdString = req.getParameter("memBd");

		if (memBdString != null && !memBdString.isEmpty()) {
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(memBdString);
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				member.setMemBd(sqlDate);
			} catch (ParseException e) {
				e.printStackTrace();
				req.setAttribute("dateError", "The provided date is invalid.");
			}
		} else {
			req.setAttribute("memBd=null", memBdString);
		}
		member.setMomoPoint(Integer.valueOf(req.getParameter("momoPoint")));
		member.setTotalClass(Integer.valueOf(req.getParameter("totalClass")));
		member.setMemStatus(Integer.valueOf(req.getParameter("memStatus")));
		memberService.updateMember(member);
		// 假設更新操作已完成，現在重新獲取最新資料
	    Member updatedMember = memberService.findMemberByNo(member.getMemNo());
	    System.out.println(member.getMemNo()+"=============");
	    // 將更新後的會員資料設置為請求屬性
	    req.setAttribute("member", updatedMember);
		// 導到指定的URL 頁面上 把請求回應都帶過去
		req.getRequestDispatcher("/backend/member/list_all_member.jsp").forward(req, res);
//		String url = req.getContextPath() + "/backend/member/list_all_member.jsp";
//		res.sendRedirect(url);
	}

	private void getOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		MemberService memberService = new MemberServiceImpl();

		try {

			req.setCharacterEncoding("UTF-8");
			// 獲取所有成員
//			List<Member> allMembers = memberService.getAllMembers();
//			req.setAttribute("Members", allMembers);
			
			// 獲取特定成員
			String memNoStr = req.getParameter("memNo");

			if (memNoStr != null && !memNoStr.trim().isEmpty()) {
				try {

					Member member = memberService.findMemberByNo(memNoStr);
					req.setAttribute("member", member);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}

			} else {
				req.setAttribute("error", "Invalid member number provided.");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/member/errorPage.jsp");
				dispatcher.forward(req, res);
				return;
			}
			// 設置編碼和轉發到指定的JSP頁面
			req.setCharacterEncoding("UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/backend/member/list_one_member.jsp");
			dispatcher.forward(req, res);

		} catch (Exception e) {
			// 處理其他潛在錯誤
			e.printStackTrace();

		}
	}

	private void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getParameter("memNo");
		memberService.deletePhoto(req.getParameter("memNo"));
		// 導到指定的URL 頁面上 把請求回應都帶過去
		String url = req.getContextPath() + "/backend/member/list_all_member.jsp";
		req.setCharacterEncoding("UTF-8");
		res.sendRedirect(url);
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
}
