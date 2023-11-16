<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>



<title>訂單</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
	padding: 20px;
}

.container {
	max-width: 800px;
}

.form-group {
	margin-bottom: 20px;
}
</style>

</head>
<body>



	<div class="container">
		<h1>搜尋全部訂單</h1>
		<form method="Post">
			<a class="btn btn-primary"
				href="${pageContext.request.contextPath}/shoporder?action=getAll">查詢全部訂單</a>
		</form>

		<h1>單一會員訂單查詢</h1>
<!-- 		可能不用透過servlet 直接從service 抓方法 -->
		<jsp:useBean id="memberService" scope="page"
				class="com.woof.member.service.MemberServiceImpl" />
			<form method="POST"
				action="${pageContext.request.contextPath}/shoporder?action=getByMemNo"
				class="form-inline">
				<label class="mr-2"><b>選擇會員編號:</b></label> 
				<select name="memNo"
					class="form-control mr-2">
					<c:forEach var="member" items="${memberService.allMembers}">
							<option value="${member.memNo}">${member.memNo}</option>
						</c:forEach>
				</select>
				<button type="submit" class="btn btn-primary">查詢</button>
			</form>


	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
