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
.span {
  font-size: 16px;
  margin-left: 5px;
  color: #2d79f3;
  font-weight: 500;
  cursor: pointer;
}
.p {
  text-align: center;
  color: black;
  font-size: 16px;
  margin: 15px 0;
}
.flex-row {
    display: flex;
    justify-content: center; /* 水平居中 */
}
.button-submit {
  margin: 20px 0 10px 0;
  background-color: #ffa500;
  border: none;
  color: white;
  font-size: 15px;
  font-weight: 500;
  border-radius: 10px;
  height: 50px;
  width: 100%;
  cursor: pointer;
}

.button-submit:hover {
  background-color: #ff8c00;
}
</style>
<script src="https://kit.fontawesome.com/3f37e88a3b.js" crossorigin="anonymous"></script>
<script type="text/javascript">
/************隱藏密碼************/
function togglePasswordVisibility(fieldId) {
    var passwordField = document.getElementById(fieldId);
    var toggleIcon = document.getElementById(fieldId === 'password' ? 'toggleNewPassword' : 'toggleConfirmPassword');
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
<title>login</title>
</head>
<body>
	<div class="login-container">
		<h3 class="login-header text-center">
			<b>登入</b>
		</h3>
		<form method="POST" action="${pageContext.request.contextPath}/login" onsubmit="return validateForm()">
			<div class="form-group">
			<i class="fa-solid fa-paw"></i>
				<label for="username"><b>帳號</b></label> <input type="text" placeholder="請輸入帳號"
					id="username" class="form-control" name="memNo">
			<div class="error-msg">${errorMsgs.loginError1}</div>
			</div>
			<div class="form-group">
			<i class="fa-solid fa-lock"></i>
				<label for="password"><b>密碼</b></label> 
				<div style="position: relative;">
				<input type="password" placeholder="請輸入密碼" id="password" class="form-control" name="memPassword"> 
					<i class="fa fa-eye-slash" style="position: absolute; right: 10px; top: 10px; cursor: pointer;" onclick="togglePasswordVisibility('password')" id="toggleNewPassword"></i>
			</div>
			<div class="error-msg">${errorMsgs.loginError}</div>
			</div>
			<div class="form-group">
				<input type="hidden" name="action" value="memberlogin">
				<button class="btn btn-access" id="loginButton" type="submit">
					<b>登入</b>
				</button>

			</div>
			<div class="form-group form-check">
				<input type="checkbox" class="form-check-input" name="remberme"
					id="remember"> <label class="form-check-label"
					for="remember"><b>記住帳號</b></label>
					 <a href='/woof/frontend/member/login/forgotpwd.jsp' style="margin-left: 180px;"><span class="span">忘記密碼？</span></a>
			</div>
				<div class="form-group">
				<input type="hidden" name="action" value="memberlogin">
				<button class="button-submit" id="loginButton" type="submit">
					<b>Sign In</b>
				</button>
			</div>
		</form>
		<a href='/woof/frontend/member/login/addmember.jsp'
			class="text-decoration-none"><b>註冊會員</b></a> 
			<a href='/woof/frontend/member/login/forgotpwd.jsp'
			class="text-decoration-none"> <b>忘記密碼？</b></a>
			<a href="${pageContext.request.contextPath}/home"
			class="text-decoration-none"><i class="fa-brands fa-google"></i></a>

		<a href='/woof/frontend/member/login/addmember.jsp'><p class="p">還沒有帳號嗎？<span class="span">註冊</span></p></a> 
		<p class="p line">Or With</p>
		<div>
		  <div class="flex-row">
      <button class="btn google">
        <svg version="1.1" width="20" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 512 512" style="enable-background:new 0 0 512 512;" xml:space="preserve">
<path style="fill:#FBBB00;" d="M113.47,309.408L95.648,375.94l-65.139,1.378C11.042,341.211,0,299.9,0,256
	c0-42.451,10.324-82.483,28.624-117.732h0.014l57.992,10.632l25.404,57.644c-5.317,15.501-8.215,32.141-8.215,49.456
	C103.821,274.792,107.225,292.797,113.47,309.408z"></path>
<path style="fill:#518EF8;" d="M507.527,208.176C510.467,223.662,512,239.655,512,256c0,18.328-1.927,36.206-5.598,53.451
	c-12.462,58.683-45.025,109.925-90.134,146.187l-0.014-0.014l-73.044-3.727l-10.338-64.535
	c29.932-17.554,53.324-45.025,65.646-77.911h-136.89V208.176h138.887L507.527,208.176L507.527,208.176z"></path>
<path style="fill:#28B446;" d="M416.253,455.624l0.014,0.014C372.396,490.901,316.666,512,256,512
	c-97.491,0-182.252-54.491-225.491-134.681l82.961-67.91c21.619,57.698,77.278,98.771,142.53,98.771
	c28.047,0,54.323-7.582,76.87-20.818L416.253,455.624z"></path>
<path style="fill:#F14336;" d="M419.404,58.936l-82.933,67.896c-23.335-14.586-50.919-23.012-80.471-23.012
	c-66.729,0-123.429,42.957-143.965,102.724l-83.397-68.276h-0.014C71.23,56.123,157.06,0,256,0
	C318.115,0,375.068,22.126,419.404,58.936z"></path>
</svg>
   
        google
        
      </button>
		</div>
	</div>
>>>>>>> refs/heads/cindy
	</div>

	<script>
        let username = document.getElementById("username");
        let remember = document.getElementById("remember");
        let account = JSON.parse(localStorage.getItem("account"));

        if(account){
            username.value = account.username;
            remember.checked = true;
        }

        remember.onchange = function(){
            if(this.checked){
                var data = {username: username.value};
                localStorage.setItem("account", JSON.stringify(data));
            } else {
                localStorage.removeItem("account");
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
    </script>
    

	<!-- 引入 Bootstrap JavaScript (依賴 jQuery 和 Popper.js) -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.9.9/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>