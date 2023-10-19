<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>latestnews</title>
</head>
<body>

	<jsp:useBean id="latestNewsService" scope="page"
		class="com.woof.latestnews.service.LatestNewsServiceImpl" />
	<form method="POST" ACTION="${pageContext.request.contextPath}/latestNews">
		<h2 style="text-align: center;">最新消息</h2>
		<div style="text-align: center;">
			<label for="LatestNews">Select LatestNews:</label> 
			<select name="LatestNews" id="selectLatestNews">
				<c:forEach var="latestNews" items="${latestNewsService.allLatestNews}">
					<option value="${latestNews.lnNo} ${latestNews.lnContent} ${latestNews.lnPhoto} ${latestNews.lnTime} ">${latestNews.lnTitle}</option>
				</c:forEach>
			</select>
			<button type="submit" id="button">提交</button>
		</div>
	</form>

</body>
</html>