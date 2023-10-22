
// 取得項目名
let pathurl = window.location.pathname;
let pathname = pathurl.split('/')[1];

// 按下提交按鈕，發送請求，取得相應的資料
document.getElementById("button").onclick = function (){
    fetchData();
}

// 進入schedule.jsp時，會直接發送請求，取得所有相關資料
document.addEventListener("DOMContentLoaded", function (){
    fetchData();
});

// 點選相關資料的修改時，發送相關資料的id，並進入到編輯頁面
$(document).on("click" , "button.modify-button" , function (){
    let url = `/${pathname}/schedule/edit/${this.getAttribute("data-id")}`;
    window.location.href = url;
});

// 點擊相關資料的報名時，更新報名人數
$(document).on("click" , "button.registration" , function (){


    let url = `/${pathname}/groupcourse/schedule/xxx/${this.getAttribute("data-id")}`;

    window.location.href = url;

});


// 抓取資料
async function fetchData(){

    // 抓取資料，根據classType取得相對應的資料
    let url = `/${pathname}/schedule/getSchedule`;
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
        html = `<table class="table table-stripclassNamext-center" border="1">
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
            <th>延期原因</th>
            <th>延期關聯表格</th>
            <th>建立時間</th>
            <th>最後修改時間</th>
            <th>修改</th>
        </tr>
        </thead>
        <tbody id="mybody">`;

        // 取得所有相關的資料
        data.forEach((item)=>{

            html+= `
            <th>${item.gcsNo}</th>
            <th>${item.groupCourse.classType.ctName} : ${item.groupCourse.skill.skillName}</th>
            <th>${item.trainer.administrator.adminName}</th>
            <th>${item.gcsStart}</th>
            <th>${item.gcsEnd}</th>
            <th>${item.minLimit}</th>
            <th>${item.maxLimit}</th>
            <th>${item.regCount}</th>
            <th>${item.gcsPrice}</th>
            <th>${item.gcsStatus}</th>
            <th>${item.gcsDelayReason}</th>
            <th>${item.relatedGcsNo}</th>
            <th>${item.createdAt}</th>
            <th>${item.updatedAt}</th>
            <td><button type="button" class="modify-button" data-id="${item.gcsNo}">修改</td>
        </tr>`;
        })

        html += `</tbody></table>`;

        // 將資料打到指定位置
        let row= document.querySelector("div.row");
        row.innerHTML = html;

    }catch (error){
        console.error('Error', error);
    }
}
