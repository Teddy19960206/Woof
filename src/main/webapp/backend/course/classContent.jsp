<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 團體內容管理</title>
    <style>
        div.container{
            margin: 10px
        }

        select{
            text-align: center;
        }

    </style>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/course/css/button.css" />

</head>

<%@ include file="/backend/backbody.file" %>





<%-- 取得團體課程列表 --%>

<div class="container mx-auto">
    <%-- 取得所有 classtype 顯示在select裡，讓使用者可以選擇 --%>

    <div class="row">
        <h1 align="center" class="my-5">團體內容管理</h1>

        <jsp:useBean id="classTypeService" scope="page" class="com.woof.classtype.service.ClassTypeServiceImpl"/>
        <div class="">
            <div class="row">
                <label class="col-form-label col-5 d-flex justify-content-end">班別</label>
                <div class="col-2">
                    <select name="Type" id="selectClass" class="form-select">
                        <option value="">顯示全部</option>
                        <c:forEach var="classType" items="${classTypeService.allClassTypes}">
                            <option value="${classType.ctNo}">${classType.ctName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row mt-3">
                <label class="col-form-label col-5 d-flex justify-content-end ">狀態</label>
                <div class="col-2">
                    <select name="status" id="selectStatus" class="form-select">
                        <option value="">顯示全部</option>
                        <option value="0">下架</option>
                        <option value="1">上架</option>
                    </select>
                </div>
            </div>

            <div class="row ">
                <div class="col-12 text-center m-3 ">
                    <button type="button" id="button" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>

    <%--    顯示課程  --%>
    <div class="row">
        <div class="row showGroup">
            <div class="d-flex justify-content-center">
                <div class="spinner-border m-5" role="status">
                    <span class="visually-hidden">Loading...</span>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="text-center mb-5">
    <%-- 新增團體課程 --%>
    <a href="${pageContext.request.contextPath}/groupcourse/addpage">
        <button class="btn btn-primary">新增團體課程</button>
    </a>
    <a href="${pageContext.request.contextPath}/classtype/all">
        <button class="btn btn-primary">查看班級名稱列表</button>
    </a>
    <%--<a href="${pageContext.request.contextPath}/backend/course/addClassType.jsp"><button>新增班別</button></a>--%>
    <a href="${pageContext.request.contextPath}/backend/">
        <button class="btn btn-secondary">返回後台首頁</button>
    </a>
</div>


<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/getGroup.js"></script>
</body>
</html>