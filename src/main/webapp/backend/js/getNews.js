let path = window.document.location.pathname;
let project = path.substring( 0 , path.substring(1).indexOf("/")+1);
const getNews = document.getElementById("getNews");
const infoModal = new bootstrap.Modal(document.getElementById('infoModal'), {});
const showInfo = document.getElementById("showInfo");

getNews.addEventListener("click" , function (){
    newsInfoFetch();

    infoModal.show();
})


// 進入網頁時，取得通知數量
$(function(){
    countFetch();
})

// 取得通知數量Fetch
async function countFetch(){
    let url = `${project}/schedule/countInfo`;

    try {
        const response  = await fetch(url , {
            method : "POST"
        })
        if (!response.ok){
            throw new Error("錯誤")
        }
        const data = await response.text();

        if(data == 0){
            getNews.innerText = "通知";
            getNews.disabled = true ;
        }else{
            html  = `通知<span class="position-absolute top-0 start-50 translate-middle badge rounded-pill bg-danger">
                   ${data}
                </span>`;

            getNews.innerHTML = html;
            getNews.disabled = false ;
        }
    }catch (error){
        console.error('Error', error);
    }
}


// 取得通知訊息
async function newsInfoFetch(){
    let url = `${project}/schedule/getNewsRedis`;

    try {
        const response  = await fetch(url , {
            method : "POST"
        })
        if (!response.ok){
            throw new Error("錯誤")
        }
        const data = await response.json();


        if (data){

            let html = "";
            let arr = new Array();

            arr.push(`<table class="table table-hover text-center align-middle border">
                        <thead class="table-light">
                            <tr>
                                <th>課程編號</th>
                                <th>課程班別</th>
                                <th>課程名稱</th>
                                <th>最低報名數</th>
                                <th>已報名人數</th>
                                <th>截止報名日期</th>
                                <th>課程延期</th>
                                <th>課程取消</th>
                            </tr>
                        </thead>
                            <tbody class="table-group-divider">`);


            data.forEach(item =>{
                arr.push(
                `<tr>
                    <td>${item.gcsNo}</td>
                    <td>${item.groupCourse.classType.ctName}</td>
                    <td>${item.groupCourse.skill.skillName}</td>
                    <td>${item.minLimit}</td>
                    <td>${item.regCount}</td>
                    <td>${item.gcsEnd}</td>
                    <td><button type="button" class="btn btn-primary schedulePostpone" data-id="${item.gcsNo}">延期</button></td>
                    <td><button type="button" class="btn btn-danger schudeleCancel" data-id="${item.gcsNo}">退款</buttonm></td>
                </tr>`);
            })


            arr.push(` </tbody></table>`);

            html = arr.join("");

            showInfo.innerHTML = html;
        }else {
            infoModal.hide();

            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "沒有資料"
            });
        }

    }catch (error){
        console.error('Error', error);
    }
}

// 延期課程
$(document).on("click" , "button.schedulePostpone" , function(){
    window.location.href = `${project}/backend/course/delaySchedule.jsp?no=${this.getAttribute("data-id")}`;
});

// 取消報名
$(document).on("click" , "button.schudeleCancel" , function(){

    Swal.fire({
        title: "確定退款嗎?",
        text: "您將無法恢復此狀態！",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "是, 退款"
    }).then((result) => {
        if (result.isConfirmed) {
            // 需發送到後端進行修改 ？？？？？？？？？？？？？？？？？？

            refundAllFetch(this.getAttribute("data-id"));
            Swal.fire({
                title: "已刪除",
                text: "刪除成功",
                icon: "success"
            });
        }
    });
}) ;


async function refundAllFetch(gcsNo){
    let url = `${project}/groupOrder/refundAllBySchedule`;
    try{
        const response = await fetch(url ,{
            method: "POST",
            headers : {
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "gcsNo="+gcsNo
        })

        if (!response.ok){
            throw new Error("異常失敗")
        }

        const data = await response.json();

        if (data.message){
            await Swal.fire({
                title: "Good job!",
                text: `${data.message}`,
                icon: "success"
            });
        }else{
            await Swal.fire({
                title: "Oops...",
                text: "退款失敗",
                icon: "error"
            });
        }
        window.location.reload();

    }catch (error){
        console.error('Error', error);
    }


}

