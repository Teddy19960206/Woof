package com.woof.shoporder.dao;

import java.util.List;

import com.woof.shoporder.entity.ShopOrder;

public interface ShopOrderDAO {
	
	int insert(ShopOrder shopOrder);

	int update(ShopOrder shopOrder);

//	int delete(Integer shopOrderNo);

	ShopOrder findByShopOrderNo(Integer shopOrderNo);

	List<ShopOrder> getAll();

	List<ShopOrder> getAll(int currentPage);

	long getTotal();
	
List<ShopOrder> findByMemNo(String memNo , int currentPage);
	
long getTotalMember(String memNo);

	//有登入會自動顯示自己的訂單
	List<ShopOrder> getOrdersByMember(String memNo);
}
