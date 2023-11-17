package com.woof.privatetrainingappointmentform.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
import java.sql.Timestamp;
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
	public PrivateTrainingAppointmentForm addPrivateTrainingAppointmentForm(Member member, Trainer trainer, Integer ptaClass) {

		PrivateTrainingAppointmentForm privateTrainingAppointmentForm = new PrivateTrainingAppointmentForm();
		privateTrainingAppointmentForm.setMember(member);
		privateTrainingAppointmentForm.setTrainer(trainer);
		privateTrainingAppointmentForm.setPtaClass(ptaClass);
		dao.insert(privateTrainingAppointmentForm);

		return privateTrainingAppointmentForm;
	}

	@Override
	public int updatePrivateTrainingAppointmentForm(Integer ptaNo, Member member, Trainer trainer, Integer ptaClass) {
		PrivateTrainingAppointmentForm privateTrainingAppointmentForm = new PrivateTrainingAppointmentForm();
		privateTrainingAppointmentForm.setPtaNo(ptaNo);
		privateTrainingAppointmentForm.setMember(member);
		privateTrainingAppointmentForm.setTrainer(trainer);
		privateTrainingAppointmentForm.setPtaClass(ptaClass);
		return dao.update(privateTrainingAppointmentForm);
	}
	
	@Override
	public int insertComment(Integer ptaNo, Member member, Trainer trainer, Integer ptaClass, String ptaComment,
			Timestamp commentTime) {
		PrivateTrainingAppointmentForm privateTrainingAppointmentForm = new PrivateTrainingAppointmentForm();
		privateTrainingAppointmentForm.setPtaNo(ptaNo);
		privateTrainingAppointmentForm.setMember(member);
		privateTrainingAppointmentForm.setTrainer(trainer);
		privateTrainingAppointmentForm.setPtaClass(ptaClass);
		privateTrainingAppointmentForm.setPtaComment(ptaComment);
		privateTrainingAppointmentForm.setCommentTime(commentTime);
		return dao.update(privateTrainingAppointmentForm);
	}

	@Override
	public int updateComment(Integer ptaNo, Member member, Trainer trainer, Integer ptaClass, String ptaComment,
			Timestamp commentTime, Timestamp commentUpTime) {
		PrivateTrainingAppointmentForm privateTrainingAppointmentForm = new PrivateTrainingAppointmentForm();
		privateTrainingAppointmentForm.setPtaNo(ptaNo);
		privateTrainingAppointmentForm.setMember(member);
		privateTrainingAppointmentForm.setTrainer(trainer);
		privateTrainingAppointmentForm.setPtaClass(ptaClass);
		privateTrainingAppointmentForm.setPtaComment(ptaComment);
		privateTrainingAppointmentForm.setCommentTime(commentTime);
		privateTrainingAppointmentForm.setCommentUpTime(commentUpTime);
		return dao.update(privateTrainingAppointmentForm);
	}

	@Override
	public int deletePrivateTrainingAppointmentForm(Integer ptaNo) {
		PrivateTrainingAppointmentForm privateTrainingAppointmentForm = new PrivateTrainingAppointmentForm();
		privateTrainingAppointmentForm.setPtaNo(ptaNo);
		return dao.delete(privateTrainingAppointmentForm);
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

//	@Override
//	public List<PrivateTrainingAppointmentForm> findPrivateTrainingAppointmentFormByMemNo(String memNo , int currentPage) {
//		return dao.findByMemNo(memNo , currentPage);
//	}
//	
//	@Override
//	public int getPageTotal2(String memNo) {
//		long total = dao.getTotalMember(memNo);
//		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
//		return pageQty;
//	}
	
	@Override
	public List<PrivateTrainingAppointmentForm> findPrivateTrainingAppointmentFormByTrainerNo(Integer trainerNo , int currentPage) {
		return dao.findByTrainerNo(trainerNo , currentPage);
	}

	@Override
	public int getPageTotal3(Integer trainerNo) {
		long total = dao.getTotalTrainer(trainerNo);
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	@Override
	public List<Date> getByTrainer(Integer year, Integer month, Integer trainerNo) {
		return dao.getByTrainer(year , month ,trainerNo);
	}

	public List<PrivateTrainingAppointmentForm> findPrivateTrainingAppointmentFormByMemNo(String memNo,
			int currentPage) {
		return dao.getByMemNo(memNo,currentPage);
	}

	@Override
	public int getPageTotal4(String memNo) {
		long total = dao.getTotalByMemNo(memNo);
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	@Override
	public List<PrivateTrainingAppointmentForm> findByBoth(String memNo, Integer trainerNo, int currentPage) {
		return dao.getByBoth(memNo, trainerNo, currentPage);
	}

	@Override
	public int getPageTotal5(String memNo, Integer trainerNo) {
		long total = dao.getTotalByBoth(memNo,trainerNo);
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}


}
