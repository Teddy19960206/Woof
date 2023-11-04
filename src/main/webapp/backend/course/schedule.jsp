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
    </style>
    <title>寵毛導師 Woof | 團體課程報名資訊</title>
</head>
<body>

<%@ include file="/backend/backbody.file" %>



<div class="row">
    <h1 align="center" class="my-5">團體課程報名資訊</h1>

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


<%-- 報名列表 --%>
<div class="container center-table">
    <div class="row showSchedule">
    </div>
    <a href="${pageContext.request.contextPath}/schedule/addpage">
        <button type="button" class="btn btn-primary">新增報名課程</button>
    </a>
    <a href="${pageContext.request.contextPath}/backend/">
        <button class="btn btn-secondary">返回後台首頁</button>
    </a>
</div>




<div class="container">
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    確定要報名嗎
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消報名</button>
                    <button type="button" class="btn btn-primary">確定報名</button>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/getSchedule.js"></script>
<script src="${pageContext.request.contextPath}/backend/course/js/addSchedule.js"></script>
</body>
</html>
