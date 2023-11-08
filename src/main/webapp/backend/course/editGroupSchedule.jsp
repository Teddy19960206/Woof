<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <%@ include file="/backend/backhead.file" %>
  <title>寵毛導師 Woof | 修改報名資訊</title>
</head>
<body>

<%@ include file="/backend/backbody.file" %>

<c:if test="${not empty errorMsgs}">
  <div class="col-6 mx-auto text-center">
    <font style="color:red ;font-size: 20px">錯誤</font>
    <ul style="list-style:none">
      <c:forEach var="message" items="${errorMsgs}">
        <li style="color:red">${message}</li>
      </c:forEach>
    </ul>
  </div>
</c:if>


<h1 align="center" class="p-3">修改報名資訊</h1>
<form action="${pageContext.request.contextPath}/schedule/modified" method="post" enctype="multipart/form-data">
  <div class="col-6 mx-auto">
    <input type="hidden" id="scheduleNo" name="scheduleNo" value="${schedule.gcsNo}">

    <input type="hidden" id="related" name="scheduleNo" value="${schedule.relatedGcsNo.gcsNo}">
    <div class="row">
      <div class="col-6">
        <label>課程：</label>
        <select id="skill" name="skill" class="form-select">
          <c:forEach items="${groupCourses}" var="course">
            <option value="${course.gcNo}"
                    <c:if test="${course.skill.skillNo eq schedule.groupCourse.skill.skillNo}">selected</c:if>
            >${course.classType.ctName} : ${course.skill.skillName}</option>
          </c:forEach>
        </select>
      </div>
      <div class="col-6">
        <label>訓練師：</label>
        <select id="trainer" name="trainer" class="form-select">
          <c:forEach items="${trainers}" var="trainer">
            <option value="${trainer.trainerNo}" <c:if test="${trainer.trainerNo eq schedule.trainer.trainerNo}">selected</c:if>>
                ${trainer.administrator.adminName}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="row">
      <div class="col-6">
        <label for="startDate">開始報名時間：</label>
        <input type="date" name="startDate" id="startDate" value="${schedule.gcsStart}" class="form-control"/>
      </div>
      <div class="col-6">
        <label for="endDate">結束報名時間：</label>
        <input type="date" name="endDate" id="endDate" value="${schedule.gcsEnd}" class="form-control"/>
      </div>
    </div>

    <div class="row">
      <div class="col-6">
        <label for="minLimit">最少開課人數：</label>
        <input type="number" name="minLimit" id="minLimit" value="${schedule.minLimit}" class="form-control">

      </div>
      <div class="col-6">
        <label for="maxLimit">最多開課人數：</label>
        <input type="number" name="maxLimit" id="maxLimit" value="${schedule.maxLimit}" class="form-control">
      </div>
    </div>

    <label for="regCount">已報名人數：</label>
    <input type="number" name="regCount" id="regCount" value="${schedule.regCount}" class="form-control">

    <label for="price">價格：</label>
    <input type="text" name="price" id="price" value="${schedule.gcsPrice}" class="form-control">

    <label for="status">課程報名狀態：</label>
    <select name="status" id="status" class="form-select">
      <option value="0" <c:if test="${schedule.gcsStatus eq 0}">selected</c:if>>下架</option>
      <option value="1" <c:if test="${schedule.gcsStatus eq 1}">selected</c:if>>上架</option>
    </select>

    <label for="price">延期的關聯課程編號：</label>
    <select name="relatedGcsNo" id="relatedGcsNo" class="form-select">
    </select>

    <label for="price">延期原因：</label>
    <input type="text" name="delayReason" id="delayReason" class="form-control my-2" value="${schedule.gcsDelayReason}">

    <div class="text-center mt-3">
      <button type="submit" class="btn btn-primary">確定修改</button>
      <button type="button" onclick="history.back()" class="btn btn-secondary">取消修改</button>
    </div>
  </div>
</form>

<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/getTrainerBySkill.js" ></script>
</body>
</html>
