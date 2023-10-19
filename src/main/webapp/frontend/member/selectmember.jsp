<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- <html> -->
<!-- <head> -->
<!-- <meta charset="UTF-8"> -->
<!-- <title>Member</title> -->
<!-- </head> -->
<!-- <body> -->

<%-- 	<jsp:useBean id="memberService" scope="page" --%>
<%-- 		class="com.woof.member.service.MemberServiceImpl" /> --%>
<%-- 	<form method="POST" ACTION="${pageContext.request.contextPath}/member"> --%>
<!-- 		<h2 style="text-align: center;">會員列表</h2> -->
<!-- 		<div style="text-align: center;"> -->
<!-- 			<label for="Member">Select Member:</label>  -->
<!-- 			<select name="Member" id="selectMember"> -->
<%-- 				<c:forEach var="member" items="${memberService.allMembers}"> --%>
<%-- 					<option value="${member.memNo} ${member.memGender} ${member.memPhoto} ${member.memEmail} ${member.memTel} ${member.memPassword} ${member.memAddress} ${member.memBd} ${member.momoPoint} ${member.totalClass} ${member.memStatus}">${member.memName}</option> --%>
<%-- 				</c:forEach> --%>
<!-- 			</select> -->
<!-- 			<button type="submit" id="button">提交</button> -->
<!-- 		</div> -->
<!-- 	</form> -->
<%-- <%-- 取得會員列表 --%>
<!-- <div class="container center-table"> -->
<!--     <div class="row"> -->
<!--     </div> -->
<!-- </div> -->
<%-- 	<script src="${pageContext.request.contextPath}/webutil/js/jquery-3.7.1.min.js"></script> --%>
<%-- 	<script src="${pageContext.request.contextPath}/frontend/member/member.js"></script> --%>
<!-- <script>
    function filterTable() {
        let filter = document.getElementById('statusFilter').value;
        let rows = document.querySelectorAll('#memberTable tbody tr');

        rows.forEach(row => {
            if (filter === 'all' || row.getAttribute('data-status') === filter) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    }
</script> -->
<!-- </body> -->
<!-- </html> -->
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

	<%-- 錯誤表列 --%>
	<%-- <c:if test="${not empty errorMsgs}"> --%>
	<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
	<!-- 	<ul> -->
	<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
	<%-- 			<li style="color:red">${message.value}</li> --%>
	<%-- 		</c:forEach> --%>
	<!-- 	</ul> -->
	<%-- </c:if> --%>

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

		<li>
			<from method="POST" ACTION="${pageContext.request.contextPath}/member">
				<b>選擇員工編號:</b> <select size="1" name="empno">
					<c:forEach var="member" items="${memberService.allMembers}">
						<option
							value="${member.memNo} ${member.memGender} ${member.memPhoto} ${member.memEmail} ${member.memTel} ${member.memPassword} ${member.memAddress} ${member.memBd} ${member.momoPoint} ${member.totalClass} ${member.memStatus}">${member.memNo}</option> --%>
					</c:forEach>
				</select> <input type="hidden" name="Member" value="getOne_For_Display">
				<input type="submit" value="送出">
			</from>
		</li>

		<li>
			<from method="POST" ACTION="${pageContext.request.contextPath}/member">
				<b>選擇員工姓名:</b> <select size="1" name="empno">
					<c:forEach var="member" items="${memberService.allMembers}">
						<option
							value="${member.memNo} ${member.memGender} ${member.memPhoto} ${member.memEmail} ${member.memTel} ${member.memPassword} ${member.memAddress} ${member.memBd} ${member.momoPoint} ${member.totalClass} ${member.memStatus}">${member.memName}</option> --%>
					</c:forEach>
				</select> <input type="hidden" name="Member" value="getOne_For_Display">
				<input type="submit" value="送出">
			</from>
		</li>
	</ul>


	<h3>員工管理</h3>

	<ul>
		<li><a href='addEmp.jsp'>Add</a> a new Emp.</li>
	</ul>