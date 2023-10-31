<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.woof.member.*"%>
<html>
<head>
<!-- 日期的套版 -->
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<title>會員資料新增 - addmember.jsp</title>
<script>
	//日期格式
	$(function() {
		$("#memBd").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	});
</script>
<style>
table#table-1 {
	width: 450px;
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
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>會員資料新增 - addmember.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="selectmember.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>
	<!-- request.getContextPath()動態根路徑，action=update找到後端switch(action)的add-->
	<form method="post"
		action="${pageContext.request.contextPath}/member.do"
		enctype="multipart/form-data" accept-charset="UTF-8">
		<table>
			<tr>
				<td>會員帳號:</td>
				<td><input type="TEXT" name="memNo" id="memNo" size="45" /></td>
				<td>${errorMsgs.memNo}</td>
			</tr>
			<tr>
				<td>會員姓名:</td>
				<td><input type="TEXT" name="memName" id="memName" size="45" /></td>
				<td>${errorMsgs.memName}</td>
			</tr>
			<tr>
				<td>性別:</td>
				<td><input type="radio" name="memGender" value="M" checked>男
					<input type="radio" name="memGender" value="F">女</td>
				<td>${errorMsgs.memGender}</td>
			</tr>
			<tr>
				<td>照片:</td>
				<td><input type="file" name="memPhoto" accept="image/*"></td>
			</tr>
			<tr>
				<td>email:</td>
				<td><input type="TEXT" name="memEmail" id="memEmail"
					placeholder="XXX@gmail.com" size="45"></td>
				<td>${errorMsgs.memEmail}</td>
			</tr>
			<tr>
				<td>密碼:</td>
				<td><input type="TEXT" name="memPassword" id="memPassword"
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
		<input type="hidden" name="action" value="add">
		<button type="submit">新增</button>
		<button type="button" onclick="history.back()">取消新增</button>
	</form>
</body>
</html>