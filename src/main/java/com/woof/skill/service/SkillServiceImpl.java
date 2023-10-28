package com.woof.skill.service;

import com.woof.skill.dao.SkillDAO;
import com.woof.skill.dao.SkillDAOImpl;
import com.woof.skill.entity.Skill;
import com.woof.trainer.entity.Trainer;
import com.woof.util.HibernateUtil;

import java.util.List;
import java.util.Set;

public class SkillServiceImpl implements SkillService{

    private SkillDAO dao;

    public SkillServiceImpl(){
        dao = new SkillDAOImpl(HibernateUtil.getSessionFactory());
    }


    @Override
    public int addSkill(String skillName) {
        Skill skill = new Skill();
        skill.setSkillName(skillName);

        return dao.insert(skill);
    }

    @Override
    public int updateSkill(Integer skillNo, String skillName) {
        Skill skill = new Skill();
        skill.setSkillNo(skillNo);
        skill.setSkillName(skillName);

        return dao.update(skill);
    }

    @Override
    public int deleteSkill(Integer skillNo) {
        return dao.delete(skillNo);
    }

    @Override
    public List<Skill> getAllSkill() {

        return dao.getAll();
    }

    @Override
    public Skill findBySkillNo(Integer skillNo) {
        return dao.findbySkillNo(skillNo);
    }

    @Override
    public Set<Trainer> getTrainersBySkillNo(Integer skillNo) {
        return dao.getTrainerBySkill(skillNo);
    }

    @Override
    public List<Skill> getTrainerNotExistsSkill(Integer trainerNo) {
        return dao.getNotExistsSkill(trainerNo);
    }
}
