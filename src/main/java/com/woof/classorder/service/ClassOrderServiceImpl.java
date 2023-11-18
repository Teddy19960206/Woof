package com.woof.classorder.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import com.woof.classorder.dao.ClassOrderDAOImpl;
import com.woof.classorder.entity.ClassOrder;
import com.woof.member.entity.Member;
import com.woof.classorder.dao.ClassOrderDAO;
import com.woof.util.HibernateUtil;

public class ClassOrderServiceImpl implements ClassOrderService{

	private ClassOrderDAO dao;
	
	public ClassOrderServiceImpl() {
		dao = new ClassOrderDAOImpl(HibernateUtil.getSessionFactory());
	}

	

	@Override
	public int addClassOrder(Member member, Integer coBc, Integer coPaymentMethod, Integer coSmmp, Timestamp coTime,
			Integer coStatus, Integer actualAmount) {
		
		ClassOrder classOrder = new ClassOrder();
		classOrder.setMember(member);
		classOrder.setCoBc(coBc);
		classOrder.setCoPaymentMethod(coPaymentMethod);
		classOrder.setCoSmmp(coSmmp);
		classOrder.setCoTime(coTime);
		classOrder.setCoStatus(coStatus);
		classOrder.setActualAmount(actualAmount);
		return dao.insert(classOrder);
	}

	@Override
	public ClassOrder findClassOrderByCoNo(Integer coNo) {
		return dao.findByCoNo(coNo);
	}

	@Override
	public int updateClassOrder(Integer coNo, Member member, Integer coBc, Integer coPaymentMethod, Integer coSmmp, Timestamp coTime, Integer coStatus, Integer actualAmount) {
		
		ClassOrder classOrder = new ClassOrder();
		classOrder.setCoNo(coNo);
		classOrder.setMember(member);
		classOrder.setCoBc(coBc);
		classOrder.setCoPaymentMethod(coPaymentMethod);
		classOrder.setCoSmmp(coSmmp);
		classOrder.setCoTime(coTime);
		classOrder.setCoStatus(coStatus);
		classOrder.setActualAmount(actualAmount);
		return dao.insert(classOrder);
	}


	@Override
	public List<ClassOrder> getAllClassOrders() {
		List<ClassOrder> classOrderList = dao.getAll();
		return classOrderList;
	}



	@Override
	public List<ClassOrder> getAllCOs(int currentPage) {
		return dao.getAll(currentPage);
	}



	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}



	@Override
	public List<ClassOrder> getAllByMemNo(String memNo, int currentPage) {
		return dao.getByMemNo(memNo,currentPage);
	}



	@Override
	public int getPageTotal2(String memNo) {
		long total = dao.getTotalByMemNo(memNo);
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
	
	
}
