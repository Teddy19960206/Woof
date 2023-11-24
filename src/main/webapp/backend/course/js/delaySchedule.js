let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
const calendarEl = document.getElementById('calendar');
const myModal = new bootstrap.Modal(document.getElementById('myModal'), {});
const next = document.getElementById("next");
const show = document.getElementById("show");
const errorMsg = document.getElementById("errorMsg");
const calendarStr = document.getElementById("calendarStr");
const submitBtn = document.getElementById("submitBtn");
const add = document.getElementById("add");
const params = new URLSearchParams(window.location.search);
const reason = document.getElementById("reason");
const reasonErr = document.getElementById("reasonErr");
const startDate = document.getElementById("startDate");
const startDateErr = document.getElementById("startDateErr");
const endDate = document.getElementById("endDate");
const endDateErr = document.getElementById("endDateErr");


let date= [];


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

        if (new Date() < arg.start){

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
                arg.event.remove()
                let indexToRemove = reserveDate.indexOf(arg.event.startStr);
                if (indexToRemove !== -1) {
                    reserveDate.splice(indexToRemove, 1);
                }


                swalWithBootstrapButtons.fire({
                    title: "已刪除",
                    text: "刪除成功",
                    icon: "success"
                });
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
        reserve(reserveDate);
    },
});

// -------------  進入下月時，清除之前的內容 -----------------
function removeAllEvents(){
    let allEvents = calendar.getEvents();
    allEvents.forEach(function (event){
        event.remove();
    })
}



function reserve(arr){
    arr.forEach(item=>{
        calendar.addEvent({
            title: "預約",
            start: item,
            backgroundColor:"#0000ff"
        })
    });
}

// ---------------- 預約清單 ----------------------------------
const reserveDate= [];

// 新增預約日期
$("#reserveBtn").on("click" , function (){

    calendar.addEvent({
        title: "預約",
        start: $("#hideDate").text(),
        backgroundColor:"#0000ff"
    })
    myModal.hide();

    reserveDate.push($("#hideDate").text());
});


// ---------------------  獲取當月所有已報名訊息  ------------------------
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

next.addEventListener("click" , function (){

    let hasError = false;
    let errorMessages = {};


    if (reason.value == null || reason.value.trim().length == 0){
        setError(reasonErr , "請勿空白")
        reason.focus();
        hasError = true;
    }

    // 檢查日期
    if (!startDateErr.value || !endDateErr.value) {
        let dateErr = false;

        // 檢查開始日期
        if (!startDate.value) {
            setError(startDateErr, "請選擇時間");
            hasError = true;
            dateErr = true;
        }

        // 檢查結束日期
        if (!endDate.value) {
            setError(endDateErr, "請選擇時間");
            hasError = true;
            dateErr = true;
        }

        if (!dateErr) {
            let now = new Date();
            let start = new Date(startDate.value);
            let end = new Date(endDate.value);

            // 比較日期
            if (now > start) {
                setError(startDateErr, "請選擇大於今日日期");
                hasError = true;
            } else if (start > end) {
                setError(endDateErr, "結束日期不能小於開始日期");
                hasError = true;
            }
        }
    }

    if (!hasError){
        submitBtn.style.display = "block";
        calendarStr.style.display = "block";
        show.style.display = "none";
        calendar.render();
    }


    function setError(element, message) {
        element.style.display = "inline-block";
        element.innerText = message;
        errorMessages[element.id] = message;
    }
})


add.addEventListener("click" , function (){

    delayFetch(params.get("no") ,reason.value );

})

async function delayFetch(id , reason){

    let url = `${projectName}/schedule/addDelay`;

    let formData = new FormData();
    formData.append("id" , id);
    formData.append("reason" , reason);
    formData.append("classDate" , reserveDate.toString());
    formData.append("startDate" , startDate.value);
    formData.append("endDate" , endDate.value);

    try{
        const response = await fetch(url, {
            method: "POST",
            body : formData
        })

        if (!response.ok){
            throw new Error("新增失敗")
        }
        const data = await response.json();

        if (data.message){

            await Swal.fire({
                icon: "success",
                title: "Good job!",
                text: "延期更改成功"
            });
        }else{
            let str = "";
            data.forEach(message =>{
                str += message +"\n"
            })

            await Swal.fire({
                icon: "error",
                title: "Oops...",
                text: `${str}`
            });
        }
        window.location.href = `${projectName}/backend/course/schedule.jsp`;

    }catch (error){
        console.error('Error', error);
    }
}

