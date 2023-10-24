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
	public int modify(Integer memNo, String memName, String memGender, byte[] memPhoto, String memEmail,
			String memPassword, String memTel, String memAddress, Date memBd, Integer momoPoint, Integer totalClass,
			Integer memStatus) {
		Member member = new Member();
		member.setMemAddress(memAddress);
		member.setMemBd(memBd);
		member.setMemEmail(memEmail);
		member.setMemGender(memGender);
		member.setMemName(memName);
		member.setMemNo(memNo);
		member.setMemPassword(memPassword);
		member.setMemStatus(memStatus);
		member.setMemTel(memTel);
		member.setMomoPoint(momoPoint);
		member.setMemStatus(memStatus);
		member.setTotalClass(totalClass);
		if (memPhoto != null) {
			member.setMemPhoto(memPhoto);
		} else {
			member.setMemPhoto(null);
		}
		dao.update(member);
		return 1;
	}

	public MemberDAO getDao() {
		return dao;
	}

	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}

	@Override
	public Member updateMember(Member member) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
		if (dao.update(member) == 1) {
			return member;
		}
		return null;
	}

	@Override
	public void deleteMember(Integer memNo) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
		if (dao.delete(memNo) == 1) {
			return;
		}
	}

	@Override
	public Member findMemberByNo(Integer memNo) {
		Member member = dao.findByMemberNo(memNo);
		return member;
	}

	@Override
	public List<Member> getAllMembers() {
		return dao.getAll();
	}

	public byte[] getPhotoById(Integer memNo) {
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
	public int addMember(String memName, String memGender, String memEmail, String memPassword, String memTel,
			String memAddress, Date memBd, Integer momoPoint, Integer totalClass, Integer memStatus) {
		Member member = new Member();
		member.setMemAddress(memAddress);
		member.setMemBd(memBd);
		member.setMemEmail(memEmail);
		member.setMemGender(memGender);
		member.setMemName(memName);
		member.setMemPassword(memPassword);
		member.setMemStatus(memStatus);
		member.setMemTel(memTel);
		member.setMomoPoint(momoPoint);
		member.setTotalClass(totalClass);
		dao.insert(member);
		return 1;
	}
}
//
