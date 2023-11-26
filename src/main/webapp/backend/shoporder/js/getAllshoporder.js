let pathName = window.document.location.pathname;
let projectName = pathName.substring(0, pathName.substring(1).indexOf("/") + 1);


document.addEventListener('DOMContentLoaded', function() {
	// 為每個修改按鈕添加事件處理器
	document.querySelectorAll('.edit-btn').forEach(function(btn) {

		console.log("11111111s");

		btn.addEventListener('click', function() {
			let tr = this.closest('tr');
			tr.querySelector('.status-text').style.display = 'none';
			tr.querySelector('.status-select').style.display = 'inline';
			tr.querySelector('.save-btn').style.display = 'inline';
			tr.querySelector('.cancel-btn').style.display = 'inline';
			this.style.display = 'none';
		});
	});

	// 為每個取消按鈕添加事件處理器
	document.querySelectorAll('.cancel-btn').forEach(function(btn) {

		//		console.log("QQQQQ");

		btn.addEventListener('click', function() {
			let tr = this.closest('tr');
			tr.querySelector('.status-text').style.display = 'inline';
			tr.querySelector('.status-select').style.display = 'none';
			tr.querySelector('.edit-btn').style.display = 'inline';
			this.style.display = 'none';
			tr.querySelector('.save-btn').style.display = 'none';
		});
	});



	function updateOrderStatusDisplay(orderStatus, tr) {
		let statusText = '';
		switch (orderStatus) {
			case 0: statusText = '成立'; break;
			case 1: statusText = '出貨'; break;
			case 2: statusText = '完成'; break;
			case 3: statusText = '取消'; break;
			case 4: statusText = '未付款'; break;
			case 5: statusText = '已付款'; break;
			default: statusText = '未知'; // 未知狀態
		}
		tr.querySelector('.status-text').textContent = statusText;
	}

	// 為每個儲存按鈕添加事件處理器
	document.querySelectorAll('.save-btn').forEach(function(btn) {

				console.log("AAAAAAAA");

		btn.addEventListener('click', function() {
			let tr = this.closest('tr');

			let shopOrderNo = tr.querySelector('.order-no').innerText;
			let memNo = tr.querySelector('.mem-no').innerText;
			let prodOrderDate = tr.querySelector('.prod-order-date').innerText;
			let payMethod = parseInt(tr.querySelector('.pay-method').innerText === '信用卡' ? 0 : 1);
			let shipMethod = tr.querySelector('.ship-method').innerText === '超商取貨';
			let recName = tr.querySelector('.rec-name').innerText;
			let recMobile = tr.querySelector('.rec-mobile').innerText;
			let recAddress = tr.querySelector('.rec-address').innerText;
			let hasReturn = tr.querySelector('.has-return').innerText === '有退貨';
			let moCoin = parseInt(tr.querySelector('.mo-coin').innerText);
			let orderTotalPrice = parseInt(tr.querySelector('.order-total-price').innerText);
			let actualPrice = parseInt(tr.querySelector('.actual-price').innerText);
			let orderStatus = parseInt(tr.querySelector('.status-select').value);


			console.log(shopOrderNo);
			console.log(memNo);
			console.log(prodOrderDate);
			console.log(payMethod);
			console.log(shipMethod);
			console.log(recName);
			console.log(recMobile);
			console.log(recAddress);
			console.log(hasReturn);
			console.log(moCoin);
			console.log(orderTotalPrice);
			console.log(actualPrice);
			console.log(orderStatus + "訂單狀態=================");


			// 使用 AJAX 發送更新請求
			fetch(`${projectName}/shoporder?action=updateshoporder`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded',
				},
				body: `shopOrderNo=${shopOrderNo}&memNo=${memNo}&prodOrderDate=${prodOrderDate}&payMethod=${payMethod}&shipMethod=${shipMethod}&recName=${recName}&recMobile=${recMobile}&recAddress=${recAddress}&hasReturn=${hasReturn}&moCoin=${moCoin}&orderTotalPrice=${orderTotalPrice}&actualPrice=${actualPrice}&orderStatus=${orderStatus}`
			})
				.then(response => response.json()) // 將響應轉換為 JSON
				.then(data => {
					console.log(data);
					if (data.message == "修改成功") {

						console.log("修改成功");
					} else {
						console.log("修改失敗!!!!!!!");
					}
					// 處理響應數據
					// AJAX 請求成功後恢復按鈕的顯示狀態
					updateOrderStatusDisplay(orderStatus, tr);
					tr.querySelector('.status-text').style.display = 'inline';
					tr.querySelector('.status-select').style.display = 'none';
					tr.querySelector('.edit-btn').style.display = 'inline';
					tr.querySelector('.save-btn').style.display = 'none';
					tr.querySelector('.cancel-btn').style.display = 'none';
				})
		});
	});
});


