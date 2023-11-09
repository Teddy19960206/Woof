let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);

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
    selectable: false,  // 可以點擊日曆事件
    selectMirror: true, //
    hiddenDays:[], // 隱藏星期六 與 星期日
    eventStartEditable : false, // 禁止拖拉
    editable: false,  // 可編輯
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

function removeAllEvents(){
    let allEvents = calendar.getEvents();
    allEvents.forEach(function (event){
        event.remove();
    })
}

document.addEventListener('DOMContentLoaded',  function() {
    calendar.render();
});

// let prevBtn = document.getElementsByClassName("fc-prev-button");

// $(function(){
//     let prevBtn = document.getElementsByClassName("fc-prev-button");
//     let nextBtn = document.getElementsByClassName("fc-next-button");
//
//     prevBtn[0].disabled = true;
//     nextBtn[0].disabled = false;
//
//     prevBtn[0].addEventListener("click" , ()=>{
//         prevBtn[0].disabled = true;
//         nextBtn[0].disabled = false;
//     })
//
//     nextBtn[0].addEventListener("click" , ()=>{
//         prevBtn[0].disabled = false;
//         nextBtn[0].disabled = true;
//     })
// })

function reserve(){

    let newDate = new Date($("#showDate").html());
    console.log(newDate);
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

    }catch (error){
        console.log(error)
    }

}