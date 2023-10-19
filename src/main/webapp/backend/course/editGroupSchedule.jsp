<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>修改報名資訊</h1>
<form action="${pageContext.request.contextPath}/schedule/modified" method="post" enctype="multipart/form-data">

  <input type="hidden" name="groupScheduleNo" value="${schedule.gcsNo}">
  <label>課程內容：</label>
  <select name="content">
    <c:forEach items="${groupCourses}" var="groupCourse">
      <option value="${groupCourse.courseContent}"
              <c:if test="${groupCourse.courseContent eq schedule.groupCourse.courseContent}">selected</c:if>
      >${groupCourse.courseContent}</option>
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

  <label for="endDate">開始報名時間：</label>
  <input type="date" name="startDate" id="endDate" value="${schedule.gcsEnd}"/>
  <br />

  <label for="minLimit">最少開課人數：</label>
  <input type="number" name="minLimit" id="minLimit">
  <br />

  <label for="maxLimit">最多開課人數：</label>
  <input type="number" name="maxLimit" id="maxLimit">
  <br />

  <label for="count">已報名人數</label>
  <input type="number" name="count" id="count">
  <br />

  <label for="price">價格</label>
  <input type="input" name="price" id="price">

  <label for="status"></label>
  <select name="status" id="status">
    <option value="0" <c:if test="${schedule.courseStatus eq 0}">selected</c:if>>上架</option>
    <option value="1" <c:if test="${schedule.courseStatus eq 1}">selected</c:if>>下架</option>
  </select>

  <button type="submit" onclick="window.location.href='../../../../../out/artifacts/woof_war_exploded/groupcourse'">確定修改</button>
  <button type="button" onclick="history.back()">取消修改</button>
</form>
</body>
</html>
