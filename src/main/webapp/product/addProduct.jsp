<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.woof.product.entity.Product"%>

<% 
   Product product = (Product) request.getAttribute("product");
%>
<html>
<head>
<title>�ӫ~��Ʒs�W - addProduct.jsp</title>
<!-- ... �ٲ���L�˦��M�]�w ... -->
</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�ӫ~��Ʒs�W - addProduct.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/product" name="form1">
<table>
	<tr>
		<td>�ӫ~�W��:</td>
		<td><input type="TEXT" name="prodName" value="<%= (product==null)? "" : product.getProdName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�ӫ~����:</td>
		<td><input type="TEXT" name="prodPrice" value="<%= (product==null)? "" : product.getProdPrice()%>" size="45"/></td>
	</tr>
	<!-- ... ��L�ӫ~������� ... -->
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>

</body>
</html>
