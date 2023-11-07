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

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "chatroom")

public class Chatroom {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROOM_NO", updatable = false, nullable = false)
	private Integer roomNo;

	@Expose
	@Column(name = "MEM_NO", nullable = false)
	private String memNo;

	@Expose
	@Column(name = "CHAT_MSG_DIRECT", nullable = false, columnDefinition = "TINYINT")
	private Integer chatMsgDirect;

	@Expose
	@Column(name = "CHAT_MSG_TIME", nullable = false)
	private Timestamp chatMsgTime;

	@Expose
	@Column(name = "CHAT_MSG_CONTEXT")
	private String chatMsgContext;

	@Expose
	@Column(name = "CHAT_MSG_PHOTO", columnDefinition = "MEDIUMBLOB")
	private byte[] chatMsgPhoto;

	public Chatroom() {
	}

	public Chatroom(Integer roomNo, String memNo, Integer chatMsgDirect, Timestamp chatMsgTime, String chatMsgContext,
			byte[] chatMsgPhoto) {
		super();
		this.roomNo = roomNo;
		this.memNo = memNo;
		this.chatMsgDirect = chatMsgDirect;
		this.chatMsgTime = chatMsgTime;
		this.chatMsgContext = chatMsgContext;
		this.chatMsgPhoto = chatMsgPhoto;
	}

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}

	public String getMemNo() {
		return memNo;
	}

	public void setMemNo(String memNo) {
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
		return "Chatroom [roomNo=" + roomNo + ", memNo=" + memNo + ", chatMsgDirect=" + chatMsgDirect + ", chatMsgTime="
				+ chatMsgTime + ", chatMsgContext=" + chatMsgContext + ", chatMsgPhoto=" + ", chatMsgPhotoLength="
				+ (chatMsgPhoto != null ? chatMsgPhoto.length : 0) + "]";
	}
}