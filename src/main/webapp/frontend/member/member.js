
let pathurl = window.location.pathname;
let pathname = pathurl.split('/')[1];


document.getElementById("button").onclick = function() {
	fetchData();
}
async function fetchData() {

	let url = `/${pathname}/member?action=getByMember&member=` + document.getElementById("selectMember").value;

	try {
		const response = await fetch(url);
		if (!response.ok) {
			throw new Error('Network response was not ok');
		}
		const data = await response.json();



		html = "";

		html += `<table class="table table-stripclassNamext-center" border="1">
        <thead>
        <tr>
            <th>MEM_NO</th>
            <th>MEM_NAME</th>
            <th>MEM_GENDER</th>
            <th>MEM_PHOTO</th>
            <th>MEM_EMAIL</th>
            <th>MEM_TEL</th>
            <th>MEM_PASSWORD</th>
            <th>MEM_ADDRESS</th>
            <th>MEM_BD</th>
            <th>MEM_MOMOPOINT</th>
            <th>MEM_TOTLCLASS</th>
            <th>MEM_NAME</th>
            <th>修改</th>
        </tr>
        </thead>
        <tbody id="mybody">`;


		data.forEach((item) => {
			console.log(item)

			html += `<tr>
            <td>${item.memNo}</td>
            <td>`;

			if (item.coursePhoto instanceof Object && Object.keys(item.coursePhoto).length > 0) {
				html += `<img src="/${pathname}/DBPngReader?action=member&id=${item.memNo}" style="width: 100px; height: 100px" >`;
			}


			html += `</td>

            <td>${item.courseContent}</td>
            <td>${item.courseStatus}</td>
            <td><button type="button" class="modify-button" data-id="${item.gcNo}">修改</td>
        </tr>`;
		})

		html += `</tbody></table>`;

		let tbody = document.querySelector("div.row");
		tbody.innerHTML = html;

	} catch (error) {
		console.error('Error', error);
	}
}

$(document).on("click", ".modify-button", function(e) {
	let url = `/${pathname}/groupcourse?action=modifiedPage&id=${this.getAttribute("data-id")}`

	console.log(url);
	window.location.href = url;

})


