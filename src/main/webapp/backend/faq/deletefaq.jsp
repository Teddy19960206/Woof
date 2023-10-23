<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
<title>刪除</title>
</head>
<body>

	<h1>刪除FAQ畫面</h1>

	<form method="Post" action="${pageContext.request.contextPath}/faq?action=deletefaq">
		
		<label>FAQ編號：</label> <input type="text" name="faqNo" /> 
		
		<input type="submit" value="確定刪除" /> 
 		<input type="button" value="取消刪除" onclick="history.back()" />
 		
   </form>
   
   <input type="button" value="返回" onclick="history.back()" >
	
</body>
</html>