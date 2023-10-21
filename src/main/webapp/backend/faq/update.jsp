<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
<title>修改</title>
</head>
<body>

	<h1>修改FAQ畫面</h1>

	<form method="Post" action="${pageContext.request.contextPath}/faq?action=updatefaq">
		
		<label>FAQ編號：</label> <input type="text" name="faqNo" /> <br /> 
		<label>FAQ類別：</label> <input type="text" name="faqClass" /> <br /> 
		<label>FAQ標題：</label> <input type="text" name="faqTitle" /> <br /> 
		<label>FAQ內容：</label> <input type="text" name="faqContent" />
		
		<input type="submit" value="確定修改" /> 
 		<input type="button" value="取消修改" onclick="history.back()" />
 		
   </form>
   
</body>
</html>