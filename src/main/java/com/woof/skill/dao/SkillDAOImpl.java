package com.woof.skill.dao;

import com.woof.groupcourse.dao.GroupCourseDAOImpl;
import com.woof.skill.entity.Skill;
import com.woof.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        return 0;
    }

    @Override
    public void update(Skill skill) {

    }

    @Override
    public void delete(Skill skill) {

    }

    @Override
    public Skill findbySkillNo(Integer skillNo) {
        return getSession().get(Skill.class ,skillNo );
    }

    @Override
    public List<Skill> getAll() {
        return getSession().createQuery("FROM Skill" , Skill.class).list();
    }

}
