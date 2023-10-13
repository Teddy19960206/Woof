package com.woof.classtype.model;

import com.woof.util.HibernateUtil;
import org.hibernate.Session;

public class Test {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        ClassTypeVO classTypeVO = session.get(ClassTypeVO.class,1);
        System.out.println(classTypeVO.getCtName());

        session.getTransaction().commit();



    }
}
