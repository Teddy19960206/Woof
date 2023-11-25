package com.woof.classorder.service;

import java.sql.Timestamp;
import java.util.List;

import com.woof.classorder.entity.ClassOrder;
import com.woof.member.entity.Member;

public interface ClassOrderService {
	int addClassOrder(Member member, Integer coBc, Integer coPaymentMethod, Integer coSmmp, Timestamp coTime, Integer coStatus, Integer actualAmount);
	
	ClassOrder findClassOrderByCoNo(Integer coNo);
	
	int updateClassOrder(Integer coNo, Member member, Integer coBc, Integer coPaymentMethod, Integer coSmmp, Timestamp coTime, Integer coStatus, Integer actualAmount );
	
	List<ClassOrder> getAllClassOrders();
	
	List<ClassOrder> getAllCOs(int currentPage);

	int getPageTotal();
	
	List<ClassOrder> getAllByMemNo(String memNo, int currentPage);
	
	int getPageTotal2(String memNo);
	
	List<ClassOrder> getOrderByMemNo(String memNo);
	
	public String formatOrderId(long orderId);
}
