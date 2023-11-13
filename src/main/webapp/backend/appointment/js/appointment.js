$(document).ready(function() {
    // 當按鈕被點擊時觸發
    $("#button").click(function() {
        // 獲取查詢條件的值
        var member = $("#member").val();
        var trainer = $("#trainer").val();

        // 使用AJAX發送POST請求到後端
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/privatetrainingappointmentform", // 替換成你的後端端點
            data: {
                member: member,
                trainer: trainer,
                
            },
            success: function(data) {
                // 成功接收後端數據後更新表格
                updateTable(data);
            },
            error: function(error) {
                console.log("Error:", error);
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
            row += "<td>" + data[i].gcoNo + "</td>";
            row += "<td>" + data[i].member.memName + "</td>";
            row += "<td>" + data[i].groupCourseSchedule.groupCourse.trainerName + "</td>";
            // 其他列的資料添加類似的代碼
            row += "<td><button type='button' class='detail-btn btn btn-primary' data-id='" + data[i].gcoNo + "'>查看</button></td>";
            row += "</tr>";

            tbody.append(row);
        }
    }
});