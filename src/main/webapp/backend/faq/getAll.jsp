<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
<title>GetAllFAQ</title>
</head>
<body>

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
				<td>${all.faqContent}
				<td>

					<FORM METHOD="post"
						action="${pageContext.request.contextPath}/backend/faq/update">
						<input type="hidden" name="action" value="updatefaq">
						<button class="btn btn-success" type="submit">修改</button>
		
					</FORM>
				</td> 
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/admin/admin.do">
						<input type="hidden" name="action" value="deletefaq">
						<button class="btn btn-danger" type="submit">刪除</button>
		
					</FORM>

				</td>
			</tr>
		</c:forEach>
		
	</table>
	
	
	<input type="button" value="返回" onclick="history.back()" >


</body>
</html>