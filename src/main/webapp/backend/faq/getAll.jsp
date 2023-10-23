<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
<title>GetAllFAQ</title>
</head>
<body>

	<h1>搜尋全部FAQ</h1>
	<table border=1>
		<tr>
			<th>編號</th>
			<th>類別</th>
			<th>標題</th>
			<th>內容</th>
			<th>狀態</th>
		</tr>

		<c:forEach var="all" items="${all}">
		
			<tr>
				<td>${all.faqNo}</td>
				<td>${all.faqClass}</td>
				<td>${all.faqTitle}</td>
				<td>${all.faqContent}</td>
				<td>

					<FORM METHOD="post"
						action="${pageContext.request.contextPath}/backend/faq/updatefaq.jsp">
						<input type="hidden" name="add" value="updatefaq">
						<button class="btn btn-success" type="submit">修改</button>
						
					</FORM>
				</td> 
				<td>
					<FORM METHOD="post"
						action="${pageContext.request.contextPath}/backend/faq/deletefaq.jsp">
						<input type="hidden" name="delete" value="${all.faqNo}">
						<button class="btn btn-danger" type="submit">刪除</button>
		
					</FORM>

				</td>
			</tr>
		</c:forEach>
		
	</table>
	
	
	<input type="button" value="返回" onclick="history.back()" >


</body>
</html>