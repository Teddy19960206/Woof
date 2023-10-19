<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>修改報名資訊</title>
</head>
<body>
<h1>修改報名資訊</h1>
<form action="${pageContext.request.contextPath}/schedule/modified" method="post" enctype="multipart/form-data">

  <input type="hidden" name="scheduleNo" value="${schedule.gcsNo}">
  <label>課程：</label>
  <select name="skill">
    <c:forEach items="${groupCourses}" var="groupCourse">
      <option value="${groupCourse.gcNo}"
              <c:if test="${groupCourse.skill.skillNo eq schedule.groupCourse.skill.skillNo}">selected</c:if>
      >${groupCourse.classType.ctName} : ${groupCourse.skill.skillName}</option>
    </c:forEach>
  </select>
  <br />

  <label>訓練師：</label>
  <select name="trainer">
    <c:forEach items="${trainers}" var="trainer">
      <option value="${trainer.trainerNo}" <c:if test="${trainer.trainerNo eq schedule.trainer.trainerNo}">selected</c:if>>
          ${trainer.trainerNo}</option>
    </c:forEach>
  </select>
  <br />

  <label for="startDate">開始報名時間：</label>
  <input type="date" name="startDate" id="startDate" value="${schedule.gcsStart}"/>
  <br />

  <label for="endDate">結束報名時間：</label>
  <input type="date" name="endDate" id="endDate" value="${schedule.gcsEnd}"/>
  <br />

  <label for="minLimit">最少開課人數：</label>
  <input type="number" name="minLimit" id="minLimit" value="${schedule.minLimit}">
  <br />

  <label for="maxLimit">最多開課人數：</label>
  <input type="number" name="maxLimit" id="maxLimit" value="${schedule.maxLimit}">
  <br />

  <label for="count">已報名人數：</label>
  <input type="number" name="count" id="count" value="${schedule.count}">
  <br />

  <label for="price">價格：</label>
  <input type="input" name="price" id="price" value="${schedule.gcsPrice}">

  <label for="status">課程報名狀態：</label>
  <select name="status" id="status">
    <option value="0" <c:if test="${schedule.gcsStatus eq 0}">selected</c:if>>上架</option>
    <option value="1" <c:if test="${schedule.gcsStatus eq 1}">selected</c:if>>下架</option>
  </select>

  <button type="submit">確定修改</button>
  <button type="button" onclick="history.back()">取消修改</button>
</form>
</body>
</html>
