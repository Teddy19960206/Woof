
// 取得項目名
let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
let className;


// 點選相關資料的修改時，發送相關資料的id，並進入到編輯頁面
$(document).on("click" , "button.modify-button" , function (){
    let url = `${projectName}/schedule/edit/${this.getAttribute("data-id")}`;
    window.location.href = url;
});

// 點擊相關資料的報名時，更新報名人數???????????????????????????????
$(document).on("click" , "button.registration" , function (){

    let url = `${projectName}/groupcourse/schedule/xxx/${this.getAttribute("data-id")}`;

    window.location.href = url;

})

// 進入schedule.jsp時，會直接發送請求，取得所有相關資料---------------
document.addEventListener("DOMContentLoaded", function (){
    fetchData();
});


// 按下提交按鈕，發送請求，取得相應的資料
document.getElementById("button").onclick = function (){
    fetchData();
}

// 抓取資料
async function fetchData(){

    // 抓取資料，根據classType取得相對應的資料
    let url = `${projectName}/schedule/getSchedule`;
    let formData = new FormData();
    formData.append("classType" , document.getElementById("selectClass").value);

    try{
        const response = await fetch(url ,{
            method : "POST",
            body:formData
        });

        if (!response.ok){
            throw new Error('Network response was not ok');
        }
        // 取得資料後，進行拼圖，並打到頁面上
        const data = await response.json();
        html = `<table class="table table-hover text-center align-middle">
        <thead>
        <tr>
            <th>團體課程編號</th>
            <th>班別</th>
            <th>訓練師</th>
            <th>開始報名時間</th>
            <th>結束報名時間</th>
            <th>最少開課人數</th>
            <th>最多開課人數</th>
            <th>已報名人數</th>
            <th>價格</th>
            <th>課程報名狀態</th>
            <th>延期的關聯課程編號</th>
            <th>延期原因</th>
            <th>建立時間</th>
            <th>最後修改時間</th>
            <th>修改</th>
            <th>詳情</th>
        </tr>
        </thead>
        <tbody id="mybody">`;

        // 取得所有相關的資料
        data.forEach((item)=>{
            console.log(item)
            html+= `<tr>
            <th>${item.gcsNo}</th>
            <th>${item.groupCourse.classType.ctName} : ${item.groupCourse.skill.skillName}</th>
            <th>${item.trainer.administrator.adminName}</th>
            <th>${item.gcsStart}</th>
            <th>${item.gcsEnd}</th>
            <th>${item.minLimit}</th>
            <th>${item.maxLimit}</th>
            <th>${item.regCount}</th>
            <th>${item.gcsPrice}</th>
            <th>${item.gcsStatus == 0 ? "下架" : "上架" }</th>
            <th>${item.relatedGcsNo !== undefined ? item.relatedGcsNo.gcsNo : '無'}</th>
            <th>${item.gcsDelayReason !== undefined && item.gcsDelayReason !== ""   ? item.gcsDelayReason : '無'}</th>
            <th>${item.createdAt}</th>
            <th>${item.updatedAt}</th>
            <td><button type="button" class="modify-button bn632-hover bn26" data-id="${item.gcsNo}" onclick="fetchDetail(${item.gcsNo})">修改</td>
            <th><button type="button" class="detail-button bn632-hover bn26" data-id="${item.gcsNo}">詳情</button></th>
        </tr>`;
        })

        html += `</tbody></table>`;

        // 將資料打到指定位置
        let showSchedule= document.querySelector("div.showSchedule");
        showSchedule.innerHTML = html;

    }catch (error){
        console.error('Error', error);
    }
}

// 抓取上課日期，依照Schedule編號---------------------------------
$(document).on("click" , "button.detail-button" , function (e){
    let url = `${projectName}/scheduleDetail/detail/${this.getAttribute("data-id")}`;
    className = $(this).closest("tr").find("th").eq(1).text();
    var newUrl = `${projectName}/backend/course/schedule.jsp`; // 想要添加的新網址
    var newState = { page: "your-page" }; // 新狀態物件

    history.pushState(newState, "新頁面標題", newUrl);
    fetchDetail(url);
});
// 監聽按下上一頁時會重新刷新頁一慢
window.onpopstate = function(event) {
    location.reload();

    console.log(event)
};


// 查看課程詳情
async function fetchDetail(url){
    // 抓取資料，根據classType取得相對應的資料
    try{
        const response = await fetch(url ,{
            method : "POST",
        });

        if (!response.ok){
            throw new Error('Network response was not ok');
        }
        // 取得資料後，進行拼圖，並打到頁面上
        const data = await response.json();

        html = `<table class="table table-hover text-center align-middle">
        <thead>
        <tr>
            <th>編號</th>
            <th>班別</th>
            <th>訓練師</th>
            <th>上課日期</th>
            <th>修改</th>
            <th>確定</th>
            <th>刪除</th>
        </tr>
        </thead>
        <tbody id="mybody">
        `;
        data.forEach((item)=>{
            html += `<tr>
                <td>${item.gcsdNo}</td>
                <td>${className}</td>
                <td>${item.trainer.administrator.adminName}</td>
                <td>${item.classDate}</td>
                <td><button type="button" class="editDetail btn btn-primary" data-id="${item.gcsdNo}">修改</button></td>
                <td><button type="button" class="modify btn btn-primary" data-id="${item.gcsdNo}" disabled>確定</button></td>
                <td><button type="button" class="deleteDetail btn btn-danger" data-id="${item.gcsdNo}">刪除</button></td>
            </tr>`
        });

        html += "</tbody></table>";


        let showSchedule= document.querySelector("div.showSchedule");
        showSchedule.innerHTML = html;

        // 將資料打到指定位置

    }catch (error){
        console.error('Error', error);
    }
}

// detail修改按鈕----------------------------------------------
$(document).on("click" , "button.editDetail" , async  function (){
    let data = await detailedit(this.getAttribute("data-id"));
    let td = $(this).closest("tr").find("td");
    let html = `<select class="form-select text-center">`
        data.forEach((item)=>{
            html += `<option value="${item.trainerNo}" ${item.administrator.adminName == td.eq(2).html() ? 'selected' : ''}>${item.administrator.adminName}</option>`;
        })
        html += `</select>`;
    td.eq(2).html(html);


    let date = `<input type="date" value="${td.eq(3).html()}">`;
    td.eq(3).html(date)

    let button = $(this).closest("tr").find("button");
    button.eq(0).prop("disabled" , true);
    button.eq(1).prop("disabled" , false);

})


async function detailedit(id){

    let url = `${projectName}/scheduleDetail/edit`;

    let formData = new FormData();
    formData.append("Detail" ,id );

    try{
        const response = await fetch(url ,{
            method : "POST",
            body : formData
        });

        if (!response.ok){
            throw new Error('Network response was not ok');
        }
        // 取得資料後，進行拼圖，並打到頁面上
        const data = await response.json();

        return data;

    }catch (error){
        console.error('Error', error);
    }
}


// 修改後，按下確認按鈕正式進行資料庫修改-----------------------------
$(document).on("click" , "button.modify" , async function () {
    let tr = $(this).closest("tr");
    let select = tr.find("select");
    let input = tr.find("input");

    let data = await detailModify(this.getAttribute("data-id"), select.val(), input.val());

    if (data){
        let button = $(this).closest("tr").find("button");
        button.eq(0).prop("disabled" , false);
        button.eq(1).prop("disabled" , true);
        tr.find("td").eq(2).html(select.find("option:selected").text());
        tr.find("td").eq(3).html(input.val());
    }
})

async function detailModify(id , trainer , classDate){
    let url = `${projectName}/scheduleDetail/modify`;

    let formData = new FormData();
    formData.append("gcsdNo" ,id );
    formData.append("trainerNo" , trainer);
    formData.append("classDate" , classDate);

    try{
        const response = await fetch(url ,{
            method : "POST",
            body : formData
        });

        if (!response.ok){
            throw new Error('Network response was not ok');
        }
        // 取得資料後，進行拼圖，並打到頁面上
        const data = await response.text();

        return data;

    }catch (error){
        console.error('Error', error);
    }
}


// 刪除detail按鈕-----------------------------------------------
$(document).on("click" , "button.deleteDetail" , function (){
    let result = confirm("確定要刪除嗎?");
    if (result) {
        detailDelete(this.getAttribute("data-id"));
        $(this).closest("tr").remove();
    }
})

async function detailDelete(id){
    // 抓取資料，根據classType取得相對應的資料
    let url = `${projectName}/scheduleDetail/delete`;

    let formData = new FormData();
    formData.append("Detail" ,id );

    try{
        const response = await fetch(url ,{
            method : "POST",
            body : formData
        });

        if (!response.ok){
            throw new Error('Network response was not ok');
        }
        // 取得資料後，進行拼圖，並打到頁面上
        const data = await response.json();

    }catch (error){
        console.error('Error', error);
    }
}

