
let pathurl = window.location.pathname;
let pathname = pathurl.split('/')[1];


document.getElementById("button").onclick = function (){
    fetchData();
}

document.addEventListener("DOMContentLoaded", function (){
    fetchData();
});


async function fetchData(){

    let url = `/${pathname}/schedule/getSchedule`;
    let formData = new FormData();
    formData.append("classType" , document.getElementById("selectClass").value);

    try{
        const response = await fetch(url ,{
            method : "POST",
            body:formData
        });

        if (!response.ok){
            throw new Error('Network response was not ok');
        }

        const data = await response.json();
        html = `<table class="table table-stripclassNamext-center" border="1">
        <thead>
        <tr>
            <th>GCS_NO</th>
            <th>CLASSTYPE</th>
            <th>TRAINER</th>
            <th>GCS_START</th>
            <th>GCS_END</th>
            <th>MIN_LIMIT</th>
            <th>MAX_LIMIT</th>
            <th>COUNT</th>
            <th>GCS_PRICE</th>
            <th>GCS_STATUS</th>
            <th>修改</th>
        </tr>
        </thead>
        <tbody id="mybody">`;

        data.forEach((item)=>{
            html+= `
            <th>${item.gcsNo}</th>
            <th>${item.groupCourse.classType.ctName}</th>
            <th>${item.trainer}</th>
            <th>${item.gcsStart}</th>
            <th>${item.gcsEnd}</th>
            <th>${item.minLimit}</th>
            <th>${item.maxLimit}</th>
            <th>${item.count}</th>
            <th>${item.gcsPrice}</th>
            <th>${item.gcsStatus}</th>
            <td><button type="button" class="modify-button" data-id="${item.gcsNo}">修改</td>
        </tr>`;
        })

        html += `</tbody></table>`;

        let row= document.querySelector("div.row");
        row.innerHTML = html;

    }catch (error){
        console.error('Error', error);
    }
}

// $(document).on("click" , "button.modify-button" ,function (e){
//     let url = `/${pathname}/groupcourse?action=modifiedPage&id=${this.getAttribute("data-id")}`
//
//     console.log(url);
//     window.location.href = url;
//
// })

$(document).on("click" , "button.modify-button" , function (){


    let url = `/${pathname}/schedule/edit/${this.getAttribute("data-id")}`;

    window.location.href = url;

});

$(document).on("click" , "button.registration" , function (){


    let url = `/${pathname}/groupcourse/schedule/xxx/${this.getAttribute("data-id")}`;

    window.location.href = url;

});

// window.addEventListener("load" , function (){
//     var myModal = document.getElementById('exampleModal')
//     var myInput = document.getElementById('myInput')
//
//     myModal.addEventListener('shown.bs.modal', function () {
//         myModal.show();
//         myInput.focus()
//     })
//
// })



