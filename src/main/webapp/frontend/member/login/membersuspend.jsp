<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>停權</title>
<!-- 引入 Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
body {
	background-color: #f8f9fa; /* 設定背景顏色 */
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	flex-direction: column;
}

.alert {
	font-size: 1.5rem; /* 設定字體大小 */
	color: #721c24; /* 設定字體顏色 */
}

.btn-link {
	font-size: 1.2rem; /* 設定按鈕字體大小 */
}
</style>
</head>
<body>
	<div class="alert alert-danger" role="alert">
		您的帳戶已被停權，由於違反我們的服務條款。</div>
	<form method="POST" action="${pageContext.request.contextPath}/login"
		style="all: unset;">
		<input type="hidden" name="action" value="memberlogout">
		<button class="nav-link btn btn-link"
			style="color: inherit; text-decoration: none;" id="logoutButton"
			type="submit">登出</button>
	</form>
</body>
</html>