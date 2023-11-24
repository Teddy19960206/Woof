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


	<div class="container p-3">
		<div class="invoice-border">
			<div class="row mb-1">
				<div class="col-9">
					<p>
						<span class="text-bold">商城訂單編號：</span>#${shopOrder.shopOrderNo}
					</p>
					<p>
						<span class="text-bold">訂單成立時間：</span>${shopOrder.prodOrderDate}
					</p>
				</div>

				<div class="col-2">
					<img
						src="<%=request.getContextPath()%>/backend/images/CUTEWOOF.png"
						style="width: 200px;" alt="Barcode" />
					<%-- 					<img src="<%=request.getContextPath()%>/backend/images/logo.png" alt="CuteWoof" /> --%>
					<p></p>
				</div>
			</div>

			<div class="invoice-header">
				<h1 class="text-center">訂單明細</h1>
			</div>
			<!-- 地址資訊 -->
			<div class="row mb-4">
				<div class="col-6">
					<p>
					<h4 class="text-bold mt-1" style="margin-bottom: 2px;">收件人資訊</h4>
					</p>
					<p class="mb-0">名稱：${shopOrder.recName}</p>
					<p class="mb-0">地址：${shopOrder.recAddress}</p>
					<p>手機:${shopOrder.recMobile}</p>
				</div>

			</div>


			<table class="table">
				<thead>
					<tr>
						<th class="col-2"
							style="background-color: #F8EEE6; text-align: center;">商品名稱</th>
						<th class="col-2"
							style="background-color: #F8EEE6; text-align: center;">數量</th>
						<th class="col-2"
							style="background-color: #F8EEE6; text-align: center;">單價</th>
						<th class="col-2"
							style="background-color: #F8EEE6; text-align: center;">總計</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orderDetails}" var="orderDetail">
						<tr>
							<td class="col-2" style="text-align: center;">${orderDetail.prodName}</td>
							<td class="col-2" style="text-align: center;">${orderDetail.orderAmount}</td>
							<td class="col-2" style="text-align: center;">${orderDetail.prodPrice}</td>
							<td class="col-2" style="text-align: center;">NT$${orderDetail.orderAmount
								* orderDetail.prodPrice}</td>
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
								<td style="text-align: right;">NT$${shopOrder.orderTotalPrice}</td>
							</tr>
							<tr>
								<th class="text-right">毛毛幣：</th>
								<td style="text-align: right;">-${shopOrder.moCoin}</td>
							</tr>
							<tr>
								<th class="text-right">總計：</th>
								<td style="text-align: right;">NT$${shopOrder.actualPrice}</td>
							</tr>
						</table>
					</div>
				</div>
			</c:if>
		</div>
	</div>

	<div style="margin-left: 14px;">
		<a class="btn btn-secondary" href="javascript:history.go(-1);">返回</a>
	</div>

	<%@ include file="/backend/backfoot.file"%>

	<%-- 		src="${pageContext.request.contextPath}/backend/shoporder/js/getAllshoporder.js"></script> --%>

</body>
</html>
