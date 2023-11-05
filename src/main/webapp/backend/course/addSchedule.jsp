<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 新增報名資訊</title>

</head>
<body>
<h1>新增報名課程</h1>
<form action="${pageContext.request.contextPath}/schedule/addSchedule" method="post">

    <div id="addSchedule">
        <label>課程：</label>
        <select id="skill" name="groupCourse">
            <option value="0">請選擇課程</option>
            <c:forEach items="${groupCourses}" var="groupCourse">
                <option value="${groupCourse.gcNo}">
                        ${groupCourse.classType.ctName} : ${groupCourse.skill.skillName}</option>
            </c:forEach>
        </select>
        <br/>

        <label>訓練師：</label>
        <select id="trainer" name="trainer">
            <option>請先選擇課程</option>
        </select>
        <br/>

        <label for="startDate">開始報名時間：</label>
        <input type="date" name="startDate" id="startDate"/>
        <br/>

        <label for="endDate">結束報名時間：</label>
        <input type="date" name="endDate" id="endDate"/>
        <br/>

        <label for="minLimit">最少開課人數：</label>
        <input type="number" name="minLimit" id="minLimit">
        <br/>

        <label for="maxLimit">最多開課人數：</label>
        <input type="number" name="maxLimit" id="maxLimit">
        <br/>

        <label for="price">價格：</label>
        <input type="input" name="price" id="price">
        <br/>

        <label for="price">延期原因：</label>
        <input type="input" name="price" id="delayReason">
        <br/>

        <label for="price">延期的關聯表格編號：</label>
        <input type="input" name="price" id="relatedGcsNo">
        <br/>

        <button type="button" id="next">下一步</button>
        <button type="button" onclick="history.back()">取消新增</button>
    </div>
    <div id="addScheduleDetail" style="display:none">
        <button type="button" id="addClassDate">+</button>
        <div id="input">
            <div>
                <label>新增上課日期：</label>
                <input type="date" name="classDate" class="classDate" data-id="${1}">
            </div>
        </div>
        <button type="button" id="back">返回上一步</button>
        <button type="submit">確定新增</button>
    </div>
</form>
<script src="${pageContext.request.contextPath}/webutil/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/backend/course/js/getTrainerBySkill.js" ></script>
<script src="${pageContext.request.contextPath}/backend/course/js/addSchedule.js"></script>
</body>
</html>
