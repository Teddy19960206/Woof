package com.woof.shoporder.service;

import java.sql.Timestamp;
import java.util.List;

import com.woof.member.entity.Member;
import com.woof.shoporder.entity.ShopOrder;

public interface ShopOrderService {

	int addShopOrder(Member member, Timestamp prodOrderDate, Integer payMethod, Boolean shipMethod, Integer orderStatus, String recName, String recMobile, String recAddress, Boolean hasReturn, Integer moCoin, Integer orderTotalPrice, Integer actualPrice);

	int updateShopOrder(Integer shopOrderNo, Member member, Timestamp prodOrderDate, Integer payMethod, Boolean shipMethod, Integer orderStatus, String recName, String recMobile, String recAddress, Boolean hasReturn, Integer moCoin, Integer orderTotalPrice, Integer actualPrice);

//	int deleteShopOrder(Integer shopOrderNo);

	List<ShopOrder> getAllShopOrder();

	ShopOrder findByShopOrderNo(Integer shopOrderNo);

	List<ShopOrder> getAllShopOrder(int currentPage);

	int getPageTotal();
}
