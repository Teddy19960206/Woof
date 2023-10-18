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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			if (dao.insert(chatroom) == 1) {
				session.getTransaction().commit();
				return chatroom;
			}
			session.getTransaction().rollback();
		return null;
	}

	@Override
	public Chatroom updateChatroom(Chatroom chatroom) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			if (dao.update(chatroom) == 1) {
				session.getTransaction().commit();
				return chatroom;
			}
			session.getTransaction().rollback();
		return null;
	}

	@Override
	public void deleteChatroom(Integer roomNo) {
		
	}

	@Override
	public Chatroom findChatroomByRoomNO(Integer roomNo) {
		Chatroom chatroom = dao.findByRoomNo(roomNo);
		return chatroom;
		}

	@Override
	public List<Chatroom> getAllChatrooms() {
			List<Chatroom> chatroomList = dao.getAll();
			return chatroomList;
		}
	}