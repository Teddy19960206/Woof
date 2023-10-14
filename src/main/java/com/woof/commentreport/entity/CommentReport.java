package com.woof.commentreport.entity;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment_report")
public class CommentReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CR_NO" , updatable = false , nullable = false)
	private Integer crNo;
	
	@Column(name = "MEM_NO" , nullable = false)
	private Integer memNo;
	
	@Column(name = "TRAINER_NO" , nullable = false)
	private Integer trainerNo;
	
	@Column(name = "PTA_NO" , nullable = false)
	private Integer ptaNo;
	
	@Column(name = "CR_CONTEXT" , nullable = false)
	private String crContext;
	
	@Column(name="CR_STATUS" , nullable = false ,  columnDefinition = "TINYINT")
	private Integer crStatus;
	
	@Column(name="CR_DATE" , nullable = false)
	private Timestamp crDate;
	
	public CommentReport() {
		
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
		CommentReport other = (CommentReport) obj;
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
