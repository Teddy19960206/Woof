package com.woof.classorder.dao;

import java.util.List;
import com.woof.classorder.entity.ClassOrder;

public interface ClassOrderDAO {

	int insert(ClassOrder classOrder);
	
	ClassOrder findByCoNo(Integer coNo);
	
	List<ClassOrder> getAll();
	
	
	int update(ClassOrder classOrder);
	
}
