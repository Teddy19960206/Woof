<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FunctionPermission</title>
</head>
<body>
	<jsp:useBean id="functionPermissionService" scope="page"
		class="com.woof.functionpermission.service.FunctionPermissionServiceImpl" />
	<form method="POST"
		ACTION="${pageContext.request.contextPath}/functionpermission">
		<select name="FunctionPermission" id="selectFunctionPermission">
			<c:forEach var="functionPermission"
				items="${functionPermissionService.allFunctionPermissions}">
				<option value="${functionPermission.funcNo}">${functionPermission.funcName}</option>
			</c:forEach>
		</select>
		<button type="submit">提交</button><br>
		<br>
		<button value="1">新增</button>
		<button value="2">修改</button>
		<button value="3">刪除</button>
	</form>
</body>
</html>
