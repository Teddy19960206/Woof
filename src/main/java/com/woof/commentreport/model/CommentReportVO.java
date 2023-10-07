package com.woof.commentreport.model;

import java.sql.Timestamp;
import java.util.Objects;

public class CommentReportVO {
	
	private Integer crNo;
	private Integer memNo;
	private Integer trainerNo;
	private Integer ptaNo;
	private String cpContext;
	private Integer cpStatus;
	private Timestamp cpDate;
	
	public CommentReportVO() {
		
	}
	
	public Integer getCrNo() {
		return crNo;
	}
	public void setCrNo(Integer crNo) {
		this.crNo = crNo;
	}
	public Integer getMemNo() {
		return memNo;
	}
	public void setMemNo(Integer memNo) {
		this.memNo = memNo;
	}
	public Integer getTrainerNo() {
		return trainerNo;
	}
	public void setTrainerNo(Integer trainerNo) {
		this.trainerNo = trainerNo;
	}
	public Integer getPtaNo() {
		return ptaNo;
	}
	public void setPtaNo(Integer ptaNo) {
		this.ptaNo = ptaNo;
	}
	public String getCpContext() {
		return cpContext;
	}
	public void setCpContext(String cpContext) {
		this.cpContext = cpContext;
	}
	public Integer getCpStatus() {
		return cpStatus;
	}
	public void setCpStatus(Integer cpStatus) {
		this.cpStatus = cpStatus;
	}
	public Timestamp getCpDate() {
		return cpDate;
	}
	public void setCpDate(Timestamp cpDate) {
		this.cpDate = cpDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cpContext, cpDate, cpStatus, crNo, memNo, ptaNo, trainerNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentReportVO other = (CommentReportVO) obj;
		return Objects.equals(cpContext, other.cpContext) && Objects.equals(cpDate, other.cpDate)
				&& Objects.equals(cpStatus, other.cpStatus) && Objects.equals(crNo, other.crNo)
				&& Objects.equals(memNo, other.memNo) && Objects.equals(ptaNo, other.ptaNo)
				&& Objects.equals(trainerNo, other.trainerNo);
	}
	@Override
	public String toString() {
		return "CommentReportVO [crNo=" + crNo + ", memNo=" + memNo + ", trainerNo=" + trainerNo + ", ptaNo=" + ptaNo
				+ ", cpContext=" + cpContext + ", cpStatus=" + cpStatus + ", cpDate=" + cpDate + "]";
	}
	
	
	
	
	
}
