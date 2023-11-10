let pathName = window.document.location.pathname;
let projectName = pathName.substring(0, pathName.substring(1).indexOf("/") + 1);


document.addEventListener('DOMContentLoaded', function() {
	// 為每個修改按鈕添加事件處理器
	document.querySelectorAll('.edit-btn').forEach(function(btn) {

		//		console.log("11111111s");

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
    switch(orderStatus) {
        case 0: statusText = '成立'; break;
        case 1: statusText = '出貨'; break;
        case 2: statusText = '完成'; break;
        case 3: statusText = '取消'; break;
        case 4: statusText = '未付款'; break;
        default: statusText = '未知'; // 未知狀態
    }
    tr.querySelector('.status-text').textContent = statusText;
}

	// 為每個儲存按鈕添加事件處理器
	document.querySelectorAll('.save-btn').forEach(function(btn) {

		//		console.log("AAAAAAAA");

		btn.addEventListener('click', function() {
			let tr = this.closest('tr');
					
			let shopOrderNo = tr.querySelector('.order-no').innerText;
		    let memNo = tr.querySelector('.mem-no').innerText;
		    let prodOrderDate = tr.querySelector('.prod-order-date').innerText;
		    let recName = tr.querySelector('.rec-name').innerText;
		    let recMobile = tr.querySelector('.rec-mobile').innerText;
		    let recAddress = tr.querySelector('.rec-address').innerText;
		    let moCoin = parseInt(tr.querySelector('.mo-coin').innerText);
		    let orderTotalPrice = parseInt(tr.querySelector('.order-total-price').innerText);
		    let actualPrice = parseInt(tr.querySelector('.actual-price').innerText);
		    let orderStatus = parseInt(tr.querySelector('.status-select').value);
		
		    // 轉換付款方式為整數
		    let payMethodText = tr.querySelector('.pay-method').innerText;
		    let payMethod = (payMethodText === '信用卡') ? 0 : 1;
		
		    // 轉換取貨方式為布林值
		    let shipMethodText = tr.querySelector('.ship-method').innerText;
		    let shipMethod = (shipMethodText === '超商取貨');
		
		    // 轉換是否有退貨為布林值
		    let hasReturnText = tr.querySelector('.has-return').innerText;
		    let hasReturn = (hasReturnText === '有退貨');
			
			
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
			console.log(orderStatus);


			// 使用 AJAX 發送更新請求
			fetch(`${projectName}/shoporder?action=updateshoporder`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded',
				},
				body: `shopOrderNo=${shopOrderNo}&memNo=${memNo}&prodOrderDate=${prodOrderDate}&payMethod=${payMethod}&shipMethod=${shipMethod}&recName=${recName}&recMobile=${recMobile}&recAddress=${recAddress}&hasReturn=${hasReturn}&moCoin=${moCoin}&orderTotalPrice=${orderTotalPrice}&actualPrice=${actualPrice}&orderStatus=${orderStatus}`
			})
				.then(response => {
					if (!response.ok) {
						throw new Error('Network response was not ok');
					}
					return response.text();
				})
				.then(data => {
					console.log(data);
					// 處理響應數據
					// AJAX 請求成功後恢復按鈕的顯示狀態
					updateOrderStatusDisplay(orderStatus, tr);
					tr.querySelector('.status-text').style.display = 'inline';
					tr.querySelector('.status-select').style.display = 'none';
					tr.querySelector('.edit-btn').style.display = 'inline';
					tr.querySelector('.save-btn').style.display = 'none';
					tr.querySelector('.cancel-btn').style.display = 'none';
				})
				.catch(error => {
					console.error('There has been a problem with your fetch operation:', error);
					// 這裡可以選擇是否要處理錯誤情況下的按鈕顯示狀態
				});
		});
	});
});


