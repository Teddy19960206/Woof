<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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



.error-msg {
	color: red;
	margin-bottom: 10px;
}
</style>
<title>login</title>
</head>
<body>
	<div class="login-container">
		<h3 class="login-header text-center">
			<b>管理員登入</b>
		</h3>
		<form method="POST" action="${pageContext.request.contextPath}/logout1" onsubmit="return validateForm()">
			<div class="error-msg">${errorMsgs.administratorlogin}</div>
			<div class="form-group">
				<label for="username"><b>帳號</b></label> <input type="text"
					id="username" class="form-control" name="adminNo">
			</div>
			<div class="error-msg">${errorMsgs.adminNo}</div>
			<div class="form-group">
				<label for="password"><b>密碼</b></label> <input type="password"
					id="password" class="form-control" name="adminPassword"> 
			</div>
			<div class="error-msg">${errorMsgs.administratorpassword}</div>
			<div class="form-group">
				<input type="hidden" name="action1" value="administratorlogout1">
				<button class="btn btn-access" id="loginButton" type="submit"   style="
                  background-color: #faba91;
                  font-family: 'Noto Sans TC', sans-serif;
                  font-weight: 700;">
					<b>登入</b>
				</button>
					  <a
                href="<%=request.getContextPath()%>/index.jsp"
                class="btn"
                style="
                  background-color: #faba91;
                  font-family: 'Noto Sans TC', sans-serif;
                  font-weight: 700;">返回前台首頁</a>
			</div>
		
			<div class="form-group form-check">
				<input type="checkbox" class="form-check-input" name="remember"
					id="remember"> <label class="form-check-label"
					for="remember"><b>記住帳號</b></label>
			</div>
		</form>
		  <a href='/woof/frontend/administrator/forgotpwd.jsp' style="margin-left: 180px;"><span class="span">忘記密碼？</span></a>
	</div>

	<script>
        let username = document.getElementById("username");
        let remember = document.getElementById("remember");
        let account1 = JSON.parse(localStorage.getItem("account1"));

        if(account1){
            username.value = account1.username;
            remember.checked = true;
        }

        remember.onchange = function(){
            if(this.checked){
                var data = {username: username.value};
                localStorage.setItem("account1", JSON.stringify(data));
            } else {
                localStorage.removeItem("account1");
            }
        }
    </script>
    <script>
        function validateForm() {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;

            if(username === "") {
                alert("帳號不能為空");
                return false;
            }
            if(password === "") {
                alert("密碼不能為空");
                return false;
            }
            return true;
        }
        // 省略其他腳本
    </script>
    

	<!-- 引入 Bootstrap JavaScript (依賴 jQuery 和 Popper.js) -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.9.9/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>