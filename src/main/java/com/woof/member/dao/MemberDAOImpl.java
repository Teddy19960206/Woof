package com.woof.member.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.woof.member.entity.Member;
import java.util.List;
import java.util.Map;

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
			getSession().update(member);
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
		// TODO Auto-generated method stub
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
}