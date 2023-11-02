let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
const selectElement = document.getElementById("skill");


$(async function (){

    let scheduleNo = $("#scheduleNo").val()
    let related = $("#related").val();
    let data = await getAllSchedule();

    html = `<option value="0">無</option>`;
    data.forEach(item =>{

        if (item.gcsNo != scheduleNo){
            html += `<option value="${item.gcsNo}" ${related != "" ? related == item.gcsNo? "selected" : ""  : ""}>
            課程報名編號：${item.gcsNo} 班別：${item.groupCourse.classType.ctName} 訓練師：${item.trainer.administrator.adminName} 課程名稱：${item.groupCourse.skill.skillName}
            </option>`;
        }
    })

    $("#relatedGcsNo").html(html)
})

// 取得所有報名課程
async function getAllSchedule(){
    let url = `${projectName}/schedule/getSchedule`;

    try{
        const response = await fetch(url , {
            method : "POST",
            headers:{
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "classType=0"
        })
        if (!response.ok){
            throw new Error("網路異常")
        }
        const data = await response.json();

        console.log(data)

        return data;
    }catch (error){
        console.log(error)
    }

}

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
    let url = `${projectName}/skill/getTrainersBySkill/${id}`;

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

