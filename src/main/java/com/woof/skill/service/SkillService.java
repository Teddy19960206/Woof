package com.woof.skill.service;

import com.woof.skill.entity.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> getAllSkill();
    Skill findBySkillNo(Integer skillNo);
}
