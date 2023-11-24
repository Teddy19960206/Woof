<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.woof.shoporderdetail.service.ShopOrderDetailServiceImpl"%>
<%@ page import="com.woof.shoporder.service.ShopOrderServiceImpl"%>
<%@ page import="com.woof.shoporder.entity.ShopOrder"%>
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
<body>
<%
    String shopOrderNoStr = request.getParameter("shopOrderNo");
	System.out.println(shopOrderNoStr+"取得的編號==========");
  
    Integer shopOrderNo = Integer.parseInt(shopOrderNoStr);

    ShopOrderDetailServiceImpl service = new ShopOrderDetailServiceImpl();
    List<ShopOrderDetailDTO> orderDetails = service.findOneShopOrderNoDetailObj(shopOrderNo);
    System.out.println(orderDetails);

    request.setAttribute("orderDetails", orderDetails);

    System.out.println(orderDetails+"ListObject取得的編號==========");

    ShopOrderServiceImpl service2 = new ShopOrderServiceImpl();
    ShopOrder shopOrder = service2.findByShopOrderNo(shopOrderNo);

    request.setAttribute("shopOrder", shopOrder);

%>
<%@ include file="body.jsp"%>
    <div class="col-12 col-md-8">
        <div class="card">
            <div class="card-body">
                <h3 class="card-title text-center p-2">訂單明細</h3>
                <table class="table text-center" id="show">
                    <thead>
                        <tr>
<!--                                 <th>訂單編號</th> -->
                            <th>商品名稱</th>
                            <th>照片</th>
                            <th>數量</th>
                            <th>價格</th>
                            <th>小計</th>
                        </tr>

                    </thead>
                    <tbody>
                   <c:forEach items="${orderDetails}" var="orderDetail">
                    <tr>
<%-- 					        <td>${orderDetail.shopOrderNo}</td> --%>
                        <td>${orderDetail.prodName}</td>
                        <td><img src="${pageContext.request.contextPath}/productImage/${orderDetail.prodNo}" style="width: 50px"></td>
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
                        <span>小計</span>
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
<%@ include file="foot.jsp"%>

<script src="${pageContext.request.contextPath}/frontend/member/js/groupOrder.js"></script>
</body>
</html>
