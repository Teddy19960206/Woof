<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
    <title>GetAllFAQ</title>
<!--     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->
    <style>



    </style>
    
<%@ include file="/backend/backhead.file" %>
    
</head>
<body>

<%@ include file="/backend/backbody.file" %>

    <div class="container">
        <h3>搜尋全部FAQ</h3>
        <table class="table table-bordered small-table" style="font-size: 12px;">
            <thead>
                <tr>
                    <th>編號</th>
                    <th>類別</th>
                    <th>標題</th>
                    <th>內容</th>
                    <th>狀態</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="all" items="${all}">
                    <tr>
                        <td>${all.faqNo}</td>
                        <td>${all.faqClass}</td>
                        <td>${all.faqTitle}</td>
                        <td>${all.faqContent}</td>
                        <td>
<%--                             <form method="post" action="${pageContext.request.contextPath}/backend/faq/updatefaq.jsp"> --%>
<%--                                 <input type="hidden" name="faqNo" value="${all.faqNo}"> --%>
<!--                                 <input type="hidden" name="action" value="updatefaq"> -->
<!--                                 <button class="btn btn-success" type="submit">修改</button> -->
<!--                             </form> -->

<form method="post" action="${pageContext.request.contextPath}/backend/faq/updatefaq.jsp">
    <input type="hidden" name="faqNo" value="${all.faqNo}">
    <input type="hidden" name="faqClass" value="${all.faqClass}">
    <input type="hidden" name="faqTitle" value="${all.faqTitle}">
    <input type="hidden" name="faqContent" value="${all.faqContent}">
	<button class="btn btn-success small-btn" style="font-size: 10px;" type="submit">修改</button>
</form>

                        </td>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/faq">
                                <input type="hidden" name="faqNo" value="${all.faqNo}">
                                <input type="hidden" name="action" value="deletefaq">
                                <button class="btn btn-danger small-btn" style="font-size: 10px;" type="submit">刪除</button>
                                
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

<%--         <p>currentPage=${currentPage}</p> --%>
<%--         <p>faqPageQty=${faqPageQty}</p> --%>
    <c:if test="${currentPage > 1}">
		<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/faq?action=getAll&page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/faq?action=getAll&page=${currentPage - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= faqPageQty}">
		<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/faq?action=getAll&page=${currentPage + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != faqPageQty}">
		<a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/faq?action=getAll&page=${faqPageQty}">至最後一頁</a>&nbsp;
	</c:if>
	
	
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/backend/faq/faqfirst.jsp">返回</a>
    </div>

<%@ include file="/backend/backfoot.file" %>

<!--     <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script> -->
<!--     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->
</body>
</html>
