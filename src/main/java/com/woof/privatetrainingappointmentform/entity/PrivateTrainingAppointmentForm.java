package com.woof.privatetrainingappointmentform.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.woof.appointmentdetail.entity.AppointmentDetail;

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
	@Column(name="MEM_NO" , nullable = false)
	private Integer memNo;
	
	@Expose
	@Column(name="TRAINER_NO" , nullable = false)
	private Integer trainerNo;
	
	@Expose
	@Column(name="PTA_CLASS" , nullable = false)
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
	public Integer getPtaClass() {
		return ptaClass;
	}
	public void setPtaClass(Integer ptaClass) {
		this.ptaClass = ptaClass;
	}
	@Override
	public int hashCode() {
		return Objects.hash(memNo, ptaClass, ptaNo, trainerNo);
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
		return Objects.equals(memNo, other.memNo);
	}
	@Override
	public String toString() {
		return "PrivateTrainingAppointmentForm [ptaNo=" + ptaNo + ", memNo=" + memNo + ", trainerNo=" + trainerNo
				+ ", ptaClass=" + ptaClass + "]";
	}

	
}
