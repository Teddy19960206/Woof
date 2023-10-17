package com.woof.nontrainingschedule.entity;

import java.sql.Date;
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
import com.woof.trainer.entity.Trainer;

@Entity
@Table(name = "non_training_schedule")
public class NonTrainingSchedule {
	
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NTS_NO" , updatable = false , nullable = false)
	private Integer ntsNo;
	
	@ManyToOne
	@JoinColumn(name = "TRAINER_NO" , referencedColumnName = "TRAINER_NO")
	private Trainer trainer;
	
	@Expose
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
	
	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public Date getNtsDate() {
		return ntsDate;
	}
	public void setNtsDate(Date ntsDate) {
		this.ntsDate = ntsDate;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ntsNo);
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
		return Objects.equals(ntsNo, other.ntsNo);
	}
	@Override
	public String toString() {
		return "NonTrainingScheduleVO [ntsNo=" + ntsNo + ", trainerNo=" + (trainer != null ? trainer.getTrainerNo() : "N/A") + ", ntsDate=" + ntsDate + "]";
	}
	
	
	
}
