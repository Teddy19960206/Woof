<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>寵毛導師 Woof | 商城訂單管理</title>
<style>
</style>

<%@ include file="/backend/backhead.file"%>

</head>
<body>

	<%@ include file="/backend/backbody.file"%>

	<div class="container text-center p-3">
		<div class="row">
			<div class="col-2 p-0">
				<a class="btn btn-primary"
					href="${pageContext.request.contextPath}/shoporder?action=getAll">查詢全部訂單</a>
			</div>
			<div class="col-5 p-0 ml-md-n3">
				<jsp:useBean id="memberService" scope="page"
					class="com.woof.member.service.MemberServiceImpl" />
				<form method="POST"
					action="${pageContext.request.contextPath}/shoporder?action=getByMemNo"
					class="form-inline">
					<label class="p-0"><b>搜尋會員編號:</b></label> <input name="memNo">
					<button type="submit" class="btn btn-primary">查詢</button>
				</form>
			</div>
		</div>
	</div>
	<!-- 	<h3 class="mt-1 text-center">全部訂單</h3> -->
	<table class="table table-hover text-center mt-4"
		style="font-size: 12px;">
		<thead class="align-middle">
			<tr class="table-secondary">
				<th>編號</th>
				<th>會員帳號</th>
				<!--                     <th>會員名稱</th> -->
				<th>成立時間</th>
				<th>付款方式</th>
				<th>取貨方式</th>
				<th>收件人</th>
				<th>電話</th>
				<th>地址</th>
				<th>是否退貨</th>
				<th>毛毛幣</th>
				<th>總計</th>
				<th>實付金額</th>
				<th>狀態</th>
				<th>更新</th>
				<th>明細</th>
			</tr>
		</thead>
		<tbody class="align-middle">
			<c:forEach var="all" items="${all}">
				<tr class="text-nowrap">
					<td class="order-no">${all.shopOrderNo}</td>

					<td class="mem-no">${all.member.memNo}</td>
					<%--         				<td class="mem-name">${all.member.memName}</td> --%>

					<td class="prod-order-date">${all.prodOrderDate}</td>
					<td class="pay-method"><c:choose>
							<c:when test="${all.payMethod == 0}">信用卡</c:when>
							<c:when test="${all.payMethod == 1}">匯款</c:when>
							<c:when test="${all.payMethod == 2}">綠界</c:when>
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
								<c:when test="${all.orderStatus == 0}">
									<span style="color: green;">成立</span>
								</c:when>
								<c:when test="${all.orderStatus == 1}">
									<span style="color: blue;">出貨</span>
								</c:when>
								<c:when test="${all.orderStatus == 2}">
									<span style="color: yellow;">完成</span>
								</c:when>
								<c:when test="${all.orderStatus == 3}">
									<span style="color: red;">取消</span>
								</c:when>
								<c:when test="${all.orderStatus == 4}">
									<span style="color: orange;">未付款</span>
								</c:when>
								<c:when test="${all.orderStatus == 5}">
									<span style="color: cyan;">已付款</span>
								</c:when>
							</c:choose>
					</span> <select class="status-select" style="display: none;">
							<option value="0" ${all.orderStatus == 0 ? 'selected' : ''}>成立</option>
							<option value="1" ${all.orderStatus == 1 ? 'selected' : ''}>出貨</option>
							<option value="2" ${all.orderStatus == 2 ? 'selected' : ''}>完成</option>
							<option value="3" ${all.orderStatus == 3 ? 'selected' : ''}>取消</option>
							<option value="4" ${all.orderStatus == 4 ? 'selected' : ''}>未付款</option>
							<option value="4" ${all.orderStatus == 5 ? 'selected' : ''}>已付款</option>
					</select></td>
					<td>
						<div class="d-flex flex-column">
							<button class="edit-btn btn btn-primary"
								style="font-size: 10px; min-width: 20px;">修改</button>
							<button class="save-btn btn btn-success"
								style="display: none; font-size: 10px; min-width: 50px;">確定</button>
							<button class="cancel-btn btn btn-danger mt-1"
								style="display: none; font-size: 10px; min-width: 50px;">取消</button>
						</div>
					<td>
						<!-- 						<form method="post" --> <%-- 							action="${pageContext.request.contextPath}/backend/shoporderdetail/shoporderdetail.jsp"> --%>
						<%-- 							<input type="hidden" name="" value="${all.shopOrderNo}"> --%>
						<!-- 							<button class="btn btn-secondary small-btn" --> <!-- 								style="font-size: 10px; min-width: 50px;" type="submit">查詢</button> -->
						<!-- 						</form>  -->
						<div class="d-flex flex-column">
							<a
								href="backend/shoporderdetail/shoporderdetail.jsp?shopOrderNo=${all.shopOrderNo}"
								class="btn btn-secondary small-btn"
								style="font-size: 10px; min-width: 20px; color: white;">查看</a>
						</div>
					</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>


	<c:if test="${currentPage > 1}">
		<a class="btn btn-outline-secondary"
			href="${pageContext.request.contextPath}/shoporder?action=getAll&page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a class="btn btn-outline-secondary"
			href="${pageContext.request.contextPath}/shoporder?action=getAll&page=${currentPage - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= shopOrderPageQty}">
		<a class="btn btn-outline-secondary"
			href="${pageContext.request.contextPath}/shoporder?action=getAll&page=${currentPage + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != shopOrderPageQty}">
		<a class="btn btn-outline-secondary"
			href="${pageContext.request.contextPath}/shoporder?action=getAll&page=${shopOrderPageQty}">至最後一頁</a>&nbsp;
	</c:if>


	<a class="btn btn-secondary"
		href="${pageContext.request.contextPath}/backend/index.jsp">返回</a>

	<%@ include file="/backend/backfoot.file"%>
	<script
		src="${pageContext.request.contextPath}/backend/shoporder/js/getAllshoporder.js"></script>

</body>
</html>
