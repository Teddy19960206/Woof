<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>


<!DOCTYPE html>
<html>
<head>
<title>寵毛導師 Woof | FAQ管理</title>

<%@ include file="/backend/backhead.file"%>

</head>
<body>
	<%@ include file="/backend/backbody.file"%>


	<div class="container pt-3">
<!-- 		<h1 class="text-center">修改FAQ畫面</h1> -->
		<form method="post"
			action="${pageContext.request.contextPath}/faq?action=updatefaq">
			<div class="form-row">
				<div class="form-group">
					<label for="faqNo">FAQ編號：</label> <span id="faqNo">${param.faqNo}</span>
					<input type="hidden" name="faqNo" value="${param.faqNo}">
				</div>
				<div class="form-group mt-2">
					<label for="faqClass">FAQ類別：</label> <input type="text"
						class="form-control" id="faqClass" name="faqClass"
						value="${param.faqClass}">
				</div>
				<div class="form-group mt-2">
					<label for="faqTitle">FAQ標題：</label> <input type="text"
						class="form-control" id="faqTitle" name="faqTitle"
						value="${param.faqTitle}">
				</div>
				<div class="form-group mt-2">
					<label for="faqContent">FAQ內容：</label> <input type="text"
						class="form-control" id="faqContent" name="faqContent"
						value="${param.faqContent}">
				</div>
				<button class="btn btn-success mt-3" type="submit">確定修改</button>
				<a class="btn btn-secondary mt-3" href="javascript:history.back()">取消修改</a>
			</div>
		</form>
	</div>

	<%@ include file="/backend/backfoot.file"%>

</body>
</html>
