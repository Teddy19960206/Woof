let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
const calendarEl = document.getElementById('calendar');
const myModal = new bootstrap.Modal(document.getElementById('myModal'), {});
const next = document.getElementById("next");
const show = document.getElementById("show");
const reason = document.getElementById("reason");
const errorMsg = document.getElementById("errorMsg");
const calendarStr = document.getElementById("calendarStr");

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

        if (confirm('請問確定要刪除嗎?')) {
            // 這裡要做ajax發送delete資料庫動作
            arg.event.remove()

            let indexToRemove = reserveDate.indexOf(arg.event.startStr);
            if (indexToRemove !== -1) {
                reserveDate.splice(indexToRemove, 1);
            }
        }
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
    },
});

// 進入下月時，清除之前的內容
function removeAllEvents(){
    let allEvents = calendar.getEvents();
    allEvents.forEach(function (event){
        event.remove();
    })
}




// 預約清單
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


// 獲取當月所有已報名訊息
async function fetchDetailByDate(year , month){

    let url = `${projectName}/scheduleDetail/getDetailByDate`;
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
        console.log(error)
    }

}

next.addEventListener("click" , function (){

    if (reason.value == null || reason.value.trim().length == 0){
        errorMsg.style.display = "inline-block";
        reason.focus();
    }else{
        calendarStr.style.display = "block";
        show.style.display = "none";
        calendar.render();
    }
})

