<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="com.woof.shoporderdetail.service.ShopOrderDetailServiceImpl"%>
<%@ page import="com.woof.shoporderdetail.entity.ShopOrderDetail"%>
<%@ page import="com.woof.shoporder.service.ShopOrderServiceImpl"%>
<%@ page import="com.woof.shoporder.entity.ShopOrder"%>
<%@ page import="com.woof.shoporderdetail.entity.ShopOrderDetailDTO"%>
<%@ page import="com.woof.member.entity.Member"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<title>訂單明細</title>
<style>
.invoice-border {
	border: 2px solid #ddd;
	padding: 15px;
	margin-top: 20px;
}

.invoice-header {
	margin-bottom: 10px;
}
</style>

<%@ include file="/backend/backhead.file"%>

</head>
<body>

	<%@ include file="/backend/backbody.file"%>

	<%
	String shopOrderNoStr = request.getParameter("shopOrderNo");
	System.out.println(shopOrderNoStr + "取得的編號==========");

	Integer shopOrderNo = Integer.parseInt(shopOrderNoStr);

	ShopOrderDetailServiceImpl service = new ShopOrderDetailServiceImpl();
	List<ShopOrderDetailDTO> orderDetails = service.findOneShopOrderNoDetailObj(shopOrderNo);
	System.out.println(orderDetails);

	request.setAttribute("orderDetails", orderDetails);

	System.out.println(orderDetails + "ListObject取得的編號==========");

	ShopOrderServiceImpl service2 = new ShopOrderServiceImpl();
	ShopOrder shopOrder = service2.findByShopOrderNo(shopOrderNo);

	request.setAttribute("shopOrder", shopOrder);
	%>

	<div class="container p-3">
		<div class="invoice-border">
			<div class="row mb-4">
				<div class="col-6">
					<!-- 發票標頭資訊在這裡 -->
					<p>
						<span class="text-bold">商城訂單編號：</span>123456
					</p>
					<p>
						<span class="text-bold">訂單成立時間：</span>2023-01-01
					</p>
				</div>
				<div class="col-6 text-right">
					<!-- 條碼和標誌在這裡 -->
					<img src="barcode_url" alt="Barcode" /> 
					<img src="barcode_url"
						alt="CuteWoof" />
					<p>COMPANY LOGO</p>
				</div>
			</div>

			<div class="invoice-header">
				<h1 class="text-center">訂單明細</h1>
			</div>
			<!-- 地址資訊 -->
			<div class="row mb-4">
				<div class="col-6">
					<p>
						<span class="text-bold">收件人資訊</span>
					</p>
					<p>名稱：${shopOrder.recName}</p>
					<p>地址：${shopOrder.recAddress}</p>
					<p>手機:${shopOrder.recMobile}</p>
				</div>

			</div>


			<table class="table">
				<thead>
					<tr>
						<th>商品名稱</th>
						<th>數量</th>
						<th>單價</th>
						<th>總計</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orderDetails}" var="orderDetail">
						<tr>
							<td>${orderDetail.prodName}</td>
							<td>${orderDetail.orderAmount}</td>
							<td>${orderDetail.prodPrice}</td>
							<td>NT$${orderDetail.orderAmount * orderDetail.prodPrice}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<c:if test="${shopOrder != null}">

				<div class="row">
					<div class="col-md-4 offset-md-8">
						<table class="table">
							<tr>
								<th class="text-right">小計：</th>
								<td>NT$${shopOrder.orderTotalPrice}</td>
							</tr>
							<tr>
								<th class="text-right">毛毛幣：</th>
								<td>-${shopOrder.moCoin}</td>
							</tr>
							<tr>
								<th class="text-right">總計：</th>
								<td>NT$${shopOrder.actualPrice}</td>
							</tr>
						</table>
					</div>
				</div>
			</c:if>
		</div>
	</div>

	<!-- 	<div class="container text-center p-3"> -->
	<!-- 		<h3>訂單明細</h3> -->
	<!-- 		<table class="table table-hover"> -->
	<!-- 			<thead> -->
	<!-- 				<tr> -->
	<!-- 					<th>訂單編號</th> -->
	<!-- 					<th>商品</th> -->
	<!-- 					<th>照片</th> -->
	<!-- 					<th>數量</th> -->
	<!-- 					<th>單價</th> -->
	<!-- 					<th>金額</th> -->
	<!-- 				</tr> -->
	<!-- 			</thead> -->
	<!-- 			<tbody> -->
	<%-- 				<c:forEach var="all" items="${all}"> --%>
	<!-- 					<tr> -->
	<%-- 						<td class="order-no">${all.shopOrderNo}</td> --%>

	<%-- 						<td class="mem-no">${all.member.memNo}</td> --%>
	<%-- 						        				<td class="mem-name">${all.member.memName}</td> --%>

	<%-- 						<td class="prod-order-date">${all.prodOrderDate}</td> --%>
	<%-- 						<td class="pay-method"><c:choose> --%>
	<%-- 								<c:when test="${all.payMethod == 0}">信用卡</c:when> --%>
	<%-- 								<c:when test="${all.payMethod == 1}">匯款</c:when> --%>
	<%-- 							</c:choose></td> --%>
	<%-- 						<td class="ship-method"><c:choose> --%>
	<%-- 								<c:when test="${all.shipMethod == false}">宅配</c:when> --%>
	<%-- 								<c:when test="${all.shipMethod == true}">超商取貨</c:when> --%>
	<%-- 							</c:choose></td> --%>
	<%-- 						<td class="rec-name">${all.recName}</td> --%>
	<%-- 						<td class="rec-mobile">${all.recMobile}</td> --%>
	<%-- 						<td class="rec-address">${all.recAddress}</td> --%>
	<%-- 						<td class="has-return"><c:choose> --%>
	<%-- 								<c:when test="${all.hasReturn == false}">無退貨</c:when> --%>
	<%-- 								<c:when test="${all.hasReturn == true}">有退貨</c:when> --%>
	<%-- 							</c:choose></td> --%>
	<%-- 						<td class="mo-coin">${all.moCoin}</td> --%>
	<%-- 						<td class="order-total-price">${all.orderTotalPrice}</td> --%>
	<%-- 						<td class="actual-price">${all.actualPrice}</td> --%>
	<%-- 						<td><span class="status-text"> <c:choose> --%>
	<%-- 									<c:when test="${all.orderStatus == 0}">成立</c:when> --%>
	<%-- 									<c:when test="${all.orderStatus == 1}">出貨</c:when> --%>
	<%-- 									<c:when test="${all.orderStatus == 2}">完成</c:when> --%>
	<%-- 									<c:when test="${all.orderStatus == 3}">取消</c:when> --%>
	<%-- 									<c:when test="${all.orderStatus == 4}">未付款</c:when> --%>
	<%-- 								</c:choose> --%>
	<!-- 						</span> <select class="status-select" style="display: none;"> -->
	<%-- 								<option value="0" ${all.orderStatus == 0 ? 'selected' : ''}>成立</option> --%>
	<%-- 								<option value="1" ${all.orderStatus == 1 ? 'selected' : ''}>出貨</option> --%>
	<%-- 								<option value="2" ${all.orderStatus == 2 ? 'selected' : ''}>完成</option> --%>
	<%-- 								<option value="3" ${all.orderStatus == 3 ? 'selected' : ''}>取消</option> --%>
	<%-- 								<option value="4" ${all.orderStatus == 4 ? 'selected' : ''}>未付款</option> --%>
	<!-- 						</select></td> -->
	<!-- 						<td> -->
	<!-- 							<button class="edit-btn btn btn-primary " -->
	<!-- 								style="font-size: 10px; min-width: 50px;">修改</button> -->
	<!-- 							<button class="save-btn btn btn-success" -->
	<!-- 								style="display: none; font-size: 10px;">確定修改</button> -->
	<!-- 							<button class="cancel-btn btn btn-danger" -->
	<!-- 								style="display: none; font-size: 10px;">取消修改</button> -->
	<!-- 						</td> -->
	<!-- 						<td> -->
	<%-- 						<form method="post" action="${pageContext.request.contextPath}/backend/faq/updatefaq.jsp"> --%>
	<%-- 						    <input type="hidden" name="faqNo" value="${all.faqNo}"> --%>
	<%-- 						    <input type="hidden" name="faqClass" value="${all.faqClass}"> --%>
	<%-- 						    <input type="hidden" name="faqTitle" value="${all.faqTitle}"> --%>
	<%-- 						    <input type="hidden" name="faqContent" value="${all.faqContent}"> --%>
	<!-- 							<button class="btn btn-secondary small-btn" style="font-size: 10px;" type="submit">查詢</button> -->
	<!-- 						</form>	 -->
	<!-- 						</td> -->
	<!-- 					</tr> -->
	<%-- 				</c:forEach> --%>

	<!-- 			</tbody> -->
	<!-- 		</table> -->




	<a class="btn btn-secondary"
		href="${pageContext.request.contextPath}/backend/shoporder/shoporderfirst.jsp">返回</a>
	</div>

	<%@ include file="/backend/backfoot.file"%>
	<!-- 	<script -->
	<%-- 		src="${pageContext.request.contextPath}/backend/shoporder/js/getAllshoporder.js"></script> --%>

</body>
</html>
