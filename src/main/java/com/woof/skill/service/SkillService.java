package com.woof.skill.service;

import com.woof.skill.entity.Skill;
import com.woof.trainer.entity.Trainer;

import java.util.List;
import java.util.Set;

public interface SkillService {


    int addSkill(String skillName);
    int updateSkill(Integer skillNo , String skillName);

    int deleteSkill(Integer skillNo);
    List<Skill> getAllSkill();
    Skill findBySkillNo(Integer skillNo);

    Set<Trainer> getTrainersBySkillNo(Integer skillNo);

    List<Skill> getTrainerNotExistsSkill(Integer trainerNo);
}
