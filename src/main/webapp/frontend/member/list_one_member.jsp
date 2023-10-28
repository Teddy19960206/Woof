<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>


<%
//EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>會員資料 - list_one_member.jsp</title>

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
	<jsp:useBean id="memberService" scope="page"
		class="com.woof.member.service.MemberServiceImpl" />
	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料 - listOneEmp.jsp</h3>
				<h4>
					<!-- <a href="/frontend/member/selectmember.jsp">回首頁</a> -->
					<button type="button" onclick="history.back()">回首頁</button>
				</h4>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<th>編號</th>
			<th>姓名</th>
			<th>性別</th>
			<th>照片</th>
			<th>email</th>
			<th>密碼</th>
			<th>電話</th>
			<th>地址</th>
			<th>生日</th>
			<th>毛毛幣</th>
			<th>課堂數</th>
			<th>狀態</th>
		</tr>

		<tr>
			<td>${member.memNo}</td>
			<td>${member.memName}</td>
			<td>${member.memGender}</td>
			<td>${member.memPhoto}</td>
			<td>${member.memEmail}</td>
			<td>${member.memPassword}</td>
			<td>${member.memTel}</td>
			<td>${member.memAddress}</td>
			<td>${member.memBd}</td>
			<td>${member.momoPoint}</td>
			<td>${member.totalClass}</td>
			<td>${member.memStatus}</td>

		</tr>
	</table>
</body>
</html>