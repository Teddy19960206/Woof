<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Member</title>
<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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
	<ul>
		<li><a href='list_all_member.jsp'>查看全部會員</a> <br> <br></li>

		<jsp:useBean id="memberService" scope="page"
			class="com.woof.member.service.MemberServiceImpl" />

		<li><form method="POST"
				ACTION="${pageContext.request.contextPath}/member.do">
				<b>選擇會員編號:</b> 
				<select name="memNo">
					<c:forEach var="member" items="${memberService.allMembers}">
						<option
							value="${member.memNo}">${member.memNo}</option>
					</c:forEach>
				</select> 
				<input type="hidden" name="action" value="getone">
				<button type="submit">提交</button>
			</form></li>
			
		<li><form method="POST"
				ACTION="${pageContext.request.contextPath}/member.do">
				<b>選擇會員姓名:</b>
				<select name="memNo">
					<c:forEach var="member" items="${memberService.allMembers}">
						<option
							value="${member.memNo}">${member.memName}</option>
					</c:forEach>
				</select>
				<input type="hidden" name="action" value="getone">
				<button type="submit">提交</button>
			</form></li>
	</ul>


	<h3>會員管理</h3>

	<ul>
		<li><a href='addmember.jsp'>Add</a> a new member.</li>
	</ul>
</html>







