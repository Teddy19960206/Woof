package com.woof.privatetrainingappointmentform.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.woof.appointmentdetail.entity.AppointmentDetail;
import com.woof.commentreport.entity.CommentReport;
import com.woof.member.entity.Member;
import com.woof.trainer.entity.Trainer;

@Entity
@Table(name = "private_training_appointment_form")
public class PrivateTrainingAppointmentForm {
	
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PTA_NO" , updatable = false , nullable = false)
	private Integer ptaNo;
	
	@Expose
	@OneToMany(mappedBy = "privateTrainingAppointmentForm" , cascade = CascadeType.ALL)
	private Set<AppointmentDetail> appointmentDetails;
	
	@Expose
	@OneToMany(mappedBy = "privateTrainingAppointmentForm" , cascade = CascadeType.ALL)
	private Set<CommentReport> commentReports;
	
	@ManyToOne
	@JoinColumn(name = "MEM_NO" , referencedColumnName = "MEM_NO")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "TRAINER_NO" , referencedColumnName = "TRAINER_NO")
	private Trainer trainer;
	
	@Expose
	@Column(name="PTA_CLASS" , nullable = false , columnDefinition = "TINYINT")
	private Integer ptaClass;
	
	public PrivateTrainingAppointmentForm() {
		
	}
	
	public Integer getPtaNo() {
		return ptaNo;
	}
	public void setPtaNo(Integer ptaNo) {
		this.ptaNo = ptaNo;
	}
	
	public Set<AppointmentDetail> getAppointmentDetails() {
		return appointmentDetails;
	}

	public void setAppointmentDetails(Set<AppointmentDetail> appointmentDetails) {
		this.appointmentDetails = appointmentDetails;
	}

	
	public Set<CommentReport> getCommentReports() {
		return commentReports;
	}

	public void setCommentReports(Set<CommentReport> commentReports) {
		this.commentReports = commentReports;
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

	public Integer getPtaClass() {
		return ptaClass;
	}
	public void setPtaClass(Integer ptaClass) {
		this.ptaClass = ptaClass;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ptaNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrivateTrainingAppointmentForm other = (PrivateTrainingAppointmentForm) obj;
		return Objects.equals(ptaNo, other.ptaNo);
	}
	@Override
	public String toString() {
		return "PrivateTrainingAppointmentForm [ptaNo=" + ptaNo + ", memNo=" + (member != null ? member.getMemNo() : "N/A") + ", trainerNo=" + (trainer != null ? trainer.getTrainerNo() : "N/A") + ", ptaClass=" + ptaClass + "]";
	}

	
}
