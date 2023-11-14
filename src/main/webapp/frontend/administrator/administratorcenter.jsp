<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<%@ include file="/backend/backhead.file" %>
<title>管理員中心</title>




<script type="text/javascript">
//表單點擊找出對應的function//
function processUpdate2(jsonData){
	  window.location.href = " <%=request.getContextPath()%>/frontend/administrator/administratorUpdate2.jsp?adminNo=" + jsonData.adminNo ;
  }
</script>
</head>
<body>
<%@ include file="/backend/backbody.file" %>
	<nav class="navbar navbar-expand-lg navbar-light">
		<a class="navbar-brand" href="#">訓練師中心</a>
	</nav>

	<div class="container mt-5">
		<div class="row">
			<div class="col-12 col-md-4">
				<div class="card">
					<div class="card-header">
						<!-- 再次使用 sessionScope 來獲取用戶名 -->
						您好，
						<c:out value="${administrator.adminName}！" />
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">
							<button value="修改" onclick="processUpdate2({adminNo:'${administrator.adminNo}'});">修改</button>
						</li>
<!-- 						<li class="list-group-item">變更密碼</li> -->
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
<%-- 										src="${pageContext.request.contextPath}/DBPngReader?action=administrator&id=${administrator.adminNo}" --%>
										class="img-thumbnail" style="width: 100px; height: 100px">
									</td>
								</tr>
								<tr>
									<th scope="row">信箱</th>
									<td>${administrator.adminEmail}</td>
								</tr>
								<tr>
									<th scope="row">密碼</th>
									<td>${administrator.adminPassword}</td>
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
									<td>${administrator.adminStatus}</td>
								</tr>
								<tr>
									<th scope="row">功能權限</th>
									<td>${administrator.adminFuncName}</td>
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
