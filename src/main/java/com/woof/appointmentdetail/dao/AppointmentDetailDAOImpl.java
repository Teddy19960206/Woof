package com.woof.appointmentdetail.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.appointmentdetail.entity.AppointmentDetail;
import org.hibernate.query.Query;

public class AppointmentDetailDAOImpl implements AppointmentDetailDAO {

	private SessionFactory factory;

	public AppointmentDetailDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(AppointmentDetail appointmentDetail) {
		try {
			getSession().save(appointmentDetail);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int update(AppointmentDetail appointmentDetail) {
		try {
			getSession().merge(appointmentDetail);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	
	@Override
	public int delete(AppointmentDetail appointmentDetail) {
		try {
			getSession().delete(appointmentDetail);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public AppointmentDetail findByAdNo(Integer adNo) {
		return getSession().get(AppointmentDetail.class, adNo);
	}

	@Override
	public List<AppointmentDetail> getAll() {
		return getSession().createQuery("FROM AppointmentDetail ORDER BY appTime DESC", AppointmentDetail.class).list();
	}

	@Override
	public List<AppointmentDetail> findByAppTime(Date appTime) {
		return getSession().createQuery("FROM AppointmentDetail a WHERE a.appTime = :appTime", AppointmentDetail.class)
				.setParameter("appTime", appTime)
				.list();
	}

	@Override
	public List<AppointmentDetail> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM AppointmentDetail ORDER BY appTime DESC", AppointmentDetail.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from AppointmentDetail", Long.class).uniqueResult();
	}

	@Override
	public List<AppointmentDetail> findByPtaNo(Integer ptaNo) {
		return getSession().createQuery("FROM AppointmentDetail a WHERE a.privateTrainingAppointmentForm.ptaNo = :ptaNo ORDER BY appTime DESC", AppointmentDetail.class)
				.setParameter("ptaNo", ptaNo)
				.list();
	}

	@Override
	public long getTotalByPtaNo(Integer ptaNo) {
		return getSession().createQuery("select count(*) from AppointmentDetail a where a.privateTrainingAppointmentForm.ptaNo = :ptaNo and appStatus = 0", Long.class)
				.setParameter("ptaNo", ptaNo)
				.uniqueResult();
	}


	public List<AppointmentDetail> getAllByTrainer(Integer trainerNo){

		String hql = "FROM AppointmentDetail ad WHERE ad.privateTrainingAppointmentForm.trainer.trainerNo = :trainerNo";
		Query query = getSession().createQuery(hql , AppointmentDetail.class);
		query.setParameter("trainerNo" , trainerNo);
		return query.list();

	}

	@Override
	public int addAll(List<AppointmentDetail> appointmentDetails){
		for (AppointmentDetail appointmentDetail : appointmentDetails){
			getSession().persist(appointmentDetail);
		}
		getSession().flush();
		return 1;
	}

	@Override
	public AppointmentDetail getOneByDate(Integer trainerNo , Date date){

		String hql = "FROM AppointmentDetail ad WHERE ad.privateTrainingAppointmentForm.trainer.trainerNo = :trainerNo and ad.appTime = :date";
		Query query = getSession().createQuery(hql , AppointmentDetail.class);
		query.setParameter("trainerNo" , trainerNo);
		query.setParameter("date", date);
		return (AppointmentDetail) query.getSingleResult();
	}

	// 找出預約時間在現在時刻或之前且預約狀態為0(接受)的預約單明細
	@Override
	public List<AppointmentDetail> getUpdateStatus() {
		return getSession().createQuery("FROM AppointmentDetail ad WHERE ad.appTime <= NOW() AND ad.appStatus = 0 ", AppointmentDetail.class)
				.list();
	}


	@Override
	public void updateStatus(List<AppointmentDetail> list) {
		
		for(AppointmentDetail appointmentDetail : list) {
			appointmentDetail.setAppStatus(2);
			getSession().persist(appointmentDetail);
		}
		getSession().flush();
		
	}

}
