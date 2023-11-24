let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);


$(document).on("click" , ".detail-button" , async function (){
    let data = await fetchOrder($(this).data("id"));

    split(data);

    var newUrl = `${projectName}/frontend/member/login/groupOrder.jsp`; // 想要添加的新網址
    var newState = { page: "寵毛導師Woof" }; // 新狀態物件
    history.pushState(newState, "", newUrl);

});

// 監聽按下上一頁時會重新刷新頁一慢
window.onpopstate = function(event) {
    location.reload();
};


async function fetchOrder(id){
    let url = `${projectName}/groupOrder/getOneOrder`;

    try{
        const response = await fetch(url , {
            method : "POST",
            headers :{
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "orderNo="+id
        })

        if (!response.ok){
            throw new Error("錯誤")
        }

        const data  = await response.json();

        console.log(data);
        return data;
    }catch (error){
        console.error("Error" , error);
    }
}

function formatOrderId(orderId) {
    return 'woofGroup' + orderId.toString().padStart(8, '0');
}
function split(data){
    let html = "";
    let arr = new Array();

    let payment;
    switch (data.gcoPaymentMethod){
        case 0:
            payment = "信用卡";
            break;
        case 1:
            payment = "匯款";
            break;
        case 2:
            payment = "使用綠界";
            break;
    }

    let status;
    switch (data.gcoStatus){
        case 0:
            status = "未付款";
            break;
        case 1:
            status = "已付款";
            break;
        case 2:
            status = "已退款";
            break;
        case 3:
            status = "已取消";
            break;
        case 4:
            status = "已完成";
            break;
        case 5:
            status = "退款申請中";
            break;
    }

    arr.push(`<tbody>
        <tr>
            <th scope="row">團體訂單編號</th>
            <td>${formatOrderId(data.gcoNo)}</td>
            <input type="hidden" value="${data.gcoNo}" id="orderNo"/>
        </tr>
        <tr>
            <th scope="row">課程班別</th>
            <td>${data.groupCourseSchedule.groupCourse.classType.ctName}</td>
        </tr>

        <tr>
            <th scope="row">課程名稱</th>
            <td>${data.groupCourseSchedule.groupCourse.skill.skillName}</td>
        </tr>
        <tr>
            <th scope="row">購買時間</th>
            <td>${data.gcoDate}</td>
        </tr>
        <tr>
            <th scope="row">付款方式</th>
            <td>${payment}</td>
        </tr>
        <tr>
            <th scope="row">折抵毛毛幣</th>
            <td>${data.gcoSmmp}</td>
        </tr>
        <tr>
            <th scope="row">實付金額</th>
            <td>${data.actualAmount}</td>
        </tr>
        <tr>
            <th scope="row">訂單狀態</th>
            <td>${status}</td>
        </tr>`);


    if (data.gcoStatus == 0){
        arr.push(`<tr>
             <th>匯款帳號</th>
             <td>
                <p>銀行：合作金庫 (006)</p>
                <p>匯款帳號：3333-333-333333</p>
                <p>付款金額：<font class="text-danger">${data.actualAmount}</font></p>
             </td>
        </tr>`);
    }


    arr.push(`</tbody>`);


    html = arr.join("");
    $("#showForm").html(html);

    // 只有 已付款 status (1) 可以按下申請退款按鈕
    if (data.gcoStatus != 1){
        $("#refundOrder").prop("disabled", true);
    } else {
        $("#refundOrder").prop("disabled", false);
    }

    // if (data.gcoStatus == 0){
    //
    // }

    $("#showBtn").show();
}


$(document).on("click" , "#refundOrder" , async function (){


    Swal.fire({
        title: "確定要退款嗎?",
        text: "此操作是不可逆的！!",
        icon: "警告!",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "是, 申請退款!",
        cancelButtonText: "否, 取消!"
    }).then(async (result) => {
        if (result.isConfirmed) {
            let data = await fetchRefund($("#orderNo").val());
            if (data.message){
                await Swal.fire({
                    title: "已申請退款!",
                    text: "請等待申請審核",
                    icon: "success"
                });
            }else {
                await Swal.fire({
                    title: "已申請失敗!",
                    text: "請重新嘗試申請",
                    icon: "error"
                });
            }
            window.location.reload();
        }
    });
})


async function fetchRefund(id){
    let url = `${projectName}/groupOrder/refundReview`;
    try{
        const response = await fetch(url , {
            method : "POST",
            headers : {
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "id="+ id
        })

        if (!response.ok){
            throw new Error("錯誤");
        }

        const data = await response.json();

        return data;
    }catch (error){
        console.error("Error" , error);
    }

}

//-------------------------------  一開始進到groupOrder會先抓該會員的訂單資料 ---------------------
//-------------------- 直接使用複合查詢 ----------------------

$(function (){
    getOrder();
})

// 格式化id 編號
let show = document.getElementById("show");

async function getOrder(page){

    if (!page){
        page = 1;
    }

    let url = `${projectName}/groupOrder/getOrder`;
    try{
        let formData = new FormData();
        formData.append("memNo" , document.getElementById("memberName").innerText);
        formData.append("page" , page);



        const response = await fetch(url , {
            method : "POST",
            body : formData
        })

        if (!response.ok){
            throw new Error("錯誤");
        }

        const data = await response.json();

        let html = "";
        let arr = new Array();
        arr.push(` <table class="table table-hover text-center align-middle border" id="showForm">
                    <thead class="table-light">
                    <tr>
                     <th class="d-none">訂單編號</th>
                     <th>課程班別</th>
                     <th>購買時間</th>
                     <th>付款方式</th>
                     <th>訂單狀態</th>
                     <th>訂單詳情</th>
                    </tr>
                  </thead>
                  <tbody class="table-group-divider">`);

        data.data.forEach(item=>{


            let paymentMethod;
            if (item.gcoPaymentMethod === 0){
                paymentMethod = '信用卡';
            }else if (item.gcoPaymentMethod === 1){
                paymentMethod = '匯款';
            }else if (item.gcoPaymentMethod === 2){
                paymentMethod = '綠界';
            }

            let statusText;

            switch (item.gcoStatus){
                case 0:
                    statusText = '未付款';
                    break;
                case 1:
                    statusText = '已付款';
                    break;
                case 2:
                    statusText = '已退款';
                    break;
                case 3:
                    statusText = '已取消';
                    break;
                case 4:
                    statusText = '已完成';
                    break;
                case 5:
                    statusText = '退款申請中';
                    break;
            }

            arr.push(` <tr>
                          <td>${formatOrderId(item.gcoNo)}</td>
                          <td>${item.groupCourseSchedule.groupCourse.classType.ctName} : ${item.groupCourseSchedule.groupCourse.skill.skillName}</td>
                          <td>${item.gcoDate}</td>
                          <td>${paymentMethod}</td>
                          <td>${statusText}</td>
                          <td><button type="button" class="detail-button btn btn-primary" data-id="${item.gcoNo}" ${item.gcoStatus == 4 ? 'disabled' : ''}>詳情</button></td>
                      </tr>`);
        })

        arr.push(`</tbody></table>`);


        // 書籤 ==============================================================
        arr.push(`<nav class="text-center d-flex justify-content-center">
                  <ul class="pagination">
                    <li class="page-item">
                      <button class="page-link" onclick="getOrder(${page == 1 ? 1 : page-1})">
                        <span aria-hidden="true">&laquo;</span>
                      </button>
                    </li>`);

        const maxPagesToShow = 3;
        const totalPages = parseInt(data.pageTotal);

        let startPage, endPage;

        if (totalPages <= maxPagesToShow) {
            // 如果總頁數 totalPages 小於或等於您希望顯示的最大頁碼按鈕數量 maxPagesToShow，則直接顯示從第一頁到最後一頁的所有頁碼按鈕。
            startPage = 1;
            endPage = totalPages;
        } else if (page <= Math.floor(maxPagesToShow / 2) + 1){
            // 當前頁位於前半部分，那麼顯示從第一頁到 maxPagesToShow 頁的按鈕。
            startPage = 1;
            endPage = maxPagesToShow;
        } else if (page >= totalPages - Math.floor(maxPagesToShow / 2)) {
            // 當前頁位於後半部分，那麼顯示從 totalPages - maxPagesToShow + 1 頁到最後一頁的按鈕。
            startPage = totalPages - maxPagesToShow + 1;
            endPage = totalPages;
        } else {
            // 當前頁位於中間部分，顯示當前頁前後各 Math.floor(maxPagesToShow / 2) 頁的按鈕。
            startPage = page - Math.floor(maxPagesToShow / 2);
            endPage = page + Math.floor(maxPagesToShow / 2);
        }

        for (let i = startPage; i <= endPage; i++) {
            arr.push(`<li class="page-item ${i === page ? "active" : ""}">
               <button class="page-link" onclick="getOrder(${i})">${i}</button>
             </li>`);
        }

        arr.push(`   <li class="page-item">
                        <button class="page-link" onclick="getOrder(${page == totalPages ? totalPages : page+1})">
                            <span aria-hidden="true">&raquo;</span></a>
                        </button>
                    </li>
                  </ul>
                </nav>`);

        html = arr.join("");

        show.innerHTML = html;

    }catch (error){
        console.error('Error', error);
    }
}
