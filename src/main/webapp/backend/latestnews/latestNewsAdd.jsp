<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ page import="com.woof.latestnews.entity.*"%>
<%
LatestNews ln = (LatestNews) request.getAttribute("latestNews");
%>
<html>
<head>
<meta charset="UTF-8">
<title>�d��ɮv Woof | �̷s�����s�W</title>
</head>
<body>
	<form method="post"
		action="<%=request.getContextPath()%>/latestNews.do"
		accept-charset="UTF-8" enctype="multipart/form-data">
		<table>
			<tr>
				<th>�������D</th>
				<td><input type="text" name="LN_TITLE" id="LN_TITLE"></td>
				<td><small class="error-msg">${errorMsgs.LN_TITLE}</small></td>
			</tr>
			<th>�������e</th>
			<td><input type="text" name="LN_CONTENT" id="LN_CONTENT">
			</td>
			<td><small class="error-msg">${errorMsgs.LN_CONTENT}</small></td>
			</tr>
			<tr>
				<th>�����ɶ�</th>
				<td><input type="text" name="LN_TIME" id="LN_TIME"></td>
				<td><small class="error-msg">${errorMsgs.LN_TIME}</small></td>
			</tr>
			<tr>
				<th>�޲z���j�Y�K</th>
				<td><input type="file" name="LN_PHOTO"></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="add">
		<button type="submit">�e�X</button>
		<input type="button"
			onclick="window.location.href='<%=request.getContextPath()%>/backend/latestnews/latestNews.jsp'"
			value="����">
	</form>
</body>
</html>