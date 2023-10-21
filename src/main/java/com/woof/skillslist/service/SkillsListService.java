package com.woof.skillslist.service;

import com.woof.skillslist.entity.SkillsList;

import java.util.List;

public interface SkillsListService {

    int TrainerAddSkill(Integer trainerNo , Integer skillNo);

    int updateTrainerSkill(Integer trainerNo , Integer skillNo);

    int deleteTrainerSkill(Integer trainerNo , Integer skillNo);

    List<SkillsList> getAll();
}
