package com.woof.nontrainingschedule.entity;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "non_training_schedule")
public class NonTrainingSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NTS_NO" , updatable = false , nullable = false)
	private Integer ntsNo;
	
	@Column(name="TRAINER_NO" , nullable = false)
	private Integer trainerNo;
	
	@Column(name="NTS_DATE")
	private Date ntsDate;
	
	public NonTrainingSchedule() {
		
	}
	
	public Integer getNtsNo() {
		return ntsNo;
	}
	public void setNtsNo(Integer ntsNo) {
		this.ntsNo = ntsNo;
	}
	public Integer getTrainerNo() {
		return trainerNo;
	}
	public void setTrainerNo(Integer trainerNo) {
		this.trainerNo = trainerNo;
	}
	public Date getNtsDate() {
		return ntsDate;
	}
	public void setNtsDate(Date ntsDate) {
		this.ntsDate = ntsDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ntsDate, ntsNo, trainerNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NonTrainingSchedule other = (NonTrainingSchedule) obj;
		return Objects.equals(ntsDate, other.ntsDate) && Objects.equals(ntsNo, other.ntsNo)
				&& Objects.equals(trainerNo, other.trainerNo);
	}
	@Override
	public String toString() {
		return "NonTrainingScheduleVO [ntsNo=" + ntsNo + ", trainerNo=" + trainerNo + ", ntsDate=" + ntsDate + "]";
	}
	
	
	
}
