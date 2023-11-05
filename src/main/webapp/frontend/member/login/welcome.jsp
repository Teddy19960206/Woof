<%@ page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>welcome</title>
</head>
<!-- 引入 Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
	background-color: #fff4e5; /* 淺橘色背景 */
}

.login-container {
	max-width: 400px;
	margin: 50px auto;
	padding: 20px;
	background-color: white;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
	border-radius: 8px;
}

.login-header {
	color: #ffa500; /* 橘色文字 */
	margin-bottom: 20px;
}

.btn-access {
	background-color: #ffa500; /* 橘色按鈕 */
	color: white;
	border: none;
}

.btn-access:hover {
	background-color: #ff8c00; /* 深橘色按鈕 */
}

.error-msg {
	color: red;
	margin-bottom: 10px;
}
</style>
<body>
	welcome
	<p>歡迎, ${member.memName}!</p>
	<p>您的毛毛幣為: ${momoPoints}</p>
	<form method="POST" action="${pageContext.request.contextPath}/login">
	<div class="form-group">
		<input type="hidden" name="action" value="memberlogout">
		<button class="btn btn-access" id="loginButton" type="submit">
			<b>登出</b>
		</button>
	</div>
	</form>
	<!-- 引入 Bootstrap JavaScript (依賴 jQuery 和 Popper.js) -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.9.9/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
