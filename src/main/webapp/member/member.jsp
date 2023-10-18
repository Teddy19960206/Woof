<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>Member</title>
</head>
<body>

	<jsp:useBean id="memberService" scope="page"
		class="com.woof.member.service.MemberServiceImpl" />
	<form method="POST" ACTION="${pageContext.request.contextPath}/member">
		<h2 style="text-align: center;">會員列表</h2>
		<div style="text-align: center;">
			<label for="Member">Select Member:</label> 
			<select name="Member" id="selectMember">
				<c:forEach var="member" items="${memberService.allMembers}">
					<option value="${member.memNo} ${member.memGender} ${member.memPhoto} ${member.memEmail} ${member.memTel} ${member.memPassword} ${member.memAddress} ${member.memBd} ${member.momoPoint} ${member.totalClass} ${member.memStatus}">${member.memName}</option>
				</c:forEach>
			</select>
			<button type="submit" id="button">提交</button>
		</div>
	</form>
<%-- 取得會員列表 --%>
<div class="container center-table">
    <div class="row">
    </div>
</div>
	<script src="${pageContext.request.contextPath}/webutil/js/jquery-3.7.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/member/member.js"></script> 
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
</body>
</html>
