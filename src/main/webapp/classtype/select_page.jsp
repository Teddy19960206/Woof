<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>CLASS TYPE</title>
</head>


<%-- 取得所有 classtype 顯示在select裡，讓使用者可以選擇 --%>
<jsp:useBean id="classTypeService" scope="page" class="com.woof.classtype.service.ClassTypeServiceImpl"/>
<form>
    <select name="Type" id="selectClass">
        <c:forEach var="classType" items="${classTypeService.allClassTypes}">
            <option  value="${classType.ctNo}">${classType.ctName}</option>
        </c:forEach>
    </select>
    <button type="button" id="button">提交</button>
</form>

<%-- 取得團體課程列表 --%>
<div class="container center-table">
    <div class="row">
    </div>
</div>

<%-- 新增團體課程 --%>
<a href="${pageContext.request.contextPath}/groupcourse?action=addpage"><button>新增團體課程</button></a>
<a href="${pageContext.request.contextPath}/groupcourse/schedule?action=getAll"><button>查看報名資訊</button></a>



<script src="${pageContext.request.contextPath}/webutil/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/classtype/js/getGroup.js"></script>
</body>
</html>