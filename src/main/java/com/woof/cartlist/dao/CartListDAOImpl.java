package com.woof.cartlist.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.woof.cartlist.entity.CartList;

public class CartListDAOImpl implements CartListDAO {

	private SessionFactory factory;

	public CartListDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}
	
	
	@Override
	public int insert(CartList cartList) {
		return (Integer) getSession().save(cartList);
	}

	@Override
	public int update(CartList cartList) {
		try {
			getSession().update(cartList);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer prodNo) {
		CartList cartList = getSession().get(CartList.class, prodNo);
		if(cartList != null) {
			getSession().delete(cartList);
			return 1;
		}else {
			return -1;
		}
	}

	@Override
	public CartList findOne(CartList cartList) {
		return getSession().get(CartList.class, cartList);
	}

	@Override
	public CartList findByProdNo(Integer prodNo) {
		return getSession().get(CartList.class, prodNo);
	}

	@Override
	public CartList findByMemNo(Integer memNo) {
		return getSession().get(CartList.class, memNo);
	}

	@Override
	public List<CartList> getAll() {
		return getSession().createQuery("FROM CartList" , CartList.class).list();
	}

	
}
