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
	<script src="<%=request.getContextPath()%>/webutil/js/jquery-3.7.1.min.js"></script>
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
					<button class="add-to-cart" data-id="1" data-name="商品 1"
						data-price="10">加入購物車</button>
				</div>
			</div>
			<div class="col-3">
				<div class="product">
					<img src="<%=request.getContextPath()%>/backend/images/4.png" alt="商品 2"
						style="max-width: 100px; max-height: 100px;">
					<h3>商品 2</h3>
					<p>價格: $20</p>
					<button class="add-to-cart" data-id="2" data-name="商品 2"
						data-price="20">加入購物車</button>
				</div>
			</div>
			<div class="col-3">
				<div class="product">
					<img src="<%=request.getContextPath()%>/backend/images/5.png" alt="商品 3"
						style="max-width: 100px; max-height: 100px;">
					<h3>商品 3</h3>
					<p>價格: $30</p>
					<button class="add-to-cart" data-id="3" data-name="商品 3"
						data-price="30">加入購物車</button>
				</div>
			</div>

			<!-- 購物車部分 -->
			<div class="col">
				<div id="cart-icon">
					<i class="fas fa-cart-plus fa-flip-horizontal"></i> <span
						id="cart-count">0</span>
				</div>
				<div id="cart-list" style="display: none;">
<!-- 					<h2>購物清單</h2> -->
<!-- 					<ul id="cart-items-list"> -->
<!-- 						購物清單內容將在這裡動態添加 -->
<!-- 					</ul> -->
<!-- 					<p> -->
<!-- 						總金額: $<span id="cart-total-amount">0</span> -->
<!-- 					</p> -->
<!-- 					<button id="checkout">結帳</button> -->
<!-- 					<button id="continue-shopping">繼續購物</button> -->
				</div>
			</div>
		</div>
	</div>



	<script>
		// 添加商品到購物車的 AJAX 請求
		$(".add-to-cart").on("click", function() {
			let prodNo = $(this).data("id");

// 			console.log(prodNo);

			let prodName = $(this).data("name");
			let prodPrice = $(this).data("price");

			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/cartlist", 
				data : {
					action : "add",
					prodNo : prodNo,
					prodName : prodName,
					prodPrice : prodPrice
				},
				success : function(data) {

					console.log(data);
				
			        // 更新前端HTML中的數字
			   		 var totalItems = data;
			        $("#cart-count").text(data);
			  
				}
				
				
				
				
			});
		});
		
		
		
		
	</script>





</body>

</html>