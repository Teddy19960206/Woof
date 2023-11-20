package com.woof.chat.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.woof.chat.jedis.JedisHandleMessage;
import com.woof.chat.model.State;
import com.woof.chat.model.ChatMessage;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{user}")
public class ChatServlet {

    private static Map<String , Session> sessionsMap = new ConcurrentHashMap<>();
    Gson gson = new Gson();

    @OnOpen
    public void onOpen(@PathParam("user") String userName, Session userSession) throws IOException {

        sessionsMap.put(userName, userSession);
        /* Sends all the connected users to the new user */
        Set<String> memberNames = sessionsMap.keySet();
        State stateMessage = new State("open", userName, memberNames);
        String stateMessageJson = gson.toJson(stateMessage);
        JsonObject jsonTree = JsonParser.parseString(stateMessageJson).getAsJsonObject();

        // 將新的陣列添加到 JSON 物件中

        Session session = sessionsMap.get("admin");
        if (session.isOpen()) {
//              發送訊息
            JedisHandleMessage.saveMemberList(userName);
            Set<String> memberList =JedisHandleMessage.getMemberList();
            jsonTree.add("memberList", gson.toJsonTree(memberList));
            session.getAsyncRemote().sendText(jsonTree.toString());
        }

        String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
                userName, memberNames);
        System.out.println(text);
    }

    @OnMessage
    public void onMessage(Session userSession, String message) {
        ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
        String sender = chatMessage.getSender();
        String receiver = chatMessage.getReceiver();

        if ("history".equals(chatMessage.getType())) {
            List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
            String historyMsg = gson.toJson(historyData);
            ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg);
            if (userSession != null && userSession.isOpen()) {
                userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
                System.out.println("history = " + gson.toJson(cmHistory));
                return;
            }
        }


        Session receiverSession = sessionsMap.get(receiver);
        if (receiverSession != null && receiverSession.isOpen()) {
            receiverSession.getAsyncRemote().sendText(message);
            userSession.getAsyncRemote().sendText(message);
            JedisHandleMessage.saveChatMessage(sender, receiver, message);
        }
        System.out.println("Message received: " + message);
    }

    @OnError
    public void onError(Session userSession, Throwable e) {
        System.out.println("Error: " + e.toString());
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        String userNameClose = null;
        Set<String> userNames = sessionsMap.keySet();
        for (String userName : userNames) {
            if (sessionsMap.get(userName).equals(userSession)) {
                userNameClose = userName;
                sessionsMap.remove(userName);
                break;
            }
        }

        if (userNameClose != null) {
            State stateMessage = new State("close", userNameClose, userNames);
            String stateMessageJson = gson.toJson(stateMessage);
            JsonObject jsonTree = JsonParser.parseString(stateMessageJson).getAsJsonObject();
            Set<String> memberList =JedisHandleMessage.getMemberList();
            jsonTree.add("memberList", gson.toJsonTree(memberList));
            Session admin = sessionsMap.get("admin");
            admin.getAsyncRemote().sendText(jsonTree.toString());
        }

        String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
                reason.getCloseCode().getCode(), userNames);
        System.out.println(text);
    }
}
