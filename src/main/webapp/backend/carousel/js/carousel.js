
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
        console.log(data);
        return data;
    }catch (error){
        console.error("Error" , error);
    }
}


function splicingCarousel(data){
    let html = "";
    let arr = new Array();
    arr.push(`<table class="table table-hover text-center align-middle border">
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
                <td><img src="${project}/CarouselImageß/${item.carId}" style="width: 100px" onerror="this.onerror=null; this.src='${project}/webutil/images/no-image.png';"></td>
                <td>${item.title}</td>
                <td>${item.content}</td>
                <td>${item.sequence}</td>
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
    console.log(data);

    let html = "";
    let arr = new Array();
    arr.push(`<form action="${pageContext.request.contextPath}/groupcourse/modified" method="post" enctype="multipart/form-data">
                <div class="row">
                    <div class="col-6">
                        <div class="col-6 mx-auto">
                            <input type="hidden" name="groupCourseNo" value="${groupCourse.gcNo}">
                            <label class="m-3">技能名稱</label>
                            <select name="skill" class="form-select" style="width: 300px">
                                <c:forEach items="${skills}" var="skill">
                                    <option value="${skill.skillNo}"
                                            <c:if test="${skill.skillNo eq groupCourse.skill.skillNo}">selected</c:if>
                                    >${skill.skillName}</option>
                                </c:forEach>
                            </select>
    
                            <label class="m-3">班別</label>
                            <select name="classType" class="form-select" style="width: 300px">
                                <c:forEach items="${classTypes}" var="classType">
                                    <option value="${classType.ctNo}"
                                            <c:if test="${classType.ctNo eq groupCourse.classType.ctNo}">selected</c:if>>
                                            ${classType.ctName}</option>
                                </c:forEach>
                            </select>
                            <label class="m-3">圖片：</label>
                            <div class="col-6 mx-auto">
                                <label for="p_file" class="uploadImage">選擇圖片</label>
                                <input type="file" name="photo" value="" id="p_file" hidden="hidden">
                            </div>
    
                            <label class="m-3">課程狀態：</label>
                            <select name="status" class="form-select">
                                <option value="0" <c:if test="${groupCourse.courseStatus eq 0}">selected</c:if>>下架</option>
                                <option value="1" <c:if test="${groupCourse.courseStatus eq 1}">selected</c:if>>上架</option>
                            </select>
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
                                src="${pageContext.request.contextPath}/DBPngReader?action=groupCourse&id=${groupCourse.gcNo}"
                                onerror="this.style.display='none'" />
                            </div>
                        </div>
                    </div>
    
                    <div class="col-12 mt-5 text-center">
                        <label>是否顯示：</label>
                        <select><option ${data.isActive == 0 ? 'selected': ''}>不顯示</option><option ${data.isActive == 1 ? 'selected': ''}>顯示</option></select>
                    </div>
                    <div class="mt-5 text-center ">
                        <button type="submit" class="btn btn-primary">確定修改</button>
                        <button type="button" onclick="history.back()" class="btn btn-secondary">取消修改</button>
                        <button type="button" id="delete" data-id="${groupCourse.gcNo}" class="btn btn-danger">刪除圖片</button>
                    </div>
                </div>
            </form>`);

    html = arr.join("");
    $(".showCarousel").html(html);
}