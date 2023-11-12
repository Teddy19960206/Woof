package com.woof.administratorpermission.dao;

import com.woof.administratorpermission.entity.AdministratorPermission;
import com.woof.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorPermissionDAOImpl implements AdministratorPermissionDAO {


    private SessionFactory factory;
    public AdministratorPermissionDAOImpl(SessionFactory factory){
        this.factory = factory;
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }

    @Override
    public int insert(AdministratorPermission administratorPermission) {
        getSession().save(administratorPermission);
        return 0;
    }

    @Override
    public int update(AdministratorPermission administratorPermission) {
        try {
            getSession().update(administratorPermission);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(AdministratorPermission administratorPermission) {

        try {
            getSession().delete(administratorPermission);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<AdministratorPermission> getAll() {
        return  getSession().createQuery("FROM AdministratorPermission " , AdministratorPermission.class).list();
    }
}
