package com.woof.privatetrainingappointmentform;

import java.util.Objects;

public class PrivateTrainingAppointmentFormVO {
	
	private Integer ptaNo;
	private Integer memNo;
	private Integer trainerNo;
	private Integer ptaClass;
	
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
		PrivateTrainingAppointmentFormVO other = (PrivateTrainingAppointmentFormVO) obj;
		return Objects.equals(memNo, other.memNo) && Objects.equals(ptaClass, other.ptaClass)
				&& Objects.equals(ptaNo, other.ptaNo) && Objects.equals(trainerNo, other.trainerNo);
	}
	@Override
	public String toString() {
		return "PrivateTrainingAppointmentForm [ptaNo=" + ptaNo + ", memNo=" + memNo + ", trainerNo=" + trainerNo
				+ ", ptaClass=" + ptaClass + "]";
	}

	
}
