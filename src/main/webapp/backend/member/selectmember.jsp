<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="/backend/backhead.file"%>
<title>會員資料查詢</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<style>
    .custom-container {
        max-width: 1200px;
        margin: auto;
        padding: 20px;
    }

    .custom-header {
        background-color: pink;
        color: white;
        padding: 10px;
        text-align: center;
        margin-bottom: 20px;
    }

    .custom-form {
        margin-bottom: 20px;
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        align-items: center;
    }

    .custom-form label {
        margin-right: 10px;
    }

    .custom-form input[type="text"],
    .custom-form select {
        flex-grow: 1;
        margin-right: 10px;
        padding: 5px;
        border: 1px solid #ddd;
    }

    .btn-orange {
        background-color: #FF8C00;
        color: white;
        border: none;
        padding: 10px 15px;
        cursor: pointer;
    }

    .btn-orange:hover {
        background-color: #FFA07A;
    }

    @media (max-width: 768px) {
        .custom-form {
            flex-direction: column;
        }
        .custom-form input[type="text"],
        .custom-form select {
            margin-bottom: 10px;
            flex-grow: 0;
        }
    }
    @media (min-width: 992px) { /* 針對較大螢幕尺寸 */
        .custom-container {
            padding-left: 100px; /* 在大螢幕上增加左側內邊距 */
        }
    }
</style>
</head>
<body>
	<%@ include file="/backend/backbody.file"%>
	<div class="custom-container">
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
				 <div class="custom-form">
					<form method="post"
						action="${pageContext.request.contextPath}/member.do"
						class="form-inline">
						<label class="mr-2"><b>輸入會員帳號:</b></label> 
						<input type="text" class="form-control mr-2" name="memNo"
							placeholder="輸入會員帳號" value="${member.memNo}" required > <input
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