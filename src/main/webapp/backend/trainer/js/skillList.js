let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
const showList = document.getElementById("showList");

// 監聽按下上一頁時會重新刷新頁一慢
window.onpopstate = function(event) {
    location.reload();
};


// 進到該頁面時，馬上抓取該訓練師的技能
$(async function (){
    let data = await fetchTrainerSkills();
    splicing(data);
})

// 抓取該訓練師已擁有的技能
async function fetchTrainerSkills(){

    let url = `${projectName}/trainer/getTrainerSkills`;
    let formData = new FormData();

    try{
        const response = await fetch(url , {
            method: "POST",
        })

        if (!response.ok){
            throw new Error("錯誤");
        }

        const data = await response.json();

        return data;

    }catch (error){
        console.error(error)
    }
}

// 進到skillList.jsp時抓取資料後馬上呈現
function splicing(data){
    let html = "";
    let arr = new Array();


        arr.push(` <table class="table table-hover text-center align-middle border">
            <thead class="table-light">
                <tr>
                    <th>技能編號</th>
                    <th>技能名稱</th>
                    <th>刪除</th>
                </tr>
            </thead>
            <tbody class="table-group-divider">`);

            data.forEach(item =>{
                arr.push(`<tr>
                    <td>${item.skillNo}</td>
                    <td>${item.skillName}</td>
                    <td><button class="btn btn-danger delete-button" data-id="${item.skillNo}">刪除</button></td>
                </tr>`);
            });
            arr.push(`</tbody></table>
                        `);
    html = arr.join("");
    showList.innerHTML = html;
}

// 點擊新增專業技能時，顯示新增頁面
$(document).on("click" ,"button.addSkill",function (){
    $("h1").text("");
    addSkillhtml();

    let newUrl = `${projectName}/backend/trainer/skillList.jsp`; // 想要添加的新網址
    let newState = { page: "寵毛導師Woof" }; // 新狀態物件

    history.pushState(newState, "", newUrl);
})

// html 新增畫面
async function addSkillhtml(){

    let data = await fetchNotExistSkill();
    console.log(data);

    let html = "";
    let arr = new Array();
    arr.push(`<div class="container">
                <div class="row mx-auto">
                    <div class="align-middle">
                        <h1 class="text-center">新增專業技能</h1>
                        <select id="skillSelect" class="form-select text-center my-5">
                        <option>請選擇專業技能</option>`);

    data.forEach(item=>{
        arr.push(`<option value="${item.skillNo}">${item.skillName}</option>`)
    })


    arr.push(`</select>
               <div class="text-center mx-auto mt-5">
                   <button type="button" class="btn btn-primary add">新增專業技能</button>
                   <button type="button" class="btn btn-secondary" onclick="window.location.reload();">取消新增</button>
               </div>
           </div>
        </div>
    </div>`);
    html = arr.join("");
    $(".divBtn").hide();
    showList.innerHTML = html;
}

// 抓取尚未擁有的技能
async function fetchNotExistSkill(){
    let url = `${projectName}/skill/getNotExistSKill`;

    try{
        const response = await fetch(url , {
            method: "POST"
        })
        if (!response.ok){
            throw new Error("錯誤")
        }

        const data = await response.json();

        return data;

    }catch (error){
        console.error("Error" ,error);
    }
}

// 新增技能
$(document).on("click" , "button.add" , function (){
    addSkillsList();
})

// Fetch新增技能到資料庫
async function addSkillsList(){
    let url = `${projectName}/SkillsList/addSkillsList`;

    try{
        const response = await fetch(url , {
            method: "POST",
            headers : {
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body:"skillNo="+$(":selected").val()
        })

        if (!response.ok){
            throw new Error("錯誤");
        }
        const data = await response.json();

        console.log(data)

        if (data.message){
            await Swal.fire({
                title: "成功!",
                text: "專業技能已新增成功!",
                icon: "success"
            });
        }else{
            await Swal.fire({
                title: "失敗!",
                text: "請重新嘗試!",
                icon: "error"
            });
        }
        location.href = `${projectName}/backend/trainer/skillList.jsp`;
    }catch (error){
        console.error("Error" , error);
    }
}
// 刪除技能
$(document).on("click" , "button.delete-button" , async function (){
    await Swal.fire({
        title: "確定要刪除嗎?",
        text: "該執行操作後無法復原",
        icon: "警告",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "是, 刪除!"
    }).then( async (result) => {
        if (result.isConfirmed) {
            let data = await fetchDeleteSkill($(this).data("id"));
            if (data.message){
                await Swal.fire({
                    title: "刪除成功!",
                    text: "已成功刪除該技能",
                    icon: "success"
                });
            } else {
                await Swal.fire({
                    title: "刪除失敗!",
                    text: "請重新嘗試",
                    icon: "error"
                });
            }
            location.href = `${projectName}/backend/trainer/skillList.jsp`;
        }
    });
})

// 刪除技能
async function fetchDeleteSkill(skillNo){
    let url = `${projectName}/SkillsList/deleteSkillsList`;
    try{
        const response = await fetch(url , {
            method : "POST",
            headers : {
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "skillNo=" +skillNo
        })

        if (!response.ok){
            throw new Error("錯誤");
        }
        const data = await response.json();
        return data;
    }catch (error){
        console.error("Error" ,error);
    }
}