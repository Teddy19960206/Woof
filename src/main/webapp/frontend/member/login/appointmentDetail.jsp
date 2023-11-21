<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/meta.file"%>
<title>私人訓練師預約</title>
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
    text-align: left; /* 使內容靠右對齊 */
    margin-right: 20px; /* 右邊距，可依需求調整 */
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
.col-12.col-md-8 {
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

.table {
    margin-top: 20px;
}

.table th,
.table td {
    text-align: center;
}

.btn {
    padding: 8px 16px;
    border: none;
    border-radius: 5px;
    font-size: 1rem;
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s ease-in-out;
}

.btn-back {
    background-color: #007bff;
    color: #fff;
    margin-top: 20px;
}

.btn-cancel {
    background-color: #dc3545;
    color: #fff;
    margin-top: 20px;
}

.btn:hover {
    filter: brightness(90%);
}

.btn-cancel:disabled {
    background-color: #f8f9fa;
    color: #ced4da;
    border: 1px solid #ced4da;
    cursor: not-allowed;
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
									<a href="${pageContext.request.contextPath}/frontend/member/login/classOrder.jsp">課程訂單管理</a>
								</div>
								<hr class="custom-divider">
								<div class="accordion-body">
									<a href="${pageContext.request.contextPath}/frontend/member/login/appointment.jsp">預約單管理</a>
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
						aria-expanded="false" aria-controls="collapseShopOrderManagement">
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
            			<h3 class="card-title text-center p-2">私人預約管理</h3>
            			<table class="table table-bordered table-hover text-center align-content-center align-middle" id="show">
                        	<thead>
                            	<tr>
                                	<th>預約明細編號</th>
                                	<th>訓練師</th>
                                	<th>預約時間</th>
                                	<th>預約狀態</th>
                                	<th>取消預約</th>
                            	</tr>
                        	</thead>
                        	<tbody>
                        		<c:forEach var="AD" items="${appointmentDetails}">
                            	<tr>
                                	<td class="truncated-text">${AD.adNo}</td>
                                	<td>${AD.privateTrainingAppointmentForm.trainer.administrator.adminName}</td>
                                	<td>${AD.appTime}</td>
                                	<td>
                						<c:choose>
        									<c:when test="${AD.appStatus == 0}">
            									預約完成
        									</c:when>
        									<c:when test="${AD.appStatus == 1}">
            									已取消
        									</c:when>
    									</c:choose>
        							</td>
                                	<td>
                                		<c:choose>
            								<c:when test="${AD.appStatus != 1}">
                                				<FORM METHOD="post" action="${pageContext.request.contextPath}/appointmentdetail?action=cancel">
<%--           											<input type="hidden" name="ptaNo" value="${AD.privateTrainingAppointmentForm.ptaNo}">      --%>
<%--           											<input type="hidden" name="adNo" value="${AD.adNo}">      --%>
          											<button data-adNo="${AD.adNo}" class="btn btn-cancel cancel" type="button">取消預約</button>
                                 				</FORM>
                                 		 	</c:when>
                                 		 	<c:otherwise>
                								<button class="btn btn-cancel" disabled>已取消</button>
            								</c:otherwise>
        								</c:choose>
                                	</td>
                            	</tr>
                         		</c:forEach>
                        	</tbody>
                    	</table>
                    <button class="btn btn-back" onclick="window.location.href='${pageContext.request.contextPath}/frontend/member/login/appointment.jsp'">返回</button>
     				</div>
    			</div>
   			</div>
		</div>
	</div>
<script src="${pageContext.request.contextPath}/frontend/member/login/js/appointmentDetail.js"></script>
</body>
</html>
