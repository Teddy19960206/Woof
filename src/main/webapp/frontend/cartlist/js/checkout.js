//let pathName = window.document.location.pathname;
//let projectName = pathName.substring(0, pathName.substring(1).indexOf("/") + 1);

const useSmmpRadio = document.getElementById("UseSmmp");
const notUseSmmpRadio = document.getElementById("notusemocoin");
const inputSmmp = document.getElementById("inputSmmp");
const credit = document.getElementById("credit");
const transfer = document.getElementById("transfer");
const ecPay = document.getElementById("ecPay");
const showPayment = document.getElementById("showPayment");
const address = document.getElementById("address");
const  hiddenInputSmmp = document.getElementById("hiddenInputSmmp");

window.addEventListener("load" , ()=>{

    notUseSmmpRadio.checked = true;
    inputSmmp.disabled = true;
    hiddenInputSmmp.value = "0";

})

// 監聽單選按鈕的變更事件
useSmmpRadio.addEventListener( "change", function () {
    // 如果選擇使用毛毛幣，移除disabled屬性
    inputSmmp.removeAttribute("disabled");
    hiddenInputSmmp.value = inputSmmp.value;
});

notUseSmmpRadio.addEventListener("change", function () {
    // 如果選擇不使用毛毛幣，增加disabled屬性
    inputSmmp.value = "";
	inputSmmp.setAttribute("disabled", "disabled");
	hiddenInputSmmp.value = "0";
});


credit.addEventListener("change" , function (){
    html = ` <div class="d-flex mt-3">
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
				</div>`;
    showPayment.innerHTML = html;
});

transfer.addEventListener("change" , function (){
    html = `<h5 class="mt-3">匯款帳號</h5>
                  <p class="m-0">銀行：合作金庫 (006)</p>
                  <p class="m-0">匯款帳號：3333-333-333333</p>
                      <div class="d-flex justify-content-start myBtn">
							<button type="submit" class="btn btn-primary mt-3">確認付款</button>
						</div>`;
    showPayment.innerHTML = html;
});

ecPay.addEventListener("change" , function (){

    html = `<div class="d-flex justify-content-start myBtn">
				<button type="submit" class="btn btn-primary mt-3">使用綠界</button>
			</div>`;
    showPayment.innerHTML = html;
});

//=============================================

document.getElementById('inputSmmp').addEventListener('input', function (e) {
    const maxCoinsElement = document.getElementById('remainingCoins');
    const maxCoins = parseInt(maxCoinsElement.innerText); // 從頁面獲取毛毛幣的餘額
    let inputCoins = parseInt(e.target.value); 

	hiddenInputSmmp.value = e.target.value; // 將輸入的值賦予 hiddenInputSmmp
	
	console.log(maxCoinsElement);
	console.log(maxCoins);
	console.log(inputCoins);

    if (isNaN(inputCoins)) {
        inputCoins = 0; // 如果輸入不是數字，設為0
    }

    if (inputCoins > maxCoins) {
        // 如果輸入的毛毛幣數量超過了餘額，顯示模態框
        $('#exceedModal').modal('show');
        e.target.value = ''; // 清空輸入框
        maxCoinsElement.innerText = maxCoins; 
        document.getElementById('totalAfterCoins').value = document.getElementById('totalPrice').value; // 重設扣除毛毛幣後的總計
    } else {
        // 正常情況下，更新扣除毛毛幣後的總計
        const totalPrice = parseInt(document.getElementById('totalPrice').value);
        const totalAfterCoins = totalPrice - inputCoins; // 防止 NaN
        document.getElementById('totalAfterCoins').value = totalAfterCoins;
    }
});



//=============================================

const prodNo = document.getElementById("prodNo");
const memNoElement = document.getElementById("memNo");
const memNo = memNoElement.getAttribute("data-memname");

console.log(memNo);	
	
//頁面近來自動載入方法
window.addEventListener("load" , ()=>{	
	
    fetchCart(memNo);
    
});

function fetchCart(memNo) {
    $.ajax({
        type: "POST",
        url: `${projectName}/checkout`,
        data: {
            action: "getCart",
            memNo: memNo
        },
        success: function(cartJson) {
			
			console.log("Returned cart data:", cartJson);	
			
            renderCartItems(cartJson);                  
        },
        error: function(error) {
            console.error("Error fetching cart data: ", error);
        }
    });
}

function renderCartItems(cart) {
	
	console.log(cart);
	
    let html = "";
	let total = 0;
	
    cart.forEach(item => {	
		                
        let subtotal = item.quantity * item.prodPrice;	
        total += subtotal;
		console.log(subtotal);	
			
        html += `<tr>
                    <td id="item-row-${item.prodNo}" class="hidden" style="display: none;">${item.prodNo}</td>                    
                    <td>${item.prodName}</td>
                    <td>
                        <div>
                            <button type="button" class="btn btn-secondary btn-sm decrease-btn mx-2" onclick="decreaseQuantity('${item.prodNo}')">-</button>              
                            <span class="quantity" id="quantity-${item.prodNo}">${item.quantity}</span>
                            <button type="button" class="btn btn-secondary btn-sm increase-btn mx-2" onclick="increaseQuantity('${item.prodNo}')">+</button>          
                        </div>
                    </td>
                    <td>NT$<span id="price-${item.prodNo}">${item.prodPrice}</span></td>
                    <td>NT$<span id="subtotal-${item.prodNo}">${subtotal}</span></td>
                    <td><i class="fa-solid fa-trash" style="cursor: pointer;" onclick="deleteItem('${item.prodNo}')"></i></td>
                </tr>`;

    });

    $("#cart-items-list").html(html);
	$("#totalPrice").val(total);
    $("#totalAfterCoins").val(total);
//    document.getElementById('totalPrice').value = total;
}

//全部刪除
function deleteItem(prodNo) {
    // 儲存當前商品編號，以便在確認刪除時使用
    $('#confirmDelete').data('prodNo', prodNo);

    // 顯示模態框
    $('#deleteConfirmationModal').modal('show');
}

$('#confirmDelete').click(function() {
	
    let prodNo = $(this).data('prodNo');
    
    $.ajax({
        type: 'POST',
        url: `${projectName}/checkout`,
        data: {
            action: 'deleteItem',
            prodNo: prodNo,
            memNo: memNo 
        },
        success: function(response) {
            // 在叫一個方法即時更新頁面消失的商品
            updateCartDisplay();
        },
        error: function(error) {
            console.error("Error: ", 刪除失敗);
        }
    });

//更新購物車顯示
function updateCartDisplay() {

    $.ajax({
        type: 'POST',
        url: `${projectName}/checkout`,
        data: {
            action: 'getCart',
            memNo: memNo 
        },
        success: function(response) {
			// response 是購物車數據的陣列	
            renderCartItems(response); 
        },
        error: function(error) {
            console.error("Error: ", error);
        }
    });
}
    // 關閉模態框
    $('#deleteConfirmationModal').modal('hide');
});


$('#cancelDelete').on('click', function() {
	
  $('#deleteConfirmationModal').modal('hide'); 
});


//減少1按扭
function decreaseQuantity(prodNo) {
	
	console.log("prodNo:", prodNo);

 	let quantitySpan = document.getElementById(`quantity-${prodNo}`);    
    console.log("quantitySpan:", quantitySpan);
    
    // 檢查 quantitySpan 是否存在
    if (quantitySpan) {
        let quantity = parseInt(quantitySpan.innerText);

		console.log("數量"+quantity);

        if (quantity > 1) {
            quantity--;
            quantitySpan.innerText = quantity;

            // 發送 AJAX 請求到後端更新數量
            updateDecreaseQuantity(prodNo, quantity);
        }
    } else {
        console.error("Quantity span not found for prodNo:", prodNo);
    }
      // 更新小計
      updateItemSubtotal(prodNo);
}

function updateDecreaseQuantity(prodNo, newQuantity) {
    $.ajax({
        type: 'POST',
        url: `${projectName}/checkout`,
        data: {
            action: 'decreaseQuantity',
            prodNo: prodNo,
            memNo: memNo,
            quantity: newQuantity 
        },
        success: function(response) {
			// 更新畫面上的購物車
            renderCartItems(response); 
        },
        error: function(error) {
            console.error("Error: ", error);
        }
    });
}


//增加1按扭
function increaseQuantity(prodNo) {
    let quantitySpan = document.getElementById(`quantity-${prodNo}`);
    if (quantitySpan) {
        let quantity = parseInt(quantitySpan.innerText);
        quantity++;
        updateIncreaseQuantity(prodNo, quantity);
    } else {
        console.error("Quantity span not found for prodNo:", prodNo);
    }
        // 更新小計
        updateItemSubtotal(prodNo);
}

function updateIncreaseQuantity(prodNo, newQuantity) {
	
	console.log(newQuantity);
	
    $.ajax({
        type: 'POST',
        url: `${projectName}/checkout`,
        data: {
            action: 'increaseQuantity',
            prodNo: prodNo,
            memNo: memNo,
            quantity: newQuantity 
        },
        success: function(response) {
			// 更新畫面上的購物車
            renderCartItems(response); 
        },
        error: function(error) {
            console.error("Error: ", error);
        }
    });
}

// 處理每個商品的小計
function updateItemSubtotal(prodNo) {
    let quantitySpan = document.getElementById(`quantity-${prodNo}`);
    let priceSpan = document.getElementById(`price-${prodNo}`);
    let subtotalSpan = document.getElementById(`subtotal-${prodNo}`);

	console.log(priceSpan);
	
    if (quantitySpan && priceSpan && subtotalSpan) {
        let quantity = parseInt(quantitySpan.innerText);
        let price = parseFloat(priceSpan.innerText);
        let subtotal = quantity * price;
        subtotal = Math.floor(subtotal); // 將小計整數化
        subtotalSpan.innerText = subtotal; // 將整數小計顯示在元素中
    }
}

