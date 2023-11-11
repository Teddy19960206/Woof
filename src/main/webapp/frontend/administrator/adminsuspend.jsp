<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>停職查辦</title>
<!-- 引入 Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
   body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .container {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            margin-top: 50px;
            text-align: center;
        }

        .alert-danger {
            background-color: #f8d7da;
            border-color: #f5c6cb;
            color: #721c24;
            padding: 0.75rem 1.25rem;
            margin-bottom: 1rem;
            border: 1px solid transparent;
            border-radius: 0.25rem;
        }

        #logoutButton {
            color: #007bff;
            text-decoration: none;
            cursor: pointer;
            border: none;
            background: none;
        }

        #logoutButton:hover {
            text-decoration: underline;
        }
</style>
</head>
<body>
	<div class="alert alert-danger" role="alert">
		您的帳戶已被停權，詳情請洽管理員。</div>
	<form method="POST" action="${pageContext.request.contextPath}/logout1"
		style="all: unset;">
		<input type="hidden" name="action1" value="administratorlogout">
		<button class="nav-link btn btn-link"
			style="background-color: #FABA91; font-family: 'Noto Sans TC', sans-serif; font-weight: 700; " text-decoration: none;" id="logoutButton"
			type="submit">登出</button>
	</form>
</body>
</html>