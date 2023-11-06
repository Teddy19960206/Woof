package com.woof.chatroom.dao;

import java.util.*;

import com.woof.chatroom.entity.Chatroom;
import com.woof.member.entity.Member;

public interface ChatroomDAO {
	int insert(Chatroom chatroom);

	int update(Chatroom chatroom);

	int delete(Integer roomNo);

	List<Chatroom> getAll();

	Chatroom findByRoomNo(Integer roomNo);

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
