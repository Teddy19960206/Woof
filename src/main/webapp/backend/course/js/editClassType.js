
$(document).on("click" , "button.edit-button" , function (){
    let modifyBtn = $(this).closest("tr").find("button").eq(1);
    modifyBtn.prop("disabled" , false)

    $(this).prop("disabled" , true);
    let className = $(this).closest("tr").find("td").eq(1);
    className.html(`<input type='text' name='classType' value='${className.text()}'>`);
})

// 點擊確認修改後須先發送 fetch 到後端判斷是否更新成功，若是成功，則還原。
$(document).on("click" , "button.modify-button" ,  async function (){

    // 獲取修改後的文字
    let name = $(this).closest("tr").find("input").val().trim();

    if ( 0 == name.length){
        throw new Error("請勿空白名稱");
    }

    // 獲取 classType 編號
    let id = $(this).closest("tr").find("td").eq(0).text();

    // 發送請求進行修改
    let result = await fetchData(id , name , "modified");

    if (result === 1){
        let editBtn = $(this).closest("tr").find("button").eq(0);
        editBtn.prop("disabled" , false)
        $(this).prop("disabled" , true);
        let className = $(this).closest("tr").find("td").eq(1);

        className.html(`${name}`);
    }
})

$(document).on("click", "button.delete-button" , async function (){


    // 獲取 classType 編號
    let id = $(this).closest("tr").find("td").eq(0).text();

    // 發送請求進行修改
    let result = await fetchData(id , null , "delete");

    if (result === 1){
        $(this).closest("tr").remove();
    }

})

async function fetchData(id , name , action){
    let pathurl = window.location.pathname;
    let pathname = pathurl.split('/')[1];

    let url = `/${pathname}/classtype/${action}`;

    let formData = new FormData();
    formData.append("ctNo" , id);
    formData.append("ctName" , name)

    try{
        const response = await fetch(url , {
            method : "POST" ,
            body : formData
        })

        if (!response.ok){
            throw new Error('Network response was not ok')
        }
        return 1;
    }catch (error){
        console.error("error" , error);
    }

    return 0;
}