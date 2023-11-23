<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<title>寵毛導師 Woof | FAQ管理</title>

<style>

</style>

<%@ include file="/backend/backhead.file"%>

</head>
<body>

	<%@ include file="/backend/backbody.file"%>

	<div class="container pt-3">
		<div class="row">
			<div class="col">
				<form method="Post">
					<a class="btn btn-secondary"
						href="${pageContext.request.contextPath}/faq?action=getAll">查詢所有FAQ</a>
				</form>
			</div>
		</div>

		<form method="Post"
			action="${pageContext.request.contextPath}/faq?action=addfaq">
			<div class="row pt-2">
				<div class="col-2">
					<label for="faqClass">FAQ類別：</label> <input type="text"
						class="form-control" id="faqClass" name="faqClass" value="付款問題	">
				</div>
				<div class="col-2">
					<label for="faqTitle">FAQ標題：</label> <input type="text"
						class="form-control" id="faqTitle" name="faqTitle" value="付款是否安全？">
				</div>
				<div class="col-6">
					<label for="faqContent">FAQ內容：</label> <input type="text"
						class="form-control" id="faqContent" name="faqContent" value="是的，我們的付款系統非常安全且符合最高安全標準。">
				</div>

				<div class="col-2  d-flex align-items-end">
					<button class="btn btn-success" type="submit">確定新增</button>
<!-- 					<a class="btn btn-secondary" -->
<%-- 						href="${pageContext.request.contextPath}/backend/index.jsp">取消新增</a> --%>
				</div>
			</div>
		</form>

		<h3 class="pt-4 text-center"></h3>
		<table class="table table-hover text-center" >
			<thead class="table-secondary">
				<tr>
					<th>編號</th>
					<th>類別</th>
					<th>標題</th>
					<th>內容</th>
					<th>狀態</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="all" items="${all}">
					<tr>
						<td>${all.faqNo}</td>
						<td>${all.faqClass}</td>
						<td>${all.faqTitle}</td>
						<td>${all.faqContent}</td>
						<td>
							<form method="post"
								action="${pageContext.request.contextPath}/backend/faq/updatefaq.jsp">
								<input type="hidden" name="faqNo" value="${all.faqNo}">
								<input type="hidden" name="faqClass" value="${all.faqClass}">
								<input type="hidden" name="faqTitle" value="${all.faqTitle}">
								<input type="hidden" name="faqContent" value="${all.faqContent}">
								<button class="btn btn-primary small-btn" type="submit">修改</button>
							</form>
						</td>
						<td>
							<form method="post"
								action="${pageContext.request.contextPath}/faq">
								<input type="hidden" name="faqNo" value="${all.faqNo}">
								<input type="hidden" name="action" value="deletefaq">
								<button class="btn btn-danger small-btn" type="submit">刪除</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<%--         <p>currentPage=${currentPage}</p> --%>
		<%--         <p>faqPageQty=${faqPageQty}</p> --%>
		<c:if test="${currentPage > 1}">
			<a class="btn btn-outline-secondary"
				href="${pageContext.request.contextPath}/faq?action=getAll&page=1">至第一頁</a>&nbsp;
	</c:if>
		<c:if test="${currentPage - 1 != 0}">
			<a class="btn btn-outline-secondary"
				href="${pageContext.request.contextPath}/faq?action=getAll&page=${currentPage - 1}">上一頁</a>&nbsp;
	</c:if>
		<c:if test="${currentPage + 1 <= faqPageQty}">
			<a class="btn btn-outline-secondary"
				href="${pageContext.request.contextPath}/faq?action=getAll&page=${currentPage + 1}">下一頁</a>&nbsp;
	</c:if>
		<c:if test="${currentPage != faqPageQty}">
			<a class="btn btn-outline-secondary"
				href="${pageContext.request.contextPath}/faq?action=getAll&page=${faqPageQty}">至最後一頁</a>&nbsp;
	</c:if>

		<a class="btn btn-secondary"
			href="${pageContext.request.contextPath}/backend/index.jsp">返回</a>
	</div>

	<%@ include file="/backend/backfoot.file"%>

</body>
</html>
