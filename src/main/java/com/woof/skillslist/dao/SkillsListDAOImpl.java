package com.woof.skillslist.dao;

import com.woof.skillslist.entity.SkillsList;
import com.woof.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillsListDAOImpl implements SkillsListDAO {


    private SessionFactory factory;
    public SkillsListDAOImpl(SessionFactory factory){
        this.factory = factory;
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }

    @Override
    public int insert(SkillsList skillsList) {
        return (Integer) getSession().save(skillsList);
    }

    @Override
    public int update(SkillsList skillsList) {
        try {
            getSession().update(skillsList);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(SkillsList skillsList) {

        try {
            getSession().delete(skillsList);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<SkillsList> getAll() {
        return  getSession().createQuery("FROM SkillsList " , SkillsList.class).list();
    }
}
