package com.woof.privatetrainingappointmentform.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

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
	public int addPrivateTrainingAppointmentForm(Member member, Trainer trainer, Integer ptaClass) {

		PrivateTrainingAppointmentForm privateTrainingAppointmentForm = new PrivateTrainingAppointmentForm();
		privateTrainingAppointmentForm.setMember(member);
		privateTrainingAppointmentForm.setTrainer(trainer);
		privateTrainingAppointmentForm.setPtaClass(ptaClass);
		return dao.insert(privateTrainingAppointmentForm);
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

	@Override
	public List<PrivateTrainingAppointmentForm> findPrivateTrainingAppointmentFormByMemNo(String memNo , int currentPage) {
		return dao.findByMemNo(memNo , currentPage);
	}
	
	@Override
	public int getPageTotal2(String memNo) {
		long total = dao.getTotalMember(memNo);
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
	
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
	public List<PrivateTrainingAppointmentForm> findPrivateTrainingAppointmentFormByMemName(String memName,
			int currentPage) {
		return dao.getByMemName(memName,currentPage);
	}

	@Override
	public int getPageTotal4(String memName) {
		long total = dao.getTotalByMemName(memName);
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	@Override
	public List<PrivateTrainingAppointmentForm> findByBoth(String memName, Integer trainerNo, int currentPage) {
		return dao.getByBoth(memName, trainerNo, currentPage);
	}

	@Override
	public int getPageTotal5(String memName, Integer trainerNo) {
		long total = dao.getTotalByBoth(memName,trainerNo);
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	
	
}
