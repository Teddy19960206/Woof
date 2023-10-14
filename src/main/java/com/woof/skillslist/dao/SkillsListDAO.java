package com.woof.skillslist.dao;

import com.woof.skillslist.entity.SkillsList;

import java.util.List;

public interface SkillsListDAO {



    void insert(SkillsList skillsList);


    void delete(SkillsList skillsList);

    SkillsList find(SkillsList skillsList);

    List<SkillsList> findbyTrainerNo(Integer TrainerNo);

    List<SkillsList> getAll();
}
