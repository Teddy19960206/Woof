<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<%@ include file="/backend/backhead.file" %>
<title>查詢會員</title>
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
<script src="https://kit.fontawesome.com/3f37e88a3b.js" crossorigin="anonymous"></script>
<body>
<div class="container mx-auto">
		<h3 class="mb-3">會員資料查詢:</h3>
		 <a href="/woof/backend/index.jsp"style="position: absolute; top: 10px; left: 20px;"><i class="fa-solid fa-house"></i></a>
		<ul class="list-unstyled">
			<li class="mb-2"><a href='list_all_member.jsp'
				class="btn btn-primary">查看全部會員</a></li>

			<jsp:useBean id="memberService" scope="page"
				class="com.woof.member.service.MemberServiceImpl" />
			<li>
				<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/member.do">
					<b>輸入會員帳號:</b> <input type="text" name="memNo"value="${member.memNo}"><font color=red>${errorMsgs.memNo}</font>
					<input type="hidden" name="action" value="getone">
					<input type="submit" class="btn btn-orange" value="查詢">
				</FORM>
			</li>
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