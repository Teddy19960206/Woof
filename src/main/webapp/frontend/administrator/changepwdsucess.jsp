<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>寵毛導師 Woof | 密碼更改成功</title>

<style>
   body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .success-message {
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        text-align: center;
    }

    h2 {
        color: #27ae60; /* 成功信息綠色 */
        margin-bottom: 20px;
    }

    p {
        color: #333;
        margin-bottom: 20px;
    }

    .login-button {
        display: inline-block;
        padding: 10px 20px;
        background-color: #007bff;
        color: white;
        text-decoration: none;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    .login-button:hover {
        background-color: #0056b3;
    } 
   
</style>
</head>
<body>
<div class="success-message">
    <h2>更改成功！</h2>
    <p>您的密碼已經更改完成，現在可以登入使用。</p>
    <a href='<%=request.getContextPath()%>/frontend/administrator/logout1.jsp' class="login-button"><b>登入</b></a>
</div>
</body>
</html>
