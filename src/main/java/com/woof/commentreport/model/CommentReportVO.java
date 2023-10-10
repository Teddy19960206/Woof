package com.woof.commentreport.model;

import java.sql.Timestamp;
import java.util.Objects;

public class CommentReportVO {
	
	private Integer crNo;
	private Integer memNo;
	private Integer trainerNo;
	private Integer ptaNo;
	private String crContext;
	private Integer crStatus;
	private Timestamp crDate;
	
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
	public String getCrContext() {
		return crContext;
	}
	public void setCrContext(String crContext) {
		this.crContext = crContext;
	}
	public Integer getCrStatus() {
		return crStatus;
	}
	public void setCrStatus(Integer crStatus) {
		this.crStatus = crStatus;
	}
	public Timestamp getCrDate() {
		return crDate;
	}
	public void setCrDate(Timestamp crDate) {
		this.crDate = crDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(crContext, crDate, crStatus, crNo, memNo, ptaNo, trainerNo);
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
		return Objects.equals(crContext, other.crContext) && Objects.equals(crDate, other.crDate)
				&& Objects.equals(crStatus, other.crStatus) && Objects.equals(crNo, other.crNo)
				&& Objects.equals(memNo, other.memNo) && Objects.equals(ptaNo, other.ptaNo)
				&& Objects.equals(trainerNo, other.trainerNo);
	}
	@Override
	public String toString() {
		return "CommentReportVO [crNo=" + crNo + ", memNo=" + memNo + ", trainerNo=" + trainerNo + ", ptaNo=" + ptaNo
				+ ", crContext=" + crContext + ", crStatus=" + crStatus + ", crDate=" + crDate + "]";
	}
	
	
	
	
	
}
