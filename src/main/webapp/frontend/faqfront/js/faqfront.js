let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);


document.addEventListener("DOMContentLoaded", function () {
    // 獲取按鈕元素
    var petTrainingBtn = document.getElementById("petTraining");
    var accountIssuesBtn = document.getElementById("accountIssues");
    var orderQueriesBtn = document.getElementById("orderQueries");
    var productInquiriesBtn = document.getElementById("productInquiries");

    // 獲取內容區域元素
    var petTrainingFaqs = document.getElementById("petTrainingFaqs");
    var accountIssuesFaqs = document.getElementById("accountIssuesFaqs");
    var orderQueriesFaqs = document.getElementById("orderQueriesFaqs");
    var productInquiriesFaqs = document.getElementById("productInquiriesFaqs");

    // 添加按鈕點擊事件監聽器
    petTrainingBtn.addEventListener("click", function () {
        // 顯示寵物訓練問題內容
        petTrainingFaqs.style.display = "block";
        // 隱藏其他類別的內容
        accountIssuesFaqs.style.display = "none";
        orderQueriesFaqs.style.display = "none";
        productInquiriesFaqs.style.display = "none";
    });

    accountIssuesBtn.addEventListener("click", function () {
        // 顯示帳號問題內容
        accountIssuesFaqs.style.display = "block";
        // 隱藏其他類別的內容
        petTrainingFaqs.style.display = "none";
        orderQueriesFaqs.style.display = "none";
        productInquiriesFaqs.style.display = "none";
    });

    orderQueriesBtn.addEventListener("click", function () {
        // 顯示訂單問題內容
        orderQueriesFaqs.style.display = "block";
        // 隱藏其他類別的內容
        petTrainingFaqs.style.display = "none";
        accountIssuesFaqs.style.display = "none";
        productInquiriesFaqs.style.display = "none";
    });

    productInquiriesBtn.addEventListener("click", function () {
        // 顯示商品問題內容
        productInquiriesFaqs.style.display = "block";
        // 隱藏其他類別的內容
        petTrainingFaqs.style.display = "none";
        accountIssuesFaqs.style.display = "none";
        orderQueriesFaqs.style.display = "none";
    });
});


