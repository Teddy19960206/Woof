package com.woof.classorder.dao;

import java.util.List;
import com.woof.classorder.entity.ClassOrder;

public interface ClassOrderDAO {

	int insert(ClassOrder classOrder);
	
	ClassOrder findByCoNo(Integer coNo);
	
	List<ClassOrder> getAll();
	
	List<ClassOrder> getAll(int currentPage);
	
	long getTotal();
	
	List<ClassOrder> getByMemNo(String memNo,int currentPage);
	
	long getTotalByMemNo(String memNo);
	
	int update(ClassOrder classOrder);
	
	List<ClassOrder> getOrderByMemNo(String memNo);
	
}
