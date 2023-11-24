<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.woof.shoporder.service.ShopOrderServiceImpl" %>
<%@ page import="com.woof.shoporder.entity.ShopOrder" %>
<%@ page import="com.woof.member.entity.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
	Member member = (Member) request.getSession(false).getAttribute("member");
	
	List<ShopOrder> list = new ShopOrderServiceImpl().getOrdersByMember(member.getMemNo());
	
	request.setAttribute("all" , list);
%>


<html>
<head>
    <%@ include file="/meta.file"%>
    <title>寵毛導師 Woof | 商城訂單管理</title>
</head>
<body>
<%@ include file="body.jsp" %>
<style>
.table th, .table td {
     border: 0px solid #ddd; /* 加強邊框  */
     padding: 8px; /* 增加內距  */
     text-align: center; /* 左對齊文本  */
}
.table th{
    border-bottom: 1px solid #b5b6b7;
}
</style>
<div class="col-12 col-md-8">
<!--             <div class="card"> -->
<!--                 <div class="card-body"> -->
    <h3 class="text-center p-2">商城訂單</h3>
    <table class="table table-hover text-center" id="show">
        <thead>
            <tr>
                <th>訂單編號</th>
                <th>成立時間</th>
                <th>付款方式</th>
                <th>訂單狀態</th>
                <th>訂單明細</th>
            </tr>

        </thead>
        <tbody>
        <c:forEach items="${all}" var="order">
          <tr>
            <td>${order.shopOrderNo}</td>

            <fmt:formatDate value="${order.prodOrderDate}" pattern="yyyy-MM-dd" var="formattedDate"/>
            <td>${formattedDate}</td>

            <td>
            <c:choose>
                <c:when test="${order.payMethod == 0}">
                    <p>信用卡</p>
                </c:when>
                <c:when test="${order.payMethod == 1}">
                    <p>匯款</p>
                </c:when>
                <c:when test="${order.payMethod == 2}">
                    <p>綠界</p>
                </c:when>
            </c:choose>
            </td>

            <td>
            <c:choose>
                <c:when test="${order.orderStatus == 0}">
                    <p><span class="text-success"> 成立</span></p>
                </c:when>
                <c:when test="${order.orderStatus == 1}">
                    <p>出貨</p>
                </c:when>
                <c:when test="${order.orderStatus == 2}">
                    <p>完成</p>
                </c:when>
                <c:when test="${order.orderStatus == 3}">
                    <p>取消</p>
                </c:when>
                <c:when test="${order.orderStatus == 4}">
                    <p><span class="text-danger"> 未付款</span></p>
                </c:when>
                <c:when test="${order.orderStatus == 5}">
                    <p>已付款</p>
                </c:when>
            </c:choose>
            </td>
            <td>
                <a href="shoporderdetailmem.jsp?shopOrderNo=${order.shopOrderNo}" class="btn btn-info">查看</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<%@ include file="foot.jsp" %>

<!--     </div> -->
<!-- </div> -->

<script src="${pageContext.request.contextPath}/frontend/member/js/groupOrder.js"></script>
</body>
</html>
