<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>更改密碼</title>
    <link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/webutil/icons/happy_1.png" />
    <style>
        body {
            background-color: #f7f7f7; /* 設定背景顏色 */
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
<script src="https://kit.fontawesome.com/3f37e88a3b.js" crossorigin="anonymous"></script>
<script type="text/javascript">
/************隱藏密碼************/
function togglePasswordVisibility(fieldId) {
    var passwordField = document.getElementById(fieldId);
    var toggleIcon = document.getElementById(fieldId === 'memPassword' ? 'toggleNewPassword' : 'toggleConfirmPassword');

    if (passwordField.type === "password") {
        passwordField.type = "text";
        toggleIcon.classList.remove("fa-eye-slash");
        toggleIcon.classList.add("fa-eye");
    } else {
        passwordField.type = "password";
        toggleIcon.classList.remove("fa-eye");
        toggleIcon.classList.add("fa-eye-slash");
    }
    passwordVisible = !passwordVisible;
}
</script>
<body>
    <div class="container">
        <div class="custom-card">
            <div class="custom-header">
                <h3>更改密碼</h3>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/resetPassword.do">
                <div class="form-group">
                    <label for="memPassword">新密碼(需大於六個字):</label>
                    <div style="position: relative;">
                    <input class="form-control" type="password" pattern=".{6,}" name="memPassword" id="memPassword" />
                     <i class="fa fa-eye-slash" style="position: absolute; right: 10px; top: 10px; cursor: pointer;" onclick="togglePasswordVisibility('memPassword')" id="toggleNewPassword"></i>
                    </div>
                    <small class="error-msg">${errorMsgs.memPassword}</small>
                </div>
                <div class="form-group">
                    <label for="memPassword">確認新密碼(需大於六個字):</label>
                     <div style="position: relative;">
                    <input class="form-control" type="password" pattern=".{6,}" name="confirmMemPassword" id="confirmMemPassword" />
                    <i class="fa fa-eye-slash" style="position: absolute; right: 10px; top: 10px; cursor: pointer;" onclick="togglePasswordVisibility('confirmMemPassword')" id="toggleConfirmPassword"></i>
                    </div>
                    <small class="error-msg">${errorMsgs.memPassword}</small>
                </div>
                <input type="hidden" name="token" value="${param.token}" />
                <input type="hidden" name="action" value="changepwd">
                <input type="hidden" name="memEmail" value="${param.memEmail}">
                <button type="submit" class="btn btn-primary">更改</button>
            </form>
        </div>
    </div>
    <!-- Bootstrap JavaScript -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
