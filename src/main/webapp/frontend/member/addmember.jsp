<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.woof.member.*"%>
<html>
<head>
<!-- ������M�� -->
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="UTF-8">
<title>�|����Ʒs�W - addmember.jsp</title>
<script>
	//����榡
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
				<h3>�|����Ʒs�W - addmember.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="selectmember.jsp">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��Ʒs�W:</h3>
	<!-- request.getContextPath()�ʺA�ڸ��|�Aaction=update�����switch(action)��add-->
	<form method="post"
		action="${pageContext.request.contextPath}/member.do"
		enctype="multipart/form-data" accept-charset="UTF-8">
		<table>
			<tr>
				<td>�|���b��:</td>
				<td><input type="TEXT" name="memNo" id="memNo" size="45" /></td>
				<td>${errorMsgs.memNo}</td>
			</tr>
			<tr>
				<td>�|���m�W:</td>
				<td><input type="TEXT" name="memName" id="memName" size="45" /></td>
				<td>${errorMsgs.memName}</td>
			</tr>
			<tr>
				<td>�ʧO:</td>
				<td><input type="radio" name="memGender" value="M" checked>�k
					<input type="radio" name="memGender" value="F">�k</td>
				<td>${errorMsgs.memGender}</td>
			</tr>
			<tr>
				<td>�Ӥ�:</td>
				<td><input type="file" name="memPhoto" accept="image/*"></td>
			</tr>
			<tr>
				<td>email:</td>
				<td><input type="TEXT" name="memEmail" id="memEmail"
					placeholder="XXX@gmail.com" size="45"></td>
				<td>${errorMsgs.memEmail}</td>
			</tr>
			<tr>
				<td>�K�X:</td>
				<td><input type="TEXT" name="memPassword" id="memPassword"
					size="45" /></td>
				<td>${errorMsgs.memPassword}</td>
			</tr>
			<tr>
				<td>�q��:</td>
				<td><input type="TEXT" name="memTel" id="memTel" size="45" /></td>
				<td>${errorMsgs.memTel}</td>
			</tr>
			<tr>
				<td>�a�}:</td>
				<td><input type="TEXT" name="memAddress" id="memAddress"
					size="45" /></td>
				<td>${errorMsgs.memAdress}</td>
			</tr>
			<tr>
				<td>�ͤ�:</td>
				<td><input type="TEXT" name="memBd" id="memBd" size="45" /></td>
				<td>${errorMsgs.memBd}</td>
			</tr>
			<tr>
				<td>����:</td>
				<td><input type="TEXT" name="momoPoint" id="momoPoint"
					size="45" /></td>
				<td>${errorMsgs.momoPoint}</td>
			</tr>
			<tr>
				<td>�`���:</td>
				<td><input type="TEXT" name="totalClass" id="totalClass"
					size="45" /></td>
				<td>${errorMsgs.totalClass}</td>
			</tr>
			<tr>
				<td>���A:</td>
				<td><input type="radio" name="memStatus" value="0">���v <input
					type="radio" name="memStatus" value="1" checked>���`</td>
				<td>${errorMsgs.memStatus}</td>
			</tr>
		</table>
		<input type="hidden" name="action" value="add">
		<button type="submit">�s�W</button>
		<button type="button" onclick="history.back()">�����s�W</button>
	</form>
</body>
</html>