//Content Loaded


let header = document.querySelector(".chat-box .header");
let chatRoom = document.querySelector(".chat-room");
let typeArea = document.querySelector(".type-area");
let inputText = document.querySelector("#inputText");
let btnSend = document.querySelector(".button-send");
let messageArea = document.querySelector(".message.message-right");

window.addEventListener("DOMContentLoaded", (e) => {

    //Button Send onclick event
    btnSend.addEventListener("click", (e) => {
        if (inputText.value !== "") {
            var mess = inputText.value;
            var bubble = document.createElement("div");
            bubble.className += " bubble bubble-dark";
            bubble.textContent = mess;
            messageArea.appendChild(bubble);
            inputText.value = "";
            scrollToBottom();
        }
    });

    inputText.addEventListener("keyup", function (event) {
        // 檢查是否按下了 Enter 鍵（keyCode 為 13）
        if (inputText.value !== "" && event.keyCode === 13) {
            var mess = inputText.value;
            var bubble = document.createElement("div");
            bubble.className += " bubble bubble-dark";
            bubble.textContent = mess;
            messageArea.appendChild(bubble);
            inputText.value = "";
            scrollToBottom();
        }
    });
});

function scrollToBottom() {
    var div = document.getElementById("chat-room");
    div.scrollTop = div.scrollHeight;
}


$(document).on("click" , ".header" , function (){
    if (typeArea.classList.contains("chat-none")) {
        header.style.borderRadius = "20px 20px 0 0";
        // 開啟連線
        connect();
    } else {
        header.style.borderRadius = "20px";

        disconnect();
    }
    typeArea.classList.toggle("chat-none");
    chatRoom.classList.toggle("chat-none");
})

// ------------------------------------------------------------------------------- 連線

let host = window.location.host;
let path = window.document.location.pathname;
let projectName = path.substring( 0 , path.substring(1).indexOf("/")+1);
let member = $("#member").text();
let myPoint = `/frontEnd/${member}`;
let endPoint = `ws://` + host + projectName + myPoint;



$(window).on('beforeunload', function() {
    disconnect();
});


let websocket;
function connect(){
    websocket = new WebSocket(endPoint);

    websocket.onopen = function (event){
        console.log("連線成功");
    }

    websocket.onmessage = function (event){
        const jsonObj = JSON.parse(event.data);
        console.log(jsonObj);
        if ("open" === jsonObj.type) {
            console.log(jsonObj)
        }
    }

    websocket.onclose = function (event) {

    }

    websocket.onerror = function (event){

    }
}

function disconnect(){
    websocket.close();
}