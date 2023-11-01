package com.woof.appointmentdetail.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

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
	public int addAd(PrivateTrainingAppointmentForm pta, Timestamp appTime, Integer appStatus, String appVenue) {
		AppointmentDetail appointmentDetail = new AppointmentDetail();
		appointmentDetail.setPrivateTrainingAppointmentForm(pta);
		appointmentDetail.setAppTime(appTime);
		appointmentDetail.setAppStatus(appStatus);
		appointmentDetail.setAppVenue(appVenue);
		dao.insert(appointmentDetail);
		System.out.println(appointmentDetail);
		return 1;
	}

	@Override
	public int updateAd(Integer adNo, PrivateTrainingAppointmentForm pta, Timestamp appTime, Integer appStatus,
			String appVenue) {
		AppointmentDetail appointmentDetail = new AppointmentDetail();
		appointmentDetail.setAdNo(adNo);
		appointmentDetail.setPrivateTrainingAppointmentForm(pta);
		appointmentDetail.setAppTime(appTime);
		appointmentDetail.setAppStatus(appStatus);
		appointmentDetail.setAppVenue(appVenue);
		dao.insert(appointmentDetail);
		System.out.println(appointmentDetail);
		return 1;
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
	public List<AppointmentDetail> findAdByAppTime(Timestamp appTime) {
		
		return dao.findByAppTime(appTime);
	}

	@Override
	public List<AppointmentDetail> findAdByPtaNo(Integer ptaNo) {
		return dao.findByPtaNo(ptaNo);
	}
	
}
