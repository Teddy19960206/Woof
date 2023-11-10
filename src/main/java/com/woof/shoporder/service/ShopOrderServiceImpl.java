package com.woof.shoporder.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.sql.Timestamp;
import java.util.List;

import com.woof.shoporder.entity.ShopOrder;
import com.woof.member.entity.Member;
import com.woof.shoporder.dao.ShopOrderDAO;
import com.woof.shoporder.dao.ShopOrderDAOImpl;
import com.woof.util.HibernateUtil;

public class ShopOrderServiceImpl implements ShopOrderService{

	private ShopOrderDAO dao;
	
	public ShopOrderServiceImpl() {
		dao = new ShopOrderDAOImpl(HibernateUtil.getSessionFactory());
	}

	
	@Override
	public int addShopOrder(Member member, Timestamp prodOrderDate, Integer payMethod, Boolean shipMethod, Integer orderStatus, String recName, String recMobile, String recAddress, Boolean hasReturn, Integer moCoin, Integer orderTotalPrice, Integer actualPrice) {
		ShopOrder shopOrder = new ShopOrder();
		shopOrder.setMember(member);
		shopOrder.setProdOrderDate(prodOrderDate);
		shopOrder.setPayMethod(payMethod);
		shopOrder.setShipMethod(shipMethod);
		shopOrder.setOrderStatus(orderStatus);
		shopOrder.setRecName(recName);
		shopOrder.setRecMobile(recMobile);
		shopOrder.setRecAddress(recAddress);
		shopOrder.setHasReturn(hasReturn);
		shopOrder.setMoCoin(moCoin);
		shopOrder.setOrderTotalPrice(orderTotalPrice);
		shopOrder.setActualPrice(actualPrice);

		return dao.insert(shopOrder);
	}

	@Override
	public int updateShopOrder(Integer shopOrderNo, Member member, Timestamp prodOrderDate, Integer payMethod, Boolean shipMethod, Integer orderStatus, String recName, String recMobile, String recAddress, Boolean hasReturn, Integer moCoin, Integer orderTotalPrice, Integer actualPrice) {
		ShopOrder shopOrder = new ShopOrder();
		shopOrder.setShopOrderNo(shopOrderNo);
		shopOrder.setMember(member);
		shopOrder.setProdOrderDate(prodOrderDate);
		shopOrder.setPayMethod(payMethod);
		shopOrder.setShipMethod(shipMethod);
		shopOrder.setOrderStatus(orderStatus);
		shopOrder.setRecName(recName);
		shopOrder.setRecMobile(recMobile);
		shopOrder.setRecAddress(recAddress);
		shopOrder.setHasReturn(hasReturn);
		shopOrder.setMoCoin(moCoin);
		shopOrder.setOrderTotalPrice(orderTotalPrice);
		shopOrder.setActualPrice(actualPrice);
		
		return dao.update(shopOrder);
	}

//	@Override
//	public int deleteShopOrder(Integer shopOrderNo) {
//		
//		  return dao.delete(shopOrderNo);
//	}

	@Override
	public List<ShopOrder> getAllShopOrder() {
		
		 return dao.getAll();
	}

	@Override
	public ShopOrder findByShopOrderNo(Integer shopOrderNo) {
		
		return dao.findByShopOrderNo(shopOrderNo);
	}
	
	@Override
	public List<ShopOrder> getAllShopOrder(int currentPage) {
		
		return dao.getAll(currentPage);
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
	
}
