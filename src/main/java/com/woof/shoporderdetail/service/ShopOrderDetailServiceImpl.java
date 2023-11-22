package com.woof.shoporderdetail.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.woof.shoporder.entity.ShopOrder;
import com.woof.shoporderdetail.dao.ShopOrderDetailDAO;
import com.woof.shoporderdetail.dao.ShopOrderDetailDAOImpl;
import com.woof.shoporderdetail.entity.ShopOrderDetail;
import com.woof.util.HibernateUtil;

public class ShopOrderDetailServiceImpl implements ShopOrderDetailService{

	private ShopOrderDetailDAO dao;
	
	public ShopOrderDetailServiceImpl() {
		dao = new ShopOrderDetailDAOImpl(HibernateUtil.getSessionFactory());
	}

	
	@Override
	public int addShopOrderDetail(Integer shopOrderNo, Integer prodNo, Integer orderAmount, Integer prodPrice, Integer hasReturned, BigDecimal discountRate) {
		ShopOrderDetail shopOrderDetail = new ShopOrderDetail();
		shopOrderDetail.setShopOrderNo(shopOrderNo);
		shopOrderDetail.setProdNo(prodNo);
		shopOrderDetail.setOrderAmount(orderAmount);
		shopOrderDetail.setProdPrice(prodPrice);
		shopOrderDetail.setHasReturned(hasReturned);
		shopOrderDetail.setDiscountRate(discountRate);

		return dao.insert(shopOrderDetail);
	}

//	@Override
//	public int updateShopOrder(Integer shopOrderNo, Member member, Timestamp prodOrderDate, Integer payMethod, Boolean shipMethod, Integer orderStatus, String recName, String recMobile, String recAddress, Boolean hasReturn, Integer moCoin, Integer orderTotalPrice, Integer actualPrice) {
//		ShopOrder shopOrder = new ShopOrder();
//		shopOrder.setShopOrderNo(shopOrderNo);
//		shopOrder.setMember(member);
//		shopOrder.setProdOrderDate(prodOrderDate);
//		shopOrder.setPayMethod(payMethod);
//		shopOrder.setShipMethod(shipMethod);
//		shopOrder.setOrderStatus(orderStatus);
//		shopOrder.setRecName(recName);
//		shopOrder.setRecMobile(recMobile);
//		shopOrder.setRecAddress(recAddress);
//		shopOrder.setHasReturn(hasReturn);
//		shopOrder.setMoCoin(moCoin);
//		shopOrder.setOrderTotalPrice(orderTotalPrice);
//		shopOrder.setActualPrice(actualPrice);
//		
//		return dao.update(shopOrder);
//	}

//	@Override
//	public int deleteShopOrder(Integer shopOrderNo) {
//		
//		  return dao.delete(shopOrderNo);
//	}

	@Override
	public List<ShopOrderDetail> getAllShopOrderDetail() {
		
		 return dao.getAll();
	}

	@Override
	public List<Object> findOneShopOrderNoDetailObj(Integer shopOrderNo) {
		
		return dao.findOneShopOrderNoDetailObj(shopOrderNo);
	}
	
//	@Override
//	public List<ShopOrderDetail> findOneShopOrderNoDetail(Integer shopOrderNo) {
//		
//		return dao.findOneShopOrderNoDetail(shopOrderNo);
//	}
	
//	@Override
//	public List<ShopOrder> getAllShopOrder(int currentPage) {
//		
//		return dao.getAll(currentPage);
//	}
//
//	@Override
//	public int getPageTotal() {
//		long total = dao.getTotal();
//		int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
//		return pageQty;
//	}
//
//
//	@Override
//	public List<ShopOrder> findByMemNo(String memNo, int currentPage) {
//		return dao.findByMemNo(memNo , currentPage);
//	}
//
//
//	@Override
//	public int getPageTotal2(String memNo) {
//		long total = dao.getTotalMember(memNo);
//		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
//		return pageQty;
//	}
	
	
}
