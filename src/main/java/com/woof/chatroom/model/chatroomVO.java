package com.woof.chatroom.model;

import java.sql.Timestamp;

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
}
