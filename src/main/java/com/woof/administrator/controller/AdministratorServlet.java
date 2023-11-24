package com.woof.administrator.controller;

import com.woof.administrator.service.AdministratorService;
import com.woof.administrator.service.AdministratorServiceImpl;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.administrator.service.AdministratorService;
import com.woof.administrator.service.AdministratorServiceImpl;
import com.alibaba.fastjson2.JSONObject;
import com.google.gson.JsonObject;
import com.woof.administrator.entity.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//使用 @WebServlet註解來指定這個 Servlet 的訪問路徑。
@WebServlet("/administrator.do")
//使用 @MultipartConfig 註解來支持多部分請求，這在處理包含檔案上傳的表單時是必要的。
@MultipartConfig
public class AdministratorServlet extends HttpServlet {

	private AdministratorService administratorService;

	@Override
	public void init() throws ServletException {
//		與資料庫交互的服務層
		administratorService = new AdministratorServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//前端的action對應到哪個 執行哪個
		switch (action) {
		case "add":
//			執行裡面的方法
			processAdd(req, res);
			break;
			
		case "update":
			processUpdate(req, res);
			break;
		case "update2":
			processUpdate2(req, res);
			break;
		case "del":
			processDelete(req, res);
			break;
		case "query":
			processQuery(req, res);
			break;
		case "getone":
			getOne(req, res);
			return;
	
		default:
		}
	}
	private void getOne(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		AdministratorService administratorService = new AdministratorServiceImpl();

		try {

			req.setCharacterEncoding("UTF-8");
		

			// 獲取特定成員
			String adminNoStr = req.getParameter("ADMIN_NO");
			System.out.println(adminNoStr);

			if (adminNoStr != null && !adminNoStr.trim().isEmpty()) {
				try {

					Administrator administrator = administratorService.findAdministratorByAdminNo(adminNoStr);;
					req.setAttribute("administrator", administrator);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			} else {
				req.setAttribute("error", "Invalid administrator number provided.");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/administrator/errorPage.jsp");
				dispatcher.forward(req, res);
				return;
			}
			// 設置編碼和轉發到指定的JSP頁面
			req.setCharacterEncoding("UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/administrator/listoneadministrator.jsp");
			dispatcher.forward(req, res);

		} catch (Exception e) {
			// 處理其他潛在錯誤
			e.printStackTrace();

		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	private void processQuery(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		//找對應的aminno
		Administrator admin = administratorService.findAdministratorByAdminNo((req.getParameter("ADMIN_NO")));
		
		res.setCharacterEncoding("UTF-8");
		//用json把值裝起來
		String str = JSONObject.toJSONString(admin);
		//回應給前端
		PrintWriter out = res.getWriter();
		out.write(str);
	}
	
	private void processDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
//		通過管理員編號來刪除特定資料
		administratorService.deleteAdministrator(req.getParameter("ADMIN_NO"));
	}
	
	private void processUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
//		錯誤驗證
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		List<Administrator> administrators = administratorService.getAllAdministrators();
		
		Administrator admin = new Administrator();
		String adminemail = req.getParameter("ADMIN_EMAIL");
		String adminNo = req.getParameter("ADMIN_NO");
		Administrator originaladmin =  administratorService.findAdministratorByAdminNo(adminNo);;

		/***********************1.接收請求參數 - 輸入格式的各式錯誤處理*************************/
		if (adminNo == null || adminNo.trim().length() == 0) {
			errorMsgs.put("ADMIN_NO","管理員帳號請勿空白");
		}
		
		String adminname = req.getParameter("ADMIN_NAME");
		String adminnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
		if (adminname == null || adminname.trim().length() == 0) {
			errorMsgs.put("ADMIN_NAME","管理員姓名: 請勿空白");
		} else if(!adminname.trim().matches(adminnameReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("ADMIN_NAME","管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
        }
		
		String admingender = req.getParameter("ADMIN_GENDER").trim();
		if (admingender == null || admingender.trim().length() == 0) {
			errorMsgs.put("ADMIN_GENDER","管理員性別請勿空白");
		}
		
		if (adminemail == null || adminemail.trim().length() == 0) {
		    errorMsgs.put("ADMIN_EMAIL", "管理員信箱請勿空白");
		} else {
		    // 檢查信箱長度
		    if (adminemail.length() < 16 || adminemail.length() > 40) {
		        errorMsgs.put("ADMIN_EMAIL", "帳號長度必須在6到30個字符之間");
		    } else {
		        // 正則表達式，用於驗證信箱格式
		        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		        Pattern pattern = Pattern.compile(emailRegex);
		        Matcher matcher = pattern.matcher(adminemail);
		        if (!matcher.matches()) {
		            errorMsgs.put("ADMIN_EMAIL", "請輸入有效的信箱地址");
		        }
		    }
		}
		
		String adminpwd = req.getParameter("ADMIN_PASSWORD").trim();
		if (adminpwd == null || adminpwd.trim().length() == 0) {
			admin.setAdminPassword(originaladmin.getAdminPassword());
		}else {
		    // 密碼加密
		    String encryptedPassword = BCrypt.hashpw(adminpwd, BCrypt.gensalt());
		    admin.setAdminPassword(encryptedPassword);
		}
		  String admintel1 = "^09\\d{8}$";
		String admintel = req.getParameter("ADMIN_TEL").trim();
		if (admintel == null || admintel.trim().length() == 0) {
			errorMsgs.put("ADMIN_TEL","管理員電話請勿空白");
		} else if(!admintel.trim().matches(admintel1)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("ADMIN_TEL","管理員電話: 只能是09開頭且總長度為10");
        }
		
		String adminaddress = req.getParameter("ADMIN_ADDRESS").trim();
		if (adminaddress == null || adminaddress.trim().length() == 0) {
			errorMsgs.put("ADMIN_ADDRESS","管理員地址請勿空白");
		}
		String emergencyContactName = req.getParameter("EMERGENCY_CONTACTNAME");
		String emergencyContactNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
		if (adminname == null || adminname.trim().length() == 0) {
			errorMsgs.put("EMERGENCY_CONTACTNAME","緊急聯絡人: 請勿空白");
		} else if(!adminname.trim().matches(adminnameReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("EMERGENCY_CONTACTNAME","緊急聯絡人姓名: 只能是中、英文字母、數字和_ , 且長度必需在1到50之間");
        }
		String emergencyContactel1 = "^09\\d{8}$";
		String emergencyContactel = req.getParameter("EMERGENCY_CONTACTEL").trim();
		if (emergencyContactel == null || emergencyContactel.trim().length() == 0) {
			errorMsgs.put("EMERGENCY_CONTACTEL","緊急聯絡人電話請勿空白");
		} else if(!emergencyContactel.trim().matches(emergencyContactel1)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("EMERGENCY_CONTACTEL","緊急聯絡人電話: 只能是09開頭且總長度為10");
        }

//		錯誤跳轉
		if (!errorMsgs.isEmpty()){
			req.getSession().setAttribute("errorMsgs", errorMsgs);
			String contextPath = req.getContextPath();
			res.sendRedirect(contextPath + "/frontend/administrator/administratorUpdate.jsp?adminNo=" + adminNo);

	    return;
//	    刪除錯誤訊息
		}else {
			req.getSession().removeAttribute("errorMsgs");
		}
	
		//接收前端修改的資料
		admin.setAdminNo (req.getParameter("ADMIN_NO"));
		admin.setAdminName (req.getParameter("ADMIN_NAME"));
		admin.setAdminGender(req.getParameter("ADMIN_GENDER"));
		admin.setAdminEmail (req.getParameter("ADMIN_EMAIL"));
//		admin.setAdminPassword (req.getParameter("ADMIN_PASSWORD"));
		admin.setAdminTel (req.getParameter("ADMIN_TEL"));
		admin.setAdminAddress (req.getParameter("ADMIN_ADDRESS"));
		
		java.sql.Date adminBD = null;
		String adminBDStr = req.getParameter("ADMIN_BD");
		if(adminBDStr == null || adminBDStr.length() == 0) {
//			錯誤處理
		}else {
			adminBD = java.sql.Date.valueOf(adminBDStr);
		}
		
		
		admin.setAdminBd(adminBD);
		admin.setEmergencyContactName(req.getParameter("EMERGENCY_CONTACTNAME"));
		admin.setEmergencyContactel(req.getParameter("EMERGENCY_CONTACTEL"));
		//java.sql的日期寫法
//		Date date = null ;
//		try {
//			date = new SimpleDateFormat("yyyy-mm-dd").parse(req.getParameter("ADMIN_HD"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		String adminHdStr = req.getParameter("ADMIN_HD");
		java.sql.Date adminHd = null;
		if(adminHdStr == null || adminHdStr.length() == 0) {
//			錯誤處裡
		}else{
			adminHd = java.sql.Date.valueOf(adminHdStr);
		}

		admin.setAdminHd(adminHd);
		
		

		
		String adminRdStr = req.getParameter("ADMIN_RD");
		java.sql.Date adminRd = null;
		if(adminRdStr == null || adminRdStr.length() == 0) {
//			錯誤處裡
		}else{
			adminRd = java.sql.Date.valueOf(adminRdStr);
		}

		admin.setAdminRd(adminRd);
		

		admin.setAdminStatus(Integer.valueOf(req.getParameter("ADMIN_STATUS")));
		admin.setAdminVerifyStatus(Integer.valueOf(req.getParameter("ADMIN_VERIFY_STATUS")));
		admin.setAdminFuncName(Integer.valueOf(req.getParameter("ADMIN_FUNC_NAME")));
		  
		//取得圖片
		Part p = req.getPart("ADMIN_PHOTO");
	        InputStream input = p.getInputStream();
	        byte[] photo = new byte[input.available()];
	        input.read(photo);
	        input.close();
	        admin.setAdminPhoto(photo);
	        System.out.println(admin);
		administratorService.updateAdministrator(admin);
		//導到指定的URL 頁面上 把請求回應都帶過去
//		String url = "/frontend/administrator/administrator.jsp";
//		RequestDispatcher rd =  req.getRequestDispatcher(url);
//		rd.forward(req, res);
		String url = req.getContextPath()+"/frontend/administrator/administrator.jsp";
		res.sendRedirect(url);
	}
private void processUpdate2(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
	Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
	
	req.setAttribute("errorMsgs", errorMsgs);
	List<Administrator> administrators = administratorService.getAllAdministrators();
//	
	Administrator admin = new Administrator();
	String adminemail = req.getParameter("ADMIN_EMAIL");
	String adminNo = req.getParameter("ADMIN_NO");
	Administrator originaladmin =  administratorService.findAdministratorByAdminNo(adminNo);;

	/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
	if (adminNo == null || adminNo.trim().length() == 0) {
		errorMsgs.put("ADMIN_NO","管理員帳號請勿空白");
	}
	
	String adminname = req.getParameter("ADMIN_NAME");
	String adminnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
	if (adminname == null || adminname.trim().length() == 0) {
		errorMsgs.put("ADMIN_NAME","管理員姓名: 請勿空白");
	} else if(!adminname.trim().matches(adminnameReg)) { 
		errorMsgs.put("ADMIN_NAME","管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
    }

	String admingender = req.getParameter("ADMIN_GENDER").trim();
	if (admingender == null || admingender.trim().length() == 0) {
		errorMsgs.put("ADMIN_GENDER","管理員性別請勿空白");
	}
	
	if (adminemail == null || adminemail.trim().length() == 0) {
	    errorMsgs.put("ADMIN_EMAIL", "管理員信箱請勿空白");
	} else {
	   
	    if (adminemail.length() < 16 || adminemail.length() > 40) {
	        errorMsgs.put("ADMIN_EMAIL", "帳號長度必須在6到30個字符之間");
	    } else {
	        
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	        Pattern pattern = Pattern.compile(emailRegex);
	        Matcher matcher = pattern.matcher(adminemail);
	        if (!matcher.matches()) {
	            errorMsgs.put("ADMIN_EMAIL", "請輸入有效的信箱地址");
	        }
	    }
	}
	
	String adminpwd = req.getParameter("ADMIN_PASSWORD").trim();
	if (adminpwd == null || adminpwd.trim().length() == 0) {
		admin.setAdminPassword(originaladmin.getAdminPassword());
	}else {
	   
	    String encryptedPassword = BCrypt.hashpw(adminpwd, BCrypt.gensalt());
	    admin.setAdminPassword(encryptedPassword);
	}
	  String admintel1 = "^09\\d{8}$";
	String admintel = req.getParameter("ADMIN_TEL").trim();
	if (admintel == null || admintel.trim().length() == 0) {
		errorMsgs.put("ADMIN_TEL","管理員電話請勿空白");
	} else if(!admintel.trim().matches(admintel1)) { //以下練習正則(規)表示式(regular-expression)
		errorMsgs.put("ADMIN_TEL","管理員電話: 只能是09開頭且總長度為10");
    }
	
	String adminaddress = req.getParameter("ADMIN_ADDRESS").trim();
	if (adminaddress == null || adminaddress.trim().length() == 0) {
		errorMsgs.put("ADMIN_ADDRESS","管理員地址請勿空白");
	}
	String emergencyContactName = req.getParameter("EMERGENCY_CONTACTNAME");
	String emergencyContactNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
	if (adminname == null || adminname.trim().length() == 0) {
		errorMsgs.put("EMERGENCY_CONTACTNAME","緊急聯絡人: 請勿空白");
	} else if(!adminname.trim().matches(adminnameReg)) { 
		errorMsgs.put("EMERGENCY_CONTACTNAME","緊急聯絡人姓名: 只能是中、英文字母、數字和_ , 且長度必需在1到50之間");
    }
	String emergencyContactel1 = "^09\\d{8}$";
	String emergencyContactel = req.getParameter("EMERGENCY_CONTACTEL").trim();
	if (emergencyContactel == null || emergencyContactel.trim().length() == 0) {
		errorMsgs.put("EMERGENCY_CONTACTEL","緊急聯絡人電話請勿空白");
	} else if(!emergencyContactel.trim().matches(emergencyContactel1)) { 
		errorMsgs.put("EMERGENCY_CONTACTEL","緊急聯絡人電話: 只能是09開頭且總長度為10");
    }
	if (!errorMsgs.isEmpty()){
		req.getSession().setAttribute("errorMsgs", errorMsgs);
		String contextPath = req.getContextPath();
		res.sendRedirect(contextPath + "/frontend/administrator/administratorUpdate2.jsp?adminNo=" + adminNo);

    return;
	}else {
		req.getSession().removeAttribute("errorMsgs");
	}
		
		
		
		admin.setAdminNo (req.getParameter("ADMIN_NO"));
		admin.setAdminName (req.getParameter("ADMIN_NAME"));
		
		admin.setAdminGender(req.getParameter("ADMIN_GENDER"));
		admin.setAdminEmail (req.getParameter("ADMIN_EMAIL"));
//		admin.setAdminPassword (req.getParameter("ADMIN_PASSWORD"));
		admin.setAdminTel (req.getParameter("ADMIN_TEL"));
		admin.setAdminAddress (req.getParameter("ADMIN_ADDRESS"));
//		日期
		java.sql.Date adminBD = null;
		String adminBDStr = req.getParameter("ADMIN_BD");
		if(adminBDStr == null || adminBDStr.length() == 0) {
//			錯誤處理
		}else {
			adminBD = java.sql.Date.valueOf(adminBDStr);
		}
		admin.setAdminBd(adminBD);
		admin.setEmergencyContactName(req.getParameter("EMERGENCY_CONTACTNAME"));
		admin.setEmergencyContactel(req.getParameter("EMERGENCY_CONTACTEL"));
//		圖片寫法  
		Part p = req.getPart("ADMIN_PHOTO");
	        InputStream input = p.getInputStream();
	        byte[] photo = new byte[input.available()];
	        input.read(photo);
	        input.close();
	        admin.setAdminPhoto(photo);
		administratorService.updateAdministrator2(admin);
		//導到指定的URL 頁面上 把請求回應都帶過去
		  // 假設更新操作已完成，現在重新獲取最新資料
	     Administrator updatedAdmin = administratorService.findAdministratorByAdminNo(admin.getAdminNo());
	     System.out.println(admin.getAdminNo()+"=============");
	     // 將更新後的管理員資料設置為請求屬性
	     req.setAttribute("administrator", updatedAdmin);
	  // 導到指定的URL 頁面上 把請求回應都帶過去
	  req.getRequestDispatcher( "/frontend/administrator/administratorcenter.jsp").forward(req, res);
	 
	 }


//		String url = req.getContextPath()+"/frontend/administrator/administratorcenter.jsp";
//		res.sendRedirect(url);
	

	private void processAdd(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		//驗證
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		List<Administrator> administrators = administratorService.getAllAdministrators();
//		
		Administrator admin = new Administrator();
		String adminemail = req.getParameter("ADMIN_EMAIL");
		String adminNo = req.getParameter("ADMIN_NO");
//		
//		印出是否有寫錯
		for(Administrator admin1 :  administrators) {
			System.out.println("---------");
			System.out.println(admin1.getAdminEmail());
			System.out.println(adminemail);
			System.out.println(admin1.getAdminEmail().equals(adminemail));
			if(admin1.getAdminNo().equals(adminNo)) {
				errorMsgs.put("ADMIN_NO","此帳號已註冊，請重新輸入");
			}
			if(admin1.getAdminEmail().equals(adminemail)) {
				errorMsgs.put("ADMIN_EMAIL", "不能使用該信箱");
			}
			if(!errorMsgs.isEmpty()) {
				break;
			}
		}
		
		if(!errorMsgs.isEmpty()) {
			
			req.getRequestDispatcher("frontend/administrator/administratorAdd.jsp").forward(req, res);
		    return;
		}
		/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		if (adminNo == null || adminNo.trim().length() == 0) {
			errorMsgs.put("ADMIN_NO","管理員帳號請勿空白");
		}
		
		String adminname = req.getParameter("ADMIN_NAME");
		String adminnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
		if (adminname == null || adminname.trim().length() == 0) {
			errorMsgs.put("ADMIN_NAME","管理員姓名: 請勿空白");
		} else if(!adminname.trim().matches(adminnameReg)) { 
			errorMsgs.put("ADMIN_NAME","管理員姓名: 只能是中、英文字母、數字和_ , 且長度必需在1到50之間");
        }
		
		String admingender = req.getParameter("ADMIN_GENDER").trim();
		if (admingender == null || admingender.trim().length() == 0) {
			errorMsgs.put("ADMIN_GENDER","管理員性別請勿空白");
		}
		
		if (adminemail == null || adminemail.trim().length() == 0) {
		    errorMsgs.put("ADMIN_EMAIL", "管理員信箱請勿空白");
		} else {
		   
		    if (adminemail.length() < 16 || adminemail.length() > 40) {
		        errorMsgs.put("ADMIN_EMAIL", "帳號長度必須在6到30個字符之間");
		    } else {
		     
		        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		        Pattern pattern = Pattern.compile(emailRegex);
		        Matcher matcher = pattern.matcher(adminemail);
		        if (!matcher.matches()) {
		            errorMsgs.put("adminEmail", "請輸入有效的信箱地址");
		        }
		    }
		}
		
		String adminpwd = req.getParameter("ADMIN_PASSWORD").trim();
		if (adminpwd == null || adminpwd.trim().length() == 0) {
//			errorMsgs.put("ADMIN_PASSWORD","管理員密碼請勿空白");
		}else {
		  
		    String encryptedPassword=BCrypt.hashpw(adminpwd,BCrypt.gensalt());
		    admin.setAdminPassword(encryptedPassword);
		    System.out.println(adminpwd);
		    System.out.println(encryptedPassword);
		}
		  String admintel1 = "^09\\d{8}$";
		String admintel = req.getParameter("ADMIN_TEL").trim();
		if (admintel == null || admintel.trim().length() == 0) {
			errorMsgs.put("ADMIN_TEL","管理員電話請勿空白");
		} else if(!admintel.trim().matches(admintel1)) { 
			errorMsgs.put("ADMIN_TEL","管理員電話: 只能是09開頭且總長度為10");
        }
		
		String adminaddress = req.getParameter("ADMIN_ADDRESS").trim();
		if (adminaddress == null || adminaddress.trim().length() == 0) {
			errorMsgs.put("ADMIN_ADDRESS","管理員地址請勿空白");
		}
		String emergencyContactName = req.getParameter("EMERGENCY_CONTACTNAME");
		String emergencyContactNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
		if (adminname == null || adminname.trim().length() == 0) {
			errorMsgs.put("EMERGENCY_CONTACTNAME","緊急聯絡人: 請勿空白");
		} else if(!adminname.trim().matches(adminnameReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("EMERGENCY_CONTACTNAME","緊急聯絡人姓名: 只能是中、英文字母、數字和_ , 且長度必需在1到50之間");
        }
		String emergencyContactel1 = "^09\\d{8}$";
		String emergencyContactel = req.getParameter("EMERGENCY_CONTACTEL").trim();
		if (emergencyContactel == null || emergencyContactel.trim().length() == 0) {
			errorMsgs.put("EMERGENCY_CONTACTEL","緊急聯絡人電話請勿空白");
		} else if(!emergencyContactel.trim().matches(emergencyContactel1)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.put("EMERGENCY_CONTACTEL","緊急聯絡人電話: 只能是09開頭且總長度為10");
        }


		if (!errorMsgs.isEmpty()){
			req.getRequestDispatcher("frontend/administrator/administratorAdd.jsp").forward(req, res);
	    return;
		}
		
		
		//把資料給前端
		admin.setAdminNo(adminNo);
		admin.setAdminName (req.getParameter("ADMIN_NAME"));
		admin.setAdminGender(req.getParameter("ADMIN_GENDER"));
		admin.setAdminEmail (adminemail);
//		admin.setAdminPassword (req.getParameter("ADMIN_PASSWORD"));
		admin.setAdminTel (req.getParameter("ADMIN_TEL"));
		admin.setAdminAddress (req.getParameter("ADMIN_ADDRESS"));
		java.sql.Date adminBD = null;
		String adminBDStr = req.getParameter("ADMIN_BD");
		if(adminBDStr != null && adminBDStr.length() > 0) {
		    try {
		        adminBD = java.sql.Date.valueOf(adminBDStr);
		    } catch (IllegalArgumentException e) {
		        // 如果 adminRDStr 不是有效的日期格式，這裡會捕捉到異常
		        // 這裡可以記錄錯誤或進行其他錯誤處理
		    }
		}
		admin.setAdminBd(adminBD);
		
		admin.setEmergencyContactName(req.getParameter("EMERGENCY_CONTACTNAME"));
		admin.setEmergencyContactel(req.getParameter("EMERGENCY_CONTACTEL"));
		//java.sql的日期寫法
		java.sql.Date adminHD = null;
		String adminHDStr = req.getParameter("ADMIN_HD");
		if(adminHDStr != null && adminHDStr.length() > 0) {
		    try {
		        adminHD = java.sql.Date.valueOf(adminHDStr);
		    } catch (IllegalArgumentException e) {
		        // 如果 adminRDStr 不是有效的日期格式，這裡會捕捉到異常
		        // 這裡可以記錄錯誤或進行其他錯誤處理
		    }
		}
		admin.setAdminHd(adminHD);
		
		java.sql.Date adminRD = null;
		String adminRDStr = req.getParameter("ADMIN_RD");
		if(adminRDStr != null && adminRDStr.length() > 0) {
		    try {
		        adminRD = java.sql.Date.valueOf(adminRDStr);
		    } catch (IllegalArgumentException e) {
		        // 如果 adminRDStr 不是有效的日期格式，這裡會捕捉到異常
		        // 這裡可以記錄錯誤或進行其他錯誤處理
		    }
		}
		admin.setAdminRd(adminRD);
		admin.setAdminStatus(Integer.valueOf(req.getParameter("ADMIN_STATUS")));
		admin.setAdminVerifyStatus(Integer.valueOf(req.getParameter("ADMIN_VERIFY_STATUS")));
		// 取得圖片 
		// 開串流
		Part p = req.getPart("ADMIN_PHOTO");
		InputStream input = p.getInputStream();
		byte[] photo = new byte[input.available()];
		input.read(photo);
		input.close();
		admin.setAdminPhoto(photo);
		admin.setAdminFuncName(Integer.valueOf(req.getParameter("ADMIN_FUNC_NAME")));
		//把資料裝起來然後新增
		administratorService.addAdministrator(admin);
		
	
		//導到指定的URL 頁面上 把請求回應都帶過去
		String url = req.getContextPath()+"/frontend/administrator/addSuccessfully.jsp";
		res.sendRedirect(url);
	}

}