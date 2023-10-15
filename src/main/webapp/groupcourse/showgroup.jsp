<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <title>Insert title here</title>
    <style>
        /* 將表格置中 */
        table {
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div class="container center-table">
    <div class="row">
    <table class="table table-striped text-center">
        <tr>
            <th>GC_NO</th>
            <th>SKILL_NAME</th>
            <th>CT_NAME</th>
            <th>COURSE_PHOTO</th>
            <th>COURSE_CONTENT</th>
            <th>COURSE_STATUS</th>
            <th>新增</th>
        </tr>
        <c:if test="${not empty groupCourseByCtNo}">
            <!-- 在這裡訪問groupCourseByCtNo集合 -->
            <c:forEach var="course" items="${groupCourseByCtNo}">
                <tr>
                    <!-- 顯示數據 -->
                    <td>${course.gcNo}</td>
                    <td>${course.skill.skillName}</td>
                    <td>${course.classType.ctName}</td>
                    <td>
                        <c:if test="${not empty course.coursePhoto}">
                        <img src="data:image/png;base64,${Base64.getEncoder().encodeToString(course.coursePhoto)}" style="width: 100px" />
                        </c:if>
                    </td>
                    <td>${course.courseContent}</td>
                    <td>
                        <c:if test="${course.courseStatus == 0}">下架</c:if>
                        <c:if test="${course.courseStatus == 1}">上架</c:if>
                    </td>
                    <td><button type="button">按鈕</button><td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
