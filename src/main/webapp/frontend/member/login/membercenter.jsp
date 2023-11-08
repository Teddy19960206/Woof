<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員中心</title>
<script type="text/javascript">
//表單點擊找出對應的function//
  function processUpdate(jsonData){
   window.location.href = " <%=request.getContextPath()%>/frontend/member/login/updatemember.jsp?memNo=" + jsonData.memNo ;
  }
</script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.4.1/font/bootstrap-icons.min.css">
<style>
body {
	background-color: #FFF3E0;
}

.card, .navbar, .navbar-collapse {
	background-color: sandybrown;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light">
		<a class="navbar-brand" href="#">會員中心</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="#">首頁
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">個人資料管理</a></li>
				<li class="nav-item"><a class="nav-link" href="#">訂單管理</a></li>
				<li class="nav-item">
					<form method="POST"
						action="${pageContext.request.contextPath}/login"
						style="all: unset;">
						<input type="hidden" name="action" value="memberlogout">
						<button class="nav-link btn btn-link"
							style="color: inherit; text-decoration: none;" id="logoutButton"
							type="submit">登出</button>
					</form>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container mt-5">
		<div class="row">
			<div class="col-12 col-md-4">
				<div class="card">
					<div class="card-header">
						<!-- 再次使用 sessionScope 來獲取用戶名 -->
						您好，
						<c:out value="${member.memName}！" />
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">
							<form method="post"
								action="${pageContext.request.contextPath}/member.do"
								style="margin-bottom: 0px;">
								<input type="hidden" name="action" value="update"> <input
									type="hidden" name="memNo" value="${member.memNo}"> <input
									type="button" class="update-btn" value="修改"
									onclick="processUpdate({memNo:'${member.memNo}'});">
							</form>
						</li>
						<li class="list-group-item">變更密碼</li>
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
									<td>${member.memNo}</td>
								</tr>
								<tr>
									<th scope="row">姓名</th>
									<td>${member.memName}</td>
								</tr>
								<tr>
									<th scope="row">性別</th>
									<td>${member.memGender}</td>
								</tr>
								<tr>
									<th scope="row">照片</th>
									<td><img
										src="${pageContext.request.contextPath}/DBPngReader?action=member&id=${member.memNo}"
										class="img-thumbnail" style="width: 100px; height: 100px">
									</td>
								</tr>
								<tr>
									<th scope="row">email</th>
									<td>${member.memEmail}</td>
								</tr>
								<tr>
									<th scope="row">密碼</th>
									<td>${member.memPassword}</td>
								</tr>
								<tr>
									<th scope="row">電話</th>
									<td>${member.memTel}</td>
								</tr>
								<tr>
									<th scope="row">地址</th>
									<td>${member.memAddress}</td>
								</tr>
								<tr>
									<th scope="row">生日</th>
									<td>${member.memBd}</td>
								</tr>
								<tr>
									<th scope="row">毛毛幣</th>
									<td>${member.momoPoint}</td>
								</tr>
								<tr>
									<th scope="row">課堂數</th>
									<td>${member.totalClass}</td>
								</tr>
								<tr>
									<th scope="row">狀態</th>
									<td>${member.memStatus}</td>
								</tr>
							</tbody>
						</table>
					</div>

				</div>
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
