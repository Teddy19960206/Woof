<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<%@ include file="/backend/backhead.file" %>
<title>寵毛導師 Woof | 訓練師專區</title>

<style>
a[name="adminNo"] {
	padding-top: 50px;
    font-family: 'Arial', sans-serif; /* 設置字體 */
    color: #ffffff; /* 文字顏色 */
    background-color: #faba91; /* 背景顏色 */
    padding: 10px 15px; /* 內邊距 */
    text-decoration: none; /* 移除文字下劃線 */
    border-radius: 4px; /* 邊框圓角 */
    transition: all 0.3s ease; /* 添加過渡效果 */
}

a[name="adminNo"]:hover {
    background-color: #ffc107; /* 滑鼠懸停時的背景顏色 */
    color: #ffffff; /* 滑鼠懸停時的文字顏色 */
}
.myBtn{
	font-family: 'Arial', sans-serif; /* 設置字體 */
	color: black; /* 文字顏色 */
	background-color: #faba91; /* 背景顏色 */
	padding: 5px 10px 5px 10px; /* 內邊距 */
	text-decoration: none; /* 移除文字下劃線 */
	border-radius: 4px; /* 邊框圓角 */
	transition: all 0.3s ease; /* 添加過渡效果 */
}
.myBtn:hover{
	background-color: #ffc107; /* 滑鼠懸停時的背景顏色 */
	color: black; /* 滑鼠懸停時的文字顏色 */
}

</style>


<script type="text/javascript">
//表單點擊找出對應的function//
function processUpdate2(jsonData){
	  window.location.href = " <%=request.getContextPath()%>/frontend/administrator/administratorUpdate2.jsp?adminNo=" + jsonData.adminNo ;
  }
</script>
</head>
<body>
<%@ include file="/backend/backbody.file" %>


	<div class="container py-5">
		<nav class="navbar navbar-expand-lg navbar-light mx-auto">
			<a class="navbar-brand" href="#">訓練師中心</a>
		</nav>
		<div class="row">
			<div class="col-12 col-md-4">
				<div class="card">
					<div class="card-header">
						<!-- 再次使用 sessionScope 來獲取用戶名 -->
						您好，
						<c:out value="${administrator.adminName}！" />
					</div>
					 <ul class="list-group list-group-flush py-3">
						 <li class="list-group-item">
							 <form method="post" action="${pageContext.request.contextPath}/administrator.do" style="margin-bottom: 0px;">
								 <input type="hidden" name="action" value="update2">
							     <input type="hidden" name="adminNo" value="${administrator.adminNo}">
							     <a name="adminNo" onclick="processUpdate2({adminNo:'${administrator.adminNo}'});">修改基本資料</a>
							     <div class="mt-3">
									 <a href="${pageContext.request.contextPath}/backend/trainer/skillList.jsp">
										 <button type="button" class="btn myBtn">技能清單</button>
									 </a>
								 </div>
						     </form>
     					 </li>
					 </ul>
				</div>
			</div>
			<div class="col-12 col-md-8">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">個人資料</h5>
						<table class="table table-bordered table-hover">
							<tbody>
								<tr>
									<th scope="row">編號</th>
									<td>${administrator.adminNo}</td>
								</tr>
								<tr>
									<th scope="row">姓名</th>
									<td>${administrator.adminName}</td>
								</tr>
								<tr>
									<th scope="row">性別</th>
									<td>${administrator.adminGender}</td>
								</tr>
								<tr>
									<th scope="row">照片</th>
									<td><img
									src="${pageContext.request.contextPath}/DBPngReader?action=administrator&id=${administrator.adminNo}" 
										class="img-thumbnail" style="width: 100px; height: 100px">
									</td>
								</tr>
								<tr>
									<th scope="row">信箱</th>
									<td>${administrator.adminEmail}</td>
								</tr>
							
								<tr>
									<th scope="row">電話</th>
									<td>${administrator.adminTel}</td>
								</tr>
								<tr>
									<th scope="row">地址</th>
									<td>${administrator.adminAddress}</td>
								</tr>
								<tr>
									<th scope="row">生日</th>
									<td>${administrator.adminBd}</td>
								</tr>
								<tr>
									<th scope="row">緊急聯絡人姓名</th>
									<td>${administrator.emergencyContactName}</td>
								</tr>
								<tr>
									<th scope="row">緊急連絡人電話</th>
									<td>${administrator.emergencyContactel}</td>
								</tr>
								<tr>
									<th scope="row">到職日</th>
									<td>${administrator.adminHd}</td>
								</tr>
								
								<tr>
									<th scope="row">狀態</th>
									<td><c:choose>
					<c:when test="${administrator.adminStatus == 2}">
						<span class="status-label status-suspended">停權</span>
					</c:when>
					<c:when test="${administrator.adminStatus == 1}">
						<span class="status-label status-normal">在職</span>
					</c:when>
					<c:when test="${administrator.adminStatus == 0}">
						<span class="status-label status-nonvalid">離職</span>
					</c:when>
				</c:choose></td>
								</tr>
								<tr>
									<th scope="row">功能權限</th>
									<td><c:choose>
					<c:when test="${administrator.adminFuncName == 0}">
						<span class="status-label status-suspended">無功能</span>
					</c:when>
					<c:when test="${administrator.adminFuncName == 1}">
						<span class="status-label status-normal">管理員</span>
					</c:when>
					<c:when test="${administrator.adminFuncName == 2}">
						<span class="status-label status-normal">訓練師</span>
					</c:when>
				</c:choose></td>
								</tr>
							</tbody>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>

	<%@ include file="/backend/backfoot.file" %>
</body>
</html>
