<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>修改報名資訊</h1>
<form action="groupcourse?action=modified" method="post" enctype="multipart/form-data">

  <input type="hidden" name="groupCourseNo" value="${groupCourse.gcNo}">
  <label>技能名稱：</label>
  <select name="skill">
    <c:forEach items="${skills}" var="skill">
      <option value="${skill.skillNo}"
              <c:if test="${skill.skillNo eq groupCourse.skill.skillNo}">selected</c:if>
      >${skill.skillName}</option>
    </c:forEach>
  </select>
  <br />

  <label>班別：</label>
  <select name="classType">
    <c:forEach items="${classTypes}" var="classType">
      <option value="${classType.ctNo}" <c:if test="${classType.ctNo eq groupCourse.classType.ctNo}">selected</c:if>>
          ${classType.ctName}</option>
    </c:forEach>
  </select>
  <br />

  <label>圖片：</label>
  <input type="file" name="photo" accept="image/*">

  <img src="${pageContext.request.contextPath}/DBPngReader?action=groupCourse&id=${groupCourse.gcNo}"
       onerror="this.style.display='none'"/>

  <br />

  <label>課程內容：</label>
  <textarea name="content" >${groupCourse.courseContent}</textarea>
  <br />

  <label>課程狀態：</label>
  <select name="status">
    <option value="0" <c:if test="${groupCourse.courseStatus eq 0}">selected</c:if>>上架</option>
    <option value="1" <c:if test="${groupCourse.courseStatus eq 1}">selected</c:if>>下架</option>
  </select>
  <br />
  <button type="submit" onclick="window.location.href='../../../../../out/artifacts/woof_war_exploded/groupcourse'">確定修改</button>
  <button type="button" onclick="history.back()">取消修改</button>
</form>
</body>
</html>