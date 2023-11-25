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

	<div class="container pt-3">
		<h1 class="text-center">新增FAQ</h1>

		<form action="${pageContext.request.contextPath}/faq/addfaq"
			method="post">

			<table class="table table-hover text-center">
				<thead class="table-secondary">
					<tr>
						<th class="col-1">FAQ編號</th>
						<th class="col-2">類別</th>
						<th class="col-3">標題</th>
						<th class="col-6">內容</th>
					</tr>
				</thead>
				<tr>
					<td>${result.faqNo}</td>
					<td>${result.faqClass}</td>
					<td>${result.faqTitle}</td>
					<td>${result.faqContent}</td>
				</tr>
			</table>
			<a class="btn btn-secondary" href="${pageContext.request.contextPath}/faq?action=getAll">返回</a>
		</form>
	</div>

	<%@ include file="/backend/backfoot.file"%>

</body>
</html>
