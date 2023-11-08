<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/ae360af17e.js"
	crossorigin="anonymous"></script>
<script
	src="<%=request.getContextPath()%>/webutil/js/jquery-3.7.1.min.js"></script>
<script src="<%=request.getContextPath()%>/webutil/js/bootstrap.min.js"></script>
<title>結帳</title>
</head>

<body>
	<div class="container">
		<h1>購物車結帳</h1>
		<c:if test="${not empty cart}">
			<div class="row">
				<div class="col-12">
					<table class="table">
						<thead>
							<tr>
								<th>商品編號</th>
								<th>名稱</th>
								<th>數量</th>
								<th>價格</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${cart}" var="item">
								<tr>
									<td><c:out value="${item.prodNo}" /></td>
									<td><c:out value="${item.prodName}" /></td>
									<td><c:out value="${item.quantity}" /></td>
									<td><c:out value="${item.prodPrice}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</c:if>
	</div>
</body>

</html>