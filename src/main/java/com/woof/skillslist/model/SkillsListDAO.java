package com.woof.skillslist.model;

import com.woof.classtype.model.ClassTypeVO;

import java.util.List;

public interface SkillsListDAO {



    void insert(SkillsListVO skillsListVO);


    void delete(SkillsListVO skillsListVO);

    SkillsListVO find(SkillsListVO skillsListVO);

    List<SkillsListVO> findbyTrainerNo(Integer TrainerNo);

    List<SkillsListVO> getAll();
}
