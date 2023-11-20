//Content Loaded


let header = document.querySelector(".chat-box .header");
let chatRoom = document.querySelector(".chat-room");
let typeArea = document.querySelector(".type-area");
let inputText = document.querySelector("#inputText");
let btnSend = document.querySelector(".button-send");
// let messageArea = document.querySelector(".message.message-right");
const charRoom = document.getElementById("chat-room");
window.addEventListener("DOMContentLoaded", (e) => {

    //Button Send onclick event
    btnSend.addEventListener("click", (e) => {
        if (inputText.value.trim() !== "") {
            sendMessage(inputText.value);
        }
    });

    inputText.addEventListener("keyup", function (event) {
        // 檢查是否按下了 Enter 鍵（keyCode 為 13）
        if (inputText.value.trim() !== "" && event.keyCode === 13) {
            sendMessage(inputText.value);
        }
    });
});



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
let myPoint = `/chat/${member}`;
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

        }else if ("chat" === jsonObj.type){
            if ("admin" === jsonObj.sender){
                addMessage(jsonObj);
            }else{

            }
        }else if ("history" === jsonObj.type){

        }
    }

    websocket.onclose = function (event) {

    }

    websocket.onerror = function (event){

    }
}


function addMessage(jsonObj){

    if (jsonObj.receiver == member){
        $("#chat-room")
            .append(`<div class="message message-left">
                    <div class="avatar-wrapper avatar-small"></div>
                    <small>客服</small>
                    <div class="bubble bubble-light">${jsonObj.message}</div>
                </div>`);
    }else {
        $("#chat-room")
            .append(`<div class="message message-right">
                        <small>${member}</small>
                        <div class="bubble bubble-dark">
                           ${jsonObj.message}
                        </div>
                    </div>`);
    }
    scrollToBottom();
}

function scrollToBottom() {
    charRoom.scrollTop = charRoom.scrollHeight;
}

function disconnect(){
    websocket.close();
}


function sendMessage(message) {

    let jsonObj = {
        "type" : "chat",
        "sender" : member,
        "receiver" : "admin",
        "message" : message
    };
    websocket.send(JSON.stringify(jsonObj));
    addMessage(jsonObj)
    inputText.value = "";
    inputText.focus();
    scrollToBottom();
}

