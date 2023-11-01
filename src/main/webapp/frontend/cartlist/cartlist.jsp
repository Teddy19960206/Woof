<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">    <title>Document</title>
</head>

<body>

    <div class="container">
        <h1>商品列表</h1>
        <div class="row">
            <div class="col-3">
                <div class="product" data-id="1" data-name="商品 1" data-price="10">
                    <img src="webapp/baclend/images/1.png" alt="商品 1" style="max-width: 100px; max-height: 100px;">
                    <h3>商品 1</h3>
                    <p>價格: $10</p>
                    <button class="add-to-cart">加入購物車</button>
                </div>
            </div>
            <div class="col-3">
                <div class="product" data-id="2" data-name="商品 2" data-price="20">
                    <img src="webapp/baclend/images/4.png" alt="商品 2" style="max-width: 100px; max-height: 100px;">
                    <h3>商品 2</h3>
                    <p>價格: $20</p>
                    <button class="add-to-cart">加入購物車</button>
                </div>
            </div>
            <div class="col-3">
                <div class="product" data-id="3" data-name="商品 3" data-price="30">
                    <img src="webapp/baclend/images/5.png" alt="商品 3" style="max-width: 100px; max-height: 100px;">
                    <h3>商品 3</h3>
                    <p>價格: $30</p>
                    <button class="add-to-cart">加入購物車</button>
                </div>
            </div>

            <!-- 購物車部分 -->
            <div class="col">
                <div id="cart-icon">
                    <i class="fas fa-cart-plus fa-flip-horizontal"></i> <span id="cart-count">0</span>
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


            <script>

                // 獲取Cookie中的購物車數據，並轉換為JavaScript對象
                function getCartFromCookie() {
                    const cookieData = document.cookie.replace(/(?:(?:^|.*;\s*)cart\s*=\s*([^;]*).*$)|^.*$/, "$1");
                    return cookieData ? JSON.parse(cookieData) : [];
                }

                // 將購物車數據保存到Cookie，並設置過期日期為一個月後
                function saveCartToCookie(cartData) {
                    const expirationDate = new Date();
                    expirationDate.setMonth(expirationDate.getMonth() + 1); 
                    const expires = `expires=${expirationDate.toUTCString()}`;
                    document.cookie = `cart=${JSON.stringify(cartData)}; ${expires}; path=/`;
                }

                const products = document.querySelectorAll('.product');
                const cartCount = document.getElementById('cart-count');
                const cartList = document.getElementById('cart-list');
                const cartItemsList = document.getElementById('cart-items-list');
                const cartTotalAmount = document.getElementById('cart-total-amount');

                let cart = getCartFromCookie();

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
                    saveCartToCookie(cart);
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

                        updateCart(); // 立即更新购物清单
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

<script src="https://kit.fontawesome.com/ae360af17e.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>


</body>

</html>