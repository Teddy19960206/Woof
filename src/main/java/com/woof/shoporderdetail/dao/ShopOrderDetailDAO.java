package com.woof.shoporderdetail.dao;

import java.util.List;

import com.woof.shoporderdetail.entity.ShopOrderDetail;

public interface ShopOrderDetailDAO {
	int insert(ShopOrderDetail shopOrderDetail);

//	int update(ShopOrderDetail shopOrderDetail);

//	int delete(Integer shopOrderDetail);

//	List<ShopOrderDetail> findOneShopOrderNoDetailObj(Integer shopOrderNo);
	
	List<Object> findOneShopOrderNoDetailObj(Integer shopOrderNo);

	List<ShopOrderDetail> getAll();

//	List<ShopOrderDetail> getByCompositeQuery(Map<String, String> map);
//
//	List<ShopOrderDetail> getAll(int currentPage);
//
//	long getTotal();
	
	//做這個
//	List<ShopOrderDetail> findByShopOrderNo(Integer shopOrderNo , int currentPage);
//	
//	long getTotalOrderDetail(Integer shopOrderNo);
}