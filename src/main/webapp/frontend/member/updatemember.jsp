<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.woof.member.*"%>
<html>
<head>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<title>所有員工資料 - updatemember.jsp</title>
<script>
$(document).ready(function(){
    $.ajax({
        //指定http參數傳輸格式為POST
        type : "POST",

        data : {memNo : <%=request.getParameter("memNo")%>},
        //請求目標的url
        url : "<%=request.getContextPath()%>/member.do?action=query",
					//Ajax成功後執行的function，response為回傳的值
					success : function(data) {
						var jsonObj = JSON.parse(data);
						console.log(jsonObj)
						$('#memName').val(jsonObj.memName);
						$(
								'input[name=memGender][value='
										+ jsonObj.memGender + ']').prop(
								'checked', true);
						$('#memEmail').val(jsonObj.memEmail);
						$('#memPassword').val(jsonObj.memPassword);
						$('#memTel').val(jsonObj.memTel);
						$('#memAddress').val(jsonObj.memAddress);
						$('#memTotalclass').val(jsonObj.totalClass);
						$('#momoPoint').val(jsonObj.momoPoint);
						$('#memBd').val(jsonObj.memBd);
						$(
								'input[name=memStatus][value='
										+ jsonObj.memStatus + ']').prop(
								'checked', true);
					},
					//Ajax失敗後要執行的function，此例為印出錯誤訊息
					error : function(xhr, ajaxOptions, thrownError) {
						alert("哇 錯了");
					}
				});

				//日期格式
				$("#memBd").datepicker({
					dateFormat : 'yy-mm-dd'
				});
			})
</script>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>
	<!--request.getContextPath()動態根路徑，action=update找到後端switch(action)的update-->
	<input type="hidden" name="member" id="member"
		value='<%=request.getParameter("memNo")%>'>
	<table id="table-1">
		<tr>
			<td>
				<h3>會員資料更新 - updatemember.jsp</h3>
				<h4>
					<a href="selectmember.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<h3>資料更新:</h3>
	<form method="post"
		action="${pageContext.request.contextPath}/member.do"
		enctype="multipart/form-data" accept-charset="UTF-8">
		<table>
			<tr>
				<td>會員姓名:</td>
				<td><input type="TEXT" name="memName" id="memName" size="45" /></td>
				<td>${errorMsgs.memName}</td>
			</tr>
			<tr>
				<td>性別:</td>
				<td><input type="radio" name="memGender" value="1" checked>男
					<input type="radio" name="memGender" value="0">女</td>
				<td>${errorMsgs.memGender}</td>
			</tr>
			<tr>
				<td>email:</td>
				<td><input type="TEXT" name="memEmail" id="memEmail"
					placeholder="XXX@gmail.com" size="45"></td>
				<td>${errorMsgs.memEmail}</td>
			</tr>
			<tr>
				<td>密碼:</td>
				<td><input type="password" name="memPassword" id="memPassword"
					size="45" /></td>
				<td>${errorMsgs.memPassword}</td>
			</tr>
			<tr>
				<td>電話:</td>
				<td><input type="TEXT" name="memTel" id="memTel" size="45" /></td>
				<td>${errorMsgs.memTel}</td>
			</tr>
			<tr>
				<td>地址:</td>
				<td><input type="TEXT" name="memAddress" id="memAddress"
					size="45" /></td>
				<td>${errorMsgs.memAdress}</td>
			</tr>
			<tr>
				<td>生日:</td>
				<td><input type="TEXT" name="memBd" id="memBd" size="45" /></td>
				<td>${errorMsgs.memBd}</td>
			</tr>
			<tr>
				<td>毛毛幣:</td>
				<td><input type="TEXT" name="momoPoint" id="momoPoint"
					size="45" /></td>
				<td>${errorMsgs.momoPoint}</td>
			</tr>
			<tr>
				<td>總堂數:</td>
				<td><input type="TEXT" name="totalClass" id="totalClass"
					size="45" /></td>
				<td>${errorMsgs.totalClass}</td>
			</tr>
			<tr>
				<td>狀態:</td>
				<td><input type="radio" name="memStatus" value="0">停權 <input
					type="radio" name="memStatus" value="1" checked>正常</td>
				<td>${errorMsgs.memStatus}</td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="submit" value="更新">
		<button type="button" onclick="history.back()">取消更新</button>
	</form>
</body>
</html>