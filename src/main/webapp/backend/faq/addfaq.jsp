<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
<title>新增</title>
</head>
<body>

	<h1>新增FAQ畫面</h1>

	<form action="${pageContext.request.contextPath}/faq/addfaq"
		method="post">

		<table border="1">
			<tr>
				<th>FAQ編號</th>
				<th>類別</th>
				<th>標題</th>
				<th>內容</th>
			</tr>

			<tr>
				<td>${result.faqNo}</td>
				<td>${result.faqClass}</td>
				<td>${result.faqTitle}</td>
				<td>${result.faqContent}</td>
			</tr>
		</table>
		<input type="button" value="返回" onclick="history.back()" />
	</form>
</body>
</html>