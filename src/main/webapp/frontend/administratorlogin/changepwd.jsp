<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>寵毛導師 Woof | 員工更改密碼</title>
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

        .form-control:focus {
            border-color: #007bff;
            box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
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

        .fa-eye, .fa-eye-slash {
            font-size: 18px;
            color: #333;
        }
    </style>
</head>
<script src="https://kit.fontawesome.com/3f37e88a3b.js" crossorigin="anonymous"></script>
<script type="text/javascript">
/************隱藏密碼************/
function togglePasswordVisibility(fieldId) {
    var passwordField = document.getElementById(fieldId);
    var toggleIcon = document.getElementById(fieldId === 'adminPassword' ? 'toggleNewPassword' : 'toggleConfirmPassword');

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
            <form method="post" action="${pageContext.request.contextPath}/forgetthepwd.do">
                <div class="form-group">
                    <label for="adminPassword">新密碼:</label>
                    <div style="position: relative;">
                    <input class="form-control" type="password" name="adminPassword" id="adminPassword" />
                     <i class="fa fa-eye-slash" style="position: absolute; right: 10px; top: 10px; cursor: pointer;" onclick="togglePasswordVisibility('adminPassword')" id="toggleNewPassword"></i>
                    </div>
                    <small class="error-msg">${errorMsgs.adminPassword}</small>
                </div>
                <div class="form-group">
                    <label for="adminPassword">確認新密碼:</label>
                     <div style="position: relative;">
                    <input class="form-control" type="password" name="confirmadminPassword" id="confirmadminPassword" />
                    <i class="fa fa-eye-slash" style="position: absolute; right: 10px; top: 10px; cursor: pointer;" onclick="togglePasswordVisibility('confirmadminPassword')" id="toggleConfirmPassword"></i>
                    </div>
                    <small class="error-msg">${errorMsgs.confirmMemPassword}</small>
                </div>
                <input type="hidden" name="token" value="${param.token}" />
                <input type="hidden" name="action" value="changepwd">
                <input type="hidden" name="adminEmail" value="${param.adminEmail}">
                <button type="submit" class="btn btn-primary">更改</button>
            </form>
        </div>
    </div>
    <!-- Bootstrap JavaScript -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
