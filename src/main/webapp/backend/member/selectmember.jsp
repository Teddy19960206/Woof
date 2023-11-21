<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="/backend/backhead.file"%>
<title>會員資料查詢</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/3f37e88a3b.js"
	crossorigin="anonymous"></script>
<style>
body {
	background-color: #f4f4f4;
	font-family: Arial, sans-serif;
}

.btn-orange {
	background-color: #FF8C00;
	color: white;
}

.btn-orange:hover {
	background-color: #FFA07A;
}

.container {
	padding-top: 40px;
}

.custom-header {
	background-color: pink;
	color: white;
	padding: 10px;
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<%@ include file="/backend/backbody.file"%>
	<div class="container">
		<div class="custom-header">
			<h3>會員資料查詢:</h3>
		</div>
		<ul class="list-unstyled">
			<li class="mb-2"><a href='list_all_member.jsp'
				class="btn btn-primary">查看全部會員</a></li>

			<jsp:useBean id="memberService" scope="page"
				class="com.woof.member.service.MemberServiceImpl" />
			<li>
				<!-- 查詢表單 -->
				<div class="mb-3">
					<form method="post"
						action="${pageContext.request.contextPath}/member.do"
						class="form-inline">
						<label class="mr-2"><b>輸入會員帳號:</b></label> 
						<input type="text" class="form-control mr-2" name="memNo"
							placeholder="輸入會員帳號" value="${member.memNo}"> <input
							type="hidden" name="action" value="getone">
						<button type="submit" class="btn btn-orange">查詢</button>
					</form>
				</div> <!-- 其他查詢選項 -->
				<div class="mb-2">
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
				</div>
				<div class="mb-2">
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
				</div> 
		</ul>
	</div>

	<!-- Bootstrap JavaScript -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<%@ include file="/backend/backfoot.file"%>
</body>
</html>