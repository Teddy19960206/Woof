<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/meta.file" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontend/group/css/groupSchedule.css">
    <title>寵毛導師 Woof | 團體課程資訊</title>
</head>
<body>
<%@ include file="/Header.file" %>

<div data-aos="zoom-in" data-aos-duration="1500" class="d-flex justify-content-center my-5">
    <div class="carousel-caption">
        <h1>團體課程</h1>
    </div>
    <img src="${pageContext.request.contextPath}/frontend/images/groupPhoto.jpg" class="img-fluid background">

</div>


<div class="container mt-5">
    <div class="row">
        <div data-aos="zoom-in" data-aos-duration="1500">
            <h1 class="h3 text-center">
                通過我們的團體課程，讓您的犬幼犬學會基本命令，改善行為問題，並促進與其他狗狗和人的社交</h1>
        </div>


        <div>
            <div class="col-md-7 mx-auto my-5"
                 data-aos="fade-down"
                 data-aos-easing="linear"
                 data-aos-duration="1500">
                <p class="mb-4">我們的寵物訓練課程是一個獨特的機會，可以幫助您的毛絨伴侶實現最佳潛力，成為一個更聽話和親近的家庭成員。我們的課程不僅提供了專業的指導，還營造了一個充滿歡樂和支持的學習環境。</p>
                <p class="mb-4">這是一個讓您的寵物學會基本指令、建立社交技能、解決行為問題的機會。您將與其他寵物愛好者分享經驗，同時建立持久的友誼。我們的訓練師是經驗豐富的專家，將為您提供個性化的指導，確保您和您的寵物實現最佳成果。加入我們，一起探索一個充滿愛和學習的旅程！</p>
            </div>

            <div class="col-md-9 mx-auto" >
                <div data-aos="fade-up" data-aos-anchor-placement="bottom-bottom" class="mb-5">
                <h1 class="h2 text-center myTextColor my-5">課程特色</h1>
                    <ul>
                        <li><span>個人化指導：</span>突出強調你的課程如何提供針對每個狗狗的個人指導，以確保最佳的學習體驗。</li>
                        <li><span>專業訓練師：</span>強調你的訓練師擁有豐富的專業經驗和認證，可以提供高品質的訓練。</li>
                        <li><span>互動練習：</span>描述課程如何包括實際的練習，讓學員在真實情境中應用所學的技能。</li>
                        <li><span>社交機會：</span>強調課程提供的社交機會，使狗狗能夠與其他狗狗和人互動，建立信任。</li>
                        <li><span>綜合課程：</span>如果你的課程包括多個模塊，提到這些模塊，讓學員知道他們將學到什麼。</li>
                    </ul>
                </div>
                <div data-aos="fade-up" data-aos-anchor-placement="bottom-bottom" class="mb-5">
                    <h1 class="h2 text-center myTextColor my-5">課程內容摘要</h1>
                    <ul>
                        <li><span>基本命令：</span>簡要列出學員將學到的基本命令，例如坐下、待命、召回等。</li>
                        <li><span>行為糾正：</span>提到課程如何幫助學員改善狗狗的不良行為，如咬咬、吠叫等。</li>
                        <li><span>社交技能：</span>描述如何培養狗狗的社交技能，使其能夠在各種環境中適應。</li>
                        <li><span>實際示範：</span>提到是否有實際示範和練習機會，讓學員能夠親自參與。</li>
                        <li><span>課程持續時間：</span>指出課程的總持續時間，例如幾個星期或幾個月。</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="text-center">
        <h1 class="myTextColor mt-5">課程報名</h1>
    </div>
    <div class="text-center"
         data-aos="fade-up"
         data-aos-anchor-placement="bottom-bottom"
         data-aos-duration="1000">
<%--       成犬班　1　幼犬班　2 --%>

<%--        <button type="button" class="m-5 myBtn" id="adultClass">成犬班--%>
<%--        </button>--%>
<%--        <button type="button" class="m-5 myBtn" id="puppyClass">幼犬班--%>
<%--        </button>--%>

        <jsp:useBean class="com.woof.classtype.service.ClassTypeServiceImpl" id="service">
            <c:forEach items="${service.allClassTypes}" var="classtype" >
                <button type="button" class="m-5 myBtn classType"  data-id="${classtype.ctNo}">${classtype.ctName}
                </button>
            </c:forEach>
        </jsp:useBean>
    </div>
</div>

<div id="show"></div>



<%@ include file="/Footer.file" %>
<script src="${pageContext.request.contextPath}/frontend/group/js/groupSchedule.js"></script>
</body>
</html>
