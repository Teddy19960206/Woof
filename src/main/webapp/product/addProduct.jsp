<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.woof.product.entity.Product"%>

<% 
   Product product = (Product) request.getAttribute("product");
%>
<html>
<head>
<title>商品資料新增 - addProduct.jsp</title>
<!-- ... 省略其他樣式和設定 ... -->
</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>商品資料新增 - addProduct.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product" name="form1">
<table>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="prodName" value="<%= (product==null)? "" : product.getProdName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品價格:</td>
		<td><input type="TEXT" name="prodPrice" value="<%= (product==null)? "" : product.getProdPrice()%>" size="45"/></td>
	</tr>
	<!-- ... 其他商品相關欄位 ... -->
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>
</html>
