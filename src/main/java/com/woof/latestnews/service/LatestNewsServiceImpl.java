package com.woof.latestnews.service;

import java.util.List;
import org.hibernate.Session;

import com.woof.latestnews.dao.LatestNewsDAO;
import com.woof.latestnews.dao.LatestNewsDAOImpl;
import com.woof.latestnews.entity.LatestNews;
import com.woof.util.HibernateUtil;

public class LatestNewsServiceImpl implements LatestNewsService {

    private LatestNewsDAO dao;

    public LatestNewsServiceImpl() {
        dao = new LatestNewsDAOImpl (HibernateUtil.getSessionFactory());
    }
    
    @Override
    public LatestNews addLatestNews(LatestNews latestNews) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            if (dao.insert(latestNews) == 1) {
                session.getTransaction().commit();
                return latestNews;
            } else {
                session.getTransaction().rollback();
                return null;
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e; // or handle the exception appropriately
        }
    }

    @Override
    public LatestNews updateLatestNews(LatestNews latestNews) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            if (dao.update(latestNews) == 1) {
                session.getTransaction().commit();
                return latestNews;
            } else {
                session.getTransaction().rollback();
                return null;
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void deleteLatestNews(Integer lnNo) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            if (dao.delete(lnNo) == 1) {
                session.getTransaction().commit();
            } else {
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public LatestNews findLatestNewsByNo(Integer lnNo) {
        return dao.findByLatestNewsNo(lnNo);
    }

    @Override
    public List<LatestNews> getAllLatestNews() {
        List<LatestNews> latestNewsList = dao.getAll();
        return latestNewsList;
    }
}
