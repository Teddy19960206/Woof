package com.woof.classorder.dao;

import java.util.List;
import com.woof.classorder.entity.ClassOrder;

public interface ClassOrderDAO {

int insert(ClassOrder classOrder);
	
	int update(ClassOrder classOrder);
	
	ClassOrder findByAdNo(Integer adNo);
	
	List<ClassOrder> getAll();
}
