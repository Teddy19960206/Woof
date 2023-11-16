package com.woof.member.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private MemberService memberService;

	@Override
	public void init() throws ServletException {
		memberService = new MemberServiceImpl();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 中文亂碼解決方法
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		// =======================登出============================//

		if (action != null) {
			switch (action) {
				case "memberlogout":

					HttpSession session = req.getSession();

					session.removeAttribute("member");
					res.sendRedirect(req.getContextPath() + "/index.jsp");
					System.out.println(session.getId() + "刪除");
					String user = (String) session.getAttribute("member");
					if (user == null) {
						System.out.println("User is not in session.");
					}
					return;
				// =================登入===============================//
				case "memberlogin":
					Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
					req.setAttribute("errorMsgs", errorMsgs);
					// 從請求中獲取會員編號和密碼
					String memNoStr = req.getParameter("memNo");
					String memPassword = req.getParameter("memPassword");
					try {
						// 驗證帳號和密碼不為空
						if (memNoStr != null && !memNoStr.trim().isEmpty() && memPassword != null
								&& !memPassword.trim().isEmpty()) {
							// 使用memberService根據會員編號查找會員
							Member member = memberService.findMemberByNo(memNoStr);
							if (member == null) {
								errorMsgs.put("loginError1", "帳號不存在");
							} else if (!member.getMemPassword().equals(memPassword)) {
								errorMsgs.put("loginError", "密碼不正確");
							} else {
								// 登入成功，將會員信息設置到session中
								HttpSession session1 = req.getSession();
								session1.setAttribute("member", member);
								// 轉發到登入成功頁面或者其他操作
								res.sendRedirect(req.getContextPath() + "/index.jsp");
							}
						} else {
							errorMsgs.put("loginError", "帳號和密碼都不能為空");
						}
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("errorMsgs", errorMsgs);
							req.getRequestDispatcher("frontend/member/login/login.jsp").forward(req, res);
						}
					} catch (Exception e) {
						e.printStackTrace();
						req.setAttribute("loginError", "系統錯誤，請聯絡系統管理員。");
						req.getRequestDispatcher("frontend/member/login/login.jsp").forward(req, res);
					}
					return;
				case "google":
					googleLogin(req ,res);
					return;
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	private void googleLogin(HttpServletRequest request , HttpServletResponse response) throws IOException {

		String id = (String) request.getAttribute("id");
		String name =(String) request.getAttribute("name");
		String email =(String) request.getAttribute("email");
		String picture = (String) request.getAttribute("picture");

		System.out.println(picture);
		Member member = memberService.findMemberByNo(id);
		HttpSession session = request.getSession();
		if (member != null && member.getMemNo().equals(id)){
			session.setAttribute("member", member);
		}else{
			Member newMember = new Member();
			newMember.setMemNo(id);
			newMember.setMemName(name);
			newMember.setMemEmail(email);

//			if (picture != null || picture.trim().length() != 0){
//				URL imageUrl = new URL(picture);
//				URLConnection connection = imageUrl.openConnection();
//				InputStream inputStream = connection.getInputStream();
//				byte[] photo = new byte[inputStream.available()];
//				inputStream.read(photo);
//				inputStream.close();
//
//				newMember.setMemPhoto(photo);
//			}

			if (picture != null && !picture.trim().isEmpty()) {
				URL imageUrl = new URL(picture);
				URLConnection connection = imageUrl.openConnection();
				InputStream inputStream = connection.getInputStream();

				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				int nRead;
				byte[] data = new byte[1024];

				while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
					buffer.write(data, 0, nRead);
				}

				buffer.flush();
				byte[] photo = buffer.toByteArray();
				buffer.close();
				inputStream.close();
				newMember.setMemPhoto(photo);
			}


//			其他為預設值
			newMember.setMemGender("M"); //預設為男生
			newMember.setMemStatus(1);
			newMember.setMomoPoint(0);
			newMember.setTotalClass(0);
			memberService.addMember(newMember);

			session.setAttribute("member", newMember);
		}
		response.sendRedirect(request.getContextPath()+"/index.jsp");

	}
}