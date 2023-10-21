<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <%@ include file="/meta.file" %>
    <title>團體內容管理</title>
</head>

<%@ include file="/Header.file" %>

<h1>團體內容管理</h1>

<%-- 取得所有 classtype 顯示在select裡，讓使用者可以選擇 --%>
<jsp:useBean id="classTypeService" scope="page" class="com.woof.classtype.service.ClassTypeServiceImpl"/>
<select name="Type" id="selectClass">
    <option value="0">-- 顯示全部 --</option>
    <c:forEach var="classType" items="${classTypeService.allClassTypes}">
        <option value="${classType.ctNo}" style="text-align: center">${classType.ctName}</option>
    </c:forEach>
</select>
<button type="button" id="button">提交</button>


<%-- 取得團體課程列表 --%>

<div class="container center-table">
    <div class="row">
        <div class="d-flex justify-content-center">
            <div class="spinner-border m-5" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>
    </div>
</div>

<%-- 新增團體課程 --%>
<a href="${pageContext.request.contextPath}/groupcourse/addpage">
    <button>新增團體課程</button>
</a>
<a href="${pageContext.request.contextPath}/classtype/all">
    <button>查看班級名稱列表</button>
</a>
<%--<a href="${pageContext.request.contextPath}/backend/course/addClassType.jsp"><button>新增班別</button></a>--%>
<a href="${pageContext.request.contextPath}/backend/">
    <button>返回後台首頁</button>
</a>


<%@ include file="/Footer.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/getGroup.js"></script>
</body>
</html>