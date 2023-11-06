<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="/backend/backhead.file" %>
    <title>寵毛導師 Woof | 新增團體內容課程</title>
    <style>
        select{
            text-align: center;
        }

        select > option{
            text-align: center;
        }

        #preview {
            border: 1px solid lightgray;
            display: inline-block;
            width: 300px;
            min-height: 400px;
            position: relative;
        }
        #preview span.text {
            position: absolute;
            display: inline-block;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            z-index: -1;
            color: lightgray;
        }
        #preview img.preview_img {
            width: 100%;
            text-align: center;
        }

        select{
            text-align: center;
        }
        select > option{
            text-align: center;
        }
        label{
            font-size: 20px;
        }



    </style>
</head>
<body>
<%@ include file="/backend/backbody.file" %>

<div class="container">
    <div class="row">
        <h1 align="center" class="mt-3">新增團體課程</h1>
        <form action="${pageContext.request.contextPath}/groupcourse/addgroup" method="post" enctype="multipart/form-data" class="form-floating">
            <div class="col-6 mx-auto my-2">
                <label>技能名稱：</label>
                <select name="skill" class="form-select">
                    <c:forEach items="${skills}" var="skill">
                        <option value="${skill.skillNo}">${skill.skillName}</option>
                    </c:forEach>
                </select>


                <label>班別：</label>
                <select name="classType" class="form-select">
                    <c:forEach items="${classTypes}" var="classType">
                        <option value="${classType.ctNo}">${classType.ctName}</option>
                    </c:forEach>
                </select>

            </div>

            <div class="col-6 mx-auto">
                <div class="col-6 mx-auto">
                    <div class="row">
                        <label>圖片預覽</label>
                    </div>
                    <div id="preview" class="text-center">
                        <span class="text">預覽圖</span>
                        <img id="photo" class="preview_img"
                             src="${pageContext.request.contextPath}/DBPngReader?action=groupCourse&id=${groupCourse.gcNo}"
                             onerror="this.style.display='none'" />
                    </div>
                </div>
            </div>
            <div class="col-6 mx-auto text-center my-5">
                <label for="p_file" class="btn btn-primary">選擇圖片</label>
                <input type="file" name="photo" value="" id="p_file" hidden="hidden">
                <button type="button" class="btn btn-danger" onclick="deletePhoto()">取消圖片</button>
            </div>

            <div class="col-6 mx-auto my-2">
                <label>課程內容：</label>
                <textarea name="content" class="form-control" style="height: 20vh"></textarea>
            </div>
            <div class="col-6 mx-auto text-center my-2">
                <button type="submit" class="btn btn-primary">新增</button>
                <button type="button" onclick="history.back()" class="btn btn-secondary">取消新增</button>
            </div>
        </form>
    </div>
</div>
<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/addGroupCourse.js"></script>
</body>
</html>
