let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
$(document).on("click" , "button.edit-button" , function (){
    let skillName = $(this).closest("tr").find("td").eq(1);
    let modifyBtn = $(this).closest("tr").find("button").eq(1);

    modifyBtn.prop("disabled" , false);
    $(this).prop("disabled" , true);

    skillName.html(`<input type='text' class='form-control text-center' value="${skillName.text()}"/>`);

});
$(document).on("click" , "button.modify-button" , async function (){

    let skillNo = $(this).closest("tr").find("td").eq(0);
    let skillName = $(this).closest("tr").find("input");
    let editBtn = $(this).closest("tr").find("button").eq(0);


    let data = await modifyFetch(skillNo.text().trim() , skillName.val().trim());

    if (data){

        let skill = $(this).closest("tr").find("td").eq(1);
        skill.text(skillName.val())

        editBtn.prop("disabled" , false);
        $(this).prop("disabled" , true);
    }else {
        await Swal.fire({
            title: "Oops...",
            text: "更新失敗",
            icon: "error"
        });
    }
});
$(document).on("click" , "button.delete-button" , async function (){

    if (confirm("確定要刪除嗎")) {
        let skillNo =  $(this).closest("tr").find("td").eq(0);
        let data = await deleteFetch(skillNo.text());
        if (data.message){

            await Swal.fire({
                title: "Good job!",
                text: `${data.message}`,
                icon: "success"
            });

            $(this).closest("tr").remove();
        }else{

            await Swal.fire({
                title: "Oops...",
                text: "刪除失敗",
                icon: "error"
            });
        }
    }

});


async function modifyFetch(skillNo , skillName){

    let url = `${projectName}/skill/modified`;

    try{
        const response = await fetch(url , {
            method: "POST",
            headers:{
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "skillNo="+skillNo+"&skillName="+skillName
        })

        if (!response.ok){
            throw new Error("錯誤");
        }

        const data = response.json();

        return data;

    }catch (error){
        console.error('Error', error);
    }


}

async function deleteFetch(skillNo){

    let url = `${projectName}/skill/delete`;
    let formDate = new FormData();
    formDate.append("skillNo" , skillNo)

    try{
        const response = await fetch(url , {
            method: "POST",
            body:formDate
        })

        if (!response.ok){
            await Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "異常錯誤"
            });
            return;
        }

        const data = await response.json();

        return data;

    }catch (error){
        console.error('Error', error);
    }
}

$("#addSkill").on("click" , function (){
    $(".container").html(addInput);
});

let addInput = `
                <div class="row my-5">
                    <h2 align="center" class="my-2">新增技能</h2>
                    <div class="col-6 mx-auto">
                        <input type="text" class="form-control" id="newSkill">
                        <div class="text-center">
                            <button type="button" class="btn btn-primary add-button my-2">確定新增</button>
                            <button type="button" class="btn btn-secondary" onclick="window.location.href='${projectName}/backend/member/trainerSkill.jsp'">取消新增</button
                        </div>
                    </div>
                </div>`;


$(document).on("click", "button.add-button" , async function (){
    let url = `${projectName}/skill/addSkill`;
    let formData = new FormData();
    formData.append("skillName" , document.getElementById("newSkill").value);

    try{
        const response = await fetch(url ,{
            method:"POST",
            body: formData
        })

        if (!response.ok){
            await Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "新增失敗"
            });
            window.location.href = `${projectName}/backend/member/trainerSkill.jsp`;
            return;
        }

        const data = await response.json();

        if (data.message){
            await Swal.fire({
                icon: "success",
                title: "Good job!",
                text: `${data.message}`
            });
        }else{
            await Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "新增失敗"
            });
        }
        window.location.href = `${projectName}/backend/member/trainerSkill.jsp`;

    }catch (error){
        console.error('Error', error);
    }

})