package com.woof.faq.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "faq")
public class FaqVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FAQ_NO" , updatable = false , nullable = false)
	private Integer faqNo;
	
	@Column(name ="FAQ_CLASS" , nullable = false)
	private String faqClass;
	
	@Column(name ="FAQ_TITLE" , nullable = false)
	private String faqTitle;
	
	@Column(name ="FAQ_CONTENT" , nullable = false)
	private String faqContent;
	
	
	public FaqVO() {
    }
	
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
