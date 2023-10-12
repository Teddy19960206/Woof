package com.woof.classorder.model;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "class_order")
public class ClassOrderVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CO_NO" , updatable = false , nullable = false)
	private Integer coNo;
	
	@Column(name = "MEM_NO", nullable = false)
	private Integer memNo;
	
	@Column(name = "CO_BC", nullable = false)
	private Integer coBc;
	
	@Column(name = "CO_PAYMENT_METHOD", nullable = false)
	private Integer coPaymentMethod;
	
	@Column(name = "CO_SMMP", nullable = false)
	private Integer coSmmp;
	
	@Column(name = "CO_TIME", nullable = false)
	private Timestamp coTime;
	
	@Column(name = "CO_STATUS", nullable = false)
	private Integer coStatus;
	
	@Column(name = "MEM_NO")
	private Timestamp coCt;
	
	@Column(name = "ACTUAL_AMOUNT", nullable = false)
	private Integer actualAmount;
	
	public ClassOrderVO() {
		
	}
	
	
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


	public Integer getCoSmmp() {
		return coSmmp;
	}


	public void setCoSmmp(Integer coSmmp) {
		this.coSmmp = coSmmp;
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
		return Objects.hash(actualAmount, coBc, coCt, coNo, coPaymentMethod, coSmmp, coStatus, coTime, memNo);
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
				&& Objects.equals(coPaymentMethod, other.coPaymentMethod) && Objects.equals(coSmmp, other.coSmmp)
				&& Objects.equals(coStatus, other.coStatus) && Objects.equals(coTime, other.coTime)
				&& Objects.equals(memNo, other.memNo);
	}
	@Override
	public String toString() {
		return "ClassOrderVO [coNo=" + coNo + ", memNo=" + memNo + ", coBc=" + coBc + ", coPaymentMethod="
				+ coPaymentMethod + ", coSsmp=" + coSmmp + ", coTime=" + coTime + ", coStatus=" + coStatus + ", coCt="
				+ coCt + ", actualAmount=" + actualAmount + "]";
	}
	
	
}
