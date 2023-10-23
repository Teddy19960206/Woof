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

	<form method="Post"
		action="${pageContext.request.contextPath}/faq?action=addfaq">


		<label>FAQ類別：</label> <input type="text" name="faqClass" value="訂單問題" />
		<br /> <label>FAQ標題：</label> <input type="text" name="faqTitle"
			value="退貨時間？" /> <br /> <label>FAQ內容：</label> <input type="text"
			name="faqContent" value="9點" /> <input type="submit" value="確定新增" />
		<input type="button" value="取消新增" onclick="history.back()" />

	</form>


	<h1>修改FAQ</h1>

	<form method="Post"
		action="${pageContext.request.contextPath}/faq?action=updatefaq">

		<label>FAQ編號：</label> 
		<input type="text" name="faqNo" /> <br /> 
		<label>FAQ類別：</label>
		<input type="text" name="faqClass" /> <br /> 
		<label>FAQ標題：</label> 
		<input
			type="text" name="faqTitle" /> <br /> 
		<label>FAQ內容：</label> <input
			type="text" name="faqContent" /> 
		<input type="submit" value="確定修改" />
		<input type="button" value="取消修改" onclick="history.back()" />

	</form>
	
	
		<h1>刪除FAQ</h1>

	<form method="Post" action="${pageContext.request.contextPath}/faq?action=deletefaq">
		
		<label>FAQ編號：</label> <input type="text" name="faqNo" /> 
		
		<input type="submit" value="確定刪除" /> 
 		<input type="button" value="取消刪除" onclick="history.back()" />
 		
   </form>

</body>
</html>