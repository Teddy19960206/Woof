let pathName = window.document.location.pathname;
//let projectName = pathName.substring(0, pathName.substring(1).indexOf("/") + 1);

$(document).ready(function() {
    if (orderSuccess) {
		console.log(orderSuccess);
        // 顯示圖標
        $('#orderSuccessIcon').show();
        
        // 在這裡添加 Swal.fire() 提示
        Swal.fire({
            title: '成功',
            text: '您的訂單已成功提交！',
            icon: 'success',
            confirmButtonText: '確定',
            confirmButtonColor: '#F79B66'
        });
    }
});