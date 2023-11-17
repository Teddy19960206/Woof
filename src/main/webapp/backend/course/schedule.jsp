<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/course/css/button.css" />
    <style>
        select > option{
            text-align: center;
        }
        table{
            font-size: .9rem;
        }
    </style>
    <title>寵毛導師 Woof | 團體課程報名資訊</title>
</head>
<body>

<%@ include file="/backend/backbody.file" %>



<div class="row">
    <h1 align="center" class="mt-2">團體課程報名資訊</h1>

    <jsp:useBean id="classTypeService" scope="page" class="com.woof.classtype.service.ClassTypeServiceImpl"/>
    <div class="my-2">
        <div class="row">
            <div class="col-3">
                <label class="col-form-label col-5 d-flex ">班別</label>
                <div class="col-12">
                    <select name="Type" id="selectClass" class="form-select">
                        <option value="">顯示全部</option>
                        <c:forEach var="classType" items="${classTypeService.allClassTypes}">
                            <option value="${classType.ctNo}">${classType.ctName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-3">
            <label class="col-form-label col-5 d-flex  ">狀態</label>
                <div class="col-12">
                    <select name="status" id="selectStatus" class="form-select">
                        <option value="">顯示全部</option>
                        <option value="0">下架</option>
                        <option value="1">上架</option>
                        <option value="2">確認開課</option>
                        <option value="3">已取消</option>
                        <option value="4">延期</option>
                        <option value="5">已結束</option>
                        <option value="6">審核中</option>
                    </select>
                </div>
            </div>
            <div class="col-3">
                <label class="col-form-label">開始查詢</label>
                <button type="button" id="button" class="btn btn-primary d-block">提交</button>
            </div>
        </div>

    </div>
</div>


<%-- 報名列表 --%>
<div class="container-fluid center-table">
    <div class="row showSchedule">
    </div>
    <div class="text-center">
        <a href="${pageContext.request.contextPath}/schedule/addpage">
            <button type="button" class="btn btn-primary">新增報名課程</button>
        </a>
        <a id="addDetail" style="display: none">
            <button type="button" class="btn btn-primary">新增上課日期</button>
        </a>
        <a href="${pageContext.request.contextPath}/backend/index.jsp">
            <button class="btn btn-secondary">返回後台首頁</button>
        </a>
    </div>
</div>


<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/getSchedule.js"></script>
<script src="${pageContext.request.contextPath}/backend/course/js/addSchedule.js"></script>
</body>
</html>
