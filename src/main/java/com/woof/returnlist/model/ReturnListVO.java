package com.woof.returnlist.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name="return_list")
public class ReturnListVO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RE_EXCH_ID")
	private Integer reExchId;

    @Column(name="SHOP_ORDER_NO")
	private Integer shopOrderNo;

    @Column(name="PROD_NO")
	private Integer prodNo;

    @Column(name="RE_REASON")
	private String reReason;

    @Column(name="RE_STATUS")
	private Integer reStatus;

    @Column(name="RE_DATE")
	private Timestamp reDate;

    @Column(name="PROC_DATE")
	private Timestamp procDate;

    @Column(name="REPAY_STATUS")
	private Integer repayStatus;

    @Column(name="RED_AMOUNT")
	private Integer redAmount;

    @Column(name="RE_PAY_AMOUNT")
	private Integer rePayAmount;

	public ReturnListVO() {
	}

	public Integer getReExchId() {
		return reExchId;
	}

	public void setReExchId(Integer reExchId) {
		this.reExchId = reExchId;
	}

	public Integer getShopOrderNo() {
		return shopOrderNo;
	}

	public void setShopOrderNo(Integer shopOrderNo) {
		this.shopOrderNo = shopOrderNo;
	}

	public Integer getProdNo() {
		return prodNo;
	}

	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}

	public String getReReason() {
		return reReason;
	}

	public void setReReason(String reReason) {
		this.reReason = reReason;
	}

	public Integer getReStatus() {
		return reStatus;
	}

	public void setReStatus(Integer reStatus) {
		this.reStatus = reStatus;
	}

	public Timestamp getReDate() {
		return reDate;
	}

	public void setReDate(Timestamp reDate) {
		this.reDate = reDate;
	}

	public Timestamp getProcDate() {
		return procDate;
	}

	public void setProcDate(Timestamp procDate) {
		this.procDate = procDate;
	}

	public Integer getRepayStatus() {
		return repayStatus;
	}

	public void setRepayStatus(Integer repayStatus) {
		this.repayStatus = repayStatus;
	}

	public Integer getRedAmount() {
		return redAmount;
	}

	public void setRedAmount(Integer redAmount) {
		this.redAmount = redAmount;
	}

	public Integer getRePayAmount() {
		return rePayAmount;
	}

	public void setRePayAmount(Integer rePayAmount) {
		this.rePayAmount = rePayAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(procDate, prodNo, reDate, reExchId, rePayAmount, reReason, reStatus, redAmount, repayStatus,
				shopOrderNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReturnListVO other = (ReturnListVO) obj;
		return Objects.equals(procDate, other.procDate) && Objects.equals(prodNo, other.prodNo)
				&& Objects.equals(reDate, other.reDate) && Objects.equals(reExchId, other.reExchId)
				&& Objects.equals(rePayAmount, other.rePayAmount) && Objects.equals(reReason, other.reReason)
				&& Objects.equals(reStatus, other.reStatus) && Objects.equals(redAmount, other.redAmount)
				&& Objects.equals(repayStatus, other.repayStatus) && Objects.equals(shopOrderNo, other.shopOrderNo);
	}

	@Override
	public String toString() {
		return "ReturnListVO [reExchId=" + reExchId + ", shopOrderNo=" + shopOrderNo + ", prodNo=" + prodNo
				+ ", reReason=" + reReason + ", reStatus=" + reStatus + ", reDate=" + reDate + ", procDate=" + procDate
				+ ", repayStatus=" + repayStatus + ", redAmount=" + redAmount + ", rePayAmount=" + rePayAmount + "]";
	}
}
