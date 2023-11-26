<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charest=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <style>
        body {
            padding-top: 20px;
        }
        .container {
            max-width: 600px;
        }
        .error-msg {
            font-size: 0.9em;
        }
        .form-group {
            margin-bottom: 15px;
        }
        img#myphoto {
            width: 50%; /* or any custom size */
            height: auto;
            margin-bottom: 15px;
        }
    </style>
<title>寵毛導師 Woof | 最新消息修改</title>
<script>


$(document).ready(function(){
    $.ajax({
        type: "POST",
        data: {LN_NO: '<%= request.getParameter("lnNo") %>'},
        url: "<%=request.getContextPath()%>/latestNews.do?action=query",
        success: function(data){
            var jsonObj = JSON.parse(data);
            $('#LN_NO').val(jsonObj.lnNo);
            $('#LN_TITLE').val(jsonObj.lnTitle);
            $('#LN_CONTENT').val(jsonObj.lnContent);
            // 使用 formatDate 函數來轉換時間戳記並設置值
            $('#LN_TIME').val(formatDate(jsonObj.lnTime));
            $('#myphoto').attr('src', "${pageContext.request.contextPath}/DBPngReader?action=latestnews&id="+jsonObj.lnNo);
        },
        error: function(xhr, ajaxOptions, thrownError){
            alert("挖 錯了");
        }
    });
 // 預覽選擇的圖片
    $("#LN_PHOTO").change(function() {
        if (this.files && this.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#myphoto').attr('src', e.target.result);
            }
            reader.readAsDataURL(this.files[0]);
        }
    });
 
    function formatDate(dateStr) {
        var date = new Date(dateStr);
        var year = date.getFullYear();
        var month = date.getMonth() + 1; // 月份是從 0 開始的
        var day = date.getDate();

        // 確保月份和日期始終是兩位數
        month = (month < 10) ? '0' + month : month;
        day = (day < 10) ? '0' + day : day;

        return year + '-' + month + '-' + day;
    }
});








</script>
</head>
<body>
<div class="container">
    <form method="post" action="<%=request.getContextPath()%>/latestNews.do?action=update" enctype="multipart/form-data">
        <div class="form-group">
            <label for="LN_NO">消息編號</label>
            <input type="text" class="form-control" name="LN_NO" id="LN_NO" value="LN_NO" readonly>
        </div>
        <div class="form-group">
            <label for="LN_TITLE">消息標題</label>
            <input type="text" class="form-control" name="LN_TITLE" id="LN_TITLE">
            <small style="color: red;" class="error-msg">${errorMsgs.LN_TITLE}</small>
        </div>
        <div class="form-group">
            <label for="LN_CONTENT">消息內容</label>
            <textarea class="form-control" name="LN_CONTENT" id="LN_CONTENT" rows="5" placeholder="請輸入消息內容"></textarea>
            <small style="color: red;" class="error-msg">${errorMsgs.LN_CONTENT}</small>
        </div>
        <div class="form-group">
            <label for="myphoto">個人大頭貼</label>
            <img src="#" id="myphoto" name="myphoto" class="img-thumbnail" accept="image/*" style="width: 200px; height: 200px">
            <input type="file" id="LN_PHOTO" name="LN_PHOTO" class="form-control-file" style="display:none;" accept="image/*">
            <button type="button" class="btn btn-info mt-2" onclick="$('#LN_PHOTO').click();">修改照片</button>
        </div>
        <button type="submit" class="btn btn-primary">送出</button>
        <button type="button" class="btn btn-secondary" onclick="window.location.href='<%=request.getContextPath()%>/backend/latestnews/latestNews.jsp'">取消</button>
    </form>
</div>



</body>
</html>