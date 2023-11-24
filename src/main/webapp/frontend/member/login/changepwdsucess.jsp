<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更改成功</title>
<link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/webutil/icons/happy_1.png" />
<!-- 加入Bootstrap的CDN連結 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
    body {
        background-color: #f7f7f7; /* 設定背景顏色 */
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        flex-direction: column;
    }
    .success-message {
        text-align: center;
        margin-bottom: 20px;
    }
    .login-button {
        text-decoration: none;
        color: white;
        background-color: #007bff; /* 設定按鈕顏色 */
        padding: 10px 20px;
        border-radius: 5px;
        transition: background-color 0.3s ease;
    }
    .login-button:hover {
        background-color: #0056b3; /* 滑鼠懸停效果 */
    }
</style>
</head>
<body>
<div class="success-message">
    <h2>更改成功！</h2>
    <p>您的密碼已經更改完成，現在可以登入使用。</p>
    <a href='/woof/frontend/member/login/login.jsp' class="login-button"><b>登入</b></a>
</div>
</body>
</html>
