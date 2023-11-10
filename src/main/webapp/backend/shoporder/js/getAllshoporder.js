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

	// 為每個儲存按鈕添加事件處理器
	document.querySelectorAll('.save-btn').forEach(function(btn) {
		
//		console.log("AAAAAAAA");
		
		btn.addEventListener('click', function() {
			let tr = this.closest('tr');
			let selectedStatus = tr.querySelector('.status-select').value;
			// 使用 AJAX 發送更新請求
			// ...
		});
	});
});