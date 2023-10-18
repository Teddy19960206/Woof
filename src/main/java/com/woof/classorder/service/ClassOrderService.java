package com.woof.classorder.service;

import java.util.List;

import com.woof.classorder.entity.ClassOrder;

public interface ClassOrderService {
	ClassOrder addClassOrder(ClassOrder classOrder);
	
	ClassOrder updateClassOrder(ClassOrder classOrder);
	
	ClassOrder findClassOrderByCoNo(Integer coNo);
	
	List<ClassOrder> getAllClassOrders();
}
