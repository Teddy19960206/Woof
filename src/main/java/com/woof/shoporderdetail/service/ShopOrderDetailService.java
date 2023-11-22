package com.woof.shoporderdetail.service;

import java.math.BigDecimal;
import java.util.List;

import com.woof.shoporderdetail.entity.ShopOrderDetail;
import com.woof.shoporderdetail.entity.ShopOrderDetailDTO;

public interface ShopOrderDetailService {

	int addShopOrderDetail(Integer shopOrderNo, Integer prodNo, Integer orderAmount, Integer prodPrice, Integer hasReturned, BigDecimal discountRate);

//	int updateShopOrder(Integer shopOrderNo, Member member, Timestamp prodOrderDate, Integer payMethod, Boolean shipMethod, Integer orderStatus, String recName, String recMobile, String recAddress, Boolean hasReturn, Integer moCoin, Integer orderTotalPrice, Integer actualPrice);

//	int deleteShopOrder(Integer shopOrderNo);

	List<ShopOrderDetail> getAllShopOrderDetail();

//	List<ShopOrderDetail> findOneShopOrderNoDetail(Integer shopOrderNo);
	
	List<ShopOrderDetailDTO> findOneShopOrderNoDetailObj(Integer shopOrderNo);

//	List<ShopOrder> getAllShopOrder(int currentPage);
//
//	int getPageTotal();

	
//List<ShopOrder> findByMemNo(String memNo , int currentPage);
//	
//int getPageTotal2(String memNo);
}
