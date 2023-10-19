package com.woof.privatetrainingappointmentform.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormService;
import com.woof.privatetrainingappointmentform.service.PrivateTrainingAppointmentFormServiceImpl;
import com.woof.member.entity.Member;
import com.woof.member.service.MemberService;
import com.woof.member.service.MemberServiceImpl;
import com.woof.trainer.entity.Trainer;
import com.woof.trainer.service.TrainerService;
import com.woof.trainer.service.TrainerServiceImpl;

@WebServlet("/privatetrainingappointmentform")
public class PrivateTrainingAppointmentFormServlet extends HttpServlet {

	private PrivateTrainingAppointmentFormService privateTrainingAppointmentFormService;

	@Override
	public void init() throws ServletException {
		privateTrainingAppointmentFormService = new PrivateTrainingAppointmentFormServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		String forwardPath = "";
        if (action != null){
            switch (action){
                case "add":
                    addPrivateTrainingAppointmentForm(req,resp);
                    forwardPath = "/frontend/privatetrainingappointmentform/privatetrainingappointmentform.jsp";
                    break;
                default:
                    forwardPath = "/frontend/privatetrainingappointmentform/privatetrainingappointmentform_add.jsp";
            }
        }else{
            forwardPath = "/frontend/privatetrainingappointmentform/privatetrainingappointmentform_add.jsp";
        }
        req.getRequestDispatcher(forwardPath).forward(req,resp);
		resp.getWriter().println(action);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		System.out.println(privateTrainingAppointmentFormService.getAllPrivateTrainingAppointmentForms());
	}

	private void addPrivateTrainingAppointmentForm(HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		Integer memNo = Integer.valueOf(request.getParameter("member"));
		MemberService memberService = new MemberServiceImpl();
		Member member = memberService.findMemberByNo(memNo);
		
		Integer trainerNo = Integer.valueOf(request.getParameter("trainer"));
		TrainerService trainerService = new TrainerServiceImpl();
		Trainer trainer = trainerService.findTrainerByTrainerNo(trainerNo);
		
		String ptaClassStr = request.getParameter("number");
		
		int result;
		
		try {
		    Integer ptaClass = Integer.parseInt(ptaClassStr);
		    result = privateTrainingAppointmentFormService.addPrivateTrainingAppointmentForm(member, trainer ,ptaClass);
		} catch (NumberFormatException e) {
		    result = -1;
		}
		
		if ( result == 1){
            System.out.println("新增成功");
        }else{
            System.out.println("新增失敗");
        }
		
		response.sendRedirect(request.getServletContext().getContextPath()+"/privatetrainingappointmentform/privatetrainingappointmentform.jsp");
	}
}
