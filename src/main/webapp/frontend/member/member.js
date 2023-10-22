//let pathurl = window.location.pathname;
//let pathname = pathurl.split('/')[1];
//
//document.getElementById("button").addEventListener('click', fetchData);
//
//async function fetchData() {
//    let memberValue = document.getElementById("selectMember").value;
//    let url = `/${pathname}/member?action=getByMember&member=${memberValue}`;
//
//    try {
//        const response = await fetch(url);
//        if (!response.ok) {
//            throw new Error('Network response was not ok');
//        }
//        const data = await response.json();
//
//        let html = `
//        <table class="table table-striped text-center" border="1">
//            <thead>
//                <tr>
//                    <th>MEM_NO</th>
//                    <th>MEM_PHOTO</th>
//                    <th>MEM_NAME</th>
//                    <th>MEM_GENDER</th>
//                    <th>MEM_EMAIL</th>
//                    <th>MEM_TEL</th>
//                    <th>MEM_PASSWORD</th>
//                    <th>MEM_ADDRESS</th>
//                    <th>MEM_BD</th>
//                    <th>MEM_MOMOPOINT</th>
//                    <th>MEM_TOTALCLASS</th>
//                    <th>修改</th>
//                </tr>
//            </thead>
//            <tbody>`;
//
//        data.forEach((item) => {
//            html += `<tr>
//                <td>${item.memNo}</td>
//                <td>`;
//
//            if (item.coursePhoto instanceof Object && Object.keys(item.coursePhoto).length > 0) {
//                html += `<img src="/${pathname}/DBPngReader?action=member&id=${item.memNo}" style="width: 100px; height: 100px" >`;
//            }
//
//            html += `</td>
//                <td>${member.memNo}</td>
//                <td>${member.memName}</td>
//                <td>${member.memGender}</td>
//                <td>${member.memEmail}</td>
//                <td>${member.memTel}</td>
//                <td>${member.memPassword}</td>
//                <td>${member.memAddress}</td>
//                <td>${member.memBd}</td>
//                <td>${member.memMomoPoint}</td>
//                <td>${member.memTotalClass}</td>
//                <td><button type="button" class="modify-button" data-id="${item.memNo}">修改</button></td>
//            </tr>`;
//        });
//
//        html += `</tbody></table>`;
//
//        document.querySelector("div.row").innerHTML = html;
//
//    } catch (error) {
//        console.error('Error', error);
//    }
//}
//
//document.addEventListener("click", function(e) {
//    if (e.target && e.target.classList.contains("modify-button")) {
//        let url = `/${pathname}/groupcourse?action=modifiedPage&id=${e.target.getAttribute("data-id")}`;
//        window.location.href = url;
//    }
//});
//
//
