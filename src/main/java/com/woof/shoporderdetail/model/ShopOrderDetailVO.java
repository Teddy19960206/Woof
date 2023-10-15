package com.woof.shoporderdetail.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "shop_order_detail")
public class ShopOrderDetailVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ShopOrderDetailPK id;

	@Column(name = "ORDER_AMOUNT", nullable=false) //訂單數量
	private Integer orderAmount;

	@Column(name = "PROD_PRICE", nullable=false) //商品單價
	private Integer prodPrice;

	@Column(name = "HAS_RETURNED", nullable=false) //是否已退貨 0:未退貨, 1:已退貨
	private Boolean hasReturned;

	@Column(name = "DISCOUNT_RATE") //商品折數
	private BigDecimal discountRate;

	@Column(name = "RE_AMOUNT") //已退貨數量 Default: 0
	private Integer reAmount;

	public ShopOrderDetailVO() {
	}

	public ShopOrderDetailPK getId() {
		return id;
	}

	public void setId(ShopOrderDetailPK id) {
		this.id = id;
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
		return Objects.hash(discountRate, hasReturned, id, orderAmount, prodPrice, reAmount);
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
				&& Objects.equals(id, other.id) && Objects.equals(orderAmount, other.orderAmount)
				&& Objects.equals(prodPrice, other.prodPrice) && Objects.equals(reAmount, other.reAmount);
	}

	@Override
	public String toString() {
		return "ShopOrderDetailVO [id=" + id + ", orderAmount=" + orderAmount + ", prodPrice=" + prodPrice
				+ ", hasReturned=" + hasReturned + ", discountRate=" + discountRate + ", reAmount=" + reAmount + "]";
	}

	@Embeddable
	public static class ShopOrderDetailPK implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		@Column(name = "SHOP_ORDER_NO", updatable = false) //商城訂單編號
		private Integer shopOrderNo;

		@Column(name = "PROD_NO", updatable = false) //商品編號
		private Integer prodNo;
		
		public ShopOrderDetailPK() {
			super();
		}
		
		public ShopOrderDetailPK(Integer shopOrderNo, Integer prodNo) {
			super();
			this.shopOrderNo = shopOrderNo;
			this.prodNo = prodNo;
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

		@Override
		public int hashCode() {
			return Objects.hash(shopOrderNo, prodNo);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ShopOrderDetailPK other = (ShopOrderDetailPK) obj;
			return Objects.equals(shopOrderNo, other.shopOrderNo) && Objects.equals(prodNo, other.prodNo);
		}
	}

}
