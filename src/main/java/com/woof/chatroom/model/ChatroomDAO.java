package com.woof.chatroom.model;

import java.util.*;

public interface ChatroomDAO {
	public void insert(ChatroomVO chatroomVO);

	public void update(ChatroomVO chatroomVO);

	public void delete(Integer chatroomVO);

	public ChatroomVO findByPrimaryKey(Integer roomNO);

	public List<ChatroomVO> getAll();
}
