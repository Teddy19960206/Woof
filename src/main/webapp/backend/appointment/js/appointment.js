// 取得項目名
let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
const trainer = $("select#trainerNo");
$(document).ready(function() {
    // 抓取訓練師 下拉式選單內容
    getTrainer();
    
    // 預設先抓取全部資料
    getPta();
    
    // 當按鈕被點擊時觸發
    $("#button").click(function() {
        // 獲取查詢條件的值
        var member = $("#memNo").val();
        var trainer = $("#trainer").val();
		
        // 使用AJAX發送POST請求到後端
        jsonObj =  {
                memNo: member,
                trainerNo: trainer,
            };
        console.log(jsonObj); 
        $.ajax({
            type: "POST",         
            url: '<%=request.getContextPath()%>/privatetrainingappointmentform?action=getpta', // 替換成你的後端端點
            data: jsonObj,
            success: function(data) {
                // 成功接收後端數據後更新表格
                updateTable(data);
                alert("後端成功接收");
            },
            error: function(error) {
                console.log("Error:", error);
                alert("失敗");
            }
        });
    });

    // 定義更新表格的函數
    function updateTable(data) {
        var tbody = $("#show tbody");
        tbody.empty(); // 清空表格內容

        // 遍歷後端返回的數據並添加到表格中
        for (var i = 0; i < data.length; i++) {
            var row = "<tr>";
            row += "<td>" + data[i].ptaNo + "</td>";
            row += "<td>" + data[i].member.memName + "</td>";
            row += "<td>" + data[i].trainer.administrator.adminName + "</td>";
            row += "<td>" + data[i].ptaClass + "</td>";
            row += "<td>" + data[i].ptaComment + "</td>";
            row += "<td>" + data[i].commentTime + "</td>";
            row += "<td>" + data[i].commentUpTime + "</td>";
            row += "<td><button type='button' class='detail-btn btn btn-primary' data-id='" + data[i].gcoNo + "'>查看明細</button></td>";
            row += "</tr>";

            tbody.append(row);
        }
    }
    
    // 抓取訓練師 下拉式選單內容
	async function getTrainer(){
			let url = `${projectName}/trainer/getTrainers`;
    	try{
        	const response = await fetch(url , {
            	method : "POST"
        	})
        	if (!response.ok){
            	throw new Error("錯誤")
        	}
        	const data = await response.json();

        	html = `<option value="">顯示全部</option>`;

        	data.forEach(item =>{
            	html += `<option value="${item.trainerNo}">${item.administrator.adminName}</option>`
        	});

        	trainer.html(html);

    	}catch (error){
        	console.log("訓練師讀取失敗");
    	}
	}
	
	async function getPta(page){

    if (!page){
        page = 1;
    }

    let url = `${projectName}/privatetrainingappointmentform/getpta`;
    try{
        let formData = new FormData();
        formData.append("trainerNo" , document.getElementById("trainerNo").value);
        formData.append("memNo" , document.getElementById("memNo").value);
        formData.append("page" , page);



        const response = await fetch(url , {
            method : "POST",
            body : formData
        })

        if (!response.ok){
            throw new Error("錯誤");
        }
		
        const data = await response.json();

        let html = "";
        let arr = new Array();
        arr.push(` <table class="table table-hover text-center align-middle border">
                    <thead class="table-light">
                    <tr>
                      	<th>預約單編號</th>
                    	<th>會員姓名</th>
                    	<th>訓練師姓名</th>
                    	<th>預約堂數</th>
                    	<th>評論</th>
                    	<th>評論時間</th>
                    	<th>評論修改時間</th>
                    	<th>查看明細</th>
                    </tr>
                  </thead>
                  <tbody class="table-group-divider">`);

        data.data.forEach(item=>{

            arr.push(` <tr>
                          <td>${item.ptaNo}</td>
                          <td>${item.member.memName}</td>
                          <td>${item.trainer.administrator.adminName}</td>
                          <td>${item.ptaClass}</td>
                          <td>${item.ptaComment}</td>
                          <td>${item.commentTime}</td>
                          <td>${item.commentUpTime}</td>
//                          <td><button type="button" class="detail-btn btn btn-primary" data-id="${item.gcoNo}">查看明細</button></td>
                      </tr>`);
        })

        arr.push(`</tbody></table>`);


        // 書籤 ==============================================================
        arr.push(`<nav class="text-center d-flex justify-content-center">
                  <ul class="pagination">
                    <li class="page-item">
                      <button class="page-link" onclick="getPta(${page == 1 ? 1 : page-1})">
                        <span aria-hidden="true">&laquo;</span>
                      </button>
                    </li>`);

        const maxPagesToShow = 3;
        const totalPages = parseInt(data.pageTotal);

        let startPage, endPage;

        if (totalPages <= maxPagesToShow) {
            // 如果總頁數 totalPages 小於或等於您希望顯示的最大頁碼按鈕數量 maxPagesToShow，則直接顯示從第一頁到最後一頁的所有頁碼按鈕。
            startPage = 1;
            endPage = totalPages;
        } else if (page <= Math.floor(maxPagesToShow / 2) + 1){
            // 當前頁位於前半部分，那麼顯示從第一頁到 maxPagesToShow 頁的按鈕。
            startPage = 1;
            endPage = maxPagesToShow;
        } else if (page >= totalPages - Math.floor(maxPagesToShow / 2)) {
            // 當前頁位於後半部分，那麼顯示從 totalPages - maxPagesToShow + 1 頁到最後一頁的按鈕。
            startPage = totalPages - maxPagesToShow + 1;
            endPage = totalPages;
        } else {
            // 當前頁位於中間部分，顯示當前頁前後各 Math.floor(maxPagesToShow / 2) 頁的按鈕。
            startPage = page - Math.floor(maxPagesToShow / 2);
            endPage = page + Math.floor(maxPagesToShow / 2);
        }

        for (let i = startPage; i <= endPage; i++) {
            arr.push(`<li class="page-item ${i === page ? "active" : ""}">
               <button class="page-link" onclick="getPta(${i})">${i}</button>
             </li>`);
        }

        arr.push(`   <li class="page-item">
                        <button class="page-link" onclick="getPta(${page == totalPages ? totalPages : page+1})">
                            <span aria-hidden="true">&raquo;</span></a>
                        </button>
                    </li>
                  </ul>
                </nav>`);

        html = arr.join("");

        show.html(html);

    }catch (error){
        console.log("翻頁失敗")
    }
}
});