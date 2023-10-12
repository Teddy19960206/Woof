package com.woof.cartlist.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.woof.cartlist.model.CartListVO.CompositeCartList;

@Entity
@Table(name = "cart_list")
@IdClass(CompositeCartList.class)
public class CartListVO implements Serializable {

	@Id
	@Column(name = "PROD_NO" , nullable = false)
	private Integer prodNo;
	
	@Id
	@Column(name = "MEM_NO" , nullable = false)
	private Integer memNo;
	
	@Column(name ="CART_AMOUNT")
	private Integer cartAmount;

	public CompositeCartList getCompositeKey() {
		return new CompositeCartList(prodNo, memNo);
	}
	
	public void setCompositeKey(CompositeCartList key) {
		this.prodNo = key.getProdNo();
		this.memNo = key.getMemNo();
	}
	
	public CartListVO() {
	}

	public Integer getProdNo() {
		return prodNo;
	}

	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}

	public Integer getMemNo() {
		return memNo;
	}

	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}

	public Integer getCartAmount() {
		return cartAmount;
	}

	public void setCartAmount(Integer cartAmount) {
		this.cartAmount = cartAmount;
	}

	static class CompositeCartList implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer prodNo;
		private Integer memNo;

		// 一定要有無參數建構子
		public CompositeCartList() {
			super();
		}
		
		public CompositeCartList(Integer prodNo, Integer memNo) {
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

		public Integer getMemNo() {
			return memNo;
		}

		public void setMemNo(Integer memNo) {
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
			CompositeCartList other = (CompositeCartList) obj;
			return Objects.equals(memNo, other.memNo) && Objects.equals(prodNo, other.prodNo);
		}

		@Override
		public String toString() {
			return "CompositeCartList [prodNo=" + prodNo + ", memNo=" + memNo + "]";
		}
		
	}

}
