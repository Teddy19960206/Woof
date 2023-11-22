<%@ page import="com.woof.member.entity.Member" %>
<%@ page import="com.woof.classorder.service.ClassOrderServiceImpl" %>
<%@ page import="com.woof.classorder.entity.ClassOrder" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
 Member member = (Member) request.getSession(false).getAttribute("member");

 ClassOrderServiceImpl c = new ClassOrderServiceImpl();
 List<ClassOrder> classOrders = c.getOrderByMemNo(member.getMemNo());
 request.setAttribute("all" , classOrders);
%>
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
.table {
  width: 100%;
  border-collapse: collapse;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.table th,
.table td {
  padding: 12px;
  text-align: center;
  border-bottom: 1px solid #dee2e6;
}

.table thead th {
  background-color: #007bff;
  color: white;
}

.btn-comment:hover {
  background-color: #218838;
}

.btn-in:hover {
  background-color: #e0a800;
}

/* 預約單編號欄位 */
.truncated-text {
  max-width: 120px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
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
                                <td class="truncated-text">${classOrder.coNo}</td>
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
                    						<c:when test="${classOrder.coStatus != 1}">
                        						class="btn btn-refundApplication-disabled" disabled="disabled"
                    						</c:when>
                    						<c:otherwise>
                        						class="btn btn-refundApplication refund" type="button"
                    						</c:otherwise>
                						</c:choose>
            						>${classOrder.coStatus == 0 ? '未付款' : classOrder.coStatus == 1 ? '申請退款' : classOrder.coStatus == 2 ? '已退款'
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
  </div>
 </div>

<script src="${pageContext.request.contextPath}/frontend/member/login/js/classOrder.js"></script>
</body>
</html>