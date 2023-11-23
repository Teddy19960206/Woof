
let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);


// $(function (){
//     $("#adultClass").on("click" , async function (){
//         let data =  await fetchSchedule(2);
//         splicing(data);
//     });
//
//     $("#puppyClass").on("click", async function (){
//         let data =  await fetchSchedule(1);
//         splicing(data);
//     });
// });


$(document).on("click", "button.classType" , async function (){
    let data = await fetchSchedule($(this).data("id"));
    splicing(data);
})

// 抓課程資訊
async function fetchSchedule(classType){

    try{

        let url = `${projectName}/schedule/getListClass`;
        let formData = new FormData();
        formData.append("classType" , classType);
        formData.append("status" , "1");

        const response = await fetch(url,{
            method : "POST",
            body : formData
        })

        if (!response.ok){
            throw new Error("錯誤")
        }

        const data = await response.json();
        console.log(data);
        return data;

    }catch (error){
        console.log(error)
    }
}

// 拼接團體課程資訊
async function splicing(data){
    let html = "";
    let count = 1;
    data.forEach( item =>{
    html +=`
        <div class="container" data-aos="fade-up" data-aos-duration="3000">
            <div class="card mb-5">
                <div class="row g-0">
                    <div class="col-md-4 myImg">
                        <img src="${projectName}/DBPngReader?action=groupCourse&id=${item.groupCourse.gcNo}"
                             class="img-fluid rounded-start myPhoto" onerror="this.onerror=null; this.src='${projectName}/webutil/images/cuteDog.jpg';">
                    </div>
                    <div class="col-md-8 myCard">
                        <div class="card-body">
                            <h5 class="card-title text-danger">${item.groupCourse.skill.skillName}</h5>
                            <p class="card-text">${item.groupCourse.courseContent}</p>
                            <p class="card-text"><small class="text-body-secondary">報名開始截止：${item.gcsEnd}</small></p>
                            <p class="card-text"><small class="text-body-secondary">價格：${item.gcsPrice}</small></p>
                        </div>
                        <div class="d-flex justify-content-end mb-1 myBtn">
                            <button class="button-62" data-bs-toggle="modal" data-bs-target="#myModal${count}" onclick="fetchClassDate(${item.gcsNo} , 'classDate${count}')">報名詳情</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                <!-- The Modal -->
        <div class="modal fade" id="myModal${count}">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
        
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">團體課程報名資訊</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
        
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div class="modal-div">
                            <img src="${projectName}/webutil/images/Carousel01.jpg" class="modal-img">
                        </div>
                        <div class="container">
                            <div class="row">
                                <h1 class="text-center m-3">${item.groupCourse.classType.ctName}</h1>
                                <h5>課程內容：</h5>
                                <p>${item.groupCourse.courseContent}</p>
                                <div class="col-6">
                                    <p>訓練師：${item.trainer.administrator.adminName}</p>
                                    <p class="text-danger">報名開始日期：${item.gcsStart}</p>
                                    <p class="text-danger">報名截止日期：${item.gcsEnd}</p>
                                    <p>價格：${item.gcsPrice}元</p>
                                </div>
                                <div class="col-6">
                                    <p>最低開課人數：${item.minLimit}位</p>
                                    <P>最多開課人數：${item.maxLimit}位</P>
                                    <p class="text-danger">目前已報名人數：${item.regCount}位</p>
                                </div>
                                <p>上課日期：</p>
                            </div>
                            <div class="classDate${count}"></div>
                        </div>
                    </div>
        
                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" onclick="registration(${item.gcsNo})">確定報名</button>
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">取消報名</button>
                    </div>
        
                </div>
            </div>
        </div>`;
        count++;
    })
    $("div#show").html(html);

}

// 點選課程詳情時 fetch 上課日期

async function fetchClassDate(gcsNo , divClass){
    try{
        let url = `${projectName}/scheduleDetail/detail/${gcsNo}`;

        const response = await fetch(url,{
            method : "POST"
        })

        if (!response.ok){
            throw new Error("錯誤")
        }

       const data = await response.json();
        let html = "";

        data.forEach(item =>{
            html += `<small class="text-body-secondary me-5">${item.classDate}</small>`;
        });


        $("div."+divClass).html(html);


    }catch (error){
        console.log(error)
    }
}


function registration(gcsNo){
    window.location.href = `${projectName}/groupOrder/getGroupInfo/${gcsNo}`;

}