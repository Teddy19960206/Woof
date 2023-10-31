<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/ae360af17e.js"
	crossorigin="anonymous"></script>
<title>購物車示例</title>
</head>
<body>
	<h1>商品列表</h1>

	<div class="container">
		<div class="row">
			<div class="col-md-3">

				<div id="products">
					<div class="product" data-id="1" data-name="商品 1"
						data-price="10.00">
						<h3>商品 1</h3>
						<p>價格: $10</p>
						<button class="add-to-cart">加入購物車</button>
					</div>

					<div class="product" data-id="2" data-name="商品 2"
						data-price="20.00">
						<h3>商品 2</h3>
						<p>價格: $20</p>
						<button class="add-to-cart">加入購物車</button>
					</div>

					<div class="product" data-id="3" data-name="商品 3"
						data-price="30.00">
						<h3>商品 3</h3>
						<p>價格: $30</p>
						<button class="add-to-cart">加入購物車</button>
					</div>
				</div>

			</div>

			<div class="col-md-9">

				<!-- 購物車部分 -->
				<div id="cart-icon">
					<i class="fas fa-cart-plus fa-flip-horizontal"></i> <span
						id="cart-count">0</span>
				</div>

				<div id="cart-list" style="display: none;">
					<h2>購物清單</h2>
					<ul id="cart-items-list">
						<!-- 購物清單內容將在這裡動態添加 -->
					</ul>
					<p>
						總金額: $<span id="cart-total-amount">0</span>
					</p>
					<button id="checkout">結帳</button>
					<button id="continue-shopping">繼續購物</button>
				</div>


			</div>
		</div>
	</div>

	<script>
	
		  const products = document.querySelectorAll('.product');
		  const cartCount = document.getElementById('cart-count');
		  const cartList = document.getElementById('cart-list');
		  const cartItemsList = document.getElementById('cart-items-list');
		  const cartTotalAmount = document.getElementById('cart-total-amount');
		
		  let cart = JSON.parse(sessionStorage.getItem('cart')) || [];
		
		  function updateCart() {
		    cartCount.textContent = cart.reduce((total, item) => total + item.qty, 0);
		    cartItemsList.innerHTML = '';
		    let totalAmount = 0;
		
		    cart.forEach(item => {
		      const li = document.createElement('li');
		      li.textContent = `${item.name} x${item.qty} - $${(item.price * item.qty)}`;
		      cartItemsList.appendChild(li);
		      totalAmount += item.price * item.qty;
		    });
		
		    cartTotalAmount.textContent = totalAmount;
		    sessionStorage.setItem('cart', JSON.stringify(cart));
		  }
		
		  products.forEach(product => {
		    const addToCartButton = product.querySelector('.add-to-cart');
		    addToCartButton.addEventListener('click', () => {
		      const productId = product.getAttribute('data-id');
		      const productName = product.getAttribute('data-name');
		      const productPrice = parseFloat(product.getAttribute('data-price'));
		      const existingItem = cart.find(item => item.id === productId);
		
		      if (existingItem) {
		        existingItem.qty += 1;
		      } else {
		        const item = { id: productId, name: productName, price: productPrice, qty: 1 };
		        cart.push(item);
		      }
		
		      updateCart();
		    });
		  });
		
		  document.getElementById('cart-icon').addEventListener('click', () => {
		    cartList.style.display = cartList.style.display === 'none' ? 'block' : 'none';
		  });
		
		  document.getElementById('checkout').addEventListener('click', () => {
		    // 執行結帳操作
		    // 在這裡添加處理結帳的邏輯
		  });
		
		  document.getElementById('continue-shopping').addEventListener('click', () => {
		    cartList.style.display = 'none';
		    // 可以執行其他繼續購物的操作
		  });
		
		  // 初始更新購物車
		  updateCart();
</script>

	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</body>
</html>


