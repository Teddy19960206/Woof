
let pathurl = window.location.pathname;
let pathname = pathurl.split('/')[1];


document.getElementById("button").onclick = function (){
    fetchData();
}
async function fetchData(){

    let url = `/${pathname}/groupcourse?action=getByClassType&classType=` + document.getElementById("selectClass").value;

    try{
        const response = await fetch(url);
        if (!response.ok){
            throw new Error('Network response was not ok');
        }
        const data = await response.json();



        html = "";

        html += `<table class="table table-stripclassNamext-center" border="1">
        <thead>
        <tr>
            <th>GC_NO</th>
            <th>SKILL_NAME</th>
            <th>COURSE_PHOTO</th>
            <th>COURSE_CONTENT</th>
            <th>COURSE_STATUS</th>
            <th>修改</th>
        </tr>
        </thead>
        <tbody id="mybody">`;


        data.forEach((item)=>{
            console.log(item)

            html+= `<tr>
            <td>${item.gcNo}</td>
            <td>${item.skill.skillName}</td>
            <td>`;

            if(item.coursePhoto instanceof Object && Object.keys(item.coursePhoto).length > 0){
                html +=  `<img src="/${pathname}/DBPngReader?action=groupCourse&id=${item.gcNo}" style="width: 100px; height: 100px" >`;
            }


            html += `</td>

            <td>${item.courseContent}</td>
            <td>${item.courseStatus}</td>
            <td><button type="button" class="modify-button" data-id="${item.gcNo}">修改</td>
        </tr>`;
        })

        html += `</tbody></table>`;

        let tbody= document.querySelector("div.row");
        tbody.innerHTML = html;

    }catch (error){
        console.error('Error', error);
    }
}

$(document).on("click" , ".modify-button" ,function (e){
    let url = `/${pathname}/groupcourse?action=modifiedPage&id=${this.getAttribute("data-id")}`

    console.log(url);
    window.location.href = url;

})



