<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/meta.file"%>
<title>私人訓練師預約</title>
<%@ include file="body.jsp"%>

<style>

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
<body>
<%@ include file="body.jsp" %>
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
									<c:when test="${AD.appStatus == 2}">
										已結束
									</c:when>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${AD.appStatus == 0}">
											<button data-id="${AD.adNo}" class="btn btn-cancel cancel" type="button">取消預約</button>
									</c:when>
									<c:when test="${AD.appStatus == 1}">
										<button class="btn btn-cancel" disabled>已取消</button>
									</c:when>
									<c:otherwise>
										<button class="btn btn-cancel" disabled>已結束</button>
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
<%@ include file="foot.jsp"%>
<script src="${pageContext.request.contextPath}/frontend/member/login/js/appointmentDetail.js"></script>
</body>
</html>
