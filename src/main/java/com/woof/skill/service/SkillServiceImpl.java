package com.woof.skill.service;

import com.woof.skill.dao.SkillDAO;
import com.woof.skill.dao.SkillDAOImpl;
import com.woof.skill.entity.Skill;
import com.woof.util.HibernateUtil;

import java.util.List;

public class SkillServiceImpl implements SkillService{

    private SkillDAO dao;

    public SkillServiceImpl(){
        dao = new SkillDAOImpl(HibernateUtil.getSessionFactory());
    }


    @Override
    public List<Skill> getAllSkill() {

        return dao.getAll();
    }

    @Override
    public Skill findBySkillNo(Integer skillNo) {
        return dao.findbySkillNo(skillNo);
    }
}
