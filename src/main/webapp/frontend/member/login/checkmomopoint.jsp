<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<html>
<head>
<title>¤ò¤ò¹ô</title>

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
	<h4>¦¹­¶¼È½m²ß±Ä¥Î Script ªº¼gªk¨ú­È:</h4>
	<table>
		<tr>
			<th>¤ò¤ò¹ô</th>
		</tr>

		<tr>
			<td>${member.momoPoint}</td>
		</tr>
	</table>
</body>
</html>