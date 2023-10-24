<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.woof.administrator.entity.*" %>
<%
	Administrator admin = (Administrator) request.getAttribute("admin");
%>
<html>
<head>
<!-- 日期的套版 -->
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet"> 
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<meta charset="UTF-8">
<title>administrator</title>
<script>
      //日期格式
    $(function() {
        $("#ADMIN_HD").datepicker({ dateFormat: 'yy-mm-dd' });
    });
</script>
</head>
<body>
<!--                               request.getContextPath()動態根路徑，action=add找到後端switch(action)的add-->
	<form method="post" action="<%=request.getContextPath()%>/administrator.do?action=add" accept-charset="UTF-8">
		<table>
		<tr>
			<th>管理員名字</th>
			<td>
				<input type="text" name="ADMIN_NAME" id="ADMIN_NAME" required >
			</td>
		</tr>
		<tr>
			<th>管理員性別</th>
			<td>
				<input type="radio" name="ADMIN_GENDER" value="1" checked>男
				<input type="radio" name="ADMIN_GENDER" value="0">女
			</td>
		</tr>
		<tr>
			<th>管理員信箱</th>
			<td>
				<input type="text" name="ADMIN_EMAIL" id="ADMIN_EMAIL" placeholder="XXX@gmail.com">
			</td>
		</tr>
		<tr>
			<th>管理員密碼</th>
			<td>
				<input type="password" name="ADMIN_PASSWORD" id="ADMIN_PASSWORD" >
			</td>
		</tr>
			<tr>
			<th>管理員電話</th>
			<td>
				<input type="text" name="ADMIN_TEL" id="ADMIN_TEL">
			</td>
		</tr>
		<tr>
			<th>管理員地址</th>
			<td>
				<input type="text" name="ADMIN_ADDRESS" id="ADMIN_ADDRESS">
			</td>
		</tr>
		<tr>
			<th>管理員緊急聯絡人</th>
			<td>
				<input type="text" name="EMERGENCY_CONTACTNAME" id="EMERGENCY_CONTACTNAME">
			</td>
		</tr>
		<tr>
			<th>管理員緊急聯絡人電話</th>
			<td>
				<input type="text" name="EMERGENCY_CONTACTEL" id="EMERGENCY_CONTACTEL">
			</td>
		</tr>
		<tr>
			<th>管理員到職日</th>
			<td>
				<input type="text" name="ADMIN_HD" id="ADMIN_HD">
			</td>
		</tr>
		<tr>
			<th>管理員帳號狀態</th>
			<td>
				<input type="radio" name="ADMIN_STATUS" value="0">離職
				<input type="radio" name="ADMIN_STATUS" value="1" checked>在職
				<input type="radio" name="ADMIN_STATUS" value="2">停職
			</td>
		</tr>
		</table>

		
		<button type="submit">送出</button>
		<!-- 		點擊取消後跳轉回去首頁 -->
		<input  type="button" onclick="window.location.href='<%=request.getContextPath()%>/frontend/administrator/administrator.jsp'" value="取消">
	</form>
</body>
</html>