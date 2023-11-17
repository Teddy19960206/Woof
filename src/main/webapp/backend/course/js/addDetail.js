let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
const calendarEl = document.getElementById('calendar');
const myModal = new bootstrap.Modal(document.getElementById('myModal'), {});
const urlParams = new URLSearchParams(window.location.search);

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

            if (reserveDate.some(item => arg.startStr === item)){
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
        reserve(reserveDate);
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


function reserve(arr){
    arr.forEach(item=>{
        calendar.addEvent({
            title: "預約",
            start: item,
            backgroundColor:"#0000ff"
        })
    })
}

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

$(function (){
    calendar.render()
})

$("#confirm").on("click" , async function (){

    Swal.fire({
        title: "確定要新增嗎?",
        text: "該操作無法返回!",
        icon: "警告",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "是, 新增!",
        closeButtonHtml: "否, 取消!"
    }).then(async (result) => {
        if (result.isConfirmed) {
            const data = await addFetch();

            if (data.message){
                await Swal.fire({
                    title: "新增成功!",
                    text: "課程日期已新增",
                    icon: "success"
                });
            }else {
                await Swal.fire({
                    title: "新增失敗!",
                    text: "請重新嘗試!",
                    icon: "error"
                });
            }
            window.location.href = `${projectName}/backend/course/schedule.jsp`;
        }
    });


})


async function addFetch(){

    let id = urlParams.get("id");
    console.log(id)

    let url = `${projectName}/scheduleDetail/addAll`;
    let formData = new FormData();
    formData.append("scheduleNo" , id);
    formData.append("classDates" , reserveDate.toString());

    try{
        const response = await fetch(url , {
            method : "POST",
            body:formData
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