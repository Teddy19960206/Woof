package com.woof.chatroom.dao;

import java.util.*;

import com.woof.chatroom.entity.Chatroom;

public interface ChatroomDAO {
	int insert(Chatroom chatroomVO);

	int update(Chatroom chatroomVO);

	int delete(Integer chatroomVO);

	Chatroom getById(Integer roomNO);

	List<Chatroom> getAll();

	Chatroom findByRoomNO(Integer roomNO);
}

//package com.woof.chatroom.dao;
//
//import java.util.*;
//
//import com.woof.chatroom.entity.ChatroomVO;
//
//public interface ChatroomDAO {
//	public void insert(ChatroomVO chatroomVO);
//
//	public void update(ChatroomVO chatroomVO);
//
//	public void delete(Integer chatroomVO);
//
//	public ChatroomVO findByPrimaryKey(Integer roomNO);
//
//	public List<ChatroomVO> getAll();
//}
