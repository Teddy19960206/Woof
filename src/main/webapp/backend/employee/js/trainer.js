

let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);

window.addEventListener("load" , async function (){
    const data = await getAllTrainers();
    selectList(data);
})

async function getAllTrainers(){

    let url = `${projectName}/trainer/getTrainers`;

    try {
        const response = await fetch(url , {
            method : "POST"
        })

        if (!response.ok){
            throw new Error("錯誤");
        }

        const data =  await response.json();

        return data;
    }catch (error){
        console.log(error)
    }

}

function selectList(data){
    html = `<select name="trainer">`;
    data.forEach(item=>{
        html += `<option value="${item.trainerNo}">${item.administrator.adminName}</option>`;
    })
    html += `</select>
        <button type="button" id="query">查詢訓練師技能</button>
    `;

    document.getElementById("showTrainers").innerHTML = html;
}


$(document).on("click" , "button#query" , async function (){
    const data = await getTrainerSkills($("option:selected").val());
    showTrainerSkills(data);
});


async function getTrainerSkills(trainerNo){

    let url = `${projectName}/trainer/getTrainerSkills`;
    let formData = new FormData();
    formData.append("trainerNo" , trainerNo)

    try{
        const response = await fetch(url , {
            method: "POST",
            body : formData
        })

        if (!response.ok){
            throw new Error("錯誤");
        }

        const data = await response.json();

        return data;

    }catch (error){
        console.log(error)
    }
}


function showTrainerSkills(data){
    html = `<table><tr><th>專業名稱</th></tr>`;

    data.forEach(item =>{
        html += `<tr><th>${item.skillName}</th></tr>`
    })

    html += `</table>`;

    document.getElementById("showTrianerSkills").innerHTML = html;
}