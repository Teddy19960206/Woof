package com.woof.classorder.entity;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.woof.member.entity.Member;

@Entity
@Table(name = "class_order")
public class ClassOrder {
	
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CO_NO" , updatable = false , nullable = false)
	private Integer coNo;
	
	@ManyToOne
	@JoinColumn(name = "MEM_NO" , referencedColumnName = "MEM_NO")
	private Member member;
	
	@Expose
	@Column(name = "CO_BC", nullable = false)
	private Integer coBc;
	
	@Expose
	@Column(name = "CO_PAYMENT_METHOD", nullable = false ,  columnDefinition = "TINYINT")
	private Integer coPaymentMethod;
	
	@Expose
	@Column(name = "CO_SMMP", nullable = false)
	private Integer coSmmp;
	
	@Expose
	@Column(name = "CO_TIME", nullable = false)
	private Timestamp coTime;
	
	@Expose
	@Column(name = "CO_STATUS", nullable = false ,  columnDefinition = "TINYINT")
	private Integer coStatus;
	
	@Expose
	@Column(name = "ACTUAL_AMOUNT", nullable = false)
	private Integer actualAmount;
	
	public ClassOrder() {
		
	}
	
	
	public Integer getCoNo() {
		return coNo;
	}


	public void setCoNo(Integer coNo) {
		this.coNo = coNo;
	}

	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
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

	public Integer getActualAmount() {
		return actualAmount;
	}


	public void setActualAmount(Integer actualAmount) {
		this.actualAmount = actualAmount;
	}


	@Override
	public int hashCode() {
		return Objects.hash(coNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassOrder other = (ClassOrder) obj;
		return Objects.equals(coNo, other.coNo);
	}	
}
