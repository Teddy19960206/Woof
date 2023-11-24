let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
const myModal = new bootstrap.Modal(document.getElementById('myModal'), {});
let date= [];

let selected = false;

var calendarEl = document.getElementById('calendar');

const calendar = new FullCalendar.Calendar(calendarEl, {
    headerToolbar: {
        left: 'prev,next',
        right: 'title'
    },
    timeZone:'local',
    locale : "zh-TW",
    initialDate: new Date(),
    // navLinks: true, // can click day/week names to navigate views
    selectable: true,  // 可以點擊日曆事件
    selectMirror: true, //
    hiddenDays:[], // 隱藏星期六 與 星期日
    eventStartEditable : false, // 禁止拖拉

    select: function(arg) {

        if (new Date() < arg.start && !selected){

            if (date.some(item => arg.startStr === item)) {
                calendar.unselect(); // 取消選擇
                return; // 結束函數執行
            }
            let newDate = new Date(arg.startStr);
            let year = newDate.getFullYear();
            let month = newDate.getMonth() + 1;
            let day = newDate.getDate();

            $("#hideDate").html(arg.startStr);
            $("#showDate").html(`<label data-value="${arg.startStr}">${year}年${month}月${day}日</label>`);
            myModal.show();

            calendar.unselect()
        }

    },

    eventClick: function(arg) {

        if (date.some(item => arg.event.startStr === item)) {
            calendar.unselect(); // 取消選擇
            return; // 結束函數執行
        }

        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {

                cancelButton: "btn btn-danger",
                confirmButton: "btn btn-success"
            },
            buttonsStyling: false
        });
        swalWithBootstrapButtons.fire({
            title: "確定要刪除嗎?",
            text: "您將無法恢復此狀態！",
            icon: "警告!",
            showCancelButton: true,
            confirmButtonText: "是, 刪除",
            cancelButtonText: "否, 取消!",
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                // 這裡要做ajax發送delete資料庫動作
                swalWithBootstrapButtons.fire({
                    title: "已刪除",
                    text: "刪除成功",
                    icon: "success"
                });
                // 刪除後全部元素刪除，全部重新抓取
                removeAllEvents();
                fetchDetailByDate($("#year").text() , $("#month").text());
                // 清空預約清單
                reserveDate = "";
                selected = false;
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire({
                    title: "已取消",
                    text: "已取消刪除的操作",
                    icon: "error"
                });
            }
        });
    },

    editable: true,  // 可編輯
    dayMaxEvents: true, // allow "more" link when too many events
    events: [

    ],

    datesSet: function (info) {
        removeAllEvents();
        // 加上七天，避免取得到上個月的日期，
        let date = new Date(info.start);
        date.setDate(date.getDate() + 7);
        fetchDetailByDate(date.getFullYear() , date.getMonth()+1);
        $("#year").text(date.getFullYear());
        $("#month").text(date.getMonth()+1);

        if (reserveDate != null){
            console.log("123");
            calendar.addEvent({
                title: "修改日期",
                start: reserveDate,
                backgroundColor:"#0000ff"
            })
        }
    },
});

function removeAllEvents(){
    let allEvents = calendar.getEvents();
    allEvents.forEach(function (event){
        event.remove();
    })
}

document.addEventListener('DOMContentLoaded',  function() {
    calendar.render();
});



async function fetchDetailByDate(year , month){

    let url = `${projectName}/scheduleDetail/getDetails`;
    try{
        const response = await fetch(url , {
            method : "POST",
            headers:{
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "year="+year+"&month="+month
        })
        if (!response.ok){
            throw new Error("網路異常")
        }
        const data = await response.json();
        data.forEach(item=>{
            calendar.addEvent({
                title:`訓練師： ${item.trainer.administrator.adminName}   時間：晚上`,
                start: item.classDate,
                backgroundColor:"#ff0000"
            });
            date.push(item.classDate);
        })

    }catch (error){
        console.error('Error', error);
    }

}

// --------------------------- 預約清單 ---------------------------------------
let reserveDate;

// 新增預約日期
$("#reserveBtn").on("click" , function (){

    calendar.addEvent({
        title: "修改日期",
        start: $("#hideDate").text(),
        backgroundColor:"#0000ff"
    })
    myModal.hide();
    selected = true;
    reserveDate = $("#hideDate").text();

    console.log(reserveDate)
});

async function change(){
    $("#calendar").hide();
    $("#next").hide();

    let data = await detailedit($("#detailNo").val());

    let html = `<select class="form-select" style="width: 200px">`
        data.forEach((item)=>{
            html += `<option value="${item.trainerNo}" ${item.administrator.adminName == $("trianer").val() ? 'selected' : ''}>${item.administrator.adminName}</option>`;
        })
        html += `</select>`;

    $("#showSelect").html(html);

    $("#confirm").show();
}


async function detailedit(id){

    let url = `${projectName}/scheduleDetail/edit`;

    let formData = new FormData();
    formData.append("Detail" ,id );

    try{
        const response = await fetch(url ,{
            method : "POST",
            body : formData
        });

        if (!response.ok){
            throw new Error('Network response was not ok');
        }
        // 取得資料後，進行拼圖，並打到頁面上
        const data = await response.json();

        return data;

    }catch (error){
        console.error('Error', error);
    }
}

$("button.modify-button").on("click"  , async function (){

    let url = `${projectName}/scheduleDetail/modify`;

    let formData = new FormData();
    formData.append("gcsdNo" ,$("#detailNo").val());
    formData.append("trainerNo", $(":selected").val());
    formData.append("classDate" , reserveDate);


    try{
        const response = await fetch(url , {
            method: "POST",
            body: formData
        })

        if (!response.ok){
            throw new Error("錯誤");
        }

        const data =await response.json();

        if (data.message){
            await Swal.fire({
                title: "Good job!",
                text: "更新成功",
                icon: "success"
            });
        }else{
            await Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "更新失敗"
            });
        }

        window.location.href = `${projectName}/backend/course/schedule.jsp`;

    }catch (error){
        console.error("Error" , error)
    }

})