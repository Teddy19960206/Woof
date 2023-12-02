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
	<table class="table table-hover text-center mt-4" style="font-size: 12px;">
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
			<!--             先在外面宣告 -->
			<c:set var="memNo" value="" />
			<!--                             servlet setAttribute("shopOrders", shopOrders);         -->
			<c:forEach var="shopOrders" items="${shopOrders}">
				<!--                           裡面的值給外面宣告，才能印出來看  -->
				<c:set var="memNo" value="${shopOrders.member.memNo}" />
				<tr class="text-nowrap">
					<td class="order-no">${shopOrders.shopOrderNo}</td>

					<td class="mem-no">${shopOrders.member.memNo}</td>
					<%--         				<td class="mem-name">${shopOrders.member.memName}</td> --%>
					<td class="prod-order-date">${shopOrders.prodOrderDate}</td>
					<td class="pay-method"><c:choose>
							<c:when test="${shopOrders.payMethod == 0}">信用卡</c:when>
							<c:when test="${shopOrders.payMethod == 1}">匯款</c:when>
							<c:when test="${shopOrders.payMethod == 2}">綠界</c:when>
						</c:choose></td>
					<td class="ship-method"><c:choose>
							<c:when test="${shopOrders.shipMethod == false}">宅配</c:when>
							<c:when test="${shopOrders.shipMethod == true}">超商取貨</c:when>
						</c:choose></td>
					<td class="rec-name">${shopOrders.recName}</td>
					<td class="rec-mobile">${shopOrders.recMobile}</td>
					<td class="rec-address">${shopOrders.recAddress}</td>
					<td class="has-return"><c:choose>
							<c:when test="${shopOrders.hasReturn == false}">無退貨</c:when>
							<c:when test="${shopOrders.hasReturn == true}">有退貨</c:when>
						</c:choose></td>
					<td class="mo-coin">${shopOrders.moCoin}</td>
					<td class="order-total-price">${shopOrders.orderTotalPrice}</td>
					<td class="actual-price">${shopOrders.actualPrice}</td>
					<td><span class="status-text"> <c:choose>
								<c:when test="${shopOrders.orderStatus == 0}">
								<span style="color: green;">成立</span>
								</c:when>
								<c:when test="${shopOrders.orderStatus == 1}">
								<span style="color: blue;">出貨</span>
								</c:when>
								<c:when test="${shopOrders.orderStatus == 2}">
								<span style="color: yellow;">完成</span>
								</c:when>
								<c:when test="${shopOrders.orderStatus == 3}">
								<span style="color: red;">取消</span>
								</c:when>
								<c:when test="${shopOrders.orderStatus == 4}">
								<span style="color: orange;">未付款</span>
								</c:when>
								<c:when test="${shopOrders.orderStatus == 5}">
								<span style="color: cyan;">已付款</span>
								</c:when>
							</c:choose>
					</span> <select class="status-select" style="display: none;">
							<option value="0"
								${shopOrders.orderStatus == 0 ? 'selected' : ''}>成立</option>
							<option value="1"
								${shopOrders.orderStatus == 1 ? 'selected' : ''}>出貨</option>
							<option value="2"
								${shopOrders.orderStatus == 2 ? 'selected' : ''}>完成</option>
							<option value="3"
								${shopOrders.orderStatus == 3 ? 'selected' : ''}>取消</option>
							<option value="4"
								${shopOrders.orderStatus == 4 ? 'selected' : ''}>未付款</option>
							<option value="5"
								${shopOrders.orderStatus == 5 ? 'selected' : ''}>已付款</option>
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
								href="backend/shoporderdetail/shoporderdetail.jsp?shopOrderNo=${shopOrders.shopOrderNo}"
								class="btn btn-secondary small-btn"
								style="font-size: 10px; min-width: 20px; color: white;">查看</a>
						</div>
					</td>
				</tr>
				</c:forEach>

			</tbody>
		</table>
		<!--         印出來看 -->
		<%-- <p>memNo=${memNo}</p> --%>
		<%-- <p>currentPage=${currentPage}</p> --%>
		<%-- <p>shopOrderPageQty2=${shopOrderPageQty2}</p> --%>

		<c:if test="${currentPage > 1}">
			<a class="btn btn-outline-secondary"
			href="${pageContext.request.contextPath}/shoporder?action=getByMemNo&page=1&memNo=${memNo}">至第一頁</a>&nbsp;
	</c:if>
		<c:if test="${currentPage - 1 != 0}">
			<a class="btn btn-outline-secondary"
			href="${pageContext.request.contextPath}/shoporder?action=getByMemNo&page=${currentPage - 1}&memNo=${memNo}">上一頁</a>&nbsp;
	</c:if>
		<c:if test="${currentPage + 1 <= shopOrderPageQty2}">
			<a class="btn btn-outline-secondary"
			href="${pageContext.request.contextPath}/shoporder?action=getByMemNo&page=${currentPage + 1}&memNo=${memNo}">下一頁</a>&nbsp;
	</c:if>
		<c:if test="${currentPage != shopOrderPageQty2}">
			<a class="btn btn-outline-secondary"
			href="${pageContext.request.contextPath}/shoporder?action=getByMemNo&page=${shopOrderPageQty2}&memNo=${memNo}">至最後一頁</a>&nbsp;
	</c:if>


		<a class="btn btn-secondary"
		href="${pageContext.request.contextPath}/backend/index.jsp">返回</a>


	<%@ include file="/backend/backfoot.file"%>
	<script
		src="${pageContext.request.contextPath}/backend/shoporder/js/getAllshoporder.js"></script>

</body>
</html>
