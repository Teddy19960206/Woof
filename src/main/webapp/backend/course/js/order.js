// 取得項目名
let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
const groupClass = $("select#groupClass");
const show = $("div#show");

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

        html = ` <table class="table table-hover text-center align-middle border">
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
                    </tr>
                  </thead>
                  <tbody class="table-group-divider">`;

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

            html += ` <tr>
                          <td>${item.gcoNo}</td>
                          <td>${item.member.memName}</td>
                          <td>${item.groupCourseSchedule.groupCourse.classType.ctName} : ${item.groupCourseSchedule.groupCourse.skill.skillName}</td>
                          <td>${item.gcoDate}</td>
                          <td>${paymentMethod}</td>
                          <td>${item.gcoSmmp}</td>
                          <td>${item.actualAmount}</td>
                          <td>${statusText}</td>
                      </tr>`;
        })

        html += `</tbody></table>`;


        // 書籤 ==============================================================
        html += `<nav class="text-center d-flex justify-content-center">
                  <ul class="pagination">
                    <li class="page-item">
                      <button class="page-link" onclick="getOrder(${page == 1 ? 1 : page-1})">
                        <span aria-hidden="true">&laquo;</span>
                      </button>
                    </li>`;

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
            html += `<li class="page-item ${i === page ? "active" : ""}">
               <button class="page-link" onclick="getOrder(${i})">${i}</button>
             </li>`;
        }

        html += `   <li class="page-item">
                        <button class="page-link" onclick="getOrder(${page == totalPages ? totalPages : page+1})">
                            <span aria-hidden="true">&raquo;</span></a>
                        </button>
                    </li>
                  </ul>
                </nav>`;

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