package com.woof.shoporderdetail.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ShopOrderDetailVO implements Serializable {
	private Integer shopOrderNo;
	private Integer prodNo;
	private Integer orderAmount;
	private Integer prodPrice;
	private Boolean hasReturned;
	private BigDecimal discountRate;
	private Integer reAmount;

	public ShopOrderDetailVO() {
	}

	public Integer getShopOrderNo() {
		return shopOrderNo;
	}

	public void setShopOrderNo(Integer shopOrderNo) {
		this.shopOrderNo = shopOrderNo;
	}

	public Integer getProdNo() {
		return prodNo;
	}

	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Boolean getHasReturned() {
		return hasReturned;
	}

	public void setHasReturned(Boolean hasReturned) {
		this.hasReturned = hasReturned;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public Integer getReAmount() {
		return reAmount;
	}

	public void setReAmount(Integer reAmount) {
		this.reAmount = reAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(discountRate, hasReturned, orderAmount, prodNo, prodPrice, reAmount, shopOrderNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopOrderDetailVO other = (ShopOrderDetailVO) obj;
		return Objects.equals(discountRate, other.discountRate) && Objects.equals(hasReturned, other.hasReturned)
				&& Objects.equals(orderAmount, other.orderAmount) && Objects.equals(prodNo, other.prodNo)
				&& Objects.equals(prodPrice, other.prodPrice) && Objects.equals(reAmount, other.reAmount)
				&& Objects.equals(shopOrderNo, other.shopOrderNo);
	}

	@Override
	public String toString() {
		return "ShopOrderDetailVO [shopOrderNo=" + shopOrderNo + ", prodNo=" + prodNo + ", orderAmount=" + orderAmount
				+ ", prodPrice=" + prodPrice + ", hasReturned=" + hasReturned + ", discountRate=" + discountRate
				+ ", reAmount=" + reAmount + "]";
	}
}
