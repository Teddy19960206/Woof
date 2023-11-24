const refundModal = new bootstrap.Modal(document.getElementById('refundModal'), {});
const showGroupRefund = document.getElementById("showGroupRefund");
const refundBtn = document.getElementById("refundBtn");
const showPrivateRefund = document.getElementById("showPrivateRefund");

// ------------------------------- 轉換訂單編號格式 --------------------------
function formatOrderId(orderId) {
    return 'woofGroup' + orderId.toString().padStart(8, '0');
}

refundBtn.addEventListener("click"  ,function (){
    groupRefundFetch();
    // privateRefundFetch();
    refundModal.show();
})


// ------------------------------- 進入網頁時，取得通知數量 --------------------------
$(function (){
    refundsFetch();
})

// ------------------------------- 取得通知數量Fetch ------------------------------


async function refundsFetch(){
    let url = `${project}/groupOrder/countRefundInfo`;

    try {
        const response  = await fetch(url , {
            method : "POST"
        })
        if (!response.ok){
            throw new Error("錯誤")
        }
        const data = await response.text();

        if(data == 0){
            refundBtn.innerText = "退款審核通知";
            refundBtn.disabled = true ;
        }else{
            html  = `團課通知<span class="position-absolute top-0 start-50 translate-middle badge rounded-pill bg-danger">
                   ${data}
                </span>`;

            refundBtn.innerHTML = html;
            refundBtn.disabled = false ;
        }
    }catch (error){
        console.error('Error', error);
    }
}

// ---------------------------------- 取得通知訊息 --------------------------------

async function groupRefundFetch(){
    let url = `${project}/groupOrder/getGroupRefund`;

    try {
        const response  = await fetch(url , {
            method : "POST"
        })
        if (!response.ok){
            throw new Error("錯誤")
        }
        const data = await response.json();

        console.log(data);

        if (data){

            let html = "";
            let arr = new Array();

            arr.push(`<table class="table table-hover text-center align-middle border">
                        <thead class="table-light">
                            <tr>
                                <th>訂單編號</th>
                                <th>會員姓名</th>
                                <th>折抵毛毛幣</th>
                                <th>付款金額</th>
                                <th>訂單成立時間</th>
                                <th>退款</th>
                                <th>取消退款</th>
                            </tr>
                        </thead>
                            <tbody class="table-group-divider">`);


            data.forEach(item =>{
                arr.push(
                    `<tr>
                    <td>${formatOrderId(item.gcoNo)}</td>
                    <td>${item.member.memName}</td>
                    <td>${item.gcoSmmp}</td>
                    <td>${item.actualAmount}</td>
                    <td>${item.gcoDate}</td>
                    <td><button type="button" class="btn btn-danger orderRefund" data-id="${item.gcoNo}">退款</buttonm></td>
                    <td><button type="button" class="btn btn-primary orderCancel" data-id="${item.gcoNo}">取消</button></td>
                </tr>`);
            })


            arr.push(` </tbody></table>`);

            html = arr.join("");

            showGroupRefund.innerHTML = html;
        }else {
            refundModal.hide();

            await Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "沒有資料"
            });
        }

    }catch (error){
        console.error('Error', error);
    }
}

// ---------------------------------- 退款按鈕 --------------------------------
$(document).on("click" , "button.orderRefund" , function(){

    Swal.fire({
        title: "確定退款嗎?",
        text: "您將無法恢復此狀態！",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "是, 退款"
    }).then(async (result) => {
        if (result.isConfirmed) {
            // 需發送到後端進行修改 ？？？？？？？？？？？？？？？？？？
            let data = await refundOneGroup(this.getAttribute("data-id"));

            if (data.message){
                await Swal.fire({
                    title: "Good job!",
                    text: `${data.message}`,
                    icon: "success"
                });
                $(this).closest("tr").remove();
            }else{
                refundError();
            }

        }
    });
}) ;


async function refundOneGroup(gcoNo){
    let url = `${project}/groupOrder/refundOneGroup`;
    try{
        const response = await fetch(url ,{
            method: "POST",
            headers : {
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "gcoNo="+gcoNo
        })

        if (!response.ok){
            throw new Error("異常失敗");
            refundError();
        }

        const data = await response.json();
        return data;

    }catch (error){
        refundError();
        console.error('Error', error);
    }
}



async function refundError(){
    await Swal.fire({
        title: "Oops...",
        text: "退款失敗",
        icon: "error"
    });
}



// ---------------------------- 取消退款 ----------------------------------
$(document).on("click" , "button.orderCancel" , function (){
    Swal.fire({
        title: "確定取消退款嗎?",
        text: "您將無法恢復此狀態！",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "是, 取消退款"
    }).then(async (result) => {
        if (result.isConfirmed) {
            await cancelRefund(this.getAttribute("data-id"));
            if (data.message){
                await Swal.fire({
                    title: "Good job!",
                    text: `${data.message}`,
                    icon: "success"
                });
                $(this).closest("tr").remove();
            }else{
                cancelError();
            }
        }
    });
})


async function cancelRefund(gcoNo){
    let url = `${project}/groupOrder/cancelRefund`;
    try{
        const response = await fetch(url , {
            method : "POST",
            headers : {
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "gcoNo="+gcoNo
        });
        if (!response.ok){
            cancelError();
            throw new Error("錯誤");
        }

        const data = await response.json();


        return data;


    }catch (error){
        cancelError();
        console.error("Error" , error);
    }
}

async function cancelError(){
    await Swal.fire({
        title: "Oops...",
        text: "取消失敗",
        icon: "error"
    });
}
