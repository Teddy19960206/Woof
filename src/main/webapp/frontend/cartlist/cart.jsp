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
<title>購物車</title>
</head>

<body>

	<div class="container">
		<h1>商品列表</h1>
		<div class="row">
			<div class="col-3">
				<div class="product">
					<img src="<%=request.getContextPath()%>/backend/images/1.png"
						alt="商品 1" style="max-width: 100px; max-height: 100px;">
					<h3>商品 1</h3>
					<p>價格: $10</p>
					<!-- 使用相同的 ID -->
					<input type="number" id="product-quantity" min="1" value="1">
					<button class="add-to-cart" data-id="1" data-name="商品 1"
						data-price="10">加入購物車</button>
				</div>
			</div>
			<div class="col-3">
				<div class="product">
					<img src="<%=request.getContextPath()%>/backend/images/4.png"
						alt="商品 2" style="max-width: 100px; max-height: 100px;">
					<h3>商品 2</h3>
					<p>價格: $20</p>
					<button class="add-to-cart" data-id="2" data-name="商品 2"
						data-price="20">加入購物車</button>
				</div>
			</div>
			<div class="col-3">
				<div class="product">
					<img src="<%=request.getContextPath()%>/backend/images/5.png"
						alt="商品 3" style="max-width: 100px; max-height: 100px;">
					<h3>商品 3</h3>
					<p>價格: $30</p>
					<button class="add-to-cart" data-id="3" data-name="商品 3"
						data-price="30">加入購物車</button>
				</div>
			</div>

			<!-- 購物車部分 -->
			<div class="col">
				<div id="cart-icon" style="cursor: pointer;">
					<i class="fas fa-cart-plus fa-flip-horizontal"></i> <span
						id="cart-count">0</span>
				</div>
				<div id="cart-list" style="display: none;"></div>
			</div>
		</div>
	</div>


	<!-- 購物車模態框 -->
	<div class="modal fade" id="cartModal" tabindex="-1"
		aria-labelledby="cartModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="cartModalLabel">購物車清單</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					購物車清單以表格形式顯示
					<table class="table">
						<thead>
							<tr>
								<th class="col-3">商品編號</th>
								<th class="col-3">商品名稱</th>
								<th class="col-2">數量</th>
								<th class="col-2">價格</th>
							</tr>
						</thead>
						<tbody id="cart-items-list">
							<!-- 							購物車商品項目將在這裡動態添加 -->
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">繼續購物</button>
					<a
						href="<%=request.getContextPath()%>/frontend/cartlist/checkout.jsp"
						class="btn btn-primary">結帳</a>
				</div>
			</div>
		</div>
	</div>


	<script
		src="<%=request.getContextPath()%>/frontend/cartlist/js/cart.js "></script>

</body>

</html>