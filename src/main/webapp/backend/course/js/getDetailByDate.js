let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);


// let time = ["2023-11-04" , "2023-11-03" ,"2023-11-10"];

let date= [];


var calendarEl = document.getElementById('calendar');

var calendar = new FullCalendar.Calendar(calendarEl, {
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

        if (date.some(item => arg.startStr === item)) {
            calendar.unselect(); // 取消選擇
            return; // 結束函數執行
        }


        $("#showDate").text(arg.startStr);
        myModal.show();
        // var title = confirm('新增資料:');
        // if (title) {
        //   calendar.addEvent({
        //     title: title,
        //     start: arg.start,
        //     end: arg.end
        //   })
        //   console.log(arg);
        // }
        calendar.unselect()
    },
    eventClick: function(arg) {
        if (date.some(item => arg.startStr === item)) {
            calendar.unselect(); // 取消選擇
            return; // 結束函數執行
        }

        if (confirm('請問確定要刪除嗎?')) {
            // 這裡要做ajax發送delete資料庫動作
            arg.event.remove()
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
        // for (let i = 0 ; i < time.length ; i ++){
        //     calendar.addEvent({
        //         title: "不可點選",
        //         start: time[i],
        //         display: "background" ,
        //         color : 'gray'
        //     })
        // }
        date.setDate(date.getDate() + 7);
        fetchDetailByDate(date.getFullYear() , date.getMonth()+1);
    }
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

let prevBtn = document.getElementsByClassName("fc-prev-button");

$(function(){
    let prevBtn = document.getElementsByClassName("fc-prev-button");
    let nextBtn = document.getElementsByClassName("fc-next-button");

    prevBtn[0].disabled = true;
    nextBtn[0].disabled = false;

    prevBtn[0].addEventListener("click" , ()=>{
        prevBtn[0].disabled = true;
        nextBtn[0].disabled = false;
    })

    nextBtn[0].addEventListener("click" , ()=>{
        prevBtn[0].disabled = false;
        nextBtn[0].disabled = true;
    })
})

var myModal = new bootstrap.Modal(document.getElementById('myModal'), {});

function reserveAM(){

    let newDate = new Date($("#showDate").html());
    console.log(newDate);
}

function reservePM(){

}

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
        console.log("11")

    }catch (error){
        console.log(error)
    }

}