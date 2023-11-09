const groupCourse = document.getElementById("groupCourse");
const groupCourseErr = document.getElementById("groupCourseErr");
const trainer = document.getElementById("trainer");
const trainerErr = document.getElementById("trainerErr");
const startDate = document.getElementById("startDate");
const startDateErr = document.getElementById("startDateErr");
const endDate = document.getElementById("endDate");
const endDateErr = document.getElementById("endDateErr");
const minLimit = document.getElementById("minLimit");
const minLimitErr = document.getElementById("minLimitErr");
const maxLimit = document.getElementById("maxLimit");
const maxLimitErr = document.getElementById("maxLimitErr");
const price = document.getElementById("price");
const priceErr = document.getElementById("priceErr");
// const relatedGcsNo = document.getElementById("relatedGcsNo");
// const relatedGcsNoErr = document.getElementById("relatedGcsNoErr");
// const delayReason = document.getElementById("delayReason");
// const delayReasonErr = document.getElementById("delayReasonErr");
const addBtn = document.getElementById("addBtn")


const calendarEl = document.getElementById('calendar');
const myModal = new bootstrap.Modal(document.getElementById('myModal'), {});



// 下一步

// 需先判斷是否填入正確
$("button#next").on("click" , function (){
    clearErr();

    let hasError = false;
    let errorMessages = {};

    // 檢查課程
    if (groupCourse.value == 0) {
        setError(groupCourseErr, "請選擇課程");
        hasError = true;
    }

    // 檢查訓練師
    if (!trainer.value) {
        setError(trainerErr, "請選擇訓練師");
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

    // 檢查最低最多人數
    if (minLimit.value.trim().length == 0 || maxLimit.value.trim().length == 0){

        let dateErr = false;

        if (minLimit.value.trim().length == 0){
            setError(minLimitErr, "請勿空白");
            hasError = true;
            dateErr = true;
        }
        if (maxLimit.value.trim().length == 0){
            setError(maxLimitErr, "請勿空白");
            hasError = true;
            dateErr = true;
        }

        if (!dataErr && minLimit.value > 100){
            setError(minLimitErr , "請勿輸入超過三位數")
        }

        if (!dateErr && maxLimit.value > 100){
            setError(maxLimitErr , "請勿輸入超過三位數")
        }

        if (!dateErr && minLimit.value > maxLimit.value){
            setError(minLimitErr , "最低人數不能大於最多人數")
            hasError = true;
        }
    }

    // 檢查價格
    if (price.value.trim().length == 0){
        setError(priceErr, "請勿空白");
        hasError = true;
    }

    // // 檢查延期關聯表格
    // if(relatedGcsNo.value.length == 0 && !delayReason.value.trim().length == 0){
    //     setError(delayReasonErr , "沒有選擇對應延期關聯表格");
    //     hasError = true;
    // }else if (!relatedGcsNo.value.length == 0 && delayReason.value.trim().length == 0){
    //     setError(delayReasonErr , "請輸入延期原因");
    //     hasError = true;
    // }


    if (!hasError){
        $("div#addSchedule").hide();
        calendarEl.style.display = 'block';
        // $("div#addScheduleDetail").show();
        calendar.render();
    }

    function setError(element, message) {
        element.style.display = "inline-block";
        element.innerText = message;
        errorMessages[element.id] = message;
    }
});


function clearErr(){
    groupCourseErr.style.display = 'none';
    trainerErr.style.display = 'none';
    startDateErr.style.display = 'none';
    endDateErr.style.display = 'none';
    minLimitErr.style.display = 'none';
    maxLimitErr.style.display = 'none';
    priceErr.style.display = 'none';
    // relatedGcsNoErr.style.display = 'none';
    // delayReasonErr.style.display = 'none';
}


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


async function addSchedule(){


    let url = `${projectName}/schedule/addSchedule`;
    let formData = new FormData();
    formData.append("groupCourse", groupCourse.value);
    formData.append("trainer", trainer.value);
    formData.append("startDate" , startDate.value);
    formData.append("endDate" , endDate.value);
    formData.append("minLimit" , minLimit.value);
    formData.append("maxLimit" , maxLimit.value);
    formData.append("price" , price.value);
    // formData.append("delayReason" , delayReason.value);
    // formData.append("relatedGcsNo" , relatedGcsNo.value);

    console.log(reserveDate.toString());

    formData.append("classDate" , reserveDate.toString());

    try{
        const response = await fetch(url , {
            method: "POST",
            body: formData
        })
        if (!response.ok){
            alert("新增失敗");
            window.location.href = `${projectName}/backend/course/schedule.jsp`;
        }
        const data = await response.json();

        if (data.message) {
            alert("新增成功");
            window.location.href = `${projectName}/backend/course/schedule.jsp`;
        }else if (data.length > 0){
            let str = "";
            data.forEach(message =>{
                str += message +"\n"
            })
            alert(str);
            window.location.href = `${projectName}/backend/course/addSchedule.jsp`;
        }

    }catch (error){
        console.log(error);
    }

}


addBtn.addEventListener("click" , function (){
    addSchedule();
});

