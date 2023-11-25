package com.woof.member.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.util.MailService;
import com.woof.util.PartParsebyte;

import redis.clients.jedis.Jedis;

@WebServlet("/member1.do")
@MultipartConfig
//一個 servlet 實體對應一個 service 實體
public class MemberServlet1 extends HttpServlet {

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
			case "delete":
				delete(req, res);
				return;
			case "query":
				processQuery(req, res);
				return;
			case "valid":
				validMember(req,res);
				return;
			case "validemail":
				validEmail(req,res);
				return;
			case "deletephoto":
				deletePhoto(req,res);
				return;
			default:
				forwardPath = "/frontend/member/selectmember.jsp";
			}
		}
		req.getRequestDispatcher(forwardPath).forward(req, res);
	}

	private void deletePhoto(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String memNo = req.getParameter("memNo");
	    memberService.deleteMemberPhoto(memNo);
		 // 從資料庫重新獲取會員資料
	    Member updatedMember = memberService.findMemberByNo(memNo);
//	    updatedMember.setMemPhoto(null);
	    // 將更新的會員資料加入請求屬性
	    req.setAttribute("member", updatedMember);
//	    // 轉發到更新頁面
	    req.getRequestDispatcher("/frontend/member/login/updatemember.jsp").forward(req, res);
	}

	private void validEmail(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String memNo = req.getParameter("memNo");
		String mememail = req.getParameter("memEmail");
		Member member = memberService.findMemberByNo(memNo);
		System.out.println(mememail+"有email");
		MailService mailService = new MailService();
		String realURL = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
		mailService.sendMail(mememail,"會員驗證", MailService.valid(realURL+req.getContextPath()+ "/member1.do?action=valid&memNo="+member.getMemNo()));
		// 導到指定的URL 頁面上 把請求回應都帶過去
		String url = req.getContextPath() + "/frontend/member/login/validemail.jsp";
		req.setCharacterEncoding("UTF-8");
		res.sendRedirect(url);
	}

	private void validMember(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(req.getParameter("memNo")+"555555");
		Member member= memberService.findMemberByNo(req.getParameter("memNo"));
		member.setMemStatus(1);
		memberService.updateMember(member);
		// 導到指定的URL 頁面上 把請求回應都帶過去
		req.getRequestDispatcher( "/frontend/member/login/validsucess.jsp").forward(req, res);
	}

	private void processQuery(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Member member = memberService.findMemberByNo(req.getParameter("memNo"));
		byte[] photoByte = memberService.getPhotoById(req.getParameter("memNo"));

		res.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd").create();

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
				errorMsgs.put("memEmail", "該信箱已註冊，請重新輸入");
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
		} else {
		    // 檢查信箱長度
		    if (mememail.length() < 16 || mememail.length() > 40) {
		        errorMsgs.put("memEmail", "帳號長度必須在6到30個字符之間");
		    } else {
		        // 正則表達式，用於驗證信箱格式
		        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		        Pattern pattern = Pattern.compile(emailRegex);
		        Matcher matcher = pattern.matcher(mememail);
		        if (!matcher.matches()) {
		            errorMsgs.put("memEmail", "請輸入有效的信箱地址");
		        }
		    }
		}
		
		String mempwd = req.getParameter("memPassword").trim();
		if (mempwd == null || mempwd.trim().length() == 0) {
//			errorMsgs.put("memPassword","會員密碼請勿空白");
		}else {
		    // 加密密碼
		    String encryptedPassword = BCrypt.hashpw(mempwd, BCrypt.gensalt());
		    member.setMemPassword(encryptedPassword);
		}
		
		String memtel1 = "^09\\d{8}$";
		String memtel = req.getParameter("memTel").trim();
		if (memtel == null || memtel.trim().length() == 0) {
			errorMsgs.put("memTel","會員電話請勿空白");
		} else if(!memtel.trim().matches(memtel1)) {
			errorMsgs.put("memTel","會員電話: 只能是09開頭且總長度為10");
        }
		
		String memaddress = req.getParameter("memAddress").trim();
		if (memaddress == null || memaddress.trim().length() == 0) {
			errorMsgs.put("memAddress","會員地址請勿空白");
		}
		String membd= req.getParameter("memBd").trim();
		if (membd == null || membd.trim().length() == 0) {
			errorMsgs.put("memBd","會員生日請勿空白");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()){
			req.getRequestDispatcher("/frontend/member/login/addmember.jsp").forward(req, res);
	    return;
		}
		
		
		// 把資料給前端
		member.setMemNo(memNo);
		member.setMemName(req.getParameter("memName"));
		member.setMemGender(req.getParameter("memGender"));
		member.setMemEmail(mememail);
//		member.setMemPassword(req.getParameter("memPassword"));
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
		// 插入圖片
		Part p = req.getPart("memPhoto");
		InputStream input = p.getInputStream();
		byte[] photo = new byte[input.available()];
		input.read(photo);
		input.close();
		member.setMemPhoto(photo);
		// 將會員狀態預設2
		member.setMemStatus(2);
		member.setMomoPoint(200);
		member.setTotalClass(0);
		try {
			memberService.addMember(member);
			String url = req.getContextPath() + "/frontend/member/login/registsucess.jsp";
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
		Member originalMem =  memberService.findMemberByNo(memNo);
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
			member.setMemPassword(originalMem.getMemPassword());
		}else {
		    // 加密密碼
		    String encryptedPassword = BCrypt.hashpw(mempwd, BCrypt.gensalt());
		    member.setMemPassword(encryptedPassword);
		}
		
		String memtel1 = "^09\\d{8}$";
		String memtel = req.getParameter("memTel").trim();
		if (memtel == null || memtel.trim().length() == 0) {
			errorMsgs.put("memTel","會員電話請勿空白");
		} else if(!memtel.trim().matches(memtel1)) {
			errorMsgs.put("memTel","會員電話: 只能是09開頭且總長度為10");
        }
		
		String memaddress = req.getParameter("memAddress").trim();
		if (memaddress == null || memaddress.trim().length() == 0) {
			errorMsgs.put("memAddress","會員地址請勿空白");
		}
		
		if (!errorMsgs.isEmpty()){
			req.getRequestDispatcher("frontend/member/login/updatemember.jsp?memNo=" + memNo).forward(req, res);
	    return;
		}
		member.setMemNo(req.getParameter("memNo"));
		member.setMemName(req.getParameter("memName"));
		member.setMemGender(req.getParameter("memGender"));
		member.setMemEmail(req.getParameter("memEmail"));
//		member.setMemPassword(req.getParameter("memPassword"));
		member.setMemTel(req.getParameter("memTel"));
		member.setMemAddress(req.getParameter("memAddress"));
		// 插入圖片
		Part p = req.getPart("memPhoto");
		byte[] bytes = null;

		if (p != null && p.getSize() > 0) {

			bytes = PartParsebyte.partToByteArray(p);
		} else {
			bytes = memberService.getPhotoById(req.getParameter("memNo"));
		}
		member.setMemPhoto(bytes);
		// 生日
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
		  // 處理 momoPoint
	    String momoPointStr = req.getParameter("momoPoint");
	    if (momoPointStr != null && !momoPointStr.isEmpty()) {
	        try {
	            Integer momoPointInteger = Integer.valueOf(momoPointStr);
	            member.setMomoPoint(momoPointInteger);
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            req.setAttribute("momoPointError", "The provided point is invalid.");
	        }
	    }

	    // 處理 TotalClass
	    String totalClassStr = req.getParameter("totalClass");
	    if (totalClassStr != null && !totalClassStr.isEmpty()) {
	        try {
	            Integer totalClassInteger = Integer.valueOf(totalClassStr);
	            member.setTotalClass(totalClassInteger);
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            // 處理錯誤
	        }
	    }
	    String memStatusStr = req.getParameter("memStatus");
	    if (memStatusStr != null && !memStatusStr.isEmpty()) {
	    	try {
	    		Integer memStatusInteger = Integer.valueOf(memStatusStr);
	    		member.setMemStatus(memStatusInteger);
	    	} catch (NumberFormatException e) {
	    		e.printStackTrace();
	    		// 處理錯誤
	    	}
	    }
		memberService.updateMember(member);
		   // 假設更新操作已完成，現在重新獲取最新資料
	    Member updatedMember = memberService.findMemberByNo(member.getMemNo());
	    // 將更新後的會員資料設置為請求屬性
	    HttpSession session = req.getSession();
	    session.setAttribute("member" , member);
	    
	    req.setAttribute("member", updatedMember);
		// 導到指定的URL 頁面上 把請求回應都帶過去
		req.getRequestDispatcher( "/frontend/member/login/membercenter.jsp").forward(req, res);
	
	}

	private void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getParameter("memNo");
		memberService.delete(req.getParameter("memNo"));
		// 導到指定的URL 頁面上 把請求回應都帶過去
		req.getRequestDispatcher( "/frontend/member/login/login.jsp").forward(req, res);
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
		}

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(memberList);
		res.setContentType("application/json;charset=UTF-8");

		res.getWriter().write(json);
		return json;
	}
}
