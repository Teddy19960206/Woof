<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>


<head>



<title>寵毛導師 Woof | FAQ管理</title>

<%@ include file="/backend/backhead.file"%>

</head>
<body>
	<%@ include file="/backend/backbody.file"%>


	<div class="container">
		<div class="row mt-5">
			<div class="col">
				<form method="Post">
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/faq?action=getAll">查詢所有FAQ</a>
				</form>
			</div>
		</div>
		<h1>新增FAQ</h1>
		<form method="Post"
			action="${pageContext.request.contextPath}/faq?action=addfaq">
			<div class="form-group">
				<label for="faqClass">FAQ類別：</label> <input type="text"
					class="form-control" id="faqClass" name="faqClass" value="">
			</div>
			<div class="form-group">
				<label for="faqTitle">FAQ標題：</label> <input type="text"
					class="form-control" id="faqTitle" name="faqTitle" value="">
			</div>
			<div class="form-group">
				<label for="faqContent">FAQ內容：</label> <input type="text"
					class="form-control" id="faqContent" name="faqContent" value="">
			</div>
			<button class="btn btn-success" type="submit">確定新增</button>
			<a class="btn btn-secondary"
				href="${pageContext.request.contextPath}/backend/index.jsp">取消新增</a>
		</form>
	</div>

	<%@ include file="/backend/backfoot.file"%>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
