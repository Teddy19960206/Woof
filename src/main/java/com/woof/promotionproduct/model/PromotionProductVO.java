package com.woof.promotionproduct.model;

import java.util.Objects;

public class PromotionProductVO {
	
	private Integer prodNo;
	private Integer paNo;
	
	
	public PromotionProductVO() {
    }
	
	public Integer getProdNo() {
		return prodNo;
	}
	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}
	public Integer getPaNo() {
		return paNo;
	}
	public void setPaNo(Integer paNo) {
		this.paNo = paNo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(paNo, prodNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromotionProductVO other = (PromotionProductVO) obj;
		return Objects.equals(paNo, other.paNo) && Objects.equals(prodNo, other.prodNo);
	}
	@Override
	public String toString() {
		return "PromotionProductVO [prodNo=" + prodNo + ", paNo=" + paNo + "]";
	}
	
	
	

}
