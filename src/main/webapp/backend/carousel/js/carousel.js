
$(async function (){
    let data = await fetchCarousel();
    splicingCarousel(data);
})


async function fetchCarousel(){

    let url = `${project}/Carousels`;
    try{
        const response = await fetch(url , {
            method : "GET"
        });
        if (!response.ok){
            throw new Error("錯誤");
        }
        const data = await response.json();
        return data;
    }catch (error){
        console.error("Error" , error);
    }
}


function splicingCarousel(data){
    let html = "";
    let arr = new Array();
    arr.push(`
      <div class="addCarousel my-3">
        <button class="btn btn-primary">新增輪播</button>
      </div>
        <table class="table table-hover text-center align-middle border">
        <thead class="table-light" >
        <tr>
            <th>編號</th>
            <th>圖片</th>
            <th>標題</th>
            <th>內容</th>
            <th>順序</th>
            <th>是否顯示</th>
            <th>刪除</th>
            <th>修改</th>
        </tr>
        </thead>
        <tbody id="mybody table-group-divider">`);

    data.forEach((item)=>{

        arr.push(`<tr>
                <td>${item.carId}</td>
                <td><img src="${project}/CarouselImage/${item.carId}" style="width: 100px" onerror="this.onerror=null; this.src='${project}/webutil/images/no-image.png';"></td>
                <td>${item.title}</td>
                <td>${item.content}</td>
                <td>${item.sequence != null ? `${item.sequence}` : ''}</td>
                <td>${item.isActive == 0 ? '不顯示' : '顯示'}</td>
                <td><button type="button" class="modifyBtn btn btn-primary" data-id="${item.carId}">修改</button></td>
                <td><button type="button" class="deleteDetail btn btn-danger" data-id="${item.carId}">刪除</button></td> 
            </tr>`);
    });

    arr.push("</tbody></table>");

    html = arr.join("");

    $(".showCarousel").html(html);
}


// -----------------------------   按下修改按鈕  -----------------------------------------

$(document).on("click" , "button.modifyBtn" , async function (){
    let data = await fetchOneCarousel($(this).data("id"));
    modifyPage(data);

    addHistory();
})


async function fetchOneCarousel(carId){

    let url = `${project}/CarouselById/${carId}`;

    try{
        const response = await fetch(url , {
            method : "GET"
        })

        if (!response.ok){
            throw new Error("錯誤");
        }

        const data = await response.json();

        return data;
    }catch (error){
        console.error("Error" , error)
    }
}

function modifyPage(data){
    let html = "";
    let arr = new Array();
    arr.push(`<div class="container">
                <div class="row">
                    <div class="col-6">
                        <div class="col-6 mx-auto">
                            <input type="hidden" name="groupCourseNo" value="${data.carId}">
                                                                           
                            <label class="m-3">是否顯示：</label>
                            <select class="form-select mx-auto" id="isActive">
                                <option ${data.isActive == 0 ? 'selected': ''} value="0">不顯示</option>
                                <option ${data.isActive == 1 ? 'selected': ''} value="1">顯示</option>
                            </select>
                   
                            <label class="m-3">排序順序：</label>
                            <input type="number" id="sequence" class="form-control" value="${data.sequence}" />
                            
                            <label class="m-3">圖片：</label>
                            <div class="col-6 mx-auto">
                                <label for="p_file" class="uploadImage">選擇圖片</label>
                                <input type="file" name="photo" value="" id="p_file" hidden="hidden">
                            </div>
                            
                        </div>
                    </div>
    
                    <div class="col-6">
                        <div class="col-6 mx-auto">
                            <div class="row">
                                <label>圖片預覽</label>
                            </div>
                            <div id="preview">
                                <span class="text">預覽圖</span>
                                <img id="photo" class="preview_img"
                                src="${project}/CarouselImage/${data.carId}" onerror="this.style.display='none'" />
                            </div>
                        </div>
                    </div>
                    <div class="col-7 mx-auto">
                        <label class="m-3">標題：</label>
                        <input type="text" id="title" class="form-control" value="${data.title}" />
                    </div>
                    <div class="col-6">
                    </div>
                    <div class="col-6"></div>
                     <div class="col-auto mx-auto">
                        <label class="m-3">內容：</label>
                        <textarea class="form-control" id="content" style="width: 500px; height: 100px" name="content">${data.content}</textarea>
                     </div>
                    
                    <div class="mt-5 text-center ">
                        <button type="button" class="btn btn-primary modify-button" data-id="${data.carId}">確定修改</button>
                        <button type="button" onclick="window.location.reload()" class="btn btn-secondary" data-id="${data.carId}">取消修改</button>
                    </div>
                </div>
            </div>`);

    html = arr.join("");
    $(".showCarousel").html(html);

    changeSelect();
}
// -------------------------- 圖取圖片 展示到頁面上 ---------------------------
$(document).on("change" , "input#p_file" , function (){

    if (this.files.length > 0) {
        readImg(this.files[0]);
    }
})

// 讀取圖片
function readImg(result) {
    let reader = new FileReader();
    reader.readAsDataURL(result);
    reader.addEventListener("load", function () {
        let img_tag = `<img src="${this.result}" class="preview_img">`;
        $("#preview").html(img_tag);
    });
}

// ------------------------------ 按下確定修改 ----------------------------

$(document).on("click" , "button.modify-button"  ,async function (){
    let data = await modifyCarousel($(this).data("id"));

    if (data){
        await Swal.fire({
            title: "已修改!",
            text: "修改成功!",
            icon: "success"
        });
        window.location.href = `${project}/backend/carousel/carousel.jsp`;
    }else{
        await Swal.fire({
            title: "失敗!",
            text: "修改失敗!",
            icon: "error"
        });
    }
})

async function modifyCarousel(carId){
    let url = `${project}/updateCarousel/${carId}`;
    let formData = new FormData();

    formData.append("image" , document.getElementById("p_file").files[0]);
    formData.append("title" ,document.getElementById("title").value );
    formData.append("content" , document.getElementById("content").value);
    formData.append("sequence" , document.getElementById("sequence").value);
    formData.append("isActive" , document.getElementById("isActive").value);


    try{
        const response = await fetch(url , {
            method : "PUT",
            body: formData
        })
        if (!response.ok){
            throw new Error("錯誤");
        }
        const data = await response.json();

        return data;

    }catch (error){
        console.error("Error" , error)
    }
}


//-------------------------- 刪除 ----------------------------------


$(document).on("click" , "button.deleteDetail" , async function (){


    Swal.fire({
        title: "你確定刪除嗎?",
        text: "此操作無法復原!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "是, 刪除!"
    }).then(async (result) => {
        if (result.isConfirmed) {
            try {
                let data = await deleteCarousel($(this).data("id"));
                await Swal.fire({
                    title: "已刪除!",
                    text: "已經成功刪除資料",
                    icon: "success"
                });
                window.location.reload();
            }
            catch(error){
                await Swal.fire({
                    title: "失敗!",
                    text: "刪除失敗",
                    icon: "error"
                });
            }

        }
    });


})


async function deleteCarousel(carId){
    let url = `${project}/deleteCarousel/${carId}`;

    try{
        const response = await fetch(url , {
            method : "DELETE"
        })

        if (!response.ok){
            throw new Error("錯誤");
        }
        const data = await response.json();

        return data;
    }catch (error){
        console.error("Error" , error);
    }

}

// -----------------------------新增 ---------------------------

$(document).on("click" , ".addCarousel" , function (){
    let html = "";
    let arr = new Array();
    arr.push(`<div class="container">
                <div class="row">
                    <div class="col-6">
                        <div class="col-6 mx-auto">
                            
                            <label class="m-3">標題：</label>
                            <input type="text" id="title" class="form-control"  />
                            
                            <label class="m-3">是否顯示：</label>
                            <select class="form-select mx-auto" id="isActive">
                                <option value="0">不顯示</option>
                                <option value="1">顯示</option>
                            </select>
                   
                            <label class="m-3">排序順序：</label>
                            <input type="number" id="sequence" class="form-control"/>
                            
                            <label class="m-5">圖片：</label>
                            <div class="col-6 mx-auto m-5">
                                <label for="p_file" class="uploadImage">選擇圖片</label>
                                <input type="file" name="photo" value="" id="p_file" hidden="hidden">
                            </div>
                            
                        </div>
                    </div>
    
                    <div class="col-6">
                        <div class="col-6 mx-auto">
                            <div class="row">
                                <label>圖片預覽</label>
                            </div>
                            <div id="preview">
                                <span class="text">預覽圖</span>
                                <img id="photo" class="preview_img"
                                onerror="this.style.display='none'" />
                            </div>
                        </div>
                    </div>
    
                    <div class="col-auto mx-auto">
                        <label class="m-3">內容：</label>
                        <textarea class="form-control" id="content" style="width: 500px; height: 100px" name="content"></textarea>
                     </div>
                    
                    <div class="mt-5 text-center ">
                        <button type="button" class="btn btn-primary add-button">確定新增</button>
                        <button type="button" class="btn btn-secondary" onclick="window.location.reload()">取消新增</button>
                    </div>
                </div>
            </div>`);
    html = arr.join("");

    $(".showCarousel").html(html);
    changeSelect();
    addHistory();
})

async function addCarousel(){
    let url = `${project}/addCarousel`;
    let formData = new FormData();
    formData.append("image" , document.getElementById("p_file").files[0]);
    formData.append("title" ,document.getElementById("title").value );
    formData.append("content" , document.getElementById("content").value);
    formData.append("sequence" , document.getElementById("sequence").value);
    formData.append("isActive" , document.getElementById("isActive").value);


    try{
        const response = await fetch(url , {
            method : "POST",
            body: formData
        })
        if (!response.ok){
            throw new Error("錯誤");
        }
        const data = await response.json()

        if (data){
            await Swal.fire({
                title: "已新稱!",
                text: "新增成功!",
                icon: "success"
            });
            window.location.reload();
        }else{
            await Swal.fire({
                title: "失敗!",
                text: "新增失敗，請確認填寫的值是否正常!",
                icon: "error"
            });
        }


    }catch (error){
        console.error("Error" , error);
    }
}


$(document).on("click" , "button.add-button" , async function (){

    let p_file = document.getElementById("p_file").files[0];
    let title = document.getElementById("title").value;
    let content = document.getElementById("content").value;

    let errorMsgs = new Array();

    if (p_file == null || p_file == 0){
        errorMsgs.push("請選擇圖片");
    }
    if (title.trim().length == 0 || title.length == ""){
        errorMsgs.push("請輸入標題")
    }
    if (content.trim().length == 0 || content.length == ""){
        errorMsgs.push("請輸入內容")
    }

    if (errorMsgs.length > 0){
        let error = errorMsgs.join(" ");
        await Swal.fire({
            title: "失敗!",
            text: `${error}`,
            icon: "error"
        });
        return;
    }
    addCarousel();
})

// -------------------------------------- 動態變化select選單 -----------------------
$(document).on("change" , "#isActive" ,function (){
    changeSelect();
})

function changeSelect(){
    if ($("#isActive").val() == 0){
        $("#sequence").val("");
        $("#sequence").prop("disabled" , true);
    }else{
        $("#sequence").prop("disabled" , false);
    }
}

// ------------------------------- 新增歷史紀錄 ------------------------------
function addHistory(){
    let newUrl = `${project}/backend/carousel/carousel.jsp`; // 想要添加的新網址
    let newState = { page: "寵毛導師 Woof | 輪播管理" }; // 新狀態物件

    history.pushState(newState, "新頁面標題", newUrl);
}

window.addEventListener("popstate", function(event) {
    if (event.state && event.state.page) {
        // 加載新頁面內容
        location.reload();
    }
});