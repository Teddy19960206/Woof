package com.woof.privatetrainingappointmentform.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.query.Query;

import javax.persistence.TemporalType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.appointmentdetail.entity.AppointmentDetail;
import com.woof.appointmentdetail.entity.AppointmentDetailDTO;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;


public class PrivateTrainingAppointmentFormDAOImpl implements PrivateTrainingAppointmentFormDAO {

	private SessionFactory factory;

	public PrivateTrainingAppointmentFormDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(PrivateTrainingAppointmentForm privateTrainingAppointmentForm) {


		return (int) getSession().save(privateTrainingAppointmentForm);

	}

	@Override
	public int update(PrivateTrainingAppointmentForm privateTrainingAppointmentForm) {
		try {
			getSession().merge(privateTrainingAppointmentForm);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	public int delete(PrivateTrainingAppointmentForm privateTrainingAppointmentForm) {
		try {
			getSession().delete(privateTrainingAppointmentForm);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	
	@Override
	public PrivateTrainingAppointmentForm findByPtaNo(Integer ptaNo) {
		return getSession().get(PrivateTrainingAppointmentForm.class, ptaNo);
	}

	@Override
	public List<PrivateTrainingAppointmentForm> getAll() {
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm", PrivateTrainingAppointmentForm.class)
				.list();
	}
	
	@Override
	public List<PrivateTrainingAppointmentForm> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm ORDER BY ptaNo DESC", PrivateTrainingAppointmentForm.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from PrivateTrainingAppointmentForm", Long.class).uniqueResult();
	}

	@Override
	public List<PrivateTrainingAppointmentForm> findByMemNo(String memNo , int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm p WHERE p.member.memNo = :memNo ORDER BY ptaNo DESC", PrivateTrainingAppointmentForm.class)
				.setParameter("memNo", memNo)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotalMember(String memNo) {
		return getSession().createQuery("SELECT COUNT(*) from PrivateTrainingAppointmentForm p WHERE p.member.memNo = :memNo", Long.class)
				.setParameter("memNo", memNo)
				.uniqueResult();
	}
	
	@Override
	public List<PrivateTrainingAppointmentForm> findByTrainerNo(Integer trainerNo , int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm WHERE trainer.trainerNo = :trainerNo ORDER BY ptaNo DESC", PrivateTrainingAppointmentForm.class)
				.setParameter("trainerNo", trainerNo)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}
	
	@Override
	public long getTotalTrainer(Integer trainerNo) {
		return getSession().createQuery("SELECT COUNT(*) from PrivateTrainingAppointmentForm  WHERE trainer.trainerNo = :trainerNo", Long.class)
				.setParameter("trainerNo", trainerNo)
				.uniqueResult();
	}
	
	public List<Date> getByTrainer(Integer year , Integer month , Integer trainerNo){

		String sql = "SELECT ad.APP_TIME " +
				"FROM PRIVATE_TRAINING_APPOINTMENT_FORM pta " +
				"JOIN APPOINTMENT_DETAIL ad ON pta.PTA_NO = ad.PTA_NO " +
				"WHERE pta.TRAINER_NO = :trainerNo AND " +
				"MONTH(ad.APP_TIME) = :month AND " +
				"YEAR(ad.APP_TIME) = :year AND " +
				"ad.APP_STATUS = 0";

		NativeQuery query = getSession().createNativeQuery(sql);
		query.setParameter("trainerNo", trainerNo);
		query.setParameter("month", month);
		query.setParameter("year", year);
		List<Date> dates = query.list();

		return dates;
	}



	@Override
	public List<PrivateTrainingAppointmentForm> getByMemNo(String memNo, int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm p WHERE p.member.memNo LIKE :memNo ORDER BY ptaNo DESC",PrivateTrainingAppointmentForm.class)
				.setParameter("memNo", "%" + memNo + "%")
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.getResultList();
		

	}

	@Override
	public long getTotalByMemNo(String memNo) {
		long totalCount = getSession()
		        .createQuery("SELECT COUNT(*) from PrivateTrainingAppointmentForm p WHERE p.member.memNo LIKE :memNo", Long.class)
		        .setParameter("memNo", "%" + memNo + "%")
		        .uniqueResult();

//		    System.out.println("===============================Total count for member " + memName + " is: " + totalCount); // 在這裡印出值

		    return totalCount;
	}

	@Override
	public List<PrivateTrainingAppointmentForm> getByBoth(String memNo, Integer trainerNo, int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm p WHERE p.trainer.trainerNo = :trainerNo AND p.member.memNo LIKE :memNo ORDER BY ptaNo DESC",PrivateTrainingAppointmentForm.class)
				.setParameter("trainerNo", trainerNo)
				.setParameter("memNo", "%" + memNo + "%")
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.getResultList();
	}

	@Override
	public long getTotalByBoth(String memNo, Integer trainerNo) {
		return getSession().createQuery("SELECT COUNT(*) FROM PrivateTrainingAppointmentForm p WHERE p.trainer.trainerNo = :trainerNo AND p.member.memNo LIKE :memNo", Long.class)
				.setParameter("trainerNo", trainerNo)
				.setParameter("memNo", "%" + memNo + "%")
		        .uniqueResult();
	}

	@Override
	public List<PrivateTrainingAppointmentForm> getAppointmentByMemNo(String memNo) {
		String sqlQuery = "SELECT * FROM private_training_appointment_form p " +
                "JOIN member m ON p.MEM_NO = m.MEM_NO " +
                "WHERE m.MEM_NO = :memNo " +
                "ORDER BY p.PTA_NO DESC";
		
		return getSession()
		      .createNativeQuery(sqlQuery, PrivateTrainingAppointmentForm.class)
		      .setParameter("memNo", memNo)
		      .list();
	}

	@Override
	public List<PrivateTrainingAppointmentForm> getNonReportedComment(Integer trainerNo) {
		return getSession().createQuery(
	            "SELECT PTA FROM PrivateTrainingAppointmentForm PTA " +
	            "LEFT JOIN PTA.commentReports CR " +
	            "WHERE (CR IS NULL OR CR.crStatus NOT IN (1)) " +
	            "AND PTA.ptaComment IS NOT NULL " +
	            "AND PTA.trainer.trainerNo = :trainerNo " +
	            "ORDER BY PTA.commentTime DESC",
	            PrivateTrainingAppointmentForm.class)
	            .setParameter("trainerNo", trainerNo)
	            .getResultList();
	}


	@Override
	public boolean canComment(Integer ptaNo) {
		boolean valuesExist = false;

        // 準備 HQL 查詢
        String hql = "SELECT COUNT(pta) "+
        		"FROM PrivateTrainingAppointmentForm pta "+
        		"JOIN pta.appointmentDetails ad "+
        		"JOIN pta.member mem "+
        		"WHERE ad.appTime <= CURRENT_DATE - 1 AND pta.ptaNo = :ptaNo";

        // 創建查詢
        Query<Long> query = getSession().createQuery(hql, Long.class);

        // 取得查詢結果
        Long count = query.setParameter("ptaNo", ptaNo).uniqueResult();

        // 檢查是否有找到值
        if (count != null && count > 0) {
            valuesExist = true;
        }

        return valuesExist;
    }
}
