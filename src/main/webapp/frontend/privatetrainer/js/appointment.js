let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
const myModal = new bootstrap.Modal(document.getElementById('myModal'), {});
const params = new URLSearchParams(window.location.search);

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

        if (new Date() < arg.start){

            if (date.some(item => arg.startStr === item)) {
                calendar.unselect(); // 取消選擇
                return; // 結束函數執行
            }

            if (reserveDate.some(item => arg.startStr === item)){
                calendar.unselect();
                return;
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
            console.log(item)
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
                arg.event.remove()

                let indexToRemove = reserveDate.indexOf(arg.event.startStr);
                if (indexToRemove !== -1) {
                    reserveDate.splice(indexToRemove, 1);
                    date.splice(indexToRemove, 1);
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
        reserve(reserveDate);
        // 加上七天，避免取得到上個月的日期，
        let nowDate = new Date(info.start);
        nowDate.setDate(nowDate.getDate() + 7);
        fetchDetail(nowDate.getFullYear() , nowDate.getMonth()+1);
    },

});

function removeAllEvents(){
    let allEvents = calendar.getEvents();
    allEvents.forEach(function (event){
        event.remove();
    })
    date.splice(0, date.length);
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

// 抓取已被預約日期
async function fetchDetail(year , month){

    let trainerNo = params.get('trainerNo');

    let url = `${projectName}/privatetrainingappointmentform/getDetails`;
    try{
        const response = await fetch(url , {
            method : "POST",
            headers:{
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body : "action=getAllByTrainer"+"&year="+year+"&month="+month+"&trainerNo="+trainerNo
        })
        if (!response.ok){
            throw new Error("網路異常")
        }
        const data = await response.json();
        data.forEach(item=>{
            calendar.addEvent({
                title:`已被預約`,
                start: item,
                backgroundColor:"#ff0000"
            });
            date.push(item);
        })


    }catch (error){
        console.error('Error', error);
    }
}

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
    // 預約清單
    reserveDate.push($("#hideDate").text());
    // 禁止同日再次點選
    // date.push($("#hideDate").text());
    console.log(reserveDate)
});

function reserve(arr){
    arr.forEach(item=>{
        calendar.addEvent({
            title: "預約",
            start: item,
            backgroundColor:"#0000ff"
        })
    });
}

$("#confirm").on("click" , function (){

    Swal.fire({
        title: "確定預約日期了嗎?",
        text: "預約後無法隨意修改",
        icon: "success",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "是, 預約!",
        closeButtonHtml: "否, 在考慮!"
    }).then(async (result) => {
        if (result.isConfirmed) {
            let data = await fetchClass();
            if (data.message){
                await Swal.fire({
                    title: "預約成功!",
                    text: "您的預約已經成功安排，我們在這裡靜候您的光臨。",
                    icon: "success"
                });
            }else{
                await Swal.fire({
                    title: "預約失敗!",
                    text: "請重新嘗試",
                    icon: "error"
                });
            }

            window.location.href = `${projectName}/frontend/privatetrainer/privateTrainer.jsp`;
        }
    });
})


// 新增預約日期
async function fetchClass(){
    let url = `${projectName}/privatetrainingappointmentform/add`;

    let trainerNo = params.get('trainerNo');
    let formdata = new FormData();
    formdata.append("action" , "add");
    formdata.append("trainerNo" , trainerNo);
    formdata.append("dates" , reserveDate.toString());

    try{
        const response = await fetch(url , {
            method : "POST",
            body: formdata
        })
        if (!response.ok){
            throw new Error("錯誤");
        }

        const data = await response.json();

        return data;

    }catch (error){
        console.error("Error" , error);
    }

}
// 抓取 訓練師 資料
$(async function (){
    let trainerNo = params.get('trainerNo');
    let data = await fetchTrainer(trainerNo);

    console.log(data);
})

async function fetchTrainer(id){
    let url = `${projectName}/trainer/getTrainerProfile`;

    try {
        const response = await fetch(url , {
            method: "POST",
            headers :{
                "Content-Type" : "application/x-www-form-urlencoded"
            },
            body:"trainerNo="+ id
        })

        if (!response.ok){
            throw new Error("錯誤");
        }
        const data = await response.json();

        return data;

    }catch (error){
        console.error("Error" , error);
    }

}
