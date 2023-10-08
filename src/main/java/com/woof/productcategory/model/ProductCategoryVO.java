package com.woof.productcategory.model;

import java.io.Serializable;
import java.util.Objects;

public class ProductCategoryVO implements Serializable {
	private Integer prodCatNo;
	private String prodCatName;
	private String prodCatContent;

	public ProductCategoryVO() {
	}

	public Integer getProdCatNo() {
		return prodCatNo;
	}

	public void setProdCatNo(Integer prodCatNo) {
		this.prodCatNo = prodCatNo;
	}

	public String getProdCatName() {
		return prodCatName;
	}

	public void setProdCatName(String prodCatName) {
		this.prodCatName = prodCatName;
	}

	public String getProdCatContent() {
		return prodCatContent;
	}

	public void setProdCatContent(String prodCatContent) {
		this.prodCatContent = prodCatContent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(prodCatContent, prodCatName, prodCatNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCategoryVO other = (ProductCategoryVO) obj;
		return Objects.equals(prodCatContent, other.prodCatContent) && Objects.equals(prodCatName, other.prodCatName)
				&& Objects.equals(prodCatNo, other.prodCatNo);
	}

	@Override
	public String toString() {
		return "ProductCategoryVO [prodCatNo=" + prodCatNo + ", prodCatName=" + prodCatName + ", prodCatContent="
				+ prodCatContent + "]";
	}
}
