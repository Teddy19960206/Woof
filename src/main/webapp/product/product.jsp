<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css">
<title>產品系統</title>
</head>
<body>
	<h1>這是一位後端人員作的網頁 QQ</h1>
	<h2>產品系統</h2>
	<a href="${pageContext.request.contextPath}/product/product.do?action=getAll">查詢所有產品</a>
	<br><br>
	<h3><b>複合查詢：</b></h3>
	<form action="${pageContext.request.contextPath}/product/product.do" method="post">
		<p><label>產品名稱模糊查詢：</label></p>
		<input type="text" name="prodName"><br>
		<p><label>產品類別：</label></p>
		<select name="prodCatNo">
			<option value="">選取類別</option>
			<option value="1">電子產品</option>
			<option value="2">家居用品</option>
			<option value="3">食品</option>
			<!-- 更多類別 -->
		</select>
		<p><label>價格範圍：</label></p>
		<input type="text" name="startPrice"> ～ <input type="text" name="endPrice"><br>
		<p><input type="submit" value="送出"></p>
		<input type="hidden" name="action" value="compositeQuery">
	</form>
</body>
</html>
