// 取得項目名
let pathurl = window.location.pathname;
let pathname = pathurl.split('/')[1];

// 點擊提交按鈕則執行fetchData()
document.getElementById("button").onclick = function (){
    fetchData();
}

// 進入到classContent.jsp時，會直接撈取全部課程資料
document.addEventListener("DOMContentLoaded", function (){
    fetchData();
});


// 撈取資料
async function fetchData(){

    // 發送請求，取得全部或與班別有相應的資料

    let url = `/${pathname}/groupcourse/getGroupCourse`;

    // 取得相應的資料，根據classTypeNo
    let formData = new FormData();
    formData.append("classType",document.getElementById("selectClass").value);

    try{
        const response = await fetch(url ,{
            method : "POST",
            body:formData
        });
        if (!response.ok){
            throw new Error('Network response was not ok');
        }
        const data = await response.json();

        html = `<table class="table table-stripclassNamext-center" border="1">
        <thead>
        <tr>
            <th>課程編號</th>
            <th>技能名稱</th>
            <th>課程圖片</th>
            <th>課程內容</th>
            <th>課程狀態</th>
            <th>修改</th>
        </tr>
        </thead>
        <tbody id="mybody">`;

        // 將取得的資料進行拼接打到頁面上
        data.forEach((item)=>{
            html+= `<tr>
            <td>${item.gcNo}</td>
            <td>${item.skill.skillName}</td>
            <td>`;
            if(item.coursePhoto instanceof Object && Object.keys(item.coursePhoto).length > 0){
                html +=  `<img src="/${pathname}/DBPngReader?action=groupCourse&id=${item.gcNo}" style="width: 100px; height: 100px" >`;
            }
            html += `</td>
            <td>${item.courseContent}</td>
            <td>${item.courseStatus == 0 ? "下架" : "<font color='red'>上架</font>"}</td>
            <td><button type="button" class="modify-button" data-id="${item.gcNo}">修改</td>
        </tr>`;
        })
        html += `</tbody></table>`;

        let tbody= document.querySelector("div.row");
        tbody.innerHTML = html;

    }catch (error){
        console.error('Error', error);
    }
}

// 修改按鈕
$(document).on("click" , "button.modify-button" ,function (e){
    let url = `/${pathname}/groupcourse/edit/${this.getAttribute("data-id")}`;
    window.location.href = url;
})

