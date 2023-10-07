package com.woof.promotionactivity.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;


public class PromotionActivityVO {
	
	private Integer paNo;
	private String paName;
	private BigDecimal paDiscount;
	private String paContent;
	private Timestamp paStart;
	private Timestamp paEnd;
	private Boolean paStatus;
	
	
	public Integer getPaNo() {
		return paNo;
	}
	public void setPaNo(Integer paNo) {
		this.paNo = paNo;
	}
	public String getPaName() {
		return paName;
	}
	public void setPaName(String paName) {
		this.paName = paName;
	}
	public BigDecimal getPaDiscount() {
		return paDiscount;
	}
	public void setPaDiscount(BigDecimal paDiscount) {
		this.paDiscount = paDiscount;
	}
	public String getPaContent() {
		return paContent;
	}
	public void setPaContent(String paContent) {
		this.paContent = paContent;
	}
	public Timestamp getPaStart() {
		return paStart;
	}
	public void setPaStart(Timestamp paStart) {
		this.paStart = paStart;
	}
	public Timestamp getPaEnd() {
		return paEnd;
	}
	public void setPaEnd(Timestamp paEnd) {
		this.paEnd = paEnd;
	}
	public Boolean getPaStatus() {
		return paStatus;
	}
	public void setPaStatus(Boolean paStatus) {
		this.paStatus = paStatus;
	}
	@Override
	public int hashCode() {
		return Objects.hash(paContent, paDiscount, paEnd, paName, paNo, paStart, paStatus);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromotionActivityVO other = (PromotionActivityVO) obj;
		return Objects.equals(paContent, other.paContent) && Objects.equals(paDiscount, other.paDiscount)
				&& Objects.equals(paEnd, other.paEnd) && Objects.equals(paName, other.paName)
				&& Objects.equals(paNo, other.paNo) && Objects.equals(paStart, other.paStart)
				&& Objects.equals(paStatus, other.paStatus);
	}
	@Override
	public String toString() {
		return "PromotionActivityVO [paNo=" + paNo + ", paName=" + paName + ", paDiscount=" + paDiscount
				+ ", paContent=" + paContent + ", paStart=" + paStart + ", paEnd=" + paEnd + ", paStatus=" + paStatus
				+ "]";
	}
	
	
	

}
