<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>


<%
//EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�|����� - list_one_member.jsp</title>

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
	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>���u��� - listOneEmp.jsp</h3>
				<h4>
					<!-- <a href="/frontend/member/selectmember.jsp">�^����</a> -->
					<button type="button" onclick="history.back()">�^����</button>
				</h4>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<th>�s��</th>
			<th>�m�W</th>
			<th>�ʧO</th>
			<th>�Ӥ�</th>
			<th>email</th>
			<th>�K�X</th>
			<th>�q��</th>
			<th>�a�}</th>
			<th>�ͤ�</th>
			<th>����</th>
			<th>�Ұ��</th>
			<th>���A</th>
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