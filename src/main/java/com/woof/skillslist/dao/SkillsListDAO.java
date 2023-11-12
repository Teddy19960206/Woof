package com.woof.skillslist.dao;

import com.woof.skillslist.entity.SkillsList;

import java.util.List;

public interface SkillsListDAO {

    int insert(SkillsList skillsList);

    int update(SkillsList skillsList);

    int delete(SkillsList skillsList);

    List<SkillsList> getAll();
}
