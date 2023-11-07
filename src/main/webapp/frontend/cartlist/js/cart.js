let pathName = window.document.location.pathname;
let projectName = pathName.substring(0, pathName.substring(1).indexOf("/") + 1);


$(function() {
	getCartTotalQuantity();
});

//自動重新刷新會載入總數
function getCartTotalQuantity() {
	let memNo = "member1"; // 這裡應該從會話中獲取真實的會員編號
	$.ajax({
		type: "POST",
		url: `${projectName}/cart`,
		data: {
			action: "getTotalQuantity",
			memNo: memNo
		},
		success: function(response) {
			// 假設後端返回的 response 是一個物件，包含 totalQuantity 屬性
			$("#cart-count").text(response.totalQuantity);
		},
		error: function(xhr, status, error) {
			// 處理錯誤
			console.error("獲取購物車總數量時出錯", status, error);
		}
	});
}


// 添加商品到購物車的 AJAX 請求
$(".add-to-cart").on("click", function() {
	let prodNo = $(this).data("id");

	// 			console.log(prodNo);

	let prodName = $(this).data("name");
	let prodPrice = $(this).data("price");

	$.ajax({
		type: "POST",
		url: `${projectName}/cart`,
		data: {
			action: "add",
			prodNo: prodNo,
			prodName: prodName,
			prodPrice: prodPrice
		},
		success: function(data) {

			console.log(data);

			// 更新前端HTML中的數字
			let totalQuantity = data.totalQuantity;
			$("#cart-count").text(totalQuantity);

		}

	});
});



// 購物車圖標點擊事件
$("#cart-icon, #cart-count").on("click", function() {
	// 假設用戶已經登入，並且會員編號已經存儲在會話中
	let memNo = "member1"; // 這裡應該從會話中獲取真實的會員編號

	$.ajax({
		type: "POST",
		url: `${projectName}/cartlist`,
		data: {
			action: "getCart",
			memNo: memNo
		},
		success: function(cartJson) {


			let html = "";
			// 填充購物車清單
			cartJson.forEach(item => {
				html += `<tr>
                        <td>${item.prodNo}</td>
                        <td>${item.prodName}</td>
                        <td>${item.quantity}</td>
                        <td>${item.prodPrice}</td>
                    </tr>`;
			});

			$("#cart-items-list").html(html);

			//	           doucument.getElementId("cart-items-list").innerHTML = html;
			// 顯示購物車模態框
			$('#cartModal').modal('show');
		}
	});
});


//結帳
$(document).ready(function() {
	// 為結帳按鈕添加點擊事件
	$('#checkout').click(function() {
		// 導航到結帳頁面
		console.log("11111");
		window.location.href = '<%=request.getContextPath()%>/checkout.jsp';
	});
});