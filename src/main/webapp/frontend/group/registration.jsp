<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/meta.file" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontend/group/css/registration.css"/>
    <title>寵毛導師 Woof | 結帳</title>
</head>
<body>
<%@ include file="/Header.file" %>
<c:if test="${not empty errorMsgs}">
    <script>
        let messageArr = [];
        <c:forEach var="message" items="${errorMsgs}">
            messageArr.push("${message}");
        </c:forEach>
        let formattedString = messageArr.join("\n");
        alert(formattedString);
    </script>
</c:if>
<div class="container">
    <div class="row">
        <div class="col-md-5 col-lg-4">
            <form class="bg-white p-5 rounded-3">
                <div class="col-12">
                    <h1 class="h3 text-center" >會員資料</h1>
                    <div class="mb-md-3 row text-center">
                        <div>
                            <img src="${pageContext.request.contextPath}/DBPngReader?action=member&id=${member.memNo}" class="img-fluid rounded-circle myImg">
                        </div>
                    </div>
                </div>
                <div class="col-12 mx-auto ">
                    <div class="mb-md-3  row mt-5  mx-auto text-xl-start text-center">
                        <label for="member" class="col-xl-6 col-12 col-form-label">會員帳號</label>
                        <div class="col-xl-6 col-12">
                            <input type="text" readonly class="form-control-plaintext text-center" id="member" value="${member.memNo}">
                        </div>
                    </div>

                    <div class="mb-md-3 row mx-auto text-xl-start text-center">
                        <label for="name" class="col-xl-6 col-12 col-form-label">會員姓名</label>
                        <div class="col-xl-6 col-12">
                            <input type="text" readonly class="form-control-plaintext text-center" id="name" value="${member.memName}">
                        </div>
                    </div>

                    <div class="mb-md-3 row mx-auto text-xl-start text-center">
                        <label for="smmp" class="col-xl-6 col-12 col-form-label">毛毛幣</label>
                        <div class="col-xl-6 col-12">
                            <input type="text" readonly class="form-control-plaintext text-center" id="smmp" value="${member.momoPoint}">
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-7 col-lg-8">
            <form class="bg-white p-5 rounded-3" method="post" action="${pageContext.request.contextPath}/groupOrder/check">
                <%-- 報名課程資訊 --%>

                <div class="row">
                    <fieldset class="col-lg-6 col-12 my-2" disabled>
                        <div class="mb-4">
                            <label for="GroupCourseInfo" class="form-label">報名課程資訊</label>
                            <input type="text" class="form-control text-center" id="GroupCourseInfo" value="${className}" readonly />
                        </div>
                    </fieldset>

                    <fieldset class="col-lg-5 col-12 my-2" disabled>
                        <label for="price" class="form-label">課程價格</label>
                        <input type="text" class="form-control text-center" name="price" id="price" value="${price}">
                    </fieldset>
                </div>

                <%-- 使用毛毛幣 --%>
                <div class="row">
                    <div class="col-md-6 col-12 my-3">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="smmp" id="UseSmmp">
                            <label class="form-check-label" for="UseSmmp">
                                使用毛毛幣
                            </label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="smmp" id="notUseSmmp"  checked>
                            <label class="form-check-label" for="notUseSmmp">
                                不使用毛毛幣
                            </label>
                        </div>
                        <fieldset id="inputSmmp" class="mt-1">
                            <input type="number" class="form-control text-center" name="smmpCount" id="smmpCount" maxlength="4" pattern="[0-9]{0,4}" title="請輸入數字0~4位數" />
                        </fieldset>
                    </div>
                    <%-- 實際付款金額 --%>
                    <div class="col-md-6 col-12 my-3">
                        <label class="form-check-label" for="UseSmmp">
                            實際付款金額
                        </label>
                        <fieldset id="actual" class="mt-1">
                            <input type="text" class="form-control text-center" name="actualAmount" value="" id="actualAmount" maxlength="4" pattern="[0-9]{0,4}" title="請輸入數字0~4位數" />
                        </fieldset>
                    </div>
                </div>

                <%-- 使用收件信箱 --%>

                <div class="mb-4">
                    <label for="email" class="form-label">收件信箱</label>
                    <input type="email" name="email" class="form-control" id="email" value="${member.memEmail}" required />
                </div>

                <%-- 付款方式
                0 是 信用卡
                1 是 匯款
                2 是 綠界 --%>
                <div class="my-5">
                    <h2>付款方式</h2>
                    <div class="form-check form-check-inline mt-3">
                        <input class="form-check-input" type="radio" name="payment" value="0" id="credit" checked />
                        <label class="form-check-label" for="credit">
                            信用卡一次付清
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="payment" value="1" id="transfer" />
                        <label class="form-check-label" for="transfer">
                            匯款
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="payment" value="2" id="ecPay" />
                        <label class="form-check-label" for="ecPay">
                            使用綠界
                        </label>
                    </div>
                </div>
                <div class="form showPayment mb-5" id="showPayment">
                    <label for="payname">付款人姓名</label>
                    <input type="text" class="form-control text-center mb-3" id="payname" style="width: 200px"/>
                    <label class="d-block">信用卡卡號</label>
                    <input class="form-control d-inline text-center mb-3" type="text" maxlength="4" onkeypress='validate(event)' value="4311" pattern="[0-9]{4}" title="請輸入4位數字" required /><span> - </span>
                    <input class="form-control d-inline text-center" type="text" maxlength="4" onkeypress='validate(event)' value="9522" pattern="[0-9]{4}" title="請輸入4位數字" required /><span> - </span>
                    <input class="form-control d-inline text-center" type="text" maxlength="4" onkeypress='validate(event)' value="2222" pattern="[0-9]{4}" title="請輸入4位數字" required /><span> - </span>
                    <input class="form-control d-inline text-center  " type="text" maxlength="4" onkeypress='validate(event)' value="2222" pattern="[0-9]{4}" title="請輸入4位數字" required />
                    <label class="d-block mb-3">有效期限</label>
                    <input class="form-control d-inline text-center" type="text" maxlength="2" onkeypress='validate(event)' value="05" pattern="[0-9]{2}" title="請輸入2位數字" required placeholder="MM"/><span> - </span>
                    <input class="form-control d-inline text-center  " type="text" maxlength="2" onkeypress='validate(event)' value="25" pattern="[0-9]{2}" title="請輸入2位數字" required placeholder="YY"/>
                    <div class="mt-2">
                        <label class="mb-3">末三碼</label>
                        <input class="form-control verification text-center" type="text" maxlength="3" onkeypress='validate(event)' value="222">
                    </div>

                    <div class="d-flex justify-content-center mt-5 myBtn">
                        <button type="submit" class="button-62">確認付款</button>
                    </div>
                </div>

                <input type="hidden" class="form-control" name="GroupScheduleNo" value="${groupScheduleNo}" readonly />

            </form>


            <div class="modal" tabindex="-1" id="myModal">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">錯誤</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>超過所擁有的毛毛幣，請重新輸入</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">確認</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
</div>


<%@ include file="/Footer.file" %>
<script src="${pageContext.request.contextPath}/frontend/group/js/registration.js"></script>
</body>
</html>
