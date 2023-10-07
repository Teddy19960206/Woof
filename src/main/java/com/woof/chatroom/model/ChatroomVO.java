package com.woof.chatroom.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

public class ChatroomVO implements java.io.Serializable {
	private Integer roomNo;
	private Integer memNo;
	private Integer chatMsgDirect;
	private Timestamp chatMsgTime;
	private String chatMsgContext;
	private byte[] chatMsgPhoto;

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
		ChatroomVO other = (ChatroomVO) obj;
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