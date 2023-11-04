<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
</head>
<body>
   <form method="POST"  ACTION="${pageContext.request.contextPath}/member.do">
	<form METHOD="POST" ACTION="${pageContext.request.contextPath}/LoginCl">
		<h3>
			<b>會員登入</b>
		</h3>
		<font color=red>${errorMsgs.memberlogin}</font><br> <label
			for="login" class="form-label"><b>帳號</b></label> <input type="text"
			id="username" class="form-control" name="memNo"> <font
			color=red>${errorMsgs.memberemail}</font><br> <label
			for="password" class="form-label"><b>密碼</b></label> <input
			type="password" id="password" class="form-control" name="memPassword">
		<font color=red>${errorMsgs.memberpassword}</font>

		<div class="login_btn">
			<input type="hidden" name="action" value="memberlogin">
			<button class="btn btnAccess" id="loginButton">
				<b>登入</b>
			</button>
		</div>

		<div id="rmbr">
			<input type="checkbox" name="remberme" id="remember"> <label
				for="remberme"><b>記住帳號</b></label>
		</div>
		</form>
	    </form>
		<h3>會員管理</h3>
			<ul>
				<li><a href='addmember.jsp'>註冊會員</a> a new member.</li>
			</ul>
			<a href="#" class="btn btn-primary"> <b>忘記密碼</b></a>
	
	

	<script>
		  let username= document.getElementById("username");
// 		  let password= document.getElementById("password");
		  let remember= document.getElementById("remember");
		  let account= JSON.parse(localStorage.getItem("account"));
		  
		  if(account){
			  username.value= account.username;
// 			  password.value= account.password;
			  remember.checked= true;
		  }
		  
		  remember.onchange = function(){
			  if(this.checked){
				  var data={username:username.value};
// 				  var data={username:username.value,password:password.value};
				  localStorage.setItem("account",JSON.stringify(data));
			  }else{
				  localStorage.removeItem("account");
			  }
		  }

	</script>
</body>
</html>
