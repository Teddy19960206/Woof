let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);

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
        $("#recordYear").text(date.getFullYear());
        $("#recordMonth").text(date.getMonth()+1);
        fetchNonClass(date.getFullYear() , date.getMonth()+1 , $("#trainers").val() , $("#trainers").find(":selected").text());
        fetchDetailByDate(date.getFullYear() , date.getMonth()+1 ,$("#trainers").val() , $("#trainers").find(":selected").text());
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

// 取得該訓練師，請假日期
async function fetchNonClass(year , month , trainerNo , trainerName){
    let url = `${projectName}/nontrainingschedule/getbyntsdate`;
    try{
        const response = await fetch(url , {
            method : "POST",
            headers : {
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "action=getByNtsDate" + "&trainerNo="+ trainerNo + "&year=" + year + "&month=" + month
        })

        if (!response.ok){
            throw new Error("異常錯誤");
        }

        const data = await response.json();

        for(let i = 0 ; i < data.length; i++){
            calendar.addEvent({
                title:`訓練師：${trainerName}  請假`,
                start: data[i],
                backgroundColor:"#aaaaaa"
            });
        };


    }catch (error){
        console.error("Error" , error)
    }
}


// 取得該訓練師，需上課的日期
async function fetchDetailByDate(year , month , trainerNo , trainerName){

    let url = `${projectName}/privatetrainingappointmentform/getDetailByDate`;
    try{
        const response = await fetch(url , {
            method : "POST",
            headers:{
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "action=getAllByTrainer" + "&trainerNo="+ trainerNo + "&year=" + year + "&month=" + month
        })
        if (!response.ok){
            throw new Error("網路異常")
        }
        const data = await response.json();

        console.log(data)

        for(let i = 0 ; i < data.length; i++){
            calendar.addEvent({
                title:`訓練師：${trainerName} 上課`,
                start: data[i],
                backgroundColor:"#0000ff"
            });
        };


    }catch (error){
        console.error('Error', error);
    }

}


$("#trainers").on("change" , function (){
    removeAllEvents();
    fetchNonClass($("#recordYear").text(), $("#recordMonth").text() , $("#trainers").val() , $("#trainers").find(":selected").text());
    fetchDetailByDate($("#recordYear").text() , $("#recordMonth").text() ,$("#trainers").val() , $("#trainers").find(":selected").text());
})


