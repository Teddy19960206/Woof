package com.woof.privatetrainingappointmentform.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;
import java.util.List;

import com.woof.member.entity.Member;
import com.woof.trainer.entity.Trainer;
import com.woof.privatetrainingappointmentform.dao.PrivateTrainingAppointmentFormDAO;
import com.woof.privatetrainingappointmentform.dao.PrivateTrainingAppointmentFormDAOImpl;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.util.HibernateUtil;

public class PrivateTrainingAppointmentFormServiceImpl implements PrivateTrainingAppointmentFormService {

	private PrivateTrainingAppointmentFormDAO dao;

	public PrivateTrainingAppointmentFormServiceImpl() {
		dao = new PrivateTrainingAppointmentFormDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public PrivateTrainingAppointmentForm findPrivateTrainingAppointmentFormByPtaNo(Integer ptaNo) {

		PrivateTrainingAppointmentForm privateTrainingAppointmentForm = new PrivateTrainingAppointmentForm();

		privateTrainingAppointmentForm = dao.findByPtaNo(ptaNo);

		return privateTrainingAppointmentForm;
	}

	@Override
	public List<PrivateTrainingAppointmentForm> getAllPrivateTrainingAppointmentForms() {

		List<PrivateTrainingAppointmentForm> privateTrainingAppointmentFormList = dao.getAll();

		// TODO Auto-generated method stub
		return privateTrainingAppointmentFormList;
	}

	@Override
	public int addPrivateTrainingAppointmentForm(Member member, Trainer trainer, Integer ptaClass) {

		PrivateTrainingAppointmentForm privateTrainingAppointmentForm = new PrivateTrainingAppointmentForm();
		privateTrainingAppointmentForm.setMember(member);
		privateTrainingAppointmentForm.setTrainer(trainer);
		privateTrainingAppointmentForm.setPtaClass(ptaClass);
		dao.insert(privateTrainingAppointmentForm);
		System.out.println(privateTrainingAppointmentForm);
		return 1;
	}

	@Override
	public int updatePrivateTrainingAppointmentForm(Integer ptaNo, Member member, Trainer trainer, Integer ptaClass) {
		PrivateTrainingAppointmentForm privateTrainingAppointmentForm = new PrivateTrainingAppointmentForm();
		privateTrainingAppointmentForm.setPtaNo(ptaNo);
		privateTrainingAppointmentForm.setMember(member);
		privateTrainingAppointmentForm.setTrainer(trainer);
		privateTrainingAppointmentForm.setPtaClass(ptaClass);
		dao.update(privateTrainingAppointmentForm);
		return 1;
	}

	@Override
	public int deletePrivateTrainingAppointmentForm(Integer ptaNo) {
		PrivateTrainingAppointmentForm privateTrainingAppointmentForm = new PrivateTrainingAppointmentForm();
		privateTrainingAppointmentForm.setPtaNo(ptaNo);
		dao.delete(privateTrainingAppointmentForm);
		return 1;
	}

	@Override
	public List<PrivateTrainingAppointmentForm> getAllPTAFs(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	@Override
	public PrivateTrainingAppointmentForm findPrivateTrainingAppointmentFormByMemNo(String memNo) {
		PrivateTrainingAppointmentForm privateTrainingAppointmentForm = new PrivateTrainingAppointmentForm();

		privateTrainingAppointmentForm = dao.findByMemNo(memNo);

		return privateTrainingAppointmentForm;

	}

	@Override
	public PrivateTrainingAppointmentForm findPrivateTrainingAppointmentFormByTrainerNo(Integer trainerNo) {
PrivateTrainingAppointmentForm privateTrainingAppointmentForm = new PrivateTrainingAppointmentForm();
		
		privateTrainingAppointmentForm = dao.findByTrainerNo(trainerNo);
		
		return privateTrainingAppointmentForm;

	}

}
