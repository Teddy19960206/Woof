$("button#next").on("click" , function (){
    $("div#addSchedule").hide();
    $("div#addScheduleDetail").show();
})

$(document).on("click" , "button#addClassDate" , function (){
    let Schedule = $("div#addScheduleDetail");

    let newId = parseInt(Schedule.find("div").last().find("input").attr("data-id")) + 1;


    $("div#input").append(`<div>
            <label for="classDate">新增上課日期：</label>
            <input type="date" name="classDate" class="classDate" data-id="${newId}">
        </div>`);
})

$("button#back").on("click" , function (){
    $("div#addSchedule").show();
    $("div#addScheduleDetail").hide();
})