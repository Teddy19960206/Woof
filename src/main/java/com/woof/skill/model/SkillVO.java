package com.woof.skill.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.woof.trainer.entity.TrainerVO;




@Entity
@Table(name = "skill")
public class SkillVO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SKILL_NO" , updatable = false)
    private Integer skillNo;
	
	@Column(name = "SKILL_NAME" , nullable = false)
    private String skillName;
	
	
	@ManyToMany
	@JoinTable(
			name = "skills_list",
			joinColumns = { @JoinColumn(name = "SKILL_NO" , referencedColumnName = "SKILL_NO")},
			inverseJoinColumns = { @JoinColumn(name = "TRAINER_NO" , referencedColumnName = "TRAINER_NO")}
			
			)
	private Set<TrainerVO> trainers;

    public SkillVO() {
    }

    public Integer getSkillNo() {
        return skillNo;
    }

    public void setSkillNo(Integer skillNo) {
        this.skillNo = skillNo;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillVO skillVO = (SkillVO) o;
        return Objects.equals(skillNo, skillVO.skillNo) && Objects.equals(skillName, skillVO.skillName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillNo, skillName);
    }

    @Override
    public String toString() {
        return "SkillVO{" +
                "skillNo=" + skillNo +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}
