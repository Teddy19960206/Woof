// 取得項目名
let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
// 點擊提交按鈕則執行fetchData()
document.getElementById("button").onclick = function (){
    fetchData();

}

// ------------------------ 進入到classContent.jsp時，會直接撈取全部課程資料  ---------------------------
document.addEventListener("DOMContentLoaded", function (){
    fetchData();
});


// 撈取資料
async function fetchData(page){

    if (!page){
        page = 1;
    }

    // 發送請求，取得全部或與班別有相應的資料

    let url = `${projectName}/groupcourse/getGroupCourse`;

    // 取得相應的資料，根據classTypeNo
    let formData = new FormData();
    formData.append("classType",document.getElementById("selectClass").value);
    formData.append("status" , document.getElementById("selectStatus").value);
    formData.append("page" , page );

    try{
        const response = await fetch(url ,{
            method : "POST",
            body:formData
        });
        if (!response.ok){
            throw new Error('Network response was not ok');
        }
        const data = await response.json();

        let html ="";
        let arr = new Array();

        arr.push(`<table class="table table-hover text-center align-middle border">
        <thead class="table-light">
        <tr>
            <th>課程編號</th>
            <th>技能名稱</th>
            <th>課程圖片</th>
            <th>課程內容</th>
            <th>課程狀態</th>
            <th>修改</th>
        </tr>
        </thead>
        <tbody id="mybody" class="table-group-divider">`);

        // 將取得的資料進行拼接打到頁面上=========================================
        data.data.forEach((item)=>{
            arr.push(`<tr>
            <td>${item.gcNo}</td>
            <td>${item.skill.skillName}</td>
            <td>`);
            if(item.coursePhoto instanceof Object && Object.keys(item.coursePhoto).length > 0){
                arr.push(`<img src="${projectName}/DBPngReader?action=groupCourse&id=${item.gcNo}" style="width: 100px; height: 100px" >`);
            }else {
                arr.push(`<img src="${projectName}/webutil/images/no-image.png" style="width: 100px; height: 100px" >`)
            }
            arr.push(`</td>
            <td width="500px" class="text-start">${item.courseContent}</td>
            <td>${item.courseStatus == 0 ? "下架" : "<font color='red'>上架</font>"}</td>
            <td><button type="button" class="modify-button bn632-hover bn26" data-id="${item.gcNo}">修改</td>
        </tr>`);
        })
        arr.push(`</tbody></table>`);

        // 書籤 ==============================================================
        arr.push(`<nav class="text-center d-flex justify-content-center">
                  <ul class="pagination">
                    <li class="page-item">
                      <button class="page-link" onclick="fetchData(${page == 1 ? 1 : page-1})">
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
               <button class="page-link" onclick="fetchData(${i})">${i}</button>
             </li>`);
        }

        arr.push(`   <li class="page-item">
                        <button class="page-link" onclick="fetchData(${page == totalPages ? totalPages : page+1})">
                            <span aria-hidden="true">&raquo;</span></a>
                        </button>
                    </li>
                  </ul>
                </nav>`);

        html = arr.join("");

        let tbody= document.querySelector("div.showGroup");
        tbody.innerHTML = html;

    }catch (error){
        console.error('Error', error);
    }
}

// 修改按鈕
$(document).on("click" , "button.modify-button" ,function (e){
    let url = `${projectName}/groupcourse/edit/${this.getAttribute("data-id")}`;
    window.location.href = url;
})

