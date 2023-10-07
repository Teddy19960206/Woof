package com.woof.faq.model;

import java.util.Objects;

public class FaqVO {
	
	private Integer faqNo;
	private String faqClass;
	private String faqTitle;
	private String faqContent;
	public Integer getFaqNo() {
		return faqNo;
	}
	public void setFaqNo(Integer faqNo) {
		this.faqNo = faqNo;
	}
	public String getFaqClass() {
		return faqClass;
	}
	public void setFaqClass(String faqClass) {
		this.faqClass = faqClass;
	}
	public String getFaqTitle() {
		return faqTitle;
	}
	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}
	public String getFaqContent() {
		return faqContent;
	}
	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}
	@Override
	public int hashCode() {
		return Objects.hash(faqClass, faqContent, faqNo, faqTitle);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FaqVO other = (FaqVO) obj;
		return Objects.equals(faqClass, other.faqClass) && Objects.equals(faqContent, other.faqContent)
				&& Objects.equals(faqNo, other.faqNo) && Objects.equals(faqTitle, other.faqTitle);
	}
	@Override
	public String toString() {
		return "FaqVO [faqNo=" + faqNo + ", faqClass=" + faqClass + ", faqTitle=" + faqTitle + ", faqContent="
				+ faqContent + "]";
	}
	
	
	
	
}
