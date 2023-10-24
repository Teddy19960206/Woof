let pathurl = window.location.pathname;
let pathname = pathurl.split('/')[1];


// 進來網頁時，抓取訓練師資料並顯示在頁面上
$(async function (){
   let data = await fetchAllTrainer();
   select(data);
})

// 取得所有訓練師資料
async function fetchAllTrainer(){
    let url = `/${pathname}/trainer/getTrianers`;

    try {
        const response = await  fetch(url , {
            method : "POST"
        });
        if (!response.ok){
            throw new Error("錯誤");
        }
        const data = await response.json();

        return data;

    }catch (error){
        console.log("錯誤" + error);
    }
}

// 傳到jsp上顯示select下拉式選單
function select(data){
    html = `<select name='trainers'>
    <option value="0">--請選擇--</option>`;
    data.forEach((item)=>{
        html += `<option value="${item.trainerNo}">${item.administrator.adminName}</option>`;
    })
    html += `</select>
    <button type="button" class="getBtn">確定</button>`;
    $("div#trainer").html(html);
}


// 選擇訓練師後送出，則去抓取該訓練師的上課日期
$(document).on("click" , "button.getBtn" , async function (){
    let getDetails = await fetchTrainerDetails($("option:selected").val());
    showDetails(getDetails);
});

async function fetchTrainerDetails(trainerId){
    let url = `/${pathname}/scheduleDetail/getClassDate`;

    let formData = new FormData;
    formData.append("trainerId" , trainerId)

    try{
        const response = await fetch(url,{
            method : "POST",
            body : formData
        });

        if (!response.ok) {
            throw new Error("錯誤");
        }
        const data = await response.json();

        console.log(data)
        return data;

    }catch (error){
        console.log(error);
    }
}

function showDetails(data){
    html =
        `<table>
            <thead>
                <tr>
                    <th>上課日期</th>
                </tr>
            </thead>
        </table>`
}