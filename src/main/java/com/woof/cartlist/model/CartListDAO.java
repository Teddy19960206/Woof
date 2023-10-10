package com.woof.cartlist.model;

import java.util.List;

public interface CartListDAO {

	void insert (CartListVO cartListVO);
	void update (CartListVO cartListVO);
	void delete (CartListVO cartListVO);

	CartListVO findOne(CartListVO cartListVO);
	
	CartListVO findByProdNo(Integer prodNo);
	CartListVO findByMemNo(Integer memNo);
	
	List<CartListVO> getAll();
	
}
