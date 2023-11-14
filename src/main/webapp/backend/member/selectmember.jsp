<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Member</title>
<!-- 引入 Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.btn-orange {
	background-color: #FF8C00;
	color: white;
}

.btn-orange:hover {
	background-color: #FFA07A;
}
body {
  background-color: #f4f4f4;
  font-family: Arial, sans-serif;
}
</style>
</head>
<body>
	<div class="container mt-5">
		<h3 class="mb-3">資料查詢:</h3>
		<ul class="list-unstyled">
			<li class="mb-2"><a href='list_all_member.jsp'
				class="btn btn-primary">查看全部會員</a></li>

			<jsp:useBean id="memberService" scope="page"
				class="com.woof.member.service.MemberServiceImpl" />

			<li class="mb-2">
				<form method="POST"
					action="${pageContext.request.contextPath}/member.do"
					class="form-inline">
					<label class="mr-2"><b>選擇會員編號:</b></label> <select name="memNo"
						class="form-control mr-2">
						<c:forEach var="member" items="${memberService.allMembers}">
							<option value="${member.memNo}">${member.memNo}</option>
						</c:forEach>
					</select> <input type="hidden" name="action" value="getone">
					<button type="submit" class="btn btn-orange">提交</button>
				</form>
			</li>

			<li>
				<form method="POST"
					action="${pageContext.request.contextPath}/member.do"
					class="form-inline">
					<label class="mr-2"><b>選擇會員姓名:</b></label> <select name="memNo"
						class="form-control mr-2">
						<c:forEach var="member" items="${memberService.allMembers}">
							<option value="${member.memNo}">${member.memName}</option>
						</c:forEach>
					</select> <input type="hidden" name="action" value="getone">
					<button type="submit" class="btn btn-orange">提交</button>
				</form>
			</li>
		</ul>
	</div>
	<!-- 引入 Bootstrap JavaScript (依賴 Popper.js) -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>







