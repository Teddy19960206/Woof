package com.woof.chatroom.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.woof.chatroom.entity.Chatroom;


public class ChatroomDAOImpl implements ChatroomDAO {

	private SessionFactory factory;

	public ChatroomDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(Chatroom chatroom) {
		return (Integer) getSession().save(chatroom);
	}

	@Override
	public int update(Chatroom chatroom) {
		try {
			getSession().update(chatroom);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer roomNo) {
		Chatroom chatroom =getSession().get(Chatroom.class, roomNo);
		if(chatroom !=null) {
			getSession().delete(chatroom);
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public Chatroom findByRoomNo(Integer roomNO) {
		return getSession().get(Chatroom.class, roomNO);
	}

	@Override
	public List<Chatroom> getAll() {
		return getSession().createQuery("FROM Chatroom", Chatroom.class).list();
	}
}
//
////package com.woof.chatroom.dao;
////
////import java.util.*;
////
////import com.woof.chatroom.entity.ChatroomVO;
////import com.woof.util.Util;
////
////import java.sql.*;
////
////public class ChatroomDAOImpl implements ChatroomDAO {
////	private static final String INSERT_STMT = "INSERT INTO chatroom (mem_no, chat_msg_direct, chat_msg_time, chat_msg_context, chat_msg_photo) VALUES (?, ?, ?, ?, ?)";
////	private static final String UPDATE_STMT = "UPDATE chatroom SET mem_no = ?,chat_msg_direct = ?, chat_msg_time = ?, chat_msg_context = ?, chat_msg_photo = ? WHERE room_no = ?";
////	private static final String DELETE_STMT = "DELETE FROM chatroom where room_no = ?";
////	private static final String FIND_BY_ROOMNO = "SELECT * FROM chatroom WHERE room_no = ?";
////	private static final String GET_ALL = "SELECT * FROM chatroom";
////
////	@Override
////	public void insert(ChatroomVO chatroomVO) {
////		Connection con = null;
////		PreparedStatement ps = null;
////		int count = 0;
////		byte[] a = null;
////		try {
////			con = Util.getConnection();
////			ps = con.prepareStatement(INSERT_STMT);
////			ps.setInt(1, chatroomVO.getMemNo());
////			ps.setInt(2, chatroomVO.getChatMsgDirect());
////			ps.setTimestamp(3, chatroomVO.getChatMsgTime());
////			ps.setString(4, chatroomVO.getChatMsgContext());
////			ps.setBytes(5, chatroomVO.getChatMsgPhoto());
////
////			count = ps.executeUpdate();
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			Util.closeResources(con, ps, null);
////		}
////
////		if (count == 1) {
////			System.out.println("新增成功");
////		} else {
////			System.out.println("新增失敗");
////		}
////
////	}
////
////	@Override
////	public void update(ChatroomVO chatroomVO) {
////		Connection con = null;
////		PreparedStatement ps = null;
////		int count = 0;
////
////		try {
////			con = Util.getConnection();
////			ps = con.prepareStatement(UPDATE_STMT);
////			ps.setInt(1, chatroomVO.getMemNo());
////			ps.setInt(2, chatroomVO.getChatMsgDirect());
////			ps.setTimestamp(3, chatroomVO.getChatMsgTime());
////			ps.setString(4, chatroomVO.getChatMsgContext());
////			ps.setBytes(5, chatroomVO.getChatMsgPhoto());
////			ps.setInt(6, chatroomVO.getRoomNo());
////
////			count = ps.executeUpdate();
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			Util.closeResources(con, ps, null);
////		}
////
////		if (count == 1) {
////			System.out.println("修改成功");
////		} else {
////			System.out.println("修改失敗");
////		}
////	}
////
////	@Override
////	public void delete(Integer roomNo) {
////		Connection con = null;
////		PreparedStatement ps = null;
////		int count = 0;
////
////		try {
////			con = Util.getConnection();
////			ps = con.prepareStatement(DELETE_STMT);
////			ps.setInt(1, roomNo);
////			count = ps.executeUpdate();
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			Util.closeResources(con, ps, null);
////		}
////
////		if (count == 1) {
////			System.out.println("刪除成功");
////		} else {
////			System.out.println("刪除失敗");
////		}
////	}
////
////	@Override
////	public ChatroomVO findByPrimaryKey(Integer roomNo) {
////		Connection con = null;
////		PreparedStatement ps = null;
////		ResultSet rs = null;
////		ChatroomVO chatroomVO = null;
////
////		try {
////			con = Util.getConnection();
////			ps = con.prepareStatement(FIND_BY_ROOMNO);
////			ps.setInt(1, roomNo);
////			rs = ps.executeQuery();
////
////			if (rs.next()) {
////				chatroomVO = new ChatroomVO();
////				chatroomVO.setRoomNo(rs.getInt("room_no"));
////				chatroomVO.setMemNo(rs.getInt("mem_no"));
////				chatroomVO.setChatMsgDirect(rs.getInt("chat_msg_direct"));
////				chatroomVO.setChatMsgTime(rs.getTimestamp("chat_msg_time"));
////				chatroomVO.setChatMsgContext(rs.getString("chat_msg_context"));
////				chatroomVO.setChatMsgPhoto(rs.getBytes("chat_msg_photo"));
////			}
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			Util.closeResources(con, ps, rs);
////		}
////
////		return chatroomVO;
////	}
////
////	@Override
////	public List<ChatroomVO> getAll() {
////		Connection con = null;
////		PreparedStatement ps = null;
////		ResultSet rs = null;
////		List<ChatroomVO> chatroomVOList = new ArrayList<>();
////
////		try {
////			con = Util.getConnection();
////			ps = con.prepareStatement(GET_ALL);
////			rs = ps.executeQuery();
////
////			while (rs.next()) {
////				ChatroomVO chatroomVO = new ChatroomVO();
////				chatroomVO.setRoomNo(rs.getInt("room_no"));
////				chatroomVO.setMemNo(rs.getInt("mem_no"));
////				chatroomVO.setChatMsgDirect(rs.getInt("chat_msg_direct"));
////				chatroomVO.setChatMsgTime(rs.getTimestamp("chat_msg_time"));
////				chatroomVO.setChatMsgContext(rs.getString("chat_msg_context"));
////				chatroomVO.setChatMsgPhoto(rs.getBytes("chat_msg_photo"));
////
////				chatroomVOList.add(chatroomVO);
////			}
////		} catch (SQLException e) {
////			e.printStackTrace();
////		} finally {
////			Util.closeResources(con, ps, rs);
////		}
////
////		return chatroomVOList;
////	}
////	public static void main(String[] args) {
//////	    ChatroomDAO chatroomDAO = new ChatroomDAOImpl();
//////	    ChatroomVO chatroomVO = new ChatroomVO();
////
//////	    // 新增
//////	    chatroomVO.setMemNo(9);  // 假設的數值，您可能需要相應的設定值
//////	    chatroomVO.setChatMsgDirect(1); // 假設的數值
//////	    chatroomVO.setChatMsgTime(Timestamp.valueOf("2023-11-30 12:00:00"));
//////	    chatroomVO.setChatMsgContext("Example context");
//////	    chatroomVO.setChatMsgPhoto(null); // 假設為null，您可能需要設定相應的值
//////	    chatroomDAO.insert(chatroomVO);
////
//////	    // 修改
//////	    chatroomVO.setMemNo(5);  // 假設的數值
//////	    chatroomVO.setChatMsgDirect(2);  // 假設的數值
//////	    chatroomVO.setChatMsgTime(Timestamp.valueOf("2023-11-29 11:00:00"));
//////	    chatroomVO.setChatMsgContext("Updated context");
//////	    chatroomVO.setRoomNo(19);  // 假設的數值
//////	    chatroomDAO.update(chatroomVO);
////
//////	    // 刪除
//////	    chatroomDAO.delete(17);  // 假設的數值
//////
//////	    // 查詢
//////	    chatroomVO = chatroomDAO.findByPrimaryKey(13);  // 假設的數值
//////	    System.out.println(chatroomVO);
//////
//////	    // 查詢全部
//////	    List<ChatroomVO> chatroomVOList = chatroomDAO.getAll();
//////	    for(ChatroomVO chatroomVOItem: chatroomVOList) {
//////	        System.out.println(chatroomVOItem.getRoomNo());
//////	        System.out.println(chatroomVOItem.getMemNo());
//////	        System.out.println(chatroomVOItem.getChatMsgDirect());
//////	        System.out.println(chatroomVOItem.getChatMsgTime());
//////	        System.out.println(chatroomVOItem.getChatMsgContext());
//////	        System.out.println(chatroomVOItem.getChatMsgPhoto());
//////	    }
////	}
////}