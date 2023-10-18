package com.woof.trainer.controller;


	
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.woof.trainer.service.TrainerService;
import com.woof.trainer.service.TrainerServiceImpl;
import com.woof.trainer.service.TrainerService;
	import com.woof.trainer.service.TrainerServiceImpl;

	@WebServlet("/trainer")
	public class TrainerServlet  extends HttpServlet {

		private TrainerService trainerService;

		@Override
		public void init() throws ServletException {
			trainerService = new TrainerServiceImpl();
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			String trainerType = req.getParameter("Trainer");

			res.getWriter().println(trainerType);
		}

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			super.doGet(req,res);
			PrintWriter writer = res.getWriter();
	        System.out.println("123");
			System.out.println(trainerService.getAllTrainers());
		}
	}


