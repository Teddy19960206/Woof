let pathName = window.document.location.pathname;
let projectName = pathName.substring(0, pathName.substring(1).indexOf("/") + 1);


//自動重新刷新會載入總數
//$(function() {
//	getCartTotalQuantity();
//});
//
//function getCartTotalQuantity() {
//	$.ajax({
//		type: "POST",
//		url: `${projectName}/cart`,
//		data: {
//			action: "getTotalQuantity",
//		},
//		 dataType: "json",
//		success: function(response) {
//			// 假設後端返回的 response 是一個物件，包含 totalQuantity 屬性
//			$("#cart-count").text(response.totalQuantity);
//		},
//		error: function(xhr, status, error) {
//			if (xhr.status === 401) { // 未授權錯誤
//				window.location.href = `${projectName}/frontend/member/login/login.jsp`;
//			}
//		}
//	});
//}


// 添加商品到購物車的 AJAX 請求
$(".add-to-cart").on("click", function() {
	
	 alert("加入購物車");
	 
	let prodNo = $(this).data("id");
	let prodName = $(this).data("name");
	let prodPrice = $(this).data("price");
	var quantity = parseInt($('#product-quantity').val()); // 從數量選擇器獲取選擇的數量
//    var productImageSrc = $('#product-image').attr('src');
	
	console.log(prodNo);
	console.log(prodName);
	console.log(prodPrice);
	console.log(quantity);
//	console.log(productImageSrc);


	$.ajax({
		type: "POST",
		url: `${projectName}/cart`,
		data: {
			action: "add",
			prodNo: prodNo,
			prodName: prodName,
			prodPrice: prodPrice,
			quantity: quantity, // 發送數量
//            productImage: productImageSrc // 發送照片 URL

		},
		success: function(response) {
			console.log(data);
			if (response.message) {
		        alert(response.message); 
		    }
		    // 更新購物車數量
		    $("#cart-count").text(response.totalQuantity);
		},
		error: function(xhr, status, error) {
			if (xhr.status === 401) { // 未授權錯誤
				window.location.href = `${projectName}/frontend/member/login/login.jsp`;
			}
		}
	});
});


// 購物車圖標點擊事件
$("#cart-icon, #cart-count").on("click", function() {
	
	$.ajax({
		type: "POST",
		url: `${projectName}/cartlist`,
		data: {
			action: "getCart",
//			memNo: memNo
		},
		success: function(cartJson) {

			//先宣告一個""
			let html = "";
			let totalAmount = 0;
			
			// 跑購物車清單
			cartJson.forEach(item => {
				let itemTotal = item.quantity * item.prodPrice; // 計算每個項目的總金額
                totalAmount += itemTotal; // 計算購物車總金額
                
				html += `<tr>
                        <td>${item.prodNo}</td>
                        <td>${item.prodName}</td>
                        <td>${item.quantity}</td>
                        <td>NT$${itemTotal}</td>
                    </tr>`;
			});

			$("#cart-items-list").html(html);
			$("#cart-total-amount").text(`NT$${totalAmount}`);

			//	           doucument.getElementId("cart-items-list").innerHTML = html;
			// 顯示購物車模態框
			$('#cartModal').modal('show');
		},
		 error: function(xhr, status, error) {
            if (xhr.status === 401) {
                window.location.href = `${projectName}/frontend/member/login/login.jsp`;
            }
         }
	});
});


//結帳
$(document).ready(function() {

	$('#checkout').click(function() {
		
		console.log("11111");
		
		window.location.href = '<%=request.getContextPath()%>/checkout.jsp';
	});
});