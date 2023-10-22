let pathurl = window.location.pathname;
let pathname = pathurl.split('/')[1];

let selectElement = document.getElementById("skill");

selectElement.addEventListener("change" , async function (e){

    let select = $(this).val();

    if (select == 0){
        select.html("請選擇課程")
    }else{
        let results = await fetchTrainers(select);

        let trainerSelect =  $("select#trainer");

        let html = "";
        if (results == undefined || results.length == 0){
            trainerSelect.html("找不到擁有該專業的訓練師");
        }else{

            results.forEach((trainers)=>{
                html += `<option value="${trainers.trainerNo}">${trainers.administrator.adminName}</option>`;
            })
        }

        trainerSelect.html(html);
    }


})


async function fetchTrainers(id){
    // 抓取資料，根據classType取得相對應的資料
    let url = `/${pathname}/skill/getTrainersBySkill/${id}`;

    try{
        const response = await fetch(url ,{
            method : "POST"
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