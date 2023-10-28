package com.woof.member.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.woof.member.dao.MemberDAO;
import com.woof.member.dao.MemberDAOImpl;
import com.woof.member.entity.Member;
import com.woof.util.HibernateUtil;

public class MemberServiceImpl implements MemberService {

	private MemberDAO dao;

	public MemberServiceImpl() {
		dao = new MemberDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public void updateMember(Member member) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
		dao.update(member);
	}

	@Override
	public void deleteMember(String memNo) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
		if (dao.delete(memNo) == 1) {
			return;
		}
	}

	@Override
	public Member findMemberByNo(String memNo) {
		Member member = dao.findByMemberNo(memNo);
		return member;
	}

	@Override
	public List<Member> getAllMembers() {
		return dao.getAll();
	}

	public byte[] getPhotoById(String memNo) {
		return findMemberByNo(memNo).getMemPhoto();
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();

		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	@Override
	public List<Member> getMembersByCompositeQuery(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addMember(Member member) {
		
		dao.insert(member);
	}
	
}