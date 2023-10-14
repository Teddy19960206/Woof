package com.woof.classorder.dao;

import java.util.List;

import com.woof.classorder.entity.ClassOrder;

public interface ClassOrderDAO {

	void insert (ClassOrder classOrderVO);
	void update (ClassOrder classOrderVO);
	void delete (ClassOrder classOrderVO);
	
	ClassOrder findByCoNo(Integer coNo);
	List<ClassOrder> getAll();
}
