package com.woof.trainer.model;

import java.util.Objects;

public class TrainerVO implements java.io.Serializable{
private Integer trainerNo;
private Integer adminNo;
private String  experience;
public TrainerVO() {
}
public Integer getTrainerNo() {
	return trainerNo;
}
public void setTrainerNo(Integer trainerNo) {
	this.trainerNo = trainerNo;
}
public Integer getAdminNo() {
	return adminNo;
}
public void setAdminNo(Integer adminNo) {
	this.adminNo = adminNo;
}
public String getExperience() {
	return experience;
}
public void setExperience(String experience) {
	this.experience = experience;
}
@Override
public int hashCode() {
	return Objects.hash(adminNo, experience, trainerNo);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	TrainerVO other = (TrainerVO) obj;
	return Objects.equals(adminNo, other.adminNo) && Objects.equals(experience, other.experience)
			&& Objects.equals(trainerNo, other.trainerNo);
}
@Override
public String toString() {
	return "TrainerVO [trainerNo=" + trainerNo + ", adminNo=" + adminNo + ", experience=" + experience
			+ ", getTrainerNo()=" + getTrainerNo() + ", getAdminNo()=" + getAdminNo() + ", getExperience()="
			+ getExperience() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
			+ super.toString() + "]";
}
}
