package com.woof.trainer.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.woof.commentreport.entity.CommentReport;
import com.woof.nontrainingschedule.entity.NonTrainingSchedule;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
import com.woof.skill.entity.Skill;

@Entity
@Table(name = "trainer")
public class Trainer implements Serializable {
	
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRAINER_NO")
	private Integer trainerNo;
	//=============================================================
//	@Expose
//	@OneToMany(mappedBy = "trainer" , cascade = CascadeType.ALL)
//	private Set<NonTrainingSchedule> nonTrainingSchedules;
//	
//	@Expose
//	@OneToMany(mappedBy = "trainer" , cascade = CascadeType.ALL)
//	private Set<PrivateTrainingAppointmentForm> privateTrainingAppointmentForms;
//	
//	@Expose
//	@OneToMany(mappedBy = "trainer" , cascade = CascadeType.ALL)
//	private Set<CommentReport> commentReports;
	//trainer做完後，通知唯宸修改這邊
	@Expose
//	@OneToOne
//	@JoinColumn(name = "ADMIN_NO", referencedColumnName = "ADMIN_NO")
	@Column(name = "ADMIN_NO")
	private Integer adminNo;
	//=============================================================
	@Expose
	@Column(name = "EXPERIENCE")
	private String experience;

	@ManyToMany
	@JoinTable(name = "skills_list", joinColumns = {
			@JoinColumn(name = "TRAINER_NO", referencedColumnName = "TRAINER_NO") }, inverseJoinColumns = {
					@JoinColumn(name = "SKILL_NO", referencedColumnName = "SKILL_NO") })
	private Set<Skill> skills;

	public Trainer() {
	}
	
//	public Set<NonTrainingSchedule> getNonTrainingSchedules() {
//		return nonTrainingSchedules;
//	}
//
//	public void setNonTrainingSchedules(Set<NonTrainingSchedule> nonTrainingSchedules) {
//		this.nonTrainingSchedules = nonTrainingSchedules;
//	}
//
//	public Set<PrivateTrainingAppointmentForm> getPrivateTrainingAppointmentForms() {
//		return privateTrainingAppointmentForms;
//	}
//
//	public void setPrivateTrainingAppointmentForms(Set<PrivateTrainingAppointmentForm> privateTrainingAppointmentForms) {
//		this.privateTrainingAppointmentForms = privateTrainingAppointmentForms;
//	}
//
//	public Set<CommentReport> getCommentReports() {
//		return commentReports;
//	}
//
//	public void setCommentReports(Set<CommentReport> commentReports) {
//		this.commentReports = commentReports;
//	}

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

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	@Override
	public int hashCode() {
		return Objects.hash(trainerNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trainer other = (Trainer) obj;
		return Objects.equals(trainerNo, other.trainerNo);
	}

	@Override
	public String toString() {
		return "TrainerVO [trainerNo=" + trainerNo + ", adminNo=" + adminNo + ", experience=" + experience + "]";
	}

}