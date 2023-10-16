<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title>CLASS TYPE</title>
</head>
<div>
<jsp:useBean id="classTypeService" scope="page" class="com.woof.classtype.service.ClassTypeServiceImpl"/>
<form method="POST" ACTION="${pageContext.request.contextPath}/classType">
    <select name="Type" id="selectClass">
        <c:forEach var="classType" items="${classTypeService.allClassTypes}">
            <option  value="${classType.ctNo}">${classType.ctName}</option>
        </c:forEach>
    </select>
    <button type="button" id="button">提交</button>
</form>

<div class="container center-table">
    <div class="row">
        <table class="table table-striped text-center">
            <thead>
            <tr>
                <th>GC_NO</th>
                <th>SKILL_NAME</th>
                <th>CT_NAME</th>
                <th>COURSE_PHOTO</th>
                <th>COURSE_CONTENT</th>
                <th>COURSE_STATUS</th>
                <th>新增</th>
            </tr>
            </thead>
            <tbody id="mybody">
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
            </tbody>
        </table>
    </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script>
    document.getElementById("button").onclick = function (){
        fetchData();
    }
    async function fetchData(){
        const url = "${pageContext.request.contextPath}/classType?classType=" + document.getElementById("selectClass").value;

        try{
            const response = await fetch(url);
            if (!response.ok){
                throw new Error('Network response was not ok');
            }
            const data = await response.json();

            let td = document.createElement("td").innerText = data;


            let mybody= document.querySelector("tbody#mybody");
            mybody.innerHTML = data;

            console.log(mybody);
        }catch (error){
            console.error('Error', error);
        }
    }

</script>
</body>
</html>