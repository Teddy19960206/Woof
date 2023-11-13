<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/meta.file" %>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/ae360af17e.js"
	crossorigin="anonymous"></script>
<script
	src="<%=request.getContextPath()%>/webutil/js/jquery-3.7.1.min.js"></script>
<script src="<%=request.getContextPath()%>/webutil/js/bootstrap.min.js"></script>
<title>寵毛導師 Woof | 購物車結帳</title>
</head>

<body>
<%@ include file="/Header.file" %>

	<div class="container mb-5">
		<h2>購物車結帳</h2>
		<div class="row">
			<div class="col-md-7 border-end ">
				<table class="table">
				    <thead>
				        <tr>
				            <th><input type="checkbox" id="selectAll"></th>
				            <th>商品編號</th>
				            <th>名稱</th>
				            <th>數量</th>
				            <th>價格</th>
				            <th class="hidden"></th>
				        </tr>
				    </thead>
				    <tbody>
				        <c:forEach items="${cart}" var="item">
						    <tr>
						        <td><input type="checkbox" class="itemCheckbox"></td>
						        <td><c:out value="${item.prodNo}" /></td>
						        <td><c:out value="${item.prodName}" /></td>
						        <td>
						            <button type="button" class="btn btn-secondary btn-sm decrease-btn" onclick="changeQuantity('${item.prodNo}', -1)">-</button>
						            <span class="quantity"><c:out value="${item.quantity}" /></span>
						            <button type="button" class="btn btn-secondary btn-sm increase-btn" onclick="changeQuantity('${item.prodNo}', 1)">+</button>
						        </td>
						        <td><c:out value="${item.prodPrice}" /></td>
						        <td><i class="fa-solid fa-trash" onclick="deleteItem('${item.prodNo}')"></i></td>
						    </tr>
						</c:forEach>
				    </tbody>
				</table>
			</div>

			<!-- 右側欄位：資訊輸入 -->
			<div class="col-md-5 ps-4">
			    <h4>收件人資訊</h4>
			    <div class="row">
			    	<div class="col-md">
				   		 <div class="form-group">
				        	<label for="name">會員姓名</label>
				        	<input type="text" class="form-control" id="name" name="name" required>
				   	 	 </div>
					</div>  
				 	<div class="col-md"> 
					     <div class="form-group">
				         	 <label for="phone">手機</label>
				        	  <input type="text" class="form-control" id="phone" name="phone" required>
				    	 </div>
					 </div>		
				</div>
				<div class="row">
					<div class="col-md">
						<div class="form-group">
						    <label for="email">E-mail</label>
						    <input type="email" class="form-control" id="email" name="email" required>
						</div>
					</div>
					<div class="col-md"> 
						<div class="form-group">
						    <label for="address">地址</label>
						    <input type="text" class="form-control" id="address" name="address" required>
						</div>
					 </div>	
				</div>
				<h4 class="mt-3">毛毛幣折抵</h4>
				<div class="row">
				<div class="col-4 p-0">	
				<input class="form-check-input" type="radio" id="UseSmmp" name="useMocoin" value="usemocoin">
				<label for="UseSmmp">使用毛毛幣</label>
				</div>
				<div class="col-4 p-0">
				<input class="form-check-input" type="radio" id="notusemocoin" name="useMocoin" value="nousemocoin">
				<label for="notusemocoin">不使用毛毛幣</label>
				</div>
				</div>
				<!-- 毛毛幣輸入框 -->
				<input type="text" id="inputSmmp" class="form-control text-center mt-1 " style=" width: 70px" onkeypress='validate(event)' disabled>	
				
					
					
				<h4 class="mt-3">付款方式</h4>
				<div class="row">
					<div class="col-4 p-0">
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="payment"
								value="0" id="credit" checked /> <label
								class="form-check-label" for="credit">信用卡一次付清</label>
						</div>
					</div>
					<div class="col-2 p-0">
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="payment"
								value="1" id="transfer" /> <label class="form-check-label"
								for="transfer">匯款</label>
						</div>
					</div>
					<div class="col-3 p-0">
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="payment"
								value="2" id="ecPay" /> <label class="form-check-label"
								for="ecPay">使用綠界</label>
						</div>
					</div>
					<div class="form showPayment" id="showPayment">
					    <div class="d-flex mt-3">
					        <input class="form-control text-center" style=" width: 70px" type="text" maxlength="4" onkeypress='validate(event)' value="1111" pattern="[0-9]{4}" title="請輸入4位數字" required />
					        <span class="mx-2 mt-1">-</span>
					        <input class="form-control text-center" style=" width: 70px" type="text" maxlength="4" onkeypress='validate(event)' value="2222" pattern="[0-9]{4}" title="請輸入4位數字" required />
					        <span class="mx-2 mt-1">-</span>
					        <input class="form-control text-center" style=" width: 70px" type="text" maxlength="4" onkeypress='validate(event)' value="3333" pattern="[0-9]{4}" title="請輸入4位數字" required />
					        <span class="mx-2 mt-1">-</span>
					        <input class="form-control text-center" style=" width: 70px" type="text" maxlength="4" onkeypress='validate(event)' value="4444" pattern="[0-9]{4}" title="請輸入4位數字" required />
					    </div>

						<div class="mt-1">
							<label class="mb-1">驗證碼</label> <input
								class="form-control verification text-center" style=" width: 70px" type="text"
								maxlength="3" onkeypress='validate(event)' value="555">
						</div>

						<div class="d-flex justify-content-start mt-3 myBtn">
							<button type="submit" class="btn btn-primary">確認付款</button>
						</div>
					</div>
			
				</div>
			</div>
		</div>
	</div>
		<%@ include file="/Footer.file" %>
		<script src="<%=request.getContextPath()%>/frontend/cartlist/js/checkout.js"></script>
</body>

</html>