package com.woof.chatroom.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.woof.chatroom.service.ChatroomService;
import com.woof.chatroom.service.ChatroomServiceImpl;

@WebServlet("/chatroom")
public class ChatroomServlet extends HttpServlet {

	private ChatroomService chatroomService;

	@Override
	public void init() throws ServletException {
		chatroomService = new ChatroomServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String chatroomType = req.getParameter("Chatroom");

		res.getWriter().println(chatroomType);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req,res);
		PrintWriter writer = res.getWriter();
        System.out.println("123");
		System.out.println(chatroomService.getAllChatrooms());
	}
}
