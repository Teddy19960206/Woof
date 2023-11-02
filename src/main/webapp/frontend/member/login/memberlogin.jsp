<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<script type="text/javascript">
	 var memNo = document.getElementsByName("memNo")[0].value;
	 var memPassword = document.getElementsByName("memPassword")[0].value;
	 var code = document.getElementsByName("code")[0].value;
<%--對輸入的賬號資訊進行判斷，帳號密碼不能為空且必須輸入驗證碼--%>
    function validate() {
    
  
        if(memNo.value===""){
   
   
            alert("帳號不能為空");
            return;
        }
        if(memPassword.value===""){
   
   
            alert("密碼不能為空");
            return;
        }
        if(code.value===""){
   
   
            alert("請輸入驗證碼");
            return;
        }
        login.submit();
    }
    function refresh() {
   
   
        login.imgValidate.src="index.jsp?id="+Math.random();
    }
</script>
<%-- 	<form method="POST"
		ACTION="${pageContext.request.contextPath}/login.do"> --%>
		<form name="login" action="/login.do" method="post">
		使用者帳號:<input type="text" name="memNo"><br> 密碼：<input
			type="password" name="memPassword"><br> 
<!-- 			<input type="checkbox" name="keep">兩週內免登錄<br>  -->
			驗證碼：<input type="text" name="code" size=10>
		<%--點選圖片可進行驗證碼重新整理--%>
		<img name="imgValidate" src="index.jsp" onclick="refresh()"><br>
		<%--注意此處的button和submit的區別--%>
		<input type="button" value="登入" onclick="validate()">
	</form>
	<form action="register.jsp" method="post">
	<input type="submit" value="註冊">
	</form>
</body>
</html>