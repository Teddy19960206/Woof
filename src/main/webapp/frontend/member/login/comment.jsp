<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/meta.file"%>
<title>寵毛導師 Woof | 預約單評論</title>
<%@ include file="body.jsp"%>
<script type="text/javascript">
//表單點擊找出對應的function//
  function processUpdate(jsonData){
   window.location.href = " <%=request.getContextPath()%>/frontend/member/login/updatemember.jsp?memNo=" + jsonData.memNo ;
  }
</script>

<style>
body {
	background-color: #FFF3E0;
}

.card {
	border: none;
	border-radius: 20px;
	background-color: #f8f9fa;
}

.card-header{
	border: none;
	border-radius: 20px;
	background-color:SandyBrown;
}

.profile-img {
	width: 100px;
	height: 100px;
	border-radius: 50%;
}

.icon-btn {
	font-size: 1.5rem;
	color: #495057;
}

.icon-btn:hover {
	color: #0056b3;
}

.update-btn {
	width: 50px; /* or whatever width you want */
}

.list-group-item {
    padding: 10px 15px;
    border: none;
    border-bottom: 1px solid #ddd;
    background-color: #f8f9fa;
    cursor: pointer;
}

.list-group-item:hover {
    background-color: #e2e6ea; /* 輕微改變背景色 */
}

.list-group-item a {
    color: #007bff; /* 連結的顏色 */
    text-decoration: none; /* 移除底線 */
}

.list-group-item a:hover {
    color: #0056b3; /* 懸停時的顏色 */
}
/* 子選單樣式 */
#orderManagementOptions,
#courseManagementOptions,
#shopManagementOptions {
	padding-left: 0px;
	background-color: orange;
}

.left-icon {
	text-align: left;
	margin-right: 20px;
}

.custom-divider {
	margin-top: 0px;
	margin-bottom: 0px;
	border: 0;
	border-top: 1px solid #ccc;
}

a {
	color: inherit;
	text-decoration: none;
}

.accordion-button:not(.collapsed) {
	background-color: #ffaa77;
	color: #333;
}

.accordion-button:hover {
	background-color: #ffbb88;
	color: #333;
}
.col-md-8 {
    padding: 20px;
}

.card {
    border: none;
    border-radius: 20px;
    background-color: #f8f9fa;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* 加入陰影效果 */
}

.card-body {
    padding: 30px;
}

.card-title {
    margin-bottom: 20px;
    font-size: 1.8rem;
    font-weight: bold;
    color: #333;
}

textarea {
    width: 100%;
    padding: 10px;
    border-radius: 8px;
    border: 1px solid #ccc;
    font-size: 1rem;
    resize: vertical;
    margin-bottom: 20px;
}

button[type="submit"] {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    font-size: 1rem;
    font-weight: bold;
    color: #fff;
    background-color: #007bff;
    cursor: pointer;
    transition: background-color 0.3s ease-in-out;
}

button[type="submit"]:hover {
    background-color: #0056b3;
}
.btn-back {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    font-size: 1rem;
    font-weight: bold;
    color: #fff;
    background-color: #6c757d;
    cursor: pointer;
    transition: background-color 0.3s ease-in-out;
    margin-top: 20px;
}

.btn-back:hover {
    background-color: #495057;
}
</style>
</head>
<script type="text/javascript">
    function toggleOrderManagement() {
        var orderoptions = document.getElementById('orderManagementOptions');
        toggleDisplay(orderoptions);
    }

    function toggleCourseManagement() {
        var courseOptions = document.getElementById('courseManagementOptions');
        var shopOrderOptions = document.getElementById('shopManagementOptions');
        toggleDisplay(courseOptions);
    }
    function toggleShopOrderManagement() {
        var shopOrderOptions = document.getElementById('shopManagementOptions');
        var courseOptions = document.getElementById('courseManagementOptions');
        toggleDisplay(shopOrderOptions);
    }

    function toggleDisplay(element) {
        if (element.style.display === 'none') {
            element.style.display = 'block';
        } else {
            element.style.display = 'none';
        }
    }
</script>
<script src="https://kit.fontawesome.com/3f37e88a3b.js" crossorigin="anonymous"></script>
<body>
	<div class="col-12 col-md-8">
		<div class="card">
        	<div class="card-body">
            	<h3 class="card-title text-center p-2">私人預約單評論</h3>
            		<form method="POST" action="${pageContext.request.contextPath}/privatetrainingappointmentform?action=updatecomment">
        				<textarea name="comment" rows="5" placeholder="請輸入評論內容" required>${param.ptaComment}</textarea>
        				<input type="hidden" name="ptaNo" value="${param.ptaNo}">					
        				<button type="submit">提交評論</button>
    				</form>
    				<button class="btn btn-back" onclick="history.back()">返回</button>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
