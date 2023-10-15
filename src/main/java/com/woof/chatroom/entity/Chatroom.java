package com.woof.chatroom.entity;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "chatroom")
@NamedQuery(name = "getAllChatrooms", query = "from Chatroom where roomNo > :roomNo order by roomNo desc")
public class Chatroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROOM_NO", updatable = false, nullable = false)
	private Integer roomNo;

	@Column(name = "MEM_NO", nullable = false)
	private Integer memNo;

	@Column(name = "CHAT_MSG_DIRECT", nullable = false, columnDefinition = "TINYINT")
	private Integer chatMsgDirect;

	@Column(name = "CHAT_MSG_TIME", nullable = false)
	private Timestamp chatMsgTime;

	@Column(name = "CHAT_MSG_CONTETXT")
	private String chatMsgContext;

	@Column(name = "CHAT_MSG_PHOTO", columnDefinition = "MEDIUMBLOB")
	private byte[] chatMsgPhoto;

	public Chatroom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public Integer getChatMsgDirect() {
		return chatMsgDirect;
	}

	public void setChatMsgDirect(Integer chatMsgDirect) {
		this.chatMsgDirect = chatMsgDirect;
	}

	public Timestamp getChatMsgTime() {
		return chatMsgTime;
	}

	public void setChatMsgTime(Timestamp chatMsgTime) {
		this.chatMsgTime = chatMsgTime;
	}

	public String getChatMsgContext() {
		return chatMsgContext;
	}

	public void setChatMsgContext(String chatMsgContext) {
		this.chatMsgContext = chatMsgContext;
	}

	public byte[] getChatMsgPhoto() {
		return chatMsgPhoto;
	}

	public void setChatMsgPhoto(byte[] chatMsgPhoto) {
		this.chatMsgPhoto = chatMsgPhoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(chatMsgPhoto);
		result = prime * result + Objects.hash(chatMsgContext, chatMsgDirect, chatMsgTime, memNo, roomNo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chatroom other = (Chatroom) obj;
		return Objects.equals(chatMsgContext, other.chatMsgContext)
				&& Objects.equals(chatMsgDirect, other.chatMsgDirect) && Arrays.equals(chatMsgPhoto, other.chatMsgPhoto)
				&& Objects.equals(chatMsgTime, other.chatMsgTime) && Objects.equals(memNo, other.memNo)
				&& Objects.equals(roomNo, other.roomNo);
	}

	@Override
	public String toString() {
		return "ChatroomVO [roomNo=" + roomNo + ", memNo=" + memNo + ", chatMsgDirect=" + chatMsgDirect
				+ ", chatMsgTime=" + chatMsgTime + ", chatMsgContext=" + chatMsgContext + ", chatMsgPhoto="
				+ Arrays.toString(chatMsgPhoto) + "]";
	}
}

//package com.woof.chatroom.model;
//
//import java.sql.Timestamp;
//import java.util.Arrays;
//import java.util.Objects;
//
//public class ChatroomVO implements java.io.Serializable {
//	private Integer roomNo;
//	private Integer memNo;
//	private Integer chatMsgDirect;
//	private Timestamp chatMsgTime;
//	private String chatMsgContext;
//	private byte[] chatMsgPhoto;
//
//	public Integer getRoomNo() {
//		return roomNo;
//	}
//
//	public void setRoomNo(Integer roomNo) {
//		this.roomNo = roomNo;
//	}
//
//	public Integer getMemNo() {
//		return memNo;
//	}
//
//	public void setMemNo(Integer memNo) {
//		this.memNo = memNo;
//	}
//
//	public Integer getChatMsgDirect() {
//		return chatMsgDirect;
//	}
//
//	public void setChatMsgDirect(Integer chatMsgDirect) {
//		this.chatMsgDirect = chatMsgDirect;
//	}
//
//	public Timestamp getChatMsgTime() {
//		return chatMsgTime;
//	}
//
//	public void setChatMsgTime(Timestamp chatMsgTime) {
//		this.chatMsgTime = chatMsgTime;
//	}
//
//	public String getChatMsgContext() {
//		return chatMsgContext;
//	}
//
//	public void setChatMsgContext(String chatMsgContext) {
//		this.chatMsgContext = chatMsgContext;
//	}
//
//	public byte[] getChatMsgPhoto() {
//		return chatMsgPhoto;
//	}
//
//	public void setChatMsgPhoto(byte[] chatMsgPhoto) {
//		this.chatMsgPhoto = chatMsgPhoto;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + Arrays.hashCode(chatMsgPhoto);
//		result = prime * result + Objects.hash(chatMsgContext, chatMsgDirect, chatMsgTime, memNo, roomNo);
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ChatroomVO other = (ChatroomVO) obj;
//		return Objects.equals(chatMsgContext, other.chatMsgContext)
//				&& Objects.equals(chatMsgDirect, other.chatMsgDirect) && Arrays.equals(chatMsgPhoto, other.chatMsgPhoto)
//				&& Objects.equals(chatMsgTime, other.chatMsgTime) && Objects.equals(memNo, other.memNo)
//				&& Objects.equals(roomNo, other.roomNo);
//	}
//
//	@Override
//	public String toString() {
//		return "ChatroomVO [roomNo=" + roomNo + ", memNo=" + memNo + ", chatMsgDirect=" + chatMsgDirect
//				+ ", chatMsgTime=" + chatMsgTime + ", chatMsgContext=" + chatMsgContext + ", chatMsgPhoto="
//				+ Arrays.toString(chatMsgPhoto) + "]";
//	}
//}