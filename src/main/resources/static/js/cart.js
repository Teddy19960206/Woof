let pathNames = window.document.location.pathname;
let pro = pathNames.substring(0, pathNames.substring(1).indexOf("/") + 1);


//自動重新刷新會載入總數
$(function() {
	getCartTotalQuantity();
});

function getCartTotalQuantity() {
	$.ajax({
		cache: false,
		type: "POST",
		url: `${pro}/cart`,
		data: {
			action: "getTotalQuantity",
		},
		dataType: "json",
		success: function(response) {
			$("#cart-count").text(response.totalQuantity);
		},
		error: function(xhr, status, error) {
			if (xhr.status === 401) {
				$("#cart-count").text("0");
				// window.location.href = `${projectName}/frontend/member/login/login.jsp`;
			}
		}
	});
}


// 添加商品到購物車的 AJAX 請求
$(".add-to-cart").on("click", function() {



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
		url: `${pro}/cart`,
		data: {
			action: "add",
			prodNo: prodNo,
			prodName: prodName,
			prodPrice: prodPrice,
			quantity: quantity,
			// productImage: productImageSrc
		},
		success: function(response) {

			console.log(response);

			if (response.message === "請先登入會員~") {
				// 如果後端返回的是登入提示，則只顯示該提示
				Swal.fire({
					icon: "error",
					title: "請先登入會員~",
				});
			} else if (response.message) {

				Swal.fire({
					icon: "success",
					title: "成功加入購物車",
				});
				getCartTotalQuantity();
//				$("#cart-count").text(response.totalQuantity);
			}
		},
		error: function(xhr, status, error) {
			// 錯誤處理
		}
	});
});


// 購物車圖標點擊事件
$(document).on("click", "#cart-icon", function() {
	$.ajax({
		type: "POST",
		url: `${pro}/cartlist`,
		data: {
			action: "getCart",
		},
		success: function(cartJson) {

			console.log(cartJson)

			if (cartJson.length === 0) {

				Swal.fire({
					icon: "warning",
					title: "購物車是空的，請去商城逛逛~",
				});

				window.location.href = `${pro}/shopHome.html`;
				return;
			}

			let html = "";
			let totalAmount = 0;

			// 跑購物車清單
			cartJson.forEach(item => {
				let itemTotal = item.quantity * item.prodPrice;
				totalAmount += itemTotal; // 計算購物車總金額

				html += `<tr>
                            <td>${item.prodName}</td>
                             <td><img src="${pro}/productImage/${item.prodNo}" style="width: 100px; height: 100px;" onerror="this.onerror=null; this.src='${pro}/fallback-image.jpg';"/></td>
                            <td>${item.quantity}</td>
                            <td>NT$${itemTotal}</td>
                        </tr>`;
			});

			$("#cart-items-list").html(html);
			$("#cart-total-amount").text(`NT$${totalAmount}`);
			$('#cartModal').modal('show');
		},
		error: function(xhr, status, error) {
			if (xhr.status === 401) {
				window.location.href = `${pro}/frontend/member/login/login.jsp`;
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