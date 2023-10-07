package com.woof.skill.model;

import java.io.Serializable;
import java.util.Objects;

public class SkillVO implements Serializable {

    private Integer skillNo;
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
