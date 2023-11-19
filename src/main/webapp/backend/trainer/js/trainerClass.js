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
        fetchNonClass(date.getFullYear() , date.getMonth()+1);
        fetchPrivateDetail(date.getFullYear() , date.getMonth()+1);
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
function reserve(){

    let newDate = new Date($("#showDate").html());
    console.log(newDate);
}

// 抓取當月團課日期
async function fetchDetailByDate(year , month){

    let url = `${projectName}/scheduleDetail/getDetails`;
    try{
        const response = await fetch(url , {
            method : "POST",
            headers:{
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "year="+year+"&month="+month+"&applyTrainerFilter=true&applyStatusFilter=true"
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

// 抓取當月該訓練師請假日期
async function fetchNonClass(year , month){
    let url = `${projectName}/nontrainingschedule/getbyntsdate`;
    try{
        const response = await fetch(url , {
            method : "POST",
            headers : {
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "action=getByNtsDate" + "&year=" + year + "&month=" + month
        })

        if (!response.ok){
            throw new Error("異常錯誤");
        }

        const data = await response.json();

        for(let i = 0 ; i < data.length; i++){
            calendar.addEvent({
                title:`已請假`,
                start: data[i],
                backgroundColor:"#aaaaaa"
            });
            date.push(data[i]);
        };


    }catch (error){
        console.error("Error" , error)
    }
}


// 抓取已被私人預約日期
async function fetchPrivateDetail(year , month){

    let url = `${projectName}/privatetrainingappointmentform/getDetails`;
    try{
        const response = await fetch(url , {
            method : "POST",
            headers:{
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "action=getAllByTrainer"+"&year="+year+"&month="+month
        })
        if (!response.ok){
            throw new Error("網路異常")
        }
        const data = await response.json();
        data.forEach(item=>{
            calendar.addEvent({
                title:`已被預約`,
                start: item,
                backgroundColor:"#0000ff"
            });
            date.push(item);
        })


    }catch (error){
        console.error('Error', error);
    }
}
