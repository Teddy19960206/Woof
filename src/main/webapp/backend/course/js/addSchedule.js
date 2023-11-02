$("button#next").on("click" , function (){
    $("div#addSchedule").hide();
    $("div#addScheduleDetail").show();
})

$(document).on("click" , "a#addClassDate" , function (){
    let Schedule = $("div#addScheduleDetail");

    let newId = parseInt(Schedule.find("div").last().find("input").attr("data-id")) + 1;


    $("div#input").append(`<div>
            <label for="classDate">新增上課日期：</label><a id="minus"><img src="${projectName}/webutil/icons/minus.svg" style="width: 20px"/></a>
            <input type="date" name="classDate" class="classDate form-control my-2" data-id="${newId}">
            
        </div>`);
})

$("button#back").on("click" , function (){
    $("div#addSchedule").show();
    $("div#addScheduleDetail").hide();
})

$(document).on("click" , "a#minus" , function (){
   $(this).closest("div").remove();
});