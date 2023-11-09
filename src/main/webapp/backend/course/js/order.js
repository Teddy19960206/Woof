// 取得項目名
let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
const groupClass = $("select#groupClass");
const show = $("div#show");
const allPage = document.getElementById("allPage");
const selectStatus = document.getSelection("selectStatus");
$(function (){

    // 抓取團體課程 下拉式選單內容
    getGroupCourse();

    // 預設先抓取全部資料
    getOrder();
})

$("button#button").on("click" , function (){
    getOrder()
})


async function getOrder(page){

    if (!page){
        page = 1;
    }

    let url = `${projectName}/groupOrder/getOrder`;
    try{
        let formData = new FormData();
        formData.append("groupClass" , document.getElementById("groupClass").value);
        formData.append("selectStatus" , document.getElementById("selectStatus").value);
        formData.append("memNo" , document.getElementById("memNo").value);
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
        arr.push(` <table class="table table-hover text-center align-middle border">
                    <thead class="table-light">
                    <tr>
                      <th>訂單編號</th>
                      <th>會員姓名</th>
                      <th>課程名稱</th>
                      <th>訂單成立時間</th>
                      <th>付款方式</th>
                      <th>折抵毛毛幣</th>
                      <th>實際付款金額</th>
                      <!-- 0:未付款 1:已付款 2:已取消 Default:0 -->
                      <th>付款狀態</th>
                      <th>修改</th>
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

            if (item.gcoStatus === 0){
                statusText = '未付款';
            }else if (item.gcoStatus === 1){
                statusText = '已付款';
            }else if (item.gcoStatus === 2){
                statusText = '已退款';
            }else if (item.gcoStatus === 3){
                statusText = '已取消';
            }else if (item.gcoStatus === 4){
                statusText = '已完成';
            }

            arr.push(` <tr>
                          <td>${item.gcoNo}</td>
                          <td>${item.member.memName}</td>
                          <td>${item.groupCourseSchedule.groupCourse.classType.ctName} : ${item.groupCourseSchedule.groupCourse.skill.skillName}</td>
                          <td>${item.gcoDate}</td>
                          <td>${paymentMethod}</td>
                          <td>${item.gcoSmmp}</td>
                          <td>${item.actualAmount}</td>
                          <td>${statusText}</td>
                          <td><button type="button" class="detail-btn btn btn-primary" data-id="${item.gcoNo}">修改</button></td>
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

        show.html(html);

    }catch (error){
        console.log(error)
    }
}

// 抓取團體課程 下拉式選單內容
async function getGroupCourse(){

    let url = `${projectName}/groupcourse/getAll`;
    try{
        const response = await fetch(url , {
            method : "POST"
        })
        if (!response.ok){
            throw new Error("錯誤")
        }
        const data = await response.json();

        html = `<option value="">顯示全部</option>`;

        data.forEach(item =>{
            html += `<option value="${item.gcNo}">${item.classType.ctName} : ${item.skill.skillName}</option>`
        });

        groupClass.html(html);

    }catch (error){
        console.log(error);
    }
}

$(document).on("click" , "button.detail-btn" ,function (){
    fetchDetail($(this).data("id"));
});

async function fetchDetail(id){
    try {
        let url = `${projectName}/groupOrder/getOneOrder`;
        const response = await fetch(url , {
            method : "POST",
            headers :{
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body : "orderNo="+id
        })

        if (!response.ok){
            throw new Error("錯誤");
        }

        const data = await response.json();

        // 0:未付款 1:已付款 2:已退款 3.已取消 Default:0

        //  0:信用卡 1:匯款 2:綠界
        let paymethod;
        switch (data.gcoPaymentMethod){
            case 0:
                paymethod = '信用卡';
                break;
            case 1:
                paymethod = '匯款';
                break;
            case 2:
                paymethod = '綠界';
                break;
        }

        let html = "";
        let arr = new Array();

        arr.push(` <div class="container my-3">
        <div class="my-3">
            
            <div class="col-12 text-center p-5">
                <h3>修改訂單</h3>
            </div>
            <div class="col-12 my-3">
                <div class="row">
                    <label class="col-5 text-center">訂單編號</label>
                    <div class="col-7">
                        <input type="text" id="gcoNo" class="form-control" value="${data.gcoNo}"  readonly>
                    </div>
                    
                </div>

                <div class="row my-3">
                    <label class="col-5 text-center">會員姓名</label>
                    <div class="col-7">
                        <input type="text" class="form-control" value="${data.member.memName}" readonly>
                    </div>
                    
                </div>


                <div class="row my-3">
                    <label class="col-5 text-center">團體課程名稱</label>
                    <div class="col-7">
                        <input type="text" class="form-control" value="${data.groupCourseSchedule.groupCourse.classType.ctName} : ${data.groupCourseSchedule.groupCourse.skill.skillName}" readonly>
                    </div>
                    
                </div>

                <div class="row my-3">
                    <label class="col-5 text-center">訂單成立時間</label>
                    <div class="col-7">
                        <input type="text" class="form-control" value="${data.gcoDate}" readonly>
                    </div>
                    
                </div>

                <div class="row my-3">
                    <label class="col-5 text-center">付款方式</label>
                    <div class="col-7">
                        <input type="text" class="form-control" value="${paymethod}" readonly>
                    </div>
                    
                </div>

                <div class="row my-3">
                    <label class="col-5 text-center">折抵毛毛幣</label>
                    <div class="col-7">
                        <input type="text" class="form-control" value="${data.gcoSmmp}" readonly>
                    </div>
                    
                </div>

                <div class="row my-3">
                    <label class="col-5 text-center">實付金額</label>
                    <div class="col-7">
                        <input type="text" class="form-control" value="${data.actualAmount}" readonly>
                    </div>
                    
                </div>
                <div class="row my-3">
                    <label class="col-5 text-center">訂單狀態</label>
                    <div class="col-7">
                        <select id="status" name="status" class="form-select">
                            <option value="0" ${data.gcoStatus == 0 ? 'selected' : ''}>未付款</option>
                            <option value="1" ${data.gcoStatus == 1 ? 'selected' : ''}>已付款</option>
                            <option value="2" ${data.gcoStatus == 2 ? 'selected' : ''}>已退款</option>
                            <option value="3" ${data.gcoStatus == 3 ? 'selected' : ''}>已取消</option>
                            <option value="3" ${data.gcoStatus == 4 ? 'selected' : ''}>已完成</option>
                            <option value="3" ${data.gcoStatus == 5 ? 'selected' : ''}>退款申請中</option>
                        </select>
                    </div>
                </div>

                <div class="text-center p-3">
                    <button class="btn btn-primary refund" ${data.gcoStatus == 5 ? '' : 'disabled'}  data-id="${data.gcoNo}">退款</button>
                    <button class="btn btn-primary modify" data-id="${data.gcoNo}">確認修改</button>
                    <button class="btn btn-secondary" onclick="window.location.href = '${projectName}/backend/course/orderManagement.jsp'">取消修改</button>
                </div>
            </div>
        </div>
    </div>`);

        html = arr.join("");
        allPage.innerHTML = html;
    }catch (error){
        console.log(error);
    }
}


$(document).on("click" , "button.modify" , function (){
    modifyFetch($(this).data("id"), $("#status").val());

})

async function modifyFetch(id , status){
    let url = `${projectName}/groupOrder/modify`;
    try{
        const response = await fetch(url , {
            method : "POST",
            headers :{
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body : `id=${id}&status=${status}`
        })

        if (!response.ok){
            throw new Error("更新失敗")
        }
        const data = await response.json();

        if (data.message){
            alert(data.message);
        }else{
            let str = "";
            data.forEach(message =>{
                str += message +"\n"
            })
            alert(str);
        }
        window.location.href = `${projectName}/backend/course/orderManagement.jsp`;

    }catch (error){
        console.log(error);
    }

}

// 退款按鈕
$(document).on("click" , "button.refund" , function (){
    if (confirm("確定要退款嗎")) {
        refundFetch($(this).data("id"));
    }
})

async function refundFetch(id){

    let url = `${projectName}/groupOrder/refund`;

    try{
        const response = await fetch(url , {
            method : "POST",
            headers : {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body : 'id='+$("input#gcoNo").val()
        })

        if (!response.ok){
            throw new Error("錯誤");
        }
        const data =await response.json();

        if (data.message){
            alert(data.message);
        }else{
            let str = "";
            data.forEach(message =>{
                str += message +"\n"
            })
            alert(str);
        }
        window.location.href = `${projectName}/backend/course/orderManagement.jsp`;

    }catch (error){
        console.log(error);
    }

}

