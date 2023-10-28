<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Schedule</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webutil/css/bootstrap.min.css"/>
</head>
<body>
<h1>團體課程報名資訊</h1>
<jsp:useBean id="classTypeService" scope="page" class="com.woof.classtype.service.ClassTypeServiceImpl"/>
<select name="Type" id="selectClass">
    <option value="0">-- 顯示全部 --</option>
    <c:forEach var="classType" items="${classTypeService.allClassTypes}">
        <option value="${classType.ctNo}" style="text-align: center">${classType.ctName}</option>
    </c:forEach>
</select>
<button type="button" id="button">提交</button>


<%-- 報名列表 --%>
<div class="container center-table">
    <div class="row">
    </div>
</div>


<a href="${pageContext.request.contextPath}/schedule/addpage">
    <button type="button">新增報名課程</button>
</a>
<a href="${pageContext.request.contextPath}/backend/">
    <button>返回後台首頁</button>
</a>

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


<script src="${pageContext.request.contextPath}/webutil/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/webutil/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/backend/course/js/getSchedule.js"></script>
<script src="${pageContext.request.contextPath}/backend/course/js/addSchedule.js"></script>
</body>
</html>
