<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="/backend/backhead.file"%>
<title>會員資料查詢</title>
<style>
body {
	background-color: #f8f9fa;
}

.custom-container {
	max-width: 80%;
	background: url('/woof/backend/member/jpg/12.jpg');
	padding: 50px;
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	margin: 40px auto;
	margin-top: 150px; /* 或者更多，視你的頁面需求而定 */
}

.custom-header {
	background-color: pink;
	color: white;
	padding: 10px;
	border-radius: 5px;
	text-align: center;
	margin-bottom: 20px;
}

.form-inline>* {
	margin: 5px 10px 5px 0;
}

.btn-orange {
	background-color: #FF8C00;
	color: white;
	border: none;
	padding: 11px 15px;
	border-radius: 3px;
}

.btn-orange:hover {
	background-color: #FFA07A;
}

.search-icon {
	color: #007bff;
	cursor: pointer;
}

select {
	text-align: center;
}
</style>
<script src="https://kit.fontawesome.com/3f37e88a3b.js" crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="/backend/backbody.file"%>
	<div class="custom-container">
		<div class="custom-header">
			<h3>會員資料查詢:</h3>
		</div>
		<ul class="list-unstyled">
			<li class="mb-2"><a href="<%=request.getContextPath()%>/member.do?action=getall"
				class="btn btn-primary">查看全部會員</a></li>

			<jsp:useBean id="memberService" scope="page"
				class="com.woof.member.service.MemberServiceImpl" />
			<li>
				<!-- 查詢表單 -->
				<div class="col-md-6">
					<form method="post" action="${pageContext.request.contextPath}/member.do">
						<label class="mr-2"><b>輸入會員帳號:</b></label>
						<div class="input-group">
						 <input type="text" class="form-control mr-2" name="memNo" value="${member.memNo}" placeholder="輸入會員帳號" required> 
							<input type="hidden"name="action" value="getone"> 
							<label for="search" class="search">
						</label>
							<div class="input-group-append">
								<button class="btn btn-orange" type="submit" id="search">
									<i class="fa-solid fa-magnifying-glass"></i>
								</button>
							</div>
							</div>
					</form>
				</div> 
				<div class="col-md-4">
					<form method="POST" action="${pageContext.request.contextPath}/member.do" class="form-inline">
						<label class="inputState form-label mr-2"><b>選擇會員編號:</b></label>
						<div class="input-group">
							<select name="memNo" class="form-select" id="inputState">
								<c:forEach var="member" items="${memberService.allMembers}">
									<option value="${member.memNo}">${member.memNo}</option>
								</c:forEach>
							</select>
							<div class="input-group-append">
								<input type="hidden" name="action" value="getone">
								<button class="btn btn-orange" type="submit" id="search">
									<i class="fa-solid fa-magnifying-glass"></i>
								</button>
							</div>
						</div>
					</form>
				</div>

				<div class="col-md-4">
					<form method="POST"
						action="${pageContext.request.contextPath}/member.do"
						class="form-inline">
						<label class="inputState form-label mr-2"><b>選擇會員姓名:</b></label>
						<div class="input-group">
							<select name="memNo" class="form-select" id="inputState">
								<c:forEach var="member" items="${memberService.allMembers}">
									<option value="${member.memNo}">${member.memName}</option>
								</c:forEach>
							</select>
							<div class="input-group-append">
								<input type="hidden" name="action" value="getone">
								<button class="btn btn-orange" type="submit" id="search">
									<i class="fa-solid fa-magnifying-glass"></i>
								</button>
							</div>
						</div>
					</form>
				</div>
				</li>
				</ul>
	</div> 
	<%@ include file="/backend/backfoot.file"%>
</body>
</html>