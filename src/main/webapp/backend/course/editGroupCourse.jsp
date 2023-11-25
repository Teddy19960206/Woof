<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>寵毛導師 Woof | 課程內容修改</title>
    <%@ include file="/backend/backhead.file" %>
</head>
<style>
    #preview {
        border: 1px solid lightgray;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 400px;
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
    .uploadImage{
        background-color: #0d6efd;
        border-radius: 10px;
        padding: 8px 15px;
        color: white;
        /*width: 100px;*/
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
<body>

<%@ include file="/backend/backbody.file" %>

<div class="container  p-5">
    <h1 align="center">課程內容修改</h1>
    <div class="mt-5">
        <form action="${pageContext.request.contextPath}/groupcourse/modified" method="post" enctype="multipart/form-data">

            <div class="row">
                <div class="col-6">
                    <div class="col-6 mx-auto">
                        <input type="hidden" name="groupCourseNo" value="${groupCourse.gcNo}">
                        <label class="m-3">技能名稱</label>
                        <select name="skill" class="form-select" style="width: 300px">
                            <c:forEach items="${skills}" var="skill">
                                <option value="${skill.skillNo}"
                                        <c:if test="${skill.skillNo eq groupCourse.skill.skillNo}">selected</c:if>
                                >${skill.skillName}</option>
                            </c:forEach>
                        </select>

                        <label class="m-3">班別</label>
                        <select name="classType" class="form-select" style="width: 300px">
                            <c:forEach items="${classTypes}" var="classType">
                                <option value="${classType.ctNo}"
                                        <c:if test="${classType.ctNo eq groupCourse.classType.ctNo}">selected</c:if>>
                                        ${classType.ctName}</option>
                            </c:forEach>
                        </select>
                        <label class="m-3">圖片：</label>
                        <div class="col-6 mx-auto">
                            <label for="p_file" class="uploadImage">選擇圖片</label>
                            <input type="file" name="photo" value="" id="p_file" hidden="hidden">
                        </div>

                        <label class="m-3">課程狀態：</label>
                        <select name="status" class="form-select">
                            <option value="0" <c:if test="${groupCourse.courseStatus eq 0}">selected</c:if>>下架</option>
                            <option value="1" <c:if test="${groupCourse.courseStatus eq 1}">selected</c:if>>上架</option>
                        </select>
                    </div>
                </div>

                <div class="col-6">
                    <div class="col-6 mx-auto">
                        <div class="row">
                            <label>圖片預覽</label>
                        </div>
                        <div id="preview">
                            <span class="text">預覽圖</span>
                            <img id="photo" class="preview_img"
                            src="${pageContext.request.contextPath}/DBPngReader?action=groupCourse&id=${groupCourse.gcNo}"
                            onerror="this.style.display='none'" />
                        </div>
                    </div>
                </div>

                <div class="col-12 mt-5 text-center">
                    <label>課程內容：</label>
                    <div class="offset-2 col-8 mx-auto">
                        <div class="form-floating mx-auto">
                          <textarea name="content" class="form-control" style="height: 100px;">${groupCourse.courseContent}</textarea>
                      </div>
                    </div>
                </div>
                <div class="mt-5 text-center ">
                    <button type="submit" class="btn btn-primary">確定修改</button>
                    <button type="button" onclick="history.back()" class="btn btn-secondary">取消修改</button>
                    <button type="button" id="delete" data-id="${groupCourse.gcNo}" class="btn btn-danger">刪除圖片</button>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file="/backend/backfoot.file" %>
<script src="${pageContext.request.contextPath}/backend/course/js/editGroup.js"></script>
</body>
</html>
