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
        </tr>
    </tbody>`)

    html = arr.join("");
    $("#show").html(html);
    if (data.gcoStatus == 2){
        $("#refundOrder").prop("disabled", true);
    } else {
        $("#refundOrder").prop("disabled", false);
    }
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
                Swal.fire({
                    title: "已申請退款!",
                    text: "請等待申請審核",
                    icon: "success"
                });
            }else {
                Swal.fire({
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
    let url = `${projectName}/groupOrder/refund`;
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