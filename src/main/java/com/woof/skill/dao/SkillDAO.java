package com.woof.skill.dao;

import com.woof.skill.entity.Skill;
import com.woof.trainer.entity.Trainer;

import java.util.List;
import java.util.Set;

public interface SkillDAO {

    int insert(Skill skill);

    int update(Skill skill);

    int delete(Integer skillNo);

    Skill findbySkillNo(Integer skillNo);

    List<Skill> getAll();

    Set<Trainer> getTrainerBySkill(Integer skillNo);

}
