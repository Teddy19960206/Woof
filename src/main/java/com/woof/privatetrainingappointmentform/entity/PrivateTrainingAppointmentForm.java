package com.woof.privatetrainingappointmentform.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "private_training_appointment")
public class PrivateTrainingAppointmentForm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PTA_NO" , updatable = false , nullable = false)
	private Integer ptaNo;
	
	@Column(name="MEM_NO" , nullable = false)
	private Integer memNo;
	
	@Column(name="TRAINER_NO" , nullable = false)
	private Integer trainerNo;
	
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
		return Objects.equals(memNo, other.memNo) && Objects.equals(ptaClass, other.ptaClass)
				&& Objects.equals(ptaNo, other.ptaNo) && Objects.equals(trainerNo, other.trainerNo);
	}
	@Override
	public String toString() {
		return "PrivateTrainingAppointmentForm [ptaNo=" + ptaNo + ", memNo=" + memNo + ", trainerNo=" + trainerNo
				+ ", ptaClass=" + ptaClass + "]";
	}

	
}
