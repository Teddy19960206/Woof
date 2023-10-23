<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>商品管理: 首頁</title>
 
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
   <tr><td><h3>商品管理: 首頁</h3><h4>( MVC )</h4></td></tr>
</table>

<p>這是商品管理的首頁</p>

<h3>資料查詢:</h3>
  
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
  <font style="color:red">請修正以下錯誤:</font>
  <ul>
      <c:forEach var="message" items="${errorMsgs}">
        <li style="color:red">${message}</li>
      </c:forEach>
  </ul>
</c:if>

<ul>
  <li><a href='listAllProduct.jsp'>列出所有商品</a><br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="/Product">
        <b>輸入商品編號 (如1):</b>
        <input type="text" name="prodNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="productSvc" scope="page" class="com.woof.product.service.ProductService" />
   
  <li>
     <FORM METHOD="post" ACTION="product.do">
       <b>選擇商品編號:</b>
       <select size="1" name="prodNo">
         <c:forEach var="product" items="${productSvc.all}">
          <option value="${product.prodNo}">${product.prodNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="product.do">
       <b>選擇商品名稱:</b>
       <select size="1" name="prodNo">
         <c:forEach var="product" items="${productSvc.all}">
          <option value="${product.prodNo}">${product.prodName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<h3>商品管理</h3>

<ul>
  <li><a href='addProduct.jsp'>新增商品</a></li>
</ul>

</body>
</html>
