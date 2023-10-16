package com.woof.chatroom.service;

import java.util.List;
import com.woof.chatroom.entity.Chatroom;

public interface ChatroomService {

	Chatroom addChatroom(Chatroom chatroom);

	Chatroom updateChatroom(Chatroom chatroom);

	void deleteChatroom(Integer roomNo);

	Chatroom findChatroomByRoomNO(Integer roomNo);

	List<Chatroom> getAllChatrooms();

}
