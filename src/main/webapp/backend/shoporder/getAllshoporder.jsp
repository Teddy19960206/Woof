<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>全部訂單</title>
<!--     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->
    <style>



    </style>
    
<%@ include file="/backend/backhead.file" %>
    
</head>
<body>

<%@ include file="/backend/backbody.file" %>

    <div class="container">
        <h3>全部訂單</h3>
        <table class="table table-bordered small-table" style="font-size: 12px;">
            <thead>
                <tr>
                    <th>商城訂單編號</th>
                    <th>會員帳號</th>
                    <th>訂單成立時間</th>
                    <th>付款方式</th>
                    <th>取貨方式</th> 
                    <th>收件人姓名</th>
                    <th>收件人電話</th>
                    <th>收件人地址</th>
                    <th>是否有退貨</th>
                    <th>折抵毛毛幣</th>
                    <th>訂單總金額</th>
                    <th>實付金額</th>
                    <th>訂單狀態</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="all" items="${all}">
                    <tr>
                        <td>${all.shopOrderNo}</td>
                        <td>${all.member.memName}</td>
                        <td>${all.prodOrderDate}</td>
                        <td>
                        	<c:choose>
						        <c:when test="${all.payMethod == 0}">信用卡</c:when>
						        <c:when test="${all.payMethod == 1}">匯款</c:when>
						    </c:choose>
                        </td>
                        <td>
						    <c:choose>
						        <c:when test="${all.shipMethod == false}">宅配</c:when>
						        <c:when test="${all.shipMethod == true}">超商取貨</c:when>
						    </c:choose>
						</td>
                        <td>${all.recName}</td>
                        <td>${all.recMobile}</td>
                        <td>${all.recAddress}</td>
                        <td>
                        <c:choose>
						        <c:when test="${all.hasReturn == false}">無退貨</c:when>
						        <c:when test="${all.hasReturn == true}">有退貨</c:when>
						    </c:choose>
                        </td>
                        <td>${all.moCoin}</td>
                        <td>${all.orderTotalPrice}</td>
                        <td>${all.actualprice}</td>
                        <td>
						    <c:choose>
						        <c:when test="${all.orderStatus == 0}">成立</c:when>
						        <c:when test="${all.orderStatus == 1}">出貨</c:when>
						        <c:when test="${all.orderStatus == 2}">完成</c:when>
						        <c:when test="${all.orderStatus == 3}">取消</c:when>
						        <c:when test="${all.orderStatus == 4}">未付款</c:when>
						        <c:otherwise>未知狀態</c:otherwise>
						    </c:choose>
						</td>
                        <td>
<%--                             <form method="post" action="${pageContext.request.contextPath}/backend/faq/updatefaq.jsp"> --%>
<%--                                 <input type="hidden" name="faqNo" value="${all.faqNo}"> --%>
<!--                                 <input type="hidden" name="action" value="updatefaq"> -->
<!--                                 <button class="btn btn-success" type="submit">修改</button> -->
<!--                             </form> -->

<%-- <form method="post" action="${pageContext.request.contextPath}/backend/faq/updatefaq.jsp"> --%>
<%--     <input type="hidden" name="faqNo" value="${all.faqNo}"> --%>
<%--     <input type="hidden" name="faqClass" value="${all.faqClass}"> --%>
<%--     <input type="hidden" name="faqTitle" value="${all.faqTitle}"> --%>
<%--     <input type="hidden" name="faqContent" value="${all.faqContent}"> --%>
<!-- 	<button class="btn btn-success small-btn" style="font-size: 10px;" type="submit">修改</button> -->
<!-- </form> -->

<!--                         </td> -->
<!--                         <td> -->
<%--                             <form method="post" action="${pageContext.request.contextPath}/faq"> --%>
<%--                                 <input type="hidden" name="faqNo" value="${all.faqNo}"> --%>
<!--                                 <input type="hidden" name="action" value="deletefaq"> -->
<!--                                 <button class="btn btn-danger small-btn" style="font-size: 10px;" type="submit">刪除</button> -->
                                
<!--                             </form> -->
<!--                         </td> -->
<!--                     </tr> -->
                </c:forEach>
            </tbody>
        </table>

<%--         <p>currentPage=${currentPage}</p> --%>
<%--         <p>faqPageQty=${faqPageQty}</p> --%>
    <c:if test="${currentPage > 1}">
		<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/shoporder?action=getAll&page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/shoporder?action=getAll&page=${currentPage - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= faqPageQty}">
		<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/shoporder?action=getAll&page=${currentPage + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != faqPageQty}">
		<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/shoporder?action=getAll&page=${shopOrderPageQty}">至最後一頁</a>&nbsp;
	</c:if>
	
	
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/backend/shoporder/shoporderfirst.jsp">返回</a>
    </div>

<%@ include file="/backend/backfoot.file" %>

<!--     <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script> -->
<!--     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->
</body>
</html>
