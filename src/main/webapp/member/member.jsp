<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>Member</title>
<!-- <style>
table {
	width: 80%;
	margin: 20px auto;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px 12px;
}

th {
	background-color: #f2f2f2;
}
</style> -->
</head>
<body>

<jsp:useBean id="memberService" scope="page" class="com.woof.member.service.MemberServiceImpl" />
<form method="POST" ACTION="${pageContext.request.contextPath}/member">
		<select name="Type" id="Member">
<!-- <h2 style="text-align:center;">List of Members</h2>
<div style="text-align:center;">
    <label for="statusFilter">Filter by Status:</label>
    <select id="statusFilter" onchange="filterTable()">
        <option value="all">All</option>
        <option value="Active">Active</option>
        <option value="Inactive">Inactive</option>
    </select>
-->
			<c:forEach var="member" items="${memberService.allMembers}">
				<option value="${member.memNo}">${member.memName}</option>
			</c:forEach>
		</select>
		<button type="button" id="button">提交</button>
	</form>

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
<!--  -->
</body>
</html>
