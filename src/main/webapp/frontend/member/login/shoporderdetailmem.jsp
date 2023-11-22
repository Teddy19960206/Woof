<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.woof.shoporderdetail.service.ShopOrderDetailServiceImpl"%>
<%@ page import="com.woof.shoporderdetail.entity.ShopOrderDetail"%>
<%@ page import="com.woof.shoporder.service.ShopOrderServiceImpl"%>
<%@ page import="com.woof.shoporder.entity.ShopOrder"%>
<%@ page import="com.woof.member.entity.Member" %>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.woof.shoporderdetail.entity.ShopOrderDetailDTO" %>
<html>
<head>
    <%@ include file="/meta.file"%>
    <title>寵毛導師 Woof | 訂單明細</title>
</head>
<style>
    .table {
/*         border-collapse: collapse; /* 確保邊框合併 */ */
    }

    .table th, .table td {
/*         border: 1px solid #ddd; /* 加強邊框 */ */
/*         padding: 8px; /* 增加內距 */ */
/*         text-align: left; /* 左對齊文本 */ */
    }

    .table th {
        background-color: #f8f9fa; /* 標題欄的背景色 */
/*         color: #333; /* 標題欄的文本顏色 */ */
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
/*         text-align: left; /* 使內容靠右對齊 */ */
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

<body>
<%
    String shopOrderNoStr = request.getParameter("shopOrderNo");
	System.out.println(shopOrderNoStr+"取得的編號==========");
  
    Integer shopOrderNo = Integer.parseInt(shopOrderNoStr);

    ShopOrderDetailServiceImpl service = new ShopOrderDetailServiceImpl();
    List<ShopOrderDetailDTO> orderDetails = service.findOneShopOrderNoDetailObj(shopOrderNo);
    System.out.println(orderDetails);
    
    for(ShopOrderDetailDTO shopOrderDetailDTO : orderDetails){
    	 System.out.println(shopOrderDetailDTO);
    }

    request.setAttribute("orderDetails", orderDetails);

    System.out.println(orderDetails+"ListObject取得的編號==========");
    
    ShopOrderServiceImpl service2 = new ShopOrderServiceImpl();
    ShopOrder shopOrder = service2.findByShopOrderNo(shopOrderNo);
    
    request.setAttribute("shopOrder", shopOrder);

%>


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
<%@ include file="/Header.file"%>

<div class="container mt-5 mb-5">
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
                    <h3 class="card-title text-center p-2">訂單明細</h3>
                    <table class="table text-center" id="show">
                        <thead>
                            <tr>
<!--                                 <th>訂單編號</th> -->
                                <th>商品名稱</th>
                                <th>數量</th>
                                <th>價格</th>
                                <th>小計</th>
                            </tr>

                        </thead>
                        <tbody>
                       <c:forEach items="${orderDetails}" var="orderDetail">
					    <tr>
<%-- 					        <td>${orderDetail.shopOrderNo}</td> --%>
					        <td>${orderDetail.prodNo}</td>
					        <td>${orderDetail.orderAmount}</td>
					        <td>NT$${orderDetail.prodPrice}</td>
					        <td>NT$${orderDetail.orderAmount * orderDetail.prodPrice}</td>
					    </tr>
					    <c:set var="totalAmount" value="${totalAmount + (orderDetail.orderAmount * orderDetail.prodPrice)}" />
					</c:forEach>
					</tbody>
					</table>
					<c:if test="${shopOrder != null}">
					    <div class="d-flex justify-content-between my-0 mt-4">
						    <span>收件人姓名</span>
						    <span>${shopOrder.recName}</span>
						</div>
						<div class="d-flex justify-content-between">
						    <span>收件人電話</span>
						    <span>${shopOrder.recMobile}</span>
						</div>
						<div class="d-flex justify-content-between">
						    <span>收件人地址</span>
						    <span>${shopOrder.recAddress}</span>
						</div>
					   <div class="d-flex justify-content-between">
						    <span>總小計</span>
						    <span>NT$${shopOrder.orderTotalPrice}</span>
						</div>
					    <div class="d-flex justify-content-between my-0 mt-0">
						    <span>毛毛幣</span>
						    <span>-${shopOrder.moCoin}</span>
						</div>
					    <div class="d-flex justify-content-between my-0 mt-0">
						    <span>總金額</span>
						    <span>NT$${shopOrder.actualPrice}</span>
						</div>
					</c:if>
                </div>
				<div class="d-flex justify-content-end">
				    <a href="${pageContext.request.contextPath}/frontend/member/login/shopordermem.jsp" class="btn btn-secondary mb-3" style="width: 60px; margin-right: 15px;">返回</a>
				</div>            
		 	</div>
        </div>

    </div>
</div>

<script src="${pageContext.request.contextPath}/frontend/member/js/groupOrder.js"></script>
</body>
</html>
