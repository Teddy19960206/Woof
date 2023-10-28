package com.woof.skillslist.service;

import com.woof.skillslist.dao.SkillsListDAO;
import com.woof.skillslist.dao.SkillsListDAOImpl;
import com.woof.skillslist.entity.SkillsList;
import com.woof.util.HibernateUtil;

import java.util.List;

public class SkillsListServiceImpl implements SkillsListService{

    private SkillsListDAO dao;

    public SkillsListServiceImpl(){
        dao = new SkillsListDAOImpl(HibernateUtil.getSessionFactory());
    }

    @Override
    public int TrainerAddSkill(Integer trainerNo, Integer skillNo) {
        SkillsList skillsList = new SkillsList();
        skillsList.setTrainerNo(trainerNo);
        skillsList.setSkillNo(skillNo);

        return dao.insert(skillsList);
    }

    @Override
    public int updateTrainerSkill(Integer trainerNo, Integer skillNo) {
        SkillsList skillsList = new SkillsList();
        skillsList.setTrainerNo(trainerNo);
        skillsList.setSkillNo(skillNo);

        return dao.update(skillsList);
    }

    @Override
    public int deleteTrainerSkill(Integer trainerNo, Integer skillNo) {
        SkillsList skillsList = new SkillsList();
        skillsList.setTrainerNo(trainerNo);
        skillsList.setSkillNo(skillNo);

        return dao.delete(skillsList);
    }

    @Override
    public List<SkillsList> getAll() {
        return dao.getAll();
    }
}
