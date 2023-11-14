<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>忘記密碼</title>
    <style>
        body {
            background-color: #f4f4f4; /* 背景顏色 */
            font-family: Arial, sans-serif; /* 字體 */
        }

        .container {
            display: flex;
            justify-content: center; /* 水平置中 */
            align-items: center; /* 垂直置中 */
            height: 100vh; /* 全屏高度 */
        }

        .custom-card {
            background: #ffffff; /* 白色卡片背景 */
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 40px;
            max-width: 400px;
            width: 100%;
            margin: 20px;
        }

        .custom-header {
            background: #4a90e2; /* 頭部標題藍色 */
            color: white;
            padding: 10px 20px;
            border-radius: 8px 8px 0 0;
            margin: -40px -40px 20px -40px;
            text-align: center;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-control {
            border-radius: 4px;
            border: 1px solid #ddd;
            padding: 10px;
            width: 100%;
        }

        .btn-primary {
            background-color: #4a90e2; /* 按鈕藍色 */
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }

        .btn-primary:hover {
            background-color: #357abD; /* 按鈕懸停顏色 */
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
            <form method="post" action="${pageContext.request.contextPath}/resetPassword.do">
                <div class="form-group">
                    <label for="memEmail">電子郵件地址:</label>
                    <input type="email" class="form-control" id="memEmail" name="memEmail" placeholder="請輸入您的電子郵件" required>
                    <small class="error-msg">${errorMsgs.memEmail}</small>
                </div>
                <input type="hidden" name="action" value="reset">
                <button type="submit" class="btn btn-primary">提交</button>
            </form>
        </div>
    </div>
    <!-- Bootstrap JavaScript -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
