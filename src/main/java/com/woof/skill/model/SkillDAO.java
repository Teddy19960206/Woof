package com.woof.skill.model;

import java.util.List;

public interface SkillDAO {

    void insert(SkillVO skillVO);

    void update(SkillVO skillVO);

    void delete(SkillVO skillVO);

    SkillVO findbySkillNo(Integer skillNo);

    List<SkillVO> getAll();



}
