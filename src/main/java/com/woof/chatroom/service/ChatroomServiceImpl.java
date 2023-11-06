package com.woof.chatroom.service;

import java.util.List;
import org.hibernate.Session;

import com.woof.chatroom.dao.ChatroomDAO;
import com.woof.chatroom.dao.ChatroomDAOImpl;
import com.woof.chatroom.entity.Chatroom;
import com.woof.util.HibernateUtil;

public class ChatroomServiceImpl implements ChatroomService {
	private ChatroomDAO dao;

	public ChatroomServiceImpl() {
		dao = new ChatroomDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public Chatroom addChatroom(Chatroom chatroom) {
		dao.insert(chatroom);
		return chatroom;
	}

	@Override
	public Chatroom updateChatroom(Chatroom chatroom) {
		dao.update(chatroom);
		return chatroom;
	}

	@Override
	public void deleteChatroom(Integer roomNo) {
		if (dao.delete(roomNo) == 1) {
			return;
		}
	}

	@Override
	public Chatroom findChatroomByRoomNO(Integer roomNo) {
		Chatroom chatroom = dao.findByRoomNo(roomNo);
		return chatroom;
	}

	@Override
	public List<Chatroom> getAllChatrooms() {
     return dao.getAll();
	}
}