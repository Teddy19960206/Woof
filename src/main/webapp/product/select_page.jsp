<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>�ӫ~�޲z: ����</title>
 
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
   <tr><td><h3>�ӫ~�޲z: ����</h3><h4>( MVC )</h4></td></tr>
</table>

<p>�o�O�ӫ~�޲z������</p>

<h3>��Ƭd��:</h3>
  
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
  <font style="color:red">�Эץ��H�U���~:</font>
  <ul>
      <c:forEach var="message" items="${errorMsgs}">
        <li style="color:red">${message}</li>
      </c:forEach>
  </ul>
</c:if>

<ul>
  <li><a href='listAllProduct.jsp'>�C�X�Ҧ��ӫ~</a><br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="/Product">
        <b>��J�ӫ~�s�� (�p1):</b>
        <input type="text" name="prodNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="productSvc" scope="page" class="com.woof.product.service.ProductService" />
   
  <li>
     <FORM METHOD="post" ACTION="product.do">
       <b>��ܰӫ~�s��:</b>
       <select size="1" name="prodNo">
         <c:forEach var="product" items="${productSvc.all}">
          <option value="${product.prodNo}">${product.prodNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="product.do">
       <b>��ܰӫ~�W��:</b>
       <select size="1" name="prodNo">
         <c:forEach var="product" items="${productSvc.all}">
          <option value="${product.prodNo}">${product.prodName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>

<h3>�ӫ~�޲z</h3>

<ul>
  <li><a href='addProduct.jsp'>�s�W�ӫ~</a></li>
</ul>

</body>
</html>
