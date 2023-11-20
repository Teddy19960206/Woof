<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/meta.file"%>
<title>會員中心</title>
<script type="text/javascript">
//表單點擊找出對應的function//
  function processUpdate(jsonData){
   window.location.href = " <%=request.getContextPath()%>/frontend/member/login/updatemember.jsp?memNo="+ jsonData.memNo;
	}
  //確認刪除提示框
  function confirmDelete(memNo) {
    // 使用 SweetAlert 顯示確認對話框
    Swal.fire({
        title: '您確定要註銷編號為 ' + memNo + ' 的會員帳號嗎？',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '是的，我要註銷！',
        cancelButtonText: '取消'
    }).then((result) => {
        // 如果用戶確認，則執行刪除操作
        if (result.isConfirmed) {
            window.location.href = "<%=request.getContextPath()%>/member1.do?action=delete&memNo=" + memNo;
        }
    });
}
</script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<style>
.table {
	border-collapse: collapse; /* 確保邊框合併 */
}

.table th, .table td {
	border: 1px solid #ddd; /* 加強邊框 */
	padding: 8px; /* 增加內距 */
	text-align: left; /* 左對齊文本 */
}

.table th {
	background-color: #f8f9fa; /* 標題欄的背景色 */
	color: #333; /* 標題欄的文本顏色 */
}

.table tr:nth-child(even) {
	background-color: #f2f2f2; /* 每兩行使用不同的背景色 */
}

.table tr:hover {
	background-color: #ddd; /* 懸停時改變背景色 */
}
</style>
<style>
body {
	background-color: #FFF3E0;
}

.card {
	border: none;
	border-radius: 20px;
	background-color: #f8f9fa;
}

.card-header {
	border: none;
	border-radius: 20px;
	background-color: SandyBrown;
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
/* 子選單樣式 */
#orderManagementOptions, #courseManagementOptions,
	#shopManagementOptions {
	padding-left: 0px; /* 增加左側內距 */
	background-color: orange; /* 與主選單區分的背景色 */
}

.left-icon {
	text-align: left; /* 使內容靠右對齊 */
	margin-right: 20px; /* 右邊距，可依需求調整 */
}

.custom-divider {
	margin-top: 0px; /* 上方間隔 */
	margin-bottom: 0px; /* 下方間隔 */
	border: 0; /* 移除邊框 */
	border-top: 1px solid #ccc; /* 設置頂部邊框，可調整顏色和粗細 */
}

a {
	color: inherit; /* 使超連結的顏色與其父元素相同 */
	text-decoration: none; /* 去除底線 */
}
/* 當按鈕被點擊或懸停時的顏色變化 */
.accordion-button:not(.collapsed) {
	background-color: #FFAA77; /* 按鈕展開時的背景色，這裡使用了稍微深一點的橘色 */
	color: #333;
}

.accordion-button:hover {
	background-color: #FFBB88; /* 按鈕懸停時的背景色 */
	color: #333;
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
<script src="https://kit.fontawesome.com/3f37e88a3b.js"
	crossorigin="anonymous"></script>
<body>
	<%@ include file="/Header.file"%>
	<jsp:useBean id="memberService" scope="page"
		class="com.woof.member.service.MemberServiceImpl" />
	<div class="container mt-5">
		<div class="row">
			<div class="col-12 col-md-4">
				<div class="accordion" id="accordionExample">
					<!-- 用戶資訊項目 -->
					<div class="accordion-item">
						<h2 class="accordion-header">
							<button class="accordion-button" type="button"
								data-bs-toggle="collapse" data-bs-target="#collapseUserInfo"
								aria-expanded="true" aria-controls="collapseUserInfo">
								您好，
								<c:out value="${member.memName}！" />
							</button>
						</h2>
						<div id="collapseUserInfo"
							class="accordion-collapse collapse show"
							data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<form method="post"
									action="${pageContext.request.contextPath}/member1.do"
									style="margin-bottom: 0px;">
									<input type="hidden" name="action" value="update"> <input
										type="hidden" name="memNo" value="${member.memNo}"> <a
										onclick="processUpdate({memNo:'${member.memNo}'});"
										style="cursor: pointer;"> <i class="fa-solid fa-user"></i>&nbsp;修改基本資料
									</a>
								</form>
							</div>
						</div>
						<hr class="custom-divider">
						<div id="collapseUserInfo"
							class="accordion-collapse collapse show"
							data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<form method="post"
									action="${pageContext.request.contextPath}/member1.do"
									style="margin-bottom: 0px;">
									<input type="hidden" name="action" value="delete"> <input
										type="hidden" name="memNo" value="${member.memNo}"> <a
										onclick="confirmDelete('${member.memNo}')"
										style="cursor: pointer;"> <i class="fa-solid fa-trash"></i>&nbsp;註銷會員
									</a>
								</form>
							</div>
						</div>
					</div>
					<!-- 課程管理項目 -->
					<div class="accordion-item">
						<h2 class="accordion-header">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse"
								data-bs-target="#collapseCourseManagement" aria-expanded="false"
								aria-controls="collapseCourseManagement">
								<i class="fa-solid fa-school"></i>&nbsp;課程管理
							</button>
						</h2>
						<div id="collapseCourseManagement"
							class="accordion-collapse collapse"
							data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<!-- 私人訓練師項目 -->
								<div class="accordion-item">
									<h2 class="accordion-header">
										<button class="accordion-button collapsed" type="button"
											data-bs-toggle="collapse"
											data-bs-target="#collapsePersonalTrainerOptions"
											aria-expanded="false"
											aria-controls="collapsePersonalTrainerOptions">
											</i> 私人訓練師
										</button>
									</h2>
									<div id="collapsePersonalTrainerOptions"
										class="accordion-collapse collapse"
										data-bs-parent="#collapseCourseManagement">
										<div class="accordion-body">
											<a href="#">課程訂單管理</a>
										</div>
										<hr class="custom-divider">
										<div class="accordion-body">
											<a
												href="${pageContext.request.contextPath}/frontend/member/login/appointment.jsp">預約單管理</a>
										</div>
									</div>
								</div>
							</div>
							<hr class="custom-divider">
							<div class="accordion-body">
								<a
									href="${pageContext.request.contextPath}/frontend/member/login/groupOrder.jsp">團體報名訂單管理</a>
							</div>
						</div>
					</div>

					<!-- 商城訂單查詢項目 -->
					<div class="accordion-item">
						<h2 class="accordion-header">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse"
								data-bs-target="#collapseShopOrderManagement"
								aria-expanded="false"
								aria-controls="collapseShopOrderManagement">
								<i class="fa-solid fa-shop"></i>&nbsp;商城訂單查詢
							</button>
						</h2>
						<div id="collapseShopOrderManagement"
							class="accordion-collapse collapse"
							data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<a href="#">訂單查詢</a>
							</div>
						</div>
						<hr class="custom-divider">
						<!-- 新增分隔線，並加入自訂類別 -->
						<div id="collapseShopOrderManagement"
							class="accordion-collapse collapse"
							data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<a href="#">訂單追蹤</a>
							</div>
						</div>
						<hr class="custom-divider">
						<!-- 新增分隔線，並加入自訂類別 -->
						<div id="collapseShopOrderManagement"
							class="accordion-collapse collapse"
							data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<a href="#">商品退貨</a>
							</div>
						</div>
					</div>
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
										onerror="this.onerror=null; this.src='/woof/frontend/member/jpg/dog.jpg';"
										class="profile-img"></td>
								</tr>
								<tr>
									<th scope="row">email</th>
									<td>${member.memEmail}<c:choose>
											<c:when test="${member.memStatus == 1}">
												<span style="color: green;">(已驗證)</span>
											</c:when>
											<c:when test="${member.memStatus == 2}">
												<span style="color: red;">(未驗證)</span>
												<!-- 新增驗證按鈕 -->
												<form method="post"
												action="${pageContext.request.contextPath}/member1.do"
												 style="display: inline;">
												<input type="hidden" name="action" value="validemail">
												<input type="hidden" name="memNo"value="${member.memNo}">
												<input type="hidden" name="memEmail"value="${member.memEmail}">
												<button type="submit" class="btn btn-primary">驗證信箱</button>
												</form>
											</c:when>
											<c:otherwise>
												<span>(狀態未知)</span>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<th scope="row">密碼</th>
									<td><a onclick="processUpdate({memNo:'${member.memNo}'});"
										style="cursor: pointer; color: Tomato;"> <i
											class="fa-solid fa-key"></i>&nbsp;更改密碼
									</a></td>
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
