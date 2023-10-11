package com.woof.skill.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "skill")
public class SkillVO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SKILL_NO" , updatable = false)
    private Integer skillNo;
	
	@Column(name = "SKILL_NAME")
    private String skillName;

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
