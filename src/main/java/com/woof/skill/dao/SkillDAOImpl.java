package com.woof.skill.dao;

import com.woof.skill.entity.Skill;
import com.woof.trainer.entity.Trainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Set;

public class SkillDAOImpl implements SkillDAO{

    private SessionFactory factory;

    public SkillDAOImpl(SessionFactory factory){
        this.factory = factory;
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }

    @Override
    public int insert(Skill skill) {
        return (Integer) getSession().save(skill);
    }

    @Override
    public int update(Skill skill) {
        try {
            getSession().update(skill);
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int delete(Integer skillNo) {
        Skill skill = getSession().get(Skill.class, skillNo);
        if (skill != null){
            getSession().delete(skill);
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public Skill findbySkillNo(Integer skillNo) {
        return getSession().get(Skill.class , skillNo);
    }

    @Override
    public List<Skill> getAll() {
        return getSession().createQuery("FROM Skill" , Skill.class).list();
    }

    @Override
    public Set<Trainer> getTrainerBySkill(Integer skillNo) {

        return getSession().get(Skill.class , skillNo).getTrainers();
    }

}
