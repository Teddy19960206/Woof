package com.woof.skillslist.model;

import java.io.Serializable;
import java.util.Objects;

public class SkillsList implements Serializable {

    private Integer trainerNo;
    private Integer skillNo;

    public SkillsList() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillsList that = (SkillsList) o;
        return Objects.equals(trainerNo, that.trainerNo) && Objects.equals(skillNo, that.skillNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainerNo, skillNo);
    }

    @Override
    public String toString() {
        return "SkillsList{" +
                "trainerNo=" + trainerNo +
                ", skillNo=" + skillNo +
                '}';
    }
}
