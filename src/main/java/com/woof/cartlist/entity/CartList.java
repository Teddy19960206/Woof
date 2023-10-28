package com.woof.cartlist.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.woof.cartlist.entity.CartList.CompositeDetail;

@Entity
@Table(name = "cart_list")
@IdClass(CartList.CompositeDetail.class)
public class CartList implements Serializable {

	@Id
	@Column(name = "PROD_NO" , nullable = false)
	private Integer prodNo;
	
	@Id
	@Column(name = "MEM_NO" , nullable = false)
	private String memNo;
	
	@Column(name ="CART_AMOUNT")
	private Integer cartAmount;

	public CompositeDetail getCompositeKey() {
		return new CompositeDetail(prodNo, memNo);
	}
	
	public void setCompositeKey(CompositeDetail key) {
		this.prodNo = key.getProdNo();
		this.memNo = key.getMemNo();
	}
	
	public CartList() {
	}

	public Integer getProdNo() {
		return prodNo;
	}

	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}

	public String getMemNo() {
		return memNo;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}

	public Integer getCartAmount() {
		return cartAmount;
	}

	public void setCartAmount(Integer cartAmount) {
		this.cartAmount = cartAmount;
	}

	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer prodNo;
		private String memNo;

		// 一定要有無參數建構子
		public CompositeDetail() {
			super();
		}
		
		public CompositeDetail(Integer prodNo, String memNo) {
			super();
			this.prodNo = prodNo;
			this.memNo = memNo;
		}

		public Integer getProdNo() {
			return prodNo;
		}

		public void setProdNo(Integer prodNo) {
			this.prodNo = prodNo;
		}

		public String getMemNo() {
			return memNo;
		}

		public void setMemNo(String memNo) {
			this.memNo = memNo;
		}

		@Override
		public int hashCode() {
			return Objects.hash(memNo, prodNo);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompositeDetail other = (CompositeDetail) obj;
			return Objects.equals(memNo, other.memNo) && Objects.equals(prodNo, other.prodNo);
		}

		@Override
		public String toString() {
			return "CompositeDetail [prodNo=" + prodNo + ", memNo=" + memNo + "]";
		}
		
	}

}
