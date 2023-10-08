package com.woof.cartlist.model;

import java.util.Objects;

public class CartListVO {
	
	private Integer prodNo;
	private Integer memNo;
	private Integer cartAmount;
	
	
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
	@Override
	public int hashCode() {
		return Objects.hash(cartAmount, memNo, prodNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartListVO other = (CartListVO) obj;
		return Objects.equals(cartAmount, other.cartAmount) && Objects.equals(memNo, other.memNo)
				&& Objects.equals(prodNo, other.prodNo);
	}
	@Override
	public String toString() {
		return "CartListVO [prodNo=" + prodNo + ", memNo=" + memNo + ", cartAmount=" + cartAmount + "]";
	}
	
	

}
