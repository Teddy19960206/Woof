package com.woof.trainer.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
import javax.persistence.Table;
@Entity
@Table(name="trainer")
=======
<<<<<<< HEAD
import javax.persistence.Table;
@Entity
@Table(name="trainer")
=======
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.woof.skill.model.SkillVO;

@Entity
@Table(name = "trainer")
>>>>>>> branch 'master' of https://github.com/Teddy19960206/Woof.git
>>>>>>> refs/heads/master
public class TrainerVO implements Serializable {
	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TRAINER_NO",updatable=false ,nullable=false)
=======
<<<<<<< HEAD
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TRAINER_NO",updatable=false ,nullable=false)
=======
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRAINER_NO")
>>>>>>> branch 'master' of https://github.com/Teddy19960206/Woof.git
>>>>>>> refs/heads/master
	private Integer trainerNo;
<<<<<<< HEAD
	@Column(name="ADMIN_NO" ,nullable=false)
=======
<<<<<<< HEAD
	@Column(name="ADMIN_NO" ,nullable=false)
=======
	
	@Column(name = "ADMIN_NO")
>>>>>>> branch 'master' of https://github.com/Teddy19960206/Woof.git
>>>>>>> refs/heads/master
	private Integer adminNo;
<<<<<<< HEAD
	@Column(name="EXPERIENCE")
=======
<<<<<<< HEAD
	@Column(name="EXPERIENCE")
=======
	
	@Column(name = "EXPERIENCE")
>>>>>>> branch 'master' of https://github.com/Teddy19960206/Woof.git
>>>>>>> refs/heads/master
	private String experience;

	
	@ManyToMany
	@JoinTable(
			name = "skills_list",
			joinColumns = { @JoinColumn(name = "TRAINER_NO" , referencedColumnName = "TRAINER_NO") },
			inverseJoinColumns = { @JoinColumn(name = "SKILL_NO" , referencedColumnName = "SKILL_NO") }
			)
	private Set<SkillVO> skills;
	
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

	public Set<SkillVO> getSkills() {
		return skills;
	}

	public void setSkills(Set<SkillVO> skills) {
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
