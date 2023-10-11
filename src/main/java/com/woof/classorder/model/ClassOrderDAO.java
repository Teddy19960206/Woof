package com.woof.classorder.model;

import java.util.List;

public interface ClassOrderDAO {

	void insert (ClassOrderVO classOrderVO);
	void update (ClassOrderVO classOrderVO);
	void delete (ClassOrderVO classOrderVO);
	
	ClassOrderVO findByCoNo(Integer coNo);
	List<ClassOrderVO> getAll();
}
