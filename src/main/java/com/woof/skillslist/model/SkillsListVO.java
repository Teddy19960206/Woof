package com.woof.skillslist.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.woof.skillslist.model.SkillsListVO.CompositeSkillsList;

@Entity
@Table(name = "skills_list")
@IdClass( SkillsListVO.CompositeSkillsList.class)
public class SkillsListVO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRAINER_NO")
    private Integer trainerNo;
	
	@Id
	@Column(name = "SKILL_NO")
    private Integer skillNo;
	
	
	public CompositeSkillsList getCompositeKey() {
		return new CompositeSkillsList(trainerNo , skillNo);
	}
	
	public void setCompositekey(CompositeSkillsList key) {
		this.trainerNo = key.getTrainerNo();
		this.skillNo = key.getSkillNo();
	}
	

    public SkillsListVO() {
    }

    public Integer getTrainerNo() {
        return trainerNo;
    }

    public void setTrainerNo(Integer trainerNo) {
        this.trainerNo = trainerNo;
    }

    public Integer getSkillNo() {
        return skillNo;
    }

    public void setSkillNo(Integer skillNo) {
        this.skillNo = skillNo;
    }

    static class CompositeSkillsList implements Serializable{
    	
    	private static final long serialVersionUID = 1L;
    	
    	private Integer trainerNo;
    	private Integer skillNo;
    	
    	public CompositeSkillsList(Integer trainerNo , Integer skillNo) {
    		super();
    		this.trainerNo = trainerNo;
    		this.skillNo = skillNo;
    	}

		public Integer getTrainerNo() {
			return trainerNo;
		}

		public void setTrainerNo(Integer trainerNo) {
			this.trainerNo = trainerNo;
		}

		public Integer getSkillNo() {
			return skillNo;
		}

		public void setSkillNo(Integer skillNo) {
			this.skillNo = skillNo;
		}

		@Override
		public int hashCode() {
			return Objects.hash(skillNo, trainerNo);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompositeSkillsList other = (CompositeSkillsList) obj;
			return Objects.equals(skillNo, other.skillNo) && Objects.equals(trainerNo, other.trainerNo);
		}

		@Override
		public String toString() {
			return "CompositeSkillsList [trainerNo=" + trainerNo + ", skillNo=" + skillNo + "]";
		}
    	
    	
    	
    }
}
