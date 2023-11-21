<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>忘記密碼</title>
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

        .container {
            width: 400px;
            background: #fff;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            border-radius: 5px;
        }

        .custom-card {
            margin: 15px 0;
        }

        .custom-header h3 {
            color: #333;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-control {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .btn-primary {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .error-msg {
            color: #e74c3c; /* 錯誤信息紅色 */
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="custom-card">
            <div class="custom-header">
                <h3>忘記密碼</h3>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/forgetthepwd.do">
                <div class="form-group">
                    <label for="adminEmail">電子郵件地址:</label>
                    <input type="email" class="form-control" id="adminEmail" name="adminEmail" placeholder="請輸入您的電子郵件" required>
                    <small class="error-msg">${errorMsgs.adminEmail}</small>
                </div>
                <input type="hidden" name="action" value="reset">
                <button type="submit" class="btn btn-primary">送出</button>
            </form>
        </div>
    </div>
   
</body>
</html>
