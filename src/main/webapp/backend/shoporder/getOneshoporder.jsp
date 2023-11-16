<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>全部訂單</title>
<style>
</style>

<%@ include file="/backend/backhead.file"%>

</head>
<body>

	<%@ include file="/backend/backbody.file"%>

	<div class="container">
		<h3>單一會員訂單</h3>
		<table class="table table-bordered small-table"
			style="font-size: 12px;">
			<thead>
				<tr>
					<th>訂單編號</th>
					<th>會員帳號</th>
					<!--                     <th>會員名稱</th> -->
					<th>訂單成立時間</th>
					<th>付款方式</th>
					<th>取貨方式</th>
					<th>收件人姓名</th>
					<th>收件人電話</th>
					<th>收件人地址</th>
					<th>是否退貨</th>
					<th>折抵毛毛幣</th>
					<th>訂單總計</th>
					<th>實付金額</th>
					<th>狀態</th>
					<th>更新</th>
					<th>明細</th>
				</tr>
			</thead>
			<tbody>
				<!--             先在外面宣告 -->
				<c:set var="memNo" value="" />
				<!--                             servlet setAttribute("shopOrders", shopOrders);         -->
				<c:forEach var="shopOrders" items="${shopOrders}">
					<!--                           裡面的值給外面宣告，才能印出來看  -->
					<c:set var="memNo" value="${shopOrders.member.memNo}" />
					<tr>
						<td class="order-no">${shopOrders.shopOrderNo}</td>

						<td class="mem-no">${shopOrders.member.memNo}</td>
						<%--         				<td class="mem-name">${shopOrders.member.memName}</td> --%>

						<td class="prod-order-date">${shopOrders.prodOrderDate}</td>
						<td class="pay-method"><c:choose>
								<c:when test="${shopOrders.payMethod == 0}">信用卡</c:when>
								<c:when test="${shopOrders.payMethod == 1}">匯款</c:when>
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
									<c:when test="${shopOrders.orderStatus == 0}">成立</c:when>
									<c:when test="${shopOrders.orderStatus == 1}">出貨</c:when>
									<c:when test="${shopOrders.orderStatus == 2}">完成</c:when>
									<c:when test="${shopOrders.orderStatus == 3}">取消</c:when>
									<c:when test="${shopOrders.orderStatus == 4}">未付款</c:when>
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
						</select></td>
						<td>
							<button class="edit-btn btn btn-primary "
								style="font-size: 10px; min-width: 50px;">修改</button>
							<button class="save-btn btn btn-success"
								style="display: none; font-size: 10px;">確定修改</button>
							<button class="cancel-btn btn btn-danger"
								style="display: none; font-size: 10px;">取消修改</button>
						</td>
					</tr>
				</c:forEach>

				<%--                             <form method="post" action="${pageContext.request.contextPath}/backend/faq/updatefaq.jsp"> --%>
				<%--                                 <input type="hidden" name="faqNo" value="${all.faqNo}"> --%>
				<!--                                 <input type="hidden" name="action" value="updatefaq"> -->
				<!--                                 <button class="btn btn-success" type="submit">修改</button> -->
				<!--                             </form> -->

				<%-- <form method="post" action="${pageContext.request.contextPath}/backend/faq/updatefaq.jsp"> --%>
				<%--     <input type="hidden" name="faqNo" value="${all.faqNo}"> --%>
				<%--     <input type="hidden" name="faqClass" value="${all.faqClass}"> --%>
				<%--     <input type="hidden" name="faqTitle" value="${all.faqTitle}"> --%>
				<%--     <input type="hidden" name="faqContent" value="${all.faqContent}"> --%>
				<!-- 	<button class="btn btn-success small-btn" style="font-size: 10px;" type="submit">修改</button> -->
				<!-- </form> -->

				<!--                         </td> -->
				<!--                         <td> -->
				<%--                             <form method="post" action="${pageContext.request.contextPath}/faq"> --%>
				<%--                                 <input type="hidden" name="faqNo" value="${all.faqNo}"> --%>
				<!--                                 <input type="hidden" name="action" value="deletefaq"> -->
				<!--                                 <button class="btn btn-danger small-btn" style="font-size: 10px;" type="submit">刪除</button> -->

				<!--                             </form> -->
				<!--                         </td> -->
				<!--                     </tr> -->
				<%--                 </c:forEach> --%>
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
			href="${pageContext.request.contextPath}/backend/shoporder/shoporderfirst.jsp">返回</a>
	</div>

	<%@ include file="/backend/backfoot.file"%>
	<script
		src="${pageContext.request.contextPath}/backend/shoporder/js/getAllshoporder.js"></script>

</body>
</html>
