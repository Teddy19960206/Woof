<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Member</title>

<style>
/* Basic reset for some elements */
body, h3, h4, p, ul, li, a {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
}

/* Page background and default color scheme */
body {
	background-color: #f4f4f4;
	color: #333;
}

/* Main table styling */
#table-1 {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

#table-1 td {
	background-color: #2c3e50;
	color: #ecf0f1;
	padding: 15px 25px;
	text-align: center;
}

/* Paragraph styles */
p {
	margin-bottom: 10px;
	font-size: 1rem;
	color: #555;
}

/* Heading styles */
h3 {
	font-size: 1.5rem;
	margin-bottom: 15px;
	color: deepyellow;
}

h4 {
	font-size: 1.2rem;
	margin-top: 5px;
	color: #777;
}

/* Error message styling */
ul {
	list-style-type: none;
	margin-bottom: 20px;
}

li {
	margin-bottom: 5px;
}

li a {
	color: #2980b9;
	text-decoration: none;
}

li a:hover {
	text-decoration: underline;
}

font[color="red"] {
	font-weight: bold;
}

/* Form styling */
form {
	margin-bottom: 20px;
}

select, input[type="text"] {
	padding: 5px 10px;
	margin-right: 10px;
	border: 1px solid #bdc3c7;
	border-radius: 4px;
}

input[type="submit"] {
	padding: 6px 15px;
	border: none;
	border-radius: 4px;
	background-color: #2980b9;
	color: #ecf0f1;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

input[type="submit"]:hover {
	background-color: #3498db;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>會員資料</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for IBM Emp: Home</p>
	<h3>資料查詢:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message.value}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllEmp.jsp'>List</a> all Emps. <br> <br></li>


		<li>
			<FORM METHOD="post" ACTION="emp.do">
				<b>輸入員工編號 (如7001):</b> <input type="text" name="Member"
					value="${param.empno}"><font color=red>${errorMsgs.empno}</font>
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="memberService" scope="page"
			class="com.woof.member.service.MemberServiceImpl" />

		<li><from method="POST"
				ACTION="${pageContext.request.contextPath}/member"> <b>選擇員工編號:</b>
			<select size="1" name="empno">
				<c:forEach var="member" items="${memberService.allMembers}">
					<option
						value="${member.memNo} ${member.memGender} ${member.memPhoto} ${member.memEmail} ${member.memTel} ${member.memPassword} ${member.memAddress} ${member.memBd} ${member.momoPoint} ${member.totalClass} ${member.memStatus}">${member.memNo}</option>
				</c:forEach>
			</select> <input type="hidden" name="Member" value="getOne_For_Display">
			<input type="submit" value="送出"> </from></li>

		<li><from method="POST"
				ACTION="${pageContext.request.contextPath}/member"> <b>選擇員工姓名:</b>
			<select size="1" name="empno">
				<c:forEach var="member" items="${memberService.allMembers}">
					<option
						value="${member.memNo} ${member.memGender} ${member.memPhoto} ${member.memEmail} ${member.memTel} ${member.memPassword} ${member.memAddress} ${member.memBd} ${member.momoPoint} ${member.totalClass} ${member.memStatus}">${member.memName}</option>
				</c:forEach>
			</select> <input type="hidden" name="Member" value="getOne_For_Display">
			<input type="submit" value="送出"> </from></li>
	</ul>


	<h3>員工管理</h3>

	<ul>
		<li><a href='addEmp.jsp'>Add</a> a new Emp.</li> -->
	</ul>
</html>







