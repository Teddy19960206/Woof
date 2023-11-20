let pathName = window.document.location.pathname;
let projectName = pathName.substring( 0 , pathName.substring(1).indexOf("/")+1);
const myModal = new bootstrap.Modal(document.getElementById('myModal'), {});

let date= [];
let groupDate = [];

let calendarEl = document.getElementById('calendar');

let calendar = new FullCalendar.Calendar(calendarEl, {
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

        let url = null;
        if (arg.el.innerText === "已被預約"){
            url = `${projectName}/appointmentdetail/detail?action=cancelFromTrainer&date=${arg.event.startStr}`;
        }else {
            url = `${projectName}/nontrainingschedule/detail?action=deleteByDate&date=${arg.event.startStr}`;
        }


        if (groupDate.some(item => arg.event.startStr === item)) {
            calendar.unselect(); // 取消選擇
            return; // 結束函數執行
        }

        let dateStart = new Date(arg.event.startStr)
        if (dateStart < Date.now()){
            return;
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
        }).then(async (result) => {
            if (result.isConfirmed) {
                let data =  await deleteFetch(url);
                if (data.message){
                    // 這裡要做ajax發送delete資料庫動作
                    await swalWithBootstrapButtons.fire({
                        title: "已刪除",
                        text: "刪除成功",
                        icon: "success"
                    });
                    // 刪除後全部元素刪除，全部重新抓取
                    removeAllEvents();
                    fetchDetailByDate($("#year").text() , $("#month").text());
                    fetchNonClass($("#year").text() , $("#month").text());
                    fetchPrivateDetail($("#year").text() , $("#month").text());

                    let indexToRemove = date.indexOf(arg.event.startStr);
                    if (indexToRemove !== -1) {
                        date.splice(indexToRemove, 1);
                    }

                }else {
                    await swalWithBootstrapButtons.fire({
                        title: "未刪除",
                        text: "刪除失敗",
                        icon: "error"
                    });
                }

            } else if (result.dismiss === Swal.DismissReason.cancel) {
                await swalWithBootstrapButtons.fire({
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
        $("#year").text(date.getFullYear());
        $("#month").text(date.getMonth()+1);

        fetchDetailByDate(date.getFullYear() , date.getMonth()+1);
        fetchNonClass(date.getFullYear() , date.getMonth()+1);
        fetchPrivateDetail(date.getFullYear() , date.getMonth()+1);
    },

});


async function deleteFetch(url){
    try{
        const response = await fetch(url ,{
            method : "POST"
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
            // 僅允許團課無法修改
            date.push(item.classDate);
            groupDate.push(item.classDate);
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

$("#reserveBtn").on("click", async function (){

    let url = `${projectName}/nontrainingschedule/non?action=addForTrainer&date=${$("#hideDate").text()}`;
    try{
        const response = await fetch(url , {
            method : "POST"
        })
        if (!response.ok){
            throw new Error("錯誤");
        }
        myModal.hide();
        const data = await response.json();

        if (data.message){
            await Swal.fire({
                title: "新增成功!",
                text: "已成功新增請假日期",
                icon: "success"
            });
            calendar.addEvent({
                title:`已請假`,
                start: $("#hideDate").text(),
                backgroundColor:"#aaaaaa"
            });
            date.push($("#hideDate").text());
        }else{
            await Swal.fire({
                title: "新增失敗!",
                text: "請重新嘗試",
                icon: "error"
            });
        }
    }catch (error){
        console.error("Error" , error);
    }
})