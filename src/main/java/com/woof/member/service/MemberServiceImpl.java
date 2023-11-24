package com.woof.member.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.woof.AppService;
import com.woof.member.dao.MemberDAO;
import com.woof.member.dao.MemberDAOImpl;
import com.woof.member.entity.Member;
import com.woof.util.HibernateUtil;

public class MemberServiceImpl implements MemberService, AppService<String> {

	private MemberDAO dao;

	public MemberServiceImpl() {
		dao = new MemberDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public void updateMember(Member member) {
		dao.update(member);
	}

	@Override
	public void delete(String memNo) {
		if (dao.delete(memNo) == 1) {
			return;
		}
	}

	@Override
	public void deleteMemberPhoto(String memNo) {
			Member member = findMemberByNo(memNo);
			if (member != null) {
				member.setMemPhoto(null); // 假設 setMemPhoto 方法用來設置照片為 null
				dao.update(member);
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
		byte[] photoBytes = null;
		try {
			photoBytes = findMemberByNo(memNo).getMemPhoto();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photoBytes;
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();

		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

	@Override
	public List<Member> getMembersByCompositeQuery(Map<String, String[]> map) {
		return null;
	}

	@Override
	public void addMember(Member member) {

		dao.insert(member);
	}

	@Override
	public List<Member> getAllMembers(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public Integer getMemberPoints(String memNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateMemberPoints(String memNo, Integer momo) {
		findMemberByNo(memNo).setMomoPoint(momo);
	}

	public Member findMemberByEmail(String memEmail) {
		Member member = dao.findByMemberEmail(memEmail);
		return member;
	}

	@Override
	public void updateMemberClass(String memNo, Integer totalClass) {
		findMemberByNo(memNo).setTotalClass(totalClass);
	}

	@Override
	public void cancelPrivateClass(String memNo) {
		dao.updateTotalClass(memNo, 1);
	}

}