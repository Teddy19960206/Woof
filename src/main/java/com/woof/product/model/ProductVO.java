package com.woof.product.model;

import java.io.Serializable;
import java.util.Objects;

public class ProductVO implements Serializable {
	private Integer prodNo;
	private Integer prodCatNo;
	private String prodContent;
	private Integer prodPrice;
	private String prodName;
	private Boolean prodStatus;

	public ProductVO() {
	}

	public Integer getProdNo() {
		return prodNo;
	}

	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}

	public Integer getProdCatNo() {
		return prodCatNo;
	}

	public void setProdCatNo(Integer prodCatNo) {
		this.prodCatNo = prodCatNo;
	}

	public String getProdContent() {
		return prodContent;
	}

	public void setProdContent(String prodContent) {
		this.prodContent = prodContent;
	}

	public Integer getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Boolean getProdStatus() {
		return prodStatus;
	}

	public void setProdStatus(Boolean prodStatus) {
		this.prodStatus = prodStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(prodCatNo, prodContent, prodName, prodNo, prodPrice, prodStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductVO other = (ProductVO) obj;
		return Objects.equals(prodCatNo, other.prodCatNo) && Objects.equals(prodContent, other.prodContent)
				&& Objects.equals(prodName, other.prodName) && Objects.equals(prodNo, other.prodNo)
				&& Objects.equals(prodPrice, other.prodPrice) && Objects.equals(prodStatus, other.prodStatus);
	}

	@Override
	public String toString() {
		return "ProductVO [prodNo=" + prodNo + ", prodCatNo=" + prodCatNo + ", prodContent=" + prodContent
				+ ", prodPrice=" + prodPrice + ", prodName=" + prodName + ", prodStatus=" + prodStatus + "]";
	}
}