package com.woof.member.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.woof.member.entity.Member;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

public class MemberDAOImpl implements MemberDAO {
	private SessionFactory factory;

	public MemberDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(Member member) {
		getSession().save(member);
		return 1;
	}

	@Override
	public int update(Member member) {
		try {
			getSession().merge(member);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int deletePhoto(String memNo) {
		Member member = getSession().get(Member.class, memNo);
		if (member != null) {
			getSession().delete(member);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Member findByMemberNo(String memNo) {
		return getSession().get(Member.class, memNo);
	}

	@Override
	public List<Member> getAll() {
		return getSession().createQuery("FROM Member", Member.class).list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("SELECT count(*) FROM Member", Long.class).uniqueResult();
	}

	@Override
	public List<Member> getByCompositeQuery(Map<String, String> map) {
		return null;
	}

	@Override
	public List<Member> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("FROM Member", Member.class).setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT).list();
	}

	@Override
	public Member findByMemberEmail(String memEmail) {
		
		String hql = "FROM Member mem WHERE mem.memEmail = :memEmail";
		Query query = getSession().createQuery(hql , Member.class);
		query.setParameter("memEmail", memEmail);
		
		return (Member) query.getSingleResult();
		
	}

	public void updateTotalClass(String memNo , Integer clazz){
		String hql = "UPDATE Member SET totalClass = totalClass + :totalClass WHERE memNo = :memNo";
		Query query = getSession().createQuery(hql);
		query.setParameter("totalClass" , clazz);
		query.setParameter("memNo" , memNo);
		query.executeUpdate();
	}

}