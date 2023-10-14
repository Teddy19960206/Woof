package com.woof.skill.dao;

import com.woof.skill.entity.Skill;

import java.util.List;

public interface SkillDAO {

    void insert(Skill skill);

    void update(Skill skill);

    void delete(Skill skill);

    Skill findbySkillNo(Integer skillNo);

    List<Skill> getAll();



}
