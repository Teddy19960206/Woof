<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>訂單明細</title>
<style>
</style>

<%@ include file="/backend/backhead.file"%>

</head>
<body>

	<%@ include file="/backend/backbody.file"%>

	<div class="container text-center p-3">
		<h3>訂單明細</h3>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>訂單編號</th>
					<th>商品</th>
					<th>照片</th>
					<th>數量</th>
					<th>單價</th>
					<th>金額</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="all" items="${all}">
					<tr>
						<td class="order-no">${all.shopOrderNo}</td>

						<td class="mem-no">${all.member.memNo}</td>
						<%--         				<td class="mem-name">${all.member.memName}</td> --%>

						<td class="prod-order-date">${all.prodOrderDate}</td>
						<td class="pay-method"><c:choose>
								<c:when test="${all.payMethod == 0}">信用卡</c:when>
								<c:when test="${all.payMethod == 1}">匯款</c:when>
							</c:choose></td>
						<td class="ship-method"><c:choose>
								<c:when test="${all.shipMethod == false}">宅配</c:when>
								<c:when test="${all.shipMethod == true}">超商取貨</c:when>
							</c:choose></td>
						<td class="rec-name">${all.recName}</td>
						<td class="rec-mobile">${all.recMobile}</td>
						<td class="rec-address">${all.recAddress}</td>
						<td class="has-return"><c:choose>
								<c:when test="${all.hasReturn == false}">無退貨</c:when>
								<c:when test="${all.hasReturn == true}">有退貨</c:when>
							</c:choose></td>
						<td class="mo-coin">${all.moCoin}</td>
						<td class="order-total-price">${all.orderTotalPrice}</td>
						<td class="actual-price">${all.actualPrice}</td>
						<td><span class="status-text"> <c:choose>
									<c:when test="${all.orderStatus == 0}">成立</c:when>
									<c:when test="${all.orderStatus == 1}">出貨</c:when>
									<c:when test="${all.orderStatus == 2}">完成</c:when>
									<c:when test="${all.orderStatus == 3}">取消</c:when>
									<c:when test="${all.orderStatus == 4}">未付款</c:when>
								</c:choose>
						</span> <select class="status-select" style="display: none;">
								<option value="0" ${all.orderStatus == 0 ? 'selected' : ''}>成立</option>
								<option value="1" ${all.orderStatus == 1 ? 'selected' : ''}>出貨</option>
								<option value="2" ${all.orderStatus == 2 ? 'selected' : ''}>完成</option>
								<option value="3" ${all.orderStatus == 3 ? 'selected' : ''}>取消</option>
								<option value="4" ${all.orderStatus == 4 ? 'selected' : ''}>未付款</option>
						</select></td>
						<td>
							<button class="edit-btn btn btn-primary "
								style="font-size: 10px; min-width: 50px;">修改</button>
							<button class="save-btn btn btn-success"
								style="display: none; font-size: 10px;">確定修改</button>
							<button class="cancel-btn btn btn-danger"
								style="display: none; font-size: 10px;">取消修改</button>
						</td>
						<td>
						<form method="post" action="${pageContext.request.contextPath}/backend/faq/updatefaq.jsp">
						    <input type="hidden" name="faqNo" value="${all.faqNo}">
						    <input type="hidden" name="faqClass" value="${all.faqClass}">
						    <input type="hidden" name="faqTitle" value="${all.faqTitle}">
						    <input type="hidden" name="faqContent" value="${all.faqContent}">
							<button class="btn btn-secondary small-btn" style="font-size: 10px;" type="submit">查詢</button>
						</form>	
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>




		<a class="btn btn-secondary"
			href="${pageContext.request.contextPath}/backend/shoporder/shoporderfirst.jsp">返回</a>
	</div>

	<%@ include file="/backend/backfoot.file"%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/backend/shoporder/js/getAllshoporder.js"></script> --%>

</body>
</html>
