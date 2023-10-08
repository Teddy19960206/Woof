package com.woof.shoporder.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class ShopOrderVO implements Serializable {
	private Integer shopOrderNo;
	private Integer memNo;
	private Timestamp prodOrderDate;
	private Integer payMethod;
	private Boolean shipMethod;
	private Integer orderStatus;
	private String recName;
	private String recMobile;
	private String recAddress;
	private Boolean hasReturn;
	private Integer moCoin;
	private Integer prodTotal;
	private Integer actualAmount;

	public ShopOrderVO() {
	}

	public Integer getShopOrderNo() {
		return shopOrderNo;
	}

	public void setShopOrderNo(Integer shopOrderNo) {
		this.shopOrderNo = shopOrderNo;
	}

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public Timestamp getProdOrderDate() {
		return prodOrderDate;
	}

	public void setProdOrderDate(Timestamp prodOrderDate) {
		this.prodOrderDate = prodOrderDate;
	}

	public Integer getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	public Boolean getShipMethod() {
		return shipMethod;
	}

	public void setShipMethod(Boolean shipMethod) {
		this.shipMethod = shipMethod;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRecName() {
		return recName;
	}

	public void setRecName(String recName) {
		this.recName = recName;
	}

	public String getRecMobile() {
		return recMobile;
	}

	public void setRecMobile(String recMobile) {
		this.recMobile = recMobile;
	}

	public String getRecAddress() {
		return recAddress;
	}

	public void setRecAddress(String recAddress) {
		this.recAddress = recAddress;
	}

	public Boolean getHasReturn() {
		return hasReturn;
	}

	public void setHasReturn(Boolean hasReturn) {
		this.hasReturn = hasReturn;
	}

	public Integer getMoCoin() {
		return moCoin;
	}

	public void setMoCoin(Integer moCoin) {
		this.moCoin = moCoin;
	}

	public Integer getProdTotal() {
		return prodTotal;
	}

	public void setProdTotal(Integer prodTotal) {
		this.prodTotal = prodTotal;
	}

	public Integer getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Integer actualAmount) {
		this.actualAmount = actualAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actualAmount, hasReturn, memNo, moCoin, orderStatus, payMethod, prodOrderDate, prodTotal,
				recAddress, recMobile, recName, shipMethod, shopOrderNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopOrderVO other = (ShopOrderVO) obj;
		return Objects.equals(actualAmount, other.actualAmount) && Objects.equals(hasReturn, other.hasReturn)
				&& Objects.equals(memNo, other.memNo) && Objects.equals(moCoin, other.moCoin)
				&& Objects.equals(orderStatus, other.orderStatus) && Objects.equals(payMethod, other.payMethod)
				&& Objects.equals(prodOrderDate, other.prodOrderDate) && Objects.equals(prodTotal, other.prodTotal)
				&& Objects.equals(recAddress, other.recAddress) && Objects.equals(recMobile, other.recMobile)
				&& Objects.equals(recName, other.recName) && Objects.equals(shipMethod, other.shipMethod)
				&& Objects.equals(shopOrderNo, other.shopOrderNo);
	}

	@Override
	public String toString() {
		return "ShopOrderVO [shopOrderNo=" + shopOrderNo + ", memNo=" + memNo + ", prodOrderDate=" + prodOrderDate
				+ ", payMethod=" + payMethod + ", shipMethod=" + shipMethod + ", orderStatus=" + orderStatus
				+ ", recName=" + recName + ", recMobile=" + recMobile + ", recAddress=" + recAddress + ", hasReturn="
				+ hasReturn + ", moCoin=" + moCoin + ", prodTotal=" + prodTotal + ", actualAmount=" + actualAmount
				+ "]";
	}
}
