<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <title>促銷活動管理</title>
</head>
<body>

<h1>促銷活動</h1>

    <form method="Post">                       <!--我註冊的路徑servlet   後面的getAll自己取名對應servlet-->
	<a href="${pageContext.request.contextPath}/promotionactivity?action=getAll">查詢所有促銷活動</a>
	</form>

<h1>新增促銷活動資訊</h1>

	 <form method="Post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/promotionactivity?action=add">
    <label>活動名稱：</label>
    <input type="text" name="paName" />
    <br />

    <label>活動折扣：</label>
    <input type="text" name="paDiscount" />
    <br />

    <label>活動內容：</label>
    <textarea name="paContent"></textarea>
    <br />

    <label>活動開始日期：</label>
    <input type="date" name="paStart" />
    <br />

    <label>活動結束日期：</label>
    <input type="date" name="paEnd" />
    <br />

    <label>活動狀態：</label>
    <select name="paStatus">
        <option value="true">上架</option>
        <option value="false">下架</option>
        
        <input type="submit" value="新增">
    </form>



</body>
</html>