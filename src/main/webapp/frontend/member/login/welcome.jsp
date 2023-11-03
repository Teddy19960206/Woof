<%@ page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.woof.*"%>
<html>
<head>
<title>welcome</title>
</head>
<body>
	<%
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	HttpSession httpSession = request.getSession(true);
	String val = (String) httpSession.getAttribute("pass");
	//如果開啟此頁面則會跳轉到登入頁面，防止非法登入
	if (val == null) {

		response.sendRedirect("memberlogin.jsp");
	}
	%>
	<jsp:useBean id="mycount" class="com.woof.member.controller.LoginCl" scope="application" />

	<jsp:useBean id="user" class="com.woof.member.controller.LoginCl" scope="session">
		<jsp:setProperty name="user" property="name" param="uname" />
		<jsp:setProperty name="user" property="pd" param="password" />
	</jsp:useBean>
	<h1>主介面</h1>
	<%--welcome name =<%=u%>  password =<%=p%><br>--%>
	welcome name :<jsp:getProperty name="user" property="name" />
	password:<jsp:getProperty name="user" property="pd" /><br>
	<%--這是你第:<%=counter%>次訪問本網站!<br>--%>
	這是你第:<jsp:getProperty name="mycount" property="count" />
	次訪問本網站!
	<br>
	<a href='LoginServlet.jsp'>返回重新登入</a>
	<br>
	<%
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {

		for (int i = 0; i < cookies.length; i++) {

			if (cookies[i].getName().equals("lastAccessTime")) {

		out.println("您上次訪問的時間是：");
		Long lastAccessTime = Long.parseLong(cookies[i].getValue());
		Date date = new Date(lastAccessTime);
		out.println(date.toLocaleString());
			}
		}
	}
	//使用者訪問過後重新設定使用者的訪問時間，儲存在cookie中，然後傳送到客戶端瀏覽器
	Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis() + "");
	//設定cookie的有效期為1min
	cookie.setMaxAge(60);
	//將cookie物件新增到response物件中，這樣伺服器在輸出response物件中的內容時
	// 就會把cookie也輸入到客戶端瀏覽器
	response.addCookie(cookie);
	%>
</body>
</html>
