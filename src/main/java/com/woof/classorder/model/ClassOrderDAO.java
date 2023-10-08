package com.woof.classorder.model;

import java.util.List;

public interface ClassOrderDAO {

	void insert (ClassOrderVO ClassOrderVO);
	void update (ClassOrderVO ClassOrderVO);
	void delete (Integer coNo);
	
	ClassOrderVO findByCoNo(Integer coNo);
	List<ClassOrderVO> getAll();
}
