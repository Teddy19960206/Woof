package com.woof.cartlist.dao;

import java.util.List;

import com.woof.cartlist.entity.CartList;

public interface CartListDAO {

	int insert (CartList cartList);
	int update (CartList cartList);
	int delete (Integer prodNo);

	CartList findOne(CartList cartList);
	
	CartList findByProdNo(Integer prodNo);
	CartList findByMemNo(Integer memNo);
	
	List<CartList> getAll();
	
}
