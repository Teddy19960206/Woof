<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charest=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<title>寵毛導師 Woof | 最新消息修改</title>
<script>

function formatDate(timestamp) {
    var date = new Date(parseInt(timestamp));
    var year = date.getFullYear();
    var month = ('0' + (date.getMonth() + 1)).slice(-2);
    var day = ('0' + date.getDate()).slice(-2);
    var hours = ('0' + date.getHours()).slice(-2);
    var minutes = ('0' + date.getMinutes()).slice(-2);
    var seconds = ('0' + date.getSeconds()).slice(-2);
    return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
}

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
});






</script>
</head>
<body>
   <form method="post" action="<%=request.getContextPath()%>/latestNews.do?action=update" enctype="multipart/form-data">
   <table>
    <tr>
   <th>消息編號</th>
   <td> <input type="text" name="LN_NO" id="LN_NO" value="LN_NO" readonly>
   </tr>
   <tr>
   <th>消息標題</th>
   <td><input type="text" name="LN_TITLE" id="LN_TITLE"></td>
   </tr>
   <tr>
   <th>消息內容</th>
   <td><input type="text" name="LN_CONTENT" id="LN_CONTENT">
   </td>
   </tr>
   <tr>
    <th>消息時間</th>
   <th><input type="text" name="LN_TIME" id="LN_TIME"></th>
   </tr>
   <!-- 	顯示大頭貼的DIV -->
<!-- 	<img> -->
	  <div>個人大頭貼</div>
   <img src="#" id="myphoto" name="myphoto" accept="image/*">
<input type="file" id="LN_PHOTO" name="LN_PHOTO" style="display:none;" accept="image/*">
<input type="button" value="修改照片" onclick="$('#LN_PHOTO').click();" >

 
   </table>
   <button type="submit">送出</button>
   <input type="button" onclick="window.location.href='<%=request.getContextPath()%>/backend/latestnews/latestNews.jsp'"
   value="取消">
   </form>



</body>
</html>