//Content Loaded
let header = document.querySelector(".chat-box .header");
let inputText = document.querySelector("#inputText");
let btnSend = document.querySelector(".button-send");
const charRoom = document.getElementById("chat-room");
const name = document.getElementById("name");
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



$(document).on("click" , ".chat-icon" , function (){
    $('#chat-box').removeClass('chat-none');
    connect();
    // 開啟連線
})

$(document).on("click" , ".chat-header" , function (){
    $('#chat-box').addClass('chat-none');
    disconnect();
})

// ------------------------------------------------------------------------------- 連線

let host = window.location.host;
let path = window.document.location.pathname;
let projectForChat = path.substring( 0 , path.substring(1).indexOf("/")+1);
let member = $("#member").text();
let myPoint = `/chat/${member}`;
let endPoint = `ws://` + host + projectForChat + myPoint;

$(window).on('beforeunload', function() {
    disconnect();
});


let websocket;
function connect(){
    websocket = new WebSocket(endPoint);

    websocket.onopen = function (event){
        inputText.disabled = false;
        inputText.placeholder = "請輸入文字";
        name.innerHTML = "客服";
        console.log("連線成功");
        getHistory();
    }

    websocket.onmessage = function (event){
        const jsonObj = JSON.parse(event.data);
        if ("open" === jsonObj.type) {
        }else if ("chat" === jsonObj.type){
            if ("admin" === jsonObj.sender){
                addMessage(jsonObj);
            }else{

            }
        }else if ("history" === jsonObj.type){
            // 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
            let messages = JSON.parse(jsonObj.message);
            for (let i = 0; i < messages.length; i++) {
                let historyData = JSON.parse(messages[i]);
                // 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
                addMessage(historyData);
            }
        }
    }

    websocket.onclose = function (event) {
        inputText.disabled = true;
        inputText.placeholder = "目前無法聯絡客服";
        name.innerHTML = "客服 <span style='color: red'>(目前無法聯絡客服)</span>";
    }

    websocket.onerror = function (event){
    }
}

function getHistory(){
    let jsonObj = {
        "type" : "history",
        "sender" : member,
        "receiver" : "admin",
        "message" : ""
    };
    websocket.send(JSON.stringify(jsonObj));
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
