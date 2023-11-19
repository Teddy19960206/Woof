let host = window.location.host;
let pathFor = window.document.location.pathname;
let projectName = pathFor.substring( 0 , pathFor.substring(1).indexOf("/")+1);
let self = "admin";
let myPoint = `/chat/${self}`;
let endPoint = `ws://` + host + projectName + myPoint;
const inputHistory = document.getElementById("input-history");
const inputText = document.getElementById("inputText");
const chatHeader = document.getElementById("chat-header");
const chatList = document.getElementById("chat-list");
// receiver 接收

// sender 傳送


let member ;

addEventListener("DOMContentLoaded" , function (){
    connect();
})
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
            refreshFriendList(jsonObj);
        }else if ("chat"){
            if (member === jsonObj.sender){
                addMessage(jsonObj);
            }else{

            }
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


function sendMessage(message) {

    let jsonObj = {
        "type" : "chat",
        "sender" : self,
        "receiver" : member,
        "message" : message
    };
    websocket.send(JSON.stringify(jsonObj));
    addMessage(jsonObj)
    inputText.value = "";
    inputText.focus();
    scrollToBottom();
}

function addMessage(jsonObj){
    $("#input-history")
        .append(`<li class="clearfix">
                    <div class="message-data ${jsonObj.receiver == self ? '' : 'text-right'}">
                      <span class="message-data-time">${new Date().toLocaleTimeString()}</span>
                    </div>         
                    <div class="message ${jsonObj.receiver == self ? 'my-message' : 'other-message float-right'}">${jsonObj.message}</div>
                 </li>`);
    scrollToBottom();
}

$(".fa-send").on("click" , function (){
    if (inputText.value.trim() !== "" ){
        sendMessage(inputText.value);
    }

});


$("#inputText").on("keyup" , function (e){
    if (inputText.value.trim() !== "" && e.keyCode === 13){
        sendMessage(inputText.value);
    }
})


function scrollToBottom() {
    let div = document.getElementById("chat-history");
    div.scrollTop = div.scrollHeight;
}

// 有好友上線或離線就更新列表
function refreshFriendList(jsonObj) {
    let members = jsonObj.users;
    chatList.innerHTML = '';
    console.log(members.length)
    for (let i = 0; i < members.length; i++) {
        if (members[i] === self) { continue; }
        appendMemberList(membersList(members[i]));
    }
    // addListener();
}

function appendMemberList(message){
    chatList.innerHTML += message;
}

function membersList(member){
    return `<li class="clearfix li-chat" data-id="${member}">
              <img src="${projectName}/DBPngReader?action=member&id=${member}" alt="avatar">
              <div class="about">
                <div class="name">${member}</div>
                <div class="status"> <i class="fa fa-circle online"></i> on-line </div>
              </div>
            </li>`;
}

function memberHeader(member){
    return `<div class="row">
              <div class="col-lg-6">
                <a href="javascript:void(0);" data-toggle="modal" data-target="#view_info">
                  <img src="${projectName}/DBPngReader?action=member&id=${member}" alt="avatar">
                </a>
                <div class="chat-about">
                  <h6 class="m-b-0">${member}</h6>
                </div>
              </div>
            </div>`;
}

$(document).on("click" , "li.li-chat" , function (){
    member = $(this).data("id");
    chatHeader.innerHTML = memberHeader(member);
    inputHistory.innerHTML = "";
//  更換聊天室
    $("#inputText").prop("disabled" , false);

})