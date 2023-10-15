package com.woof.trainer.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.woof.skill.entity.Skill;

@Entity
@Table(name = "trainer")
public class TrainerVO implements Serializable {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "TRAINER_NO")
 private Integer trainerNo;
 
 @Column(name = "ADMIN_NO")
 private Integer adminNo;
 
 @Column(name = "EXPERIENCE")
 private String experience;

	
	@ManyToMany
	@JoinTable(
			name = "skills_list",
			joinColumns = { @JoinColumn(name = "TRAINER_NO" , referencedColumnName = "TRAINER_NO") },
			inverseJoinColumns = { @JoinColumn(name = "SKILL_NO" , referencedColumnName = "SKILL_NO") }
			)
	private Set<Skill> skills;
	
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
  TrainerVO other = (TrainerVO) obj;
  return Objects.equals(trainerNo, other.trainerNo);
 }

 @Override
 public String toString() {
  return "TrainerVO [trainerNo=" + trainerNo + ", adminNo=" + adminNo + ", experience=" + experience + "]";
 }
 
 

}