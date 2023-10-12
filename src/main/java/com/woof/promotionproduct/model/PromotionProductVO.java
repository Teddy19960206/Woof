package com.woof.promotionproduct.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.woof.promotionproduct.model.PromotionProductVO.CompositePromotionProduct;

@Entity
@Table(name = "promotion_product")
@IdClass(CompositePromotionProduct.class)
public class PromotionProductVO implements Serializable {
	
	@Id
	@Column(name = "PROD_NO" , nullable = false)
	private Integer prodNo;
	
	@Id
	@Column(name = "PA_NO" , nullable = false)
	private Integer paNo;
	
	public CompositePromotionProduct getCompositeKey() {
		return new CompositePromotionProduct(prodNo, paNo);
	}
	
	public void setCompositeKey(CompositePromotionProduct key) {
		this.prodNo = key.getProdNo();
		this.paNo = key.getPaNo();
	}

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
	
	static class CompositePromotionProduct implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private Integer prodNo;
		private Integer paNo;
		
		public CompositePromotionProduct() {
			super();
		}

		public CompositePromotionProduct(Integer prodNo, Integer paNo) {
			super();
			this.prodNo = prodNo;
			this.paNo = paNo;
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
			CompositePromotionProduct other = (CompositePromotionProduct) obj;
			return Objects.equals(paNo, other.paNo) && Objects.equals(prodNo, other.prodNo);
		}

		@Override
		public String toString() {
			return "CompositeCartList [prodNo=" + prodNo + ", paNo=" + paNo + "]";
		}

	}

}
