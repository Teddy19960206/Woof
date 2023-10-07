package com.woof.classorder.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ClassOrderVO {

	private Integer coNo;
	private Integer memNo;
	private Integer coBc;
	private Integer coPaymentMethod;
	private Integer coSsmp;
	private Timestamp coTime;
	private Integer coStatus;
	private Timestamp coCt;
	private Integer actualAmount;
	
	public Integer getCoNo() {
		return coNo;
	}
	public void setCoNo(Integer coNo) {
		this.coNo = coNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getCoBc() {
		return coBc;
	}
	public void setCoBc(Integer coBc) {
		this.coBc = coBc;
	}
	public Integer getCoPaymentMethod() {
		return coPaymentMethod;
	}
	public void setCoPaymentMethod(Integer coPaymentMethod) {
		this.coPaymentMethod = coPaymentMethod;
	}
	public Integer getCoSsmp() {
		return coSsmp;
	}
	public void setCoSsmp(Integer coSsmp) {
		this.coSsmp = coSsmp;
	}
	public Timestamp getCoTime() {
		return coTime;
	}
	public void setCoTime(Timestamp coTime) {
		this.coTime = coTime;
	}
	public Integer getCoStatus() {
		return coStatus;
	}
	public void setCoStatus(Integer coStatus) {
		this.coStatus = coStatus;
	}
	public Timestamp getCoCt() {
		return coCt;
	}
	public void setCoCt(Timestamp coCt) {
		this.coCt = coCt;
	}
	public Integer getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(Integer actualAmount) {
		this.actualAmount = actualAmount;
	}
	@Override
	public int hashCode() {
		return Objects.hash(actualAmount, coBc, coCt, coNo, coPaymentMethod, coSsmp, coStatus, coTime, memNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassOrderVO other = (ClassOrderVO) obj;
		return Objects.equals(actualAmount, other.actualAmount) && Objects.equals(coBc, other.coBc)
				&& Objects.equals(coCt, other.coCt) && Objects.equals(coNo, other.coNo)
				&& Objects.equals(coPaymentMethod, other.coPaymentMethod) && Objects.equals(coSsmp, other.coSsmp)
				&& Objects.equals(coStatus, other.coStatus) && Objects.equals(coTime, other.coTime)
				&& Objects.equals(memNo, other.memNo);
	}
	@Override
	public String toString() {
		return "ClassOrderVO [coNo=" + coNo + ", memNo=" + memNo + ", coBc=" + coBc + ", coPaymentMethod="
				+ coPaymentMethod + ", coSsmp=" + coSsmp + ", coTime=" + coTime + ", coStatus=" + coStatus + ", coCt="
				+ coCt + ", actualAmount=" + actualAmount + "]";
	}
	
	
}
