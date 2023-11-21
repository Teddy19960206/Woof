<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>分類 FAQ</title>
    <!-- 加入必要的 CSS 樣式 -->
    <style>
        /* 樣式設計省略，請自行設計 */
    </style>
    <!-- 引入 jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
    $(document).ready(function(){
        // 預設隱藏所有答案
        $(".faq-content").hide();

        // 處理分類的點擊事件
        $(".faq-class").click(function(){
            // 獲取被點擊的分類
            var faqClass = $(this).data("class");
            // 隱藏所有問題和答案
            $(".faq-item").hide();
            // 只顯示對應分類的問題
            $(".faq-item." + faqClass).show();
        });

        // 處理問題的點擊事件，展開或隱藏答案
        $(".faq-question").click(function(){
            $(this).next(".faq-content").slideToggle();
        });
    });
    </script>
</head>
<body>

<h1>分類 FAQ</h1>

<!-- 分類按鈕 -->
<div>
    <button class="faq-class" data-class="shipping">運費與送貨問題</button>
    <button class="faq-class" data-class="payment">付款問題</button>
    <!-- 其他分類按鈕... -->
</div>

<!-- FAQ 問題與答案 -->
<div>
    <c:forEach var="faq" items="${all}">
        <div class="faq-item ${faq.faqClass}">
            <div class="faq-question">${faq.faqTitle}</div>
            <div class="faq-content">${faq.faqContent}</div>
        </div>
    </c:forEach>
</div>

</body>
</html>