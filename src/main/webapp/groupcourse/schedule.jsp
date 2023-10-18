<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Schedule</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webutil/css/bootstrap.min.css" />
</head>
<body>
<h1>團體課程報名資訊</h1>
<jsp:useBean id="classTypeService" scope="page" class="com.woof.classtype.service.ClassTypeServiceImpl"/>
<form>
    <select name="Type" id="selectClass">
        <c:forEach var="classType" items="${classTypeService.allClassTypes}">
            <option  value="${classType.ctNo}">${classType.ctName}</option>
        </c:forEach>
    </select>
    <button type="button" id="button">提交</button>
</form>

<%--<c:forEach items="${scheduleList}" var="schedule">--%>
<%--    ${schedule.gcsNo}--%>
<%--    ${schedule.groupCourse.gcName}--%>
<%--    ${schedule.trainer.trainerNo}--%>
<%--    ${schedule.gcsStart}--%>
<%--    ${schedule.gcsEnd}--%>
<%--    ${schedule.minLimit}--%>
<%--    ${schedule.maxLimit}--%>
<%--    ${schedule.count}--%>
<%--    ${schedule.gcsPrice}--%>
<%--    ${schedule.gcsStatus}--%>

<%--</c:forEach>--%>


<%-- 報名列表 --%>
<div class="container center-table">
    <div class="row">
        <table class="table table-stripclassNamext-center" border="1">
            <thead>
                <tr>
                    <th>GCS_NO</th>
                    <th>GC_NO</th>
                    <th>TRAINER_NO</th>
                    <th>GCS_START</th>
                    <th>GCS_END</th>
                    <th>MIN_LIMIT</th>
                    <th>MAX_LIMIT</th>
                    <th>COUNT</th>
                    <th>GCS_PRICE</th>
                    <th>GCS_STATUS</th>
                    <th>修改</th>
                    <th>報名</th>
                </tr>
            </thead>
            <tbody id="mybody">
                <c:forEach items="${scheduleList}" var="schedule">
                    <tr>

                        <td>${schedule.gcsNo}</td>
                        <td>${schedule.groupCourse.gcNo}</td>
                        <td>${schedule.trainer.trainerNo}</td>
                        <td>${schedule.gcsStart}</td>
                        <td>${schedule.gcsEnd}</td>
                        <td>${schedule.minLimit}</td>
                        <td>${schedule.maxLimit}</td>
                        <td>${schedule.count}</td>
                        <td>${schedule.gcsPrice}</td>
                        <td>${schedule.gcsStatus}</td>

                        <td>
                            <button type="button" class="modify-button" data-id="${schedule.gcsNo}">修改</button>
                        </td>
                        <td>
<%--                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">--%>
<%--                                報名--%>
<%--                            </button>--%>
                            <button type="button" class="registration" data-id="${schedule.gcsNo}">報名</button>
                        </td>
                    </tr>
                </c:forEach>
        </table>
        <a href="#"><button>新增團體課程報名</button></a>



        <div class="container">
<%--            <h2>Basic Modal Example</h2>--%>
<%--            <!-- Button trigger modal -->--%>
<%--            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">--%>
<%--                Launch demo modal--%>
<%--            </button>--%>

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

    </div>
</div>


<script src="${pageContext.request.contextPath}/webutil/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/webutil/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/groupcourse/js/getSchedule.js"></script>
</body>
</html>
