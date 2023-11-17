package com.woof.shoporderdetail.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.*;

import com.google.gson.annotations.Expose;

//可能要重做

@Entity
@Table(name = "shop_order_detail")
public class ShopOrderDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SHOP_ORDER_DETAIL_NO", updatable = false)		//商城訂單明細編號
	private Integer shopOrderDetailNo;
	
	@Expose
    @Column(name="SHOP_ORDER_NO", nullable=false)   //商城訂單編號
	private Integer shopOrderNo;
	
	@Expose
    @Column(name="PROD_NO", nullable=false)   //商品編號
	private Integer prodNo;
	
	@Expose
    @Column(name="ORDER_AMOUNT", nullable=false)   //訂購數量
	private Integer orderAmount;
	
	@Expose
    @Column(name="PROD_PRICE", nullable=false)   //商品單價
	private Integer prodPrice;
	
	@Expose
    @Column(name="HAS_RETURNED", nullable=false, columnDefinition = "TINYINT")   //是否已退貨 0:未退貨, 1:已退貨
	private Integer hasReturned;
	
	@Expose
    @Column(name="DISCOUNT_RATE", nullable=false)   //商品折數
	private BigDecimal discountRate;

	public Integer getShopOrderDetailNo() {
		return shopOrderDetailNo;
	}

	public void setShopOrderDetailNo(Integer shopOrderDetailNo) {
		this.shopOrderDetailNo = shopOrderDetailNo;
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

	public Integer getHasReturned() {
		return hasReturned;
	}

	public void setHasReturned(Integer hasReturned) {
		this.hasReturned = hasReturned;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(discountRate, hasReturned, orderAmount, prodNo, prodPrice, shopOrderDetailNo, shopOrderNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopOrderDetail other = (ShopOrderDetail) obj;
		return Objects.equals(discountRate, other.discountRate) && Objects.equals(hasReturned, other.hasReturned)
				&& Objects.equals(orderAmount, other.orderAmount) && Objects.equals(prodNo, other.prodNo)
				&& Objects.equals(prodPrice, other.prodPrice)
				&& Objects.equals(shopOrderDetailNo, other.shopOrderDetailNo)
				&& Objects.equals(shopOrderNo, other.shopOrderNo);
	}

	@Override
	public String toString() {
		return "ShopOrderDetail [shopOrderDetailNo=" + shopOrderDetailNo + ", shopOrderNo=" + shopOrderNo + ", prodNo="
				+ prodNo + ", orderAmount=" + orderAmount + ", prodPrice=" + prodPrice + ", hasReturned=" + hasReturned
				+ ", discountRate=" + discountRate + "]";
	}
	
	
	
	
	
}

