<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/meta.file"%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/ae360af17e.js"
	crossorigin="anonymous"></script>
<script
	src="<%=request.getContextPath()%>/webutil/js/jquery-3.7.1.min.js"></script>
<script src="<%=request.getContextPath()%>/webutil/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/cartlist/css/finishorder.css">
<title>寵毛導師 Woof | 訂單完成</title>

</head>

<body>
	<script>
		let orderSuccess =
	<%=request.getAttribute("orderSuccess") != null ? request.getAttribute("orderSuccess") : false%>
		;
	</script>
	<%@ include file="/Header.file"%>

	<div class="container mb-5 bg-white p-5 rounded-4 shadow">
		<div id="orderSuccessIcon" style="display: none; text-align: center;">
			<h2>訂單完成</h2>


			<p>商城訂單編號: ${result.shopOrderNo}</p>
			<p>會員帳號: ${result.member.memNo}</p>
			<p>訂單成立時間: ${result.prodOrderDate}</p>


			<c:choose>
				<c:when test="${result.payMethod == 0}">
					<p>付款方式: 信用卡</p>
				</c:when>
				<c:when test="${result.payMethod == 1}">
					<p>付款方式: 匯款</p>
				</c:when>
				<c:when test="${result.payMethod == 2}">
					<p>付款方式: 綠界</p>
				</c:when>
			</c:choose>

			<c:choose>
				<c:when test="${result.shipMethod}">
					<p>取貨方式: 宅配</p>
				</c:when>
				<c:otherwise>
					<p>取貨方式: 超商取貨(未支援)</p>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${result.orderStatus == 0}">
					<p>訂單狀態: 成立</p>
				</c:when>
				<c:when test="${result.orderStatus == 1}">
					<p>訂單狀態: 出貨</p>
				</c:when>
				<c:when test="${result.orderStatus == 2}">
					<p>訂單狀態: 完成</p>
				</c:when>
				<c:when test="${result.orderStatus == 3}">
					<p>訂單狀態: 取消</p>
				</c:when>
				<c:when test="${result.orderStatus == 4}">
					<p>訂單狀態: 未付款</p>
				</c:when>
				<c:when test="${result.orderStatus == 5}">
					<p>訂單狀態: 已付款</p>
				</c:when>
			</c:choose>

			<c:choose>
				<c:when test="${result.hasReturn}">
					<p>是否有退貨: 有退貨</p>
				</c:when>
				<c:otherwise>
					<p>是否有退貨: 無退貨</p>
				</c:otherwise>
			</c:choose>


			<p>收件人姓名: ${result.recName}</p>
			<p>收件人電話: ${result.recMobile}</p>
			<p>收件人地址: ${result.recAddress}</p>
			<p>折抵毛毛幣: ${result.moCoin}</p>
			<p>訂單總金額: ${result.orderTotalPrice}</p>
			<p>實付金額: ${result.actualPrice}</p>
		</div>

	</div>
	<%@ include file="/Footer.file"%>
	<script
		src="<%=request.getContextPath()%>/frontend/cartlist/js/finishorder.js"></script>
</body>

</html>