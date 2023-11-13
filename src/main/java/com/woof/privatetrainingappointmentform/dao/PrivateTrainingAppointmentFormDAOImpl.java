package com.woof.privatetrainingappointmentform.dao;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.groupcourseorder.entity.GroupCourseOrder;
import com.woof.member.dao.MemberDAO;
import com.woof.member.dao.MemberDAOImpl;
import com.woof.member.entity.Member;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;


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
		try {
			getSession().save(privateTrainingAppointmentForm);
			return 1;
		} catch (Exception e) {
			return -1;
		}
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
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm", PrivateTrainingAppointmentForm.class)
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
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm p WHERE p.member.memNo = :memNo", PrivateTrainingAppointmentForm.class)
				.setParameter("memNo", memNo)
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
		return getSession().createQuery("FROM PrivateTrainingAppointmentForm WHERE trainer.trainerNo = :trainerNo", PrivateTrainingAppointmentForm.class)
				.setParameter("trainerNo", trainerNo)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}
	
	@Override
	public long getTotalTrainer(Integer trainerNo) {
		return getSession().createQuery("SELECT COUNT(*) from PrivateTrainingAppointmentForm  WHERE trainer.trainerNo = :trainerNo", Long.class)
				.setParameter("trainerNo", trainerNo)
				.uniqueResult();
	}

	@Override
	public List<PrivateTrainingAppointmentForm> getAllMemberAndTrainer(String memNo, Integer trainerNo,
			Integer currentPage) {
		  CriteriaBuilder builder = getSession().getCriteriaBuilder();
		  CriteriaQuery<PrivateTrainingAppointmentForm> criteriaQuery = builder.createQuery(PrivateTrainingAppointmentForm.class);
		  Root<PrivateTrainingAppointmentForm> root = criteriaQuery.from(PrivateTrainingAppointmentForm.class);
		  
		  List<Predicate> predicates = new ArrayList<>();
		  

	      if (trainerNo != null){
	          predicates.add(builder.equal(root.get("trainer").get("administrator").get("adminName") , trainerNo));
	      }
	       
	      if (memNo != null){
	          predicates.add(builder.like(root.get("member").get("memName"), '%'+memNo+'%'));
	      }
	      int first = (currentPage - 1) * PAGE_MAX_RESULT;
	      criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
	      TypedQuery<PrivateTrainingAppointmentForm> query = getSession().createQuery(criteriaQuery);
	      
	      List<PrivateTrainingAppointmentForm> list = query
	                .setFirstResult(first)
	                .setMaxResults(PAGE_MAX_RESULT)
	                .getResultList();

	        return list;
	}

	@Override
	public long getTotalMemberAndTrainer(String memNo, Integer trainerNo) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<GroupCourseOrder> root = criteriaQuery.from(GroupCourseOrder.class);

        criteriaQuery.select(builder.count(root));

        List<Predicate> predicates = new ArrayList<>();

        if (trainerNo != null){
            predicates.add(builder.equal(root.get("trainer").get("trainerNo") , trainerNo));
        }

        if (memNo != null){
            predicates.add(builder.like(root.get("member").get("memName"), '%'+memNo+'%'));
        }

        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<Long> query = getSession().createQuery(criteriaQuery);
        return query.getSingleResult();
	}
	
	
}
