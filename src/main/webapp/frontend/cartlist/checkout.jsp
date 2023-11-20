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
<link rel="stylesheet" href="${pageContext.request.contextPath}/frontend/cartlist/css/checkout.css" />
<title>寵毛導師 Woof | 購物車結帳</title>

<style>

</style>

</head>

<body>
	<%@ include file="/Header.file"%>
	<form
		action="${pageContext.request.contextPath}/shoporder?action=addshoporder"
		method="post">

		<div class="container mb-5 bg-white p-5 rounded-4 shadow">
			<h2>購物車結帳</h2>
			<div class="row">
				<div class="col-md-7 border-end " style="text-align: center;">
					<table class="table table-hover">
						<thead>
							<tr>
								<!-- 							<th><input type="checkbox" id="selectAll"></th> -->
								<th>名稱</th>
								<th>數量</th>
								<th>單價</th>
								<th>小計</th>
								<th class="hidden"></th>
							</tr>
						</thead>
						<tbody id="cart-items-list">
							<!-- 							有時間就做 -->
							<!-- 						<td style="text-align: center;"><input type="checkbox" class="itemCheckbox"></td> -->
							<!-- 動態生成購物車內容 -->
						</tbody>
					</table>

					<!-- 總計的地方 -->
					<div class="summary-container">
						<div class="summary-row">
							<div class="summary-label">總計:</div>
							<div class="summary-value">
								NT$<input id="totalPrice" name="totalPrice"
									class="form-control border-0 text-end  p-0"
									style="width: 120px; height: 22px;" value="0"
									readonly="readonly">

							</div>
						</div>
						<div class="summary-row">
							<div class="summary-label">毛毛幣折抵:</div>
							<div class="summary-value ">
								<input class="form-check-input ml-5" type="radio"
									name="mocoinOption" value="usemocoin" id="UseSmmp">使用 <input
									class="form-check-input" type="radio" name="mocoinOption"
									value="nousemocoin" id="notusemocoin" checked>不使用
							</div>
						</div>
						<div class="summary-value">
							<input type="text" id="inputSmmp" name="inputSmmp" value="0"
								class="form-control text-end mb-1 p-0"
								style="width: 50px; height: 25px;" disabled>
						</div>
						<div class="summary-row">
							<div class="summary-label">剩餘毛毛幣:</div>
							<div class="summary-value">
								<span id="remainingCoins">${member.momoPoint}</span>
							</div>
						</div>
						<div class="summary-row">
							<div class="summary-label">扣除毛毛幣總金額:</div>
							<div class="summary-value">
								<!-- 								NT$<span id="totalAfterCoins" name="totalAfterCoins"></span> -->
								NT$<input id="totalAfterCoins" name="totalAfterCoins"
									class="form-control border-0 text-end p-0"
									style="width: 120px; height: 22px;" value="0"
									readonly="readonly">

							</div>
						</div>
					</div>

				</div>

				<!-- 超過自己所擁有的毛毛幣模態框 -->
				<div class="modal fade" id="exceedModal" tabindex="-1"
					aria-labelledby="exceedModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-body text-center">
								<div class="modal-icon mb-4">
									<i class="fas fa-exclamation-circle fa-3x text-warning"></i>
								</div>
								<h5>毛毛幣超過了餘額唷</h5>
							</div>
							<div class="modal-footer justify-content-center">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">關閉</button>
							</div>
						</div>
					</div>
				</div>

				<!-- 刪除模態框 -->
				<div class="modal" id="deleteConfirmationModal" tabindex="-1"
					role="dialog">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-body text-center">
								<div class="modal-icon mb-4">
									<i class="fas fa-exclamation-circle fa-3x text-warning"></i>
								</div>
								<h5>確定要刪除此商品嗎🥹</h5>
							</div>
							<div class="modal-footer justify-content-center">
								<button type="button" class="btn btn-danger" id="confirmDelete">確定刪除</button>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal" id="cancelDelete">取消刪除</button>
							</div>
						</div>
					</div>
				</div>

				<!-- 右側欄位：資訊輸入 -->
				<div class="col-md-5 ps-4">
					<h4>收件人資訊</h4>
					<div class="row">
						<div class="col-md">
							<div class="form-group">
								<label for="name" class="mt-3">會員姓名</label> <input type="hidden"
									id="memNo" name="memNo" value="${member.memNo}"
									data-memname="${member.memNo}" /> <input type="text"
									class="form-control" id="name" name="memName"
									value="${member.memName}" required>
							</div>
						</div>
						<div class="col-md">
							<div class="form-group">
								<label for="phone" class="mt-3">手機</label> <input type="text"
									class="form-control" id="phone" name="phone"
									value="${member.memTel}" required>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="email">E-mail</label> <input type="email"
							class="form-control" id="email" name="email"
							value="${member.memEmail}" required>
						<h4 class="mt-3">取貨方式</h4>
						<div class="row">
							<div>
								<input class="form-check-input" type="radio" id="shipMethod"
									name="shipMethod" value="false" checked> <label
									for="shipMethod">宅配</label> <input class="form-check-input"
									type="radio" id="convenienceStore" name="shipMethod"
									value="true"> <label for="shipMethod">超商</label>
							</div>
						</div>
						<div>
							<label for="address" class="mt-3">地址</label> <input type="text"
								class="form-control" id="address" name="address"
								value="${member.memAddress}" required>
						</div>
					</div>

					<h4 class="mt-3">付款方式</h4>
					<div class="row">
						<div>
							<input class="form-check-input" type="radio" name="payment"
								value="0" id="credit" checked /> <label
								class="form-check-label" for="credit">信用卡一次付清</label> <input
								class="form-check-input" type="radio" name="payment" value="1"
								id="transfer" /> <label class="form-check-label" for="transfer">匯款
							</label> <input class="form-check-input" type="radio" name="payment"
								value="2" id="ecPay" /> <label class="form-check-label"
								for="ecPay">使用綠界</label>
						</div>
						<div class="form showPayment" id="showPayment">
							<div class="d-flex mt-3">
								<input class="form-control text-center" style="width: 70px"
									type="text" maxlength="4" onkeypress='validate(event)'
									value="1111" pattern="[0-9]{4}" title="請輸入4位數字" required /> <span
									class="mx-2 mt-1">-</span> <input
									class="form-control text-center" style="width: 70px"
									type="text" maxlength="4" onkeypress='validate(event)'
									value="2222" pattern="[0-9]{4}" title="請輸入4位數字" required /> <span
									class="mx-2 mt-1">-</span> <input
									class="form-control text-center" style="width: 70px"
									type="text" maxlength="4" onkeypress='validate(event)'
									value="3333" pattern="[0-9]{4}" title="請輸入4位數字" required /> <span
									class="mx-2 mt-1">-</span> <input
									class="form-control text-center" style="width: 70px"
									type="text" maxlength="4" onkeypress='validate(event)'
									value="4444" pattern="[0-9]{4}" title="請輸入4位數字" required />
							</div>

							<div class="mt-1">
								<label class="mb-1">驗證碼</label> <input
									class="form-control verification text-center"
									style="width: 70px" type="text" maxlength="3"
									onkeypress='validate(event)' value="555">
							</div>
							<div class="d-flex justify-content-start mt-3 myBtn">

								<button type="submit" class="btn btn-primary">確認付款</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<%@ include file="/Footer.file"%>
	<script
		src="<%=request.getContextPath()%>/frontend/cartlist/js/checkout.js"></script>
</body>

</html>