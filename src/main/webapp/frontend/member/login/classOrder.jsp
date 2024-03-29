<%@ page import="com.woof.member.entity.Member" %>
<%@ page import="com.woof.classorder.service.ClassOrderServiceImpl" %>
<%@ page import="com.woof.classorder.service.ClassOrderService" %>
<%@ page import="com.woof.classorder.entity.ClassOrder" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	Member member = (Member) request.getSession(false).getAttribute("member");

	ClassOrderServiceImpl c = new ClassOrderServiceImpl();
	List<ClassOrder> classOrders = c.getOrderByMemNo(member.getMemNo());
	request.setAttribute("all" , classOrders);
	
	List<Integer> coNoList = new ArrayList<>();

	for (ClassOrder classOrder : classOrders) {
		Integer coNo = classOrder.getCoNo();
		coNoList.add(coNo);
	}

	List<String> orderIdList = new ArrayList<>();

	ClassOrderService classOrderService = new ClassOrderServiceImpl();
	for (Integer coNo : coNoList) {
		String coId = classOrderService.formatOrderId(coNo);
		orderIdList.add(coId);
	}

	Map<ClassOrder, String> coId = new LinkedHashMap<>();

	for (int i = 0; i < classOrders.size(); i++) {
		coId.put(classOrders.get(i), orderIdList.get(i));
	}

	request.setAttribute("coId", coId);
%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@ include file="/meta.file"%>
	<title>寵毛導師 Woof | 課堂訂單管理</title>
	<script type="text/javascript">
		//表單點擊找出對應的function//
		function processUpdate(jsonData){
			window.location.href = " <%=request.getContextPath()%>/frontend/member/login/updatemember.jsp?memNo=" + jsonData.memNo ;
		}
	</script>

	<style>
		.table thead th {
			background-color: #007bff;
			color: white;
		}
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

		.btn-refundApplication {
			background-color: #ff6347; /* 改變按鈕顏色 */
			color: #fff;
			border: none;
			padding: 8px 16px;
			cursor: pointer;
			border-radius: 3px;
			font-size: 14px;
			transition: background-color 0.3s;
		}


	</style>
</head>
<body>
<%@ include file="body.jsp"%>
<div class="col-12 col-md-8">
	<div class="card">
		<div class="card-body">
			<h3 class="card-title text-center p-2">課堂訂單管理</h3>
			<h5 class="card-title">剩餘課堂數:${member.getTotalClass()}</h5>
			<table class="table table-bordered table-hover text-center align-content-center align-middle" id="show">
				<thead>
				<tr>
					<th>課堂訂單編號</th>
					<th>購買課堂堂數</th>
					<th>付款方式</th>
					<th>折抵毛毛幣</th>
					<th>訂單時間</th>
					<th>訂單狀態</th>
					<th>實付金額</th>
					<th></th>
				</tr>

				</thead>
				<tbody>

				<c:forEach items="${all}" var="classOrder" begin="0" end="9">
					<tr>
						<td class="truncated-text">${coId[classOrder]}</td>
						<td>${classOrder.coBc}</td>
						<td>${classOrder.coPaymentMethod == 0 ? '信用卡' : classOrder.coPaymentMethod == 1 ? '匯款' : "綠界"}</td>
						<td>${classOrder.coSmmp}</td>
						<td>${classOrder.coTime}</td>
						<td>${classOrder.coStatus == 0 ? '未付款' : classOrder.coStatus == 1 ? '已付款' : classOrder.coStatus == 2 ? '已退款'
								: classOrder.coStatus == 3 ? '退款申請中' : '其他情況'}
						</td>
						<td>${classOrder.actualAmount}</td>
						<td>
							<input type="hidden" name="coNo" value="${classOrder.coNo}">
							<button data-id="${classOrder.coNo}"
									<c:choose>
										<c:when test="${classOrder.coStatus != 1 || classOrder.member.totalClass < classOrder.coBc}">
											class="btn btn-refundApplication-disabled" disabled="disabled"
										</c:when>
										<c:otherwise>
											class="btn btn-refundApplication refund" type="button"
										</c:otherwise>
									</c:choose>
							>${classOrder.coStatus == 0 ? '未付款' : classOrder.coStatus == 1 && classOrder.member.totalClass < classOrder.coBc ? '課堂不足退款' : classOrder.coStatus == 1 ? '申請退款' : classOrder.coStatus == 2 ? '已退款'
									: classOrder.coStatus == 3 ? '退款申請中' : '其他情況'}
							</button>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%@ include file="foot.jsp"%>

<script src="${pageContext.request.contextPath}/frontend/member/login/js/classOrder.js"></script>
</body>
</html>