<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
<title>FAQQQ</title>
</head>
<body>

	<h1>搜尋全部</h1>

	<form method="Post">
		<!--我註冊的路徑servlet   後面的getAll自己取名對應servlet-->
		<a href="${pageContext.request.contextPath}/faq?action=getAll">查詢所有FAQ</a>
	</form>


	<h1>新增FAQ</h1>

	<form method="Post" action="${pageContext.request.contextPath}/faq?action=addfaq">
		

		<label>FAQ類別：</label> <input type="text" name="faqClass" value="訂單問題" /> <br /> 
		<label>FAQ標題：</label> <input type="text" name="faqTitle" value="退貨時間？" /> <br /> 
		<label>FAQ內容：</label> <input type="text" name="faqContent" value="10點"/>
		
		<input type="submit" value="確定新增" /> 
 		<input type="button" value="取消新增" onclick="history.back()" />
 		
   </form>
   
</body>
</html>