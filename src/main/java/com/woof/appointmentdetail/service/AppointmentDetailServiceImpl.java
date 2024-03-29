package com.woof.appointmentdetail.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;
import com.woof.appointmentdetail.dao.AppointmentDetailDAO;
import com.woof.appointmentdetail.dao.AppointmentDetailDAOImpl;
import com.woof.appointmentdetail.entity.AppointmentDetail;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.util.HibernateUtil;

public class AppointmentDetailServiceImpl implements AppointmentDetailService{
	
	private AppointmentDetailDAO dao;
	
	public AppointmentDetailServiceImpl() {
		dao = new AppointmentDetailDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public AppointmentDetail findAdByAdNo(Integer adNo) {

		return dao.findByAdNo(adNo);
	}

	@Override
	public int addAd(PrivateTrainingAppointmentForm pta, Date appTime, Integer appStatus) {
		AppointmentDetail appointmentDetail = new AppointmentDetail();
		appointmentDetail.setPrivateTrainingAppointmentForm(pta);
		appointmentDetail.setAppTime(appTime);
		appointmentDetail.setAppStatus(appStatus);
		return dao.insert(appointmentDetail);
	}

	@Override
	public int updateAd(Integer adNo, PrivateTrainingAppointmentForm pta, Date appTime, Integer appStatus
			) {
		AppointmentDetail appointmentDetail = new AppointmentDetail();
		appointmentDetail.setAdNo(adNo);
		appointmentDetail.setPrivateTrainingAppointmentForm(pta);
		appointmentDetail.setAppTime(appTime);
		appointmentDetail.setAppStatus(appStatus);
		return dao.update(appointmentDetail);

	}

	
	@Override
	public int deleteAd(Integer adNo) {
		AppointmentDetail appointmentDetail = new AppointmentDetail();
		appointmentDetail.setAdNo(adNo);
		return dao.delete(appointmentDetail);
	}

	@Override
	public List<AppointmentDetail> getAllAppointmentDetails() {
		return dao.getAll();
	}

	@Override
	public List<AppointmentDetail> getAds(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	@Override
	public List<AppointmentDetail> findAdByAppTime(Date appTime) {
		
		return dao.findByAppTime(appTime);
	}

	@Override
	public List<AppointmentDetail> findAdByPtaNo(Integer ptaNo) {
		return dao.findByPtaNo(ptaNo);
	}
	
	@Override
	public long getTotalByPtaNo(Integer ptaNo) {
		return dao.getTotalByPtaNo(ptaNo);
	}


	public int addAll(PrivateTrainingAppointmentForm pta, List<java.sql.Date> appTime, Integer appStatus){

		List<AppointmentDetail> appointmentDetails = new ArrayList<>();
		for (Date date : appTime){
			AppointmentDetail appointmentDetail = new AppointmentDetail();
			appointmentDetail.setPrivateTrainingAppointmentForm(pta);
			appointmentDetail.setAppTime(date);
			appointmentDetail.setAppStatus(appStatus);

			appointmentDetails.add(appointmentDetail);
		}

		return dao.addAll(appointmentDetails);
	}

	@Override
	public AppointmentDetail getOneByDate(Integer trainerNo, Date date){
		return dao.getOneByDate(trainerNo , date);
	}

	@Override
	public List<AppointmentDetail> getAllUpdateStatus() {
		return dao.getUpdateStatus();
	}


	@Override
	public void updateComplete(List<AppointmentDetail> list) {
		// TODO Auto-generated method stub
		dao.updateStatus(list);
	}

}

