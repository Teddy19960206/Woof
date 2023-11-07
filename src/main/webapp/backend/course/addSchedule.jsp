<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <script src='${pageContext.request.contextPath}/webutil/js/index.global.min.js'></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/backend/course/css/calendar.css" />
    <title>寵毛導師 Woof | 新增報名資訊</title>
</head>

<body>
<%@ include file="/backend/backbody.file" %>
<div class="container">
        <form action="${pageContext.request.contextPath}/schedule/addSchedule" method="post">
            <h1 align="center" class="mt-5 py-3">新增報名課程</h1>
            <div id="addSchedule">
                <div class="row">
                    <div class="col-6">
                        <div class="col-6 mx-auto">
                            <label>課程：<span id="skillErr" style="color: red; display: none">錯誤</span></label>
                            <select id="groupCourse" name="groupCourse" class="form-select my-2">
                                <option value="">請選擇課程</option>
                                <c:forEach items="${groupCourses}" var="groupCourse">
                                    <option value="${groupCourse.gcNo}">
                                            ${groupCourse.classType.ctName} : ${groupCourse.skill.skillName}</option>
                                </c:forEach>
                            </select>
                            <label>訓練師：<span id="trainerErr" style="color: red; display: none">錯誤</span></label>
                            <select id="trainer" name="trainer" class="form-select my-2">
                                <option>請先選擇課程</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="col-6 mx-auto">
                            <label for="startDate">開始報名時間：<span id="startDateErr" style="color: red; display: none">錯誤</span></label>
                            <input type="date" name="startDate" id="startDate" class="form-control my-2"/>

                            <label for="endDate">結束報名時間：<span id="endDateErr" style="color: red; display: none">錯誤</span></label>
                            <input type="date" name="endDate" id="endDate" class="form-control my-2"/>
                        </div>
                    </div>
                    <div class="col-6 mx-auto">
                        <div class="col-6 mx-auto">
                            <label for="minLimit">最少開課人數：<span id="minLimitErr" style="color: red; display: none">錯誤</span></label>
                            <input type="number" name="minLimit" id="minLimit" class="form-control my-2">
                        </div>
                    </div>
                    <div class="col-6 mx-auto mb-3">
                        <div class="col-6 mx-auto">
                            <label for="maxLimit">最多開課人數：<span id="maxLimitErr" style="color: red; display: none">錯誤</span></label>
                            <input type="number" name="maxLimit" id="maxLimit" class="form-control my-2">
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="row">

                        <div class="col-10 mx-auto mb-3">
                                <label for="price">價格：<span id="priceErr" style="color: red; display: none">錯誤</span></label>
                                <input type="number" name="price" id="price" class="form-control my-2">
                        </div>
                        <div class="col-10 mx-auto mb-3">
                            <label for="price">延期的關聯課程編號：<span id="relatedGcsNoErr" style="color: red; display: none">錯誤</span></label>
                            <select name="relatedGcsNo" id="relatedGcsNo" class="form-select">
                            </select>
                        </div>
                        <div class="col-10 mx-auto mb-3">
                            <label for="delayReason">延期原因：<span id="delayReasonErr" style="color: red; display: none">錯誤</span></label>
                            <input type="input" name="delayReason" id="delayReason" class="form-control my-2">
                        </div>

                        <div class="text-center my-2">
                            <button type="button" id="next" class="btn btn-primary">下一步</button>
                            <button type="button" onclick="history.back()" class="btn btn-secondary">取消新增</button>
                        </div>
                    </div>
                </div>
            </div>
<%--            <div id="addScheduleDetail" style="display:none" class="col-6 mx-auto">--%>
<%--                <a id="addClassDate"><img src="${pageContext.request.contextPath}/webutil/icons/plus.svg" width="20px"/></a>--%>
<%--                <div id="input">--%>
<%--                    <div>--%>
<%--                        <label>新增上課日期：</label>--%>
<%--                        <input type="date" name="classDate" class="classDate form-control my-2" data-id="${1}">--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <button type="button" id="back" class="btn btn-secondary">返回上一步</button>--%>
<%--                <button type="submit" class="btn btn-primary">確定新增</button>--%>
<%--            </div>--%>
            <div id="calendar" style="display: none"><button type="button" onclick="addSchedule()">送出</button></div>

        </form>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">確定預約該日期嗎?</h5>`
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body text-center">
                <div id="showDate"></div>
                <div id="hideDate" style="display:none"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="myModal.hide();">取消</button>
                <button type="button" class="btn btn-primary" id="reserveBtn">確定預約</button>
            </div>
        </div>
    </div>
</div>


<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/getTrainerBySkill.js" ></script>
<script src="${pageContext.request.contextPath}/backend/course/js/addSchedule.js"></script>
<script></script>
</body>
</html>
