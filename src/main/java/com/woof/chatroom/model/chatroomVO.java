package com.woof.chatroom.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

public class chatroomVO implements java.io.Serializable {
	private Integer roomno;
	private Integer memno;
	private Integer chatmsgdirect;
	private Timestamp chatmsgtime;
	private String chatmsgcontext;
	private byte[] chatmsgphoto;

	public Integer getRoomno() {
		return roomno;
	}

	public void setRoomno(Integer roomno) {
		this.roomno = roomno;
	}

	public Integer getMemno() {
		return memno;
	}

	public void setMemno(Integer memno) {
		this.memno = memno;
	}

	public Integer getChatmsgdirect() {
		return chatmsgdirect;
	}

	public void setChatmsgdirect(Integer chatmsgdirect) {
		this.chatmsgdirect = chatmsgdirect;
	}

	public Timestamp getChatmsgtime() {
		return chatmsgtime;
	}

	public void setChatmsgtime(Timestamp chatmsgtime) {
		this.chatmsgtime = chatmsgtime;
	}

	public String getChatmsgcontext() {
		return chatmsgcontext;
	}

	public void setChatmsgcontext(String chatmsgcontext) {
		this.chatmsgcontext = chatmsgcontext;
	}

	public byte[] getChatmsgphoto() {
		return chatmsgphoto;
	}

	public void setChatmsgphoto(byte[] chatmsgphoto) {
		this.chatmsgphoto = chatmsgphoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(chatmsgphoto);
		result = prime * result + Objects.hash(chatmsgcontext, chatmsgdirect, chatmsgtime, memno, roomno);
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
		chatroomVO other = (chatroomVO) obj;
		return Objects.equals(chatmsgcontext, other.chatmsgcontext)
				&& Objects.equals(chatmsgdirect, other.chatmsgdirect) && Arrays.equals(chatmsgphoto, other.chatmsgphoto)
				&& Objects.equals(chatmsgtime, other.chatmsgtime) && Objects.equals(memno, other.memno)
				&& Objects.equals(roomno, other.roomno);
	}

	@Override
	public String toString() {
		return "chatroomVO [roomno=" + roomno + ", memno=" + memno + ", chatmsgdirect=" + chatmsgdirect
				+ ", chatmsgtime=" + chatmsgtime + ", chatmsgcontext=" + chatmsgcontext + ", chatmsgphoto="
				+ Arrays.toString(chatmsgphoto) + "]";
	}

}
