<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<html>
<head>
<meta charset="UTF-8">
<title>�|�����</title>

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
	<h4></h4>
	<table>
		<tr>
			<td>
				<h3>�Ҧ��|����� - listAllEmp.jsp</h3>
				<h4>
					<a href="selectmember.jsp">�^����</a>
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
		<c:forEach var="member" items="${memberService.allMembers}">
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
				<td>

					<form method="post"
						action="${pageContext.request.contextPath}/member"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="empno" value="${member.memNo}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</form>
				</td>
				<td>
					<FORM METHOD="POST"
						ACTION="<%=request.getContextPath()%>/member"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="empno" value="${member.memNo}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
	</html>