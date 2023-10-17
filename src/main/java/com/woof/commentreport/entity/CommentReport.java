package com.woof.commentreport.entity;

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
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.trainer.entity.Trainer;

@Entity
@Table(name = "comment_report")
public class CommentReport {
	
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CR_NO" , updatable = false , nullable = false)
	private Integer crNo;
	
	@ManyToOne
	@JoinColumn(name = "MEM_NO" , referencedColumnName = "MEM_NO")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "TRAINER_NO" , referencedColumnName = "TRAINER_NO")
	private Trainer trainer;
	
	@ManyToOne
	@JoinColumn(name="PTA_NO" , referencedColumnName = "PTA_NO")
	private PrivateTrainingAppointmentForm privateTrainingAppointmentForm;
	
	@Expose
	@Column(name = "CR_CONTEXT" , nullable = false)
	private String crContext;
	
	@Expose
	@Column(name="CR_STATUS" , nullable = false ,  columnDefinition = "TINYINT")
	private Integer crStatus;
	
	@Expose
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public PrivateTrainingAppointmentForm getPrivateTrainingAppointmentForm() {
		return privateTrainingAppointmentForm;
	}

	public void setPrivateTrainingAppointmentForm(PrivateTrainingAppointmentForm privateTrainingAppointmentForm) {
		this.privateTrainingAppointmentForm = privateTrainingAppointmentForm;
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
		return Objects.hash(crNo);
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
		return Objects.equals(crNo, other.crNo);
	}
	@Override
	public String toString() {
		return "CommentReportVO [crNo=" + crNo + ", memNo=" + (member != null ? member.getMemNo() : "N/A") + ", trainerNo=" + (trainer != null ? trainer.getTrainerNo() : "N/A") + ", ptaNo=" + (privateTrainingAppointmentForm != null ? privateTrainingAppointmentForm.getPtaNo() : "N/A") + ", crContext=" + crContext + ", crStatus=" + crStatus + ", crDate=" + crDate + "]";
	}
	
	
	
	
	
}
