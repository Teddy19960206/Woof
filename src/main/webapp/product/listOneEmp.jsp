<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.woof.product.entity.Product"%>
<%@ page import="com.woof.product.service.ProductService"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ProductService productSvc = new ProductService();
    List<Product> list = productSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有商品資料 - listAllProduct.jsp</title>

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
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
 <tr><td>
   <h3>所有商品資料 - listAllEmp.jsp</h3>
   <h4><a href="select_page_product.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
 </td></tr>
</table>

<table>
 <tr>
  <th>商品編號</th>
  <th>商品類別編號</th>
  <th>商品描述</th>
  <th>商品價格</th>
  <th>商品名稱</th>
  <th>商品狀態</th>
  <th>修改</th>
  <th>刪除</th>
 </tr>
 <%@ include file="page1.file" %> 
 <c:forEach var="product" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
  
  <tr>
   <td>${product.prodNo}</td>
   <td>${product.prodCatNo}</td>
   <td>${product.prodContent}</td>
   <td>${product.prodPrice}</td>
   <td>${product.prodName}</td>
   <td>${product.prodStatus}</td>
   <td>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product" style="margin-bottom: 0px;">
        <input type="submit" value="修改">
        <input type="hidden" name="prodNo"  value="${product.prodNo}">
        <input type="hidden" name="action" value="getOne_For_Update"></FORM>
   </td>
   <td>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product" style="margin-bottom: 0px;">
        <input type="submit" value="刪除">
        <input type="hidden" name="prodNo"  value="${product.prodNo}">
        <input type="hidden" name="action" value="delete"></FORM>
   </td>
  </tr>
 </c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>