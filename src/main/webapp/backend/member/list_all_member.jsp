<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<html>
<head>
<meta charset="UTF-8">
<title>�Ҧ��|�����</title>
<script type="text/javascript">
//����I����X������function//
  function processUpdate(jsonData){
	  window.location.href = " <%=request.getContextPath()%>/frontend/member/updatemember.jsp?memNo=" + jsonData.memNo ;
  }
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
.delete-btn {
    width: 50px; /* or whatever width you want */
}
.update-btn {
    width: 50px; /* or whatever width you want */
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
				<td><img src="${pageContext.request.contextPath}/DBPngReader?action=member&id=${member.memNo}" style="width: 100px; height: 100px"></td>
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
						action="${pageContext.request.contextPath}/member.do"
						style="margin-bottom: 0px;">
						<input type="hidden" name="action" value="update"> 
						<input type="hidden" name="memNo" value="${member.memNo}"> 
						<input type="button" class="update-btn" value="�ק�" onclick="processUpdate({memNo:'${member.memNo}'});">
						
					</form>
				</td>
<!-- 				<td> -->
<!-- 					<FORM METHOD="POST" -->
<%-- 						ACTION="<%=request.getContextPath()%>/member.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="hidden" name="action" value="delete" > -->
<%-- 						<input type="hidden" name="memNo" value="${member.memNo}">  --%>
<!-- 						<button type="submit" class="delete-btn" >�R��</button> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
			</tr>
		</c:forEach>
	</table>
</body>
	</html>