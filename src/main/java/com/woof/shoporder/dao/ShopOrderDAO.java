package com.woof.shoporder.dao;

import java.util.List;
import java.util.Map;
import com.woof.shoporder.entity.ShopOrder;

public interface ShopOrderDAO {
	
	int insert(ShopOrder shopOrder);

	int update(ShopOrder shopOrder);

//	int delete(Integer shopOrderNo);

	ShopOrder findByShopOrderNo(Integer shopOrderNo);

	List<ShopOrder> getAll();

	List<ShopOrder> getAll(int currentPage);

	long getTotal();
}
