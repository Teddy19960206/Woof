<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src='${pageContext.request.contextPath}/webutil/js/index.global.min.js'></script>
<link href="${pageContext.request.contextPath}/webutil/css/bootstrap.min.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/webutil/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/webutil/js/jquery-3.7.1.min.js"></script>

<script>

  let time = ["2023-10-04" , "2023-10-03" ,"2023-10-10"];

  // 不能點選的日期，背景加上灰色
  function convertTimeToBackgroundEvents(time) {
    return time.map(date => {
      return {
        start: date,
        display: 'background',  // 這會將事件作為背景顯示
        color: 'gray' // 指定背景顏色
      };
    });
  }

  document.addEventListener('DOMContentLoaded', function() {
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
      hiddenDays:[6,0], // 隱藏星期六 與 星期日 
      eventStartEditable : false, // 禁止拖拉
      
      select: function(arg) {
        
        if (time.some(item => arg.startStr === item)) {
          calendar.unselect(); // 取消選擇
          return; // 結束函數執行
        }
        $("#showDate").text(arg.startStr);
        console.log(arg);
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
        if (confirm('請問確定要刪除嗎?')) {
          // 這裡要做ajax發送delete資料庫動作
          arg.event.remove()
        }
      },
      editable: true,  // 可編輯
      dayMaxEvents: true, // allow "more" link when too many events
      events: [
        
        ...convertTimeToBackgroundEvents(time),
        {
          title: 'All Day Event',
          start: '2023-01-02T04:00:00',
        },
        {
          
          title: 'Repeating Event',
          start: '2023-01-09'
        },
        {
          title: 'Repeating Event',
          start: '2023-01-16 '
        },
      ],
    });

    calendar.render();
  });

  // let prevBtn = document.getElementsByClassName("fc-prev-button");

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


  

</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }

  a {
    text-decoration: none;
    color: black;
  }
  div#showDate{
    font-size: 30px;
  }
</style>
</head>
<body>

  <div id='calendar'></div>

  <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">請選擇預約時段</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body text-center">
          <div id="showDate"></div>
          <button type="button" class="btn btn-primary" onclick="reserveAM()">預約上午</button>
          <button type="button" class="btn btn-primary" onclick="reservePM()">預約下午</button>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>

  <script>
    var myModal = new bootstrap.Modal(document.getElementById('myModal'), {});
    
    function reserveAM(){

      let newDate = new Date($("#showDate").html());
      console.log(newDate);
    }

    function reservePM(){

    }

  </script>
</body>
</html>
