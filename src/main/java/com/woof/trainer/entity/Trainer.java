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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.woof.administrator.entity.Administrator;
import com.woof.commentreport.entity.CommentReport;
import com.woof.groupscheduledetail.entity.GroupScheduleDetail;
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

	@Expose
	@OneToMany(mappedBy = "trainer" , cascade = CascadeType.ALL)
	private Set<NonTrainingSchedule> nonTrainingSchedules;
	
	@Expose
	@OneToMany(mappedBy = "trainer" , cascade = CascadeType.ALL)
	private Set<PrivateTrainingAppointmentForm> privateTrainingAppointmentForms;
	
	@Expose
	@OneToMany(mappedBy = "trainer" , cascade = CascadeType.ALL)
	private Set<CommentReport> commentReports;
	
	@Expose
	@OneToOne
	@JoinColumn(name = "ADMIN_NO", referencedColumnName = "ADMIN_NO")
	private Administrator administrator;

	@Expose
	@Column(name = "EXPERIENCE")
	private String experience;

	@ManyToMany
	@JoinTable(name = "skills_list", joinColumns = {
			@JoinColumn(name = "TRAINER_NO", referencedColumnName = "TRAINER_NO") }, inverseJoinColumns = {
					@JoinColumn(name = "SKILL_NO", referencedColumnName = "SKILL_NO") })
	private Set<Skill> skills;


	@OneToMany(mappedBy = "trainer" , cascade = CascadeType.ALL)
	private Set<GroupScheduleDetail> groupScheduleDetailSet;

	public Trainer() {
	}
	
	public Set<NonTrainingSchedule> getNonTrainingSchedules() {
		return nonTrainingSchedules;
	}

	public void setNonTrainingSchedules(Set<NonTrainingSchedule> nonTrainingSchedules) {
		this.nonTrainingSchedules = nonTrainingSchedules;
	}

	public Set<PrivateTrainingAppointmentForm> getPrivateTrainingAppointmentForms() {
		return privateTrainingAppointmentForms;
	}

	public void setPrivateTrainingAppointmentForms(Set<PrivateTrainingAppointmentForm> privateTrainingAppointmentForms) {
		this.privateTrainingAppointmentForms = privateTrainingAppointmentForms;
	}

	public Set<CommentReport> getCommentReports() {
		return commentReports;
	}

	public void setCommentReports(Set<CommentReport> commentReports) {
		this.commentReports = commentReports;
	}

	public Integer getTrainerNo() {
		return trainerNo;
	}

	public void setTrainerNo(Integer trainerNo) {
		this.trainerNo = trainerNo;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
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

	public Set<GroupScheduleDetail> getGroupScheduleDetailSet() {
		return groupScheduleDetailSet;
	}

	public void setGroupScheduleDetailSet(Set<GroupScheduleDetail> groupScheduleDetailSet) {
		this.groupScheduleDetailSet = groupScheduleDetailSet;
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
		return "Trainer [trainerNo=" + trainerNo + ", nonTrainingSchedules=" + nonTrainingSchedules
				+ ", privateTrainingAppointmentForms=" + privateTrainingAppointmentForms + ", commentReports="
				+ commentReports + ", administrator=" + administrator + ", experience=" + experience + ", skills="
				+ skills + "]";
	}



}