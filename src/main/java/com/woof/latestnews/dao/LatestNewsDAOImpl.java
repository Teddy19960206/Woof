package com.woof.latestnews.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.woof.latestnews.entity.LatestNews;
public class LatestNewsDAOImpl implements LatestNewsDAO {

		private SessionFactory factory;

		public LatestNewsDAOImpl(SessionFactory factory) {
			this.factory = factory;
		}

		public Session getSession() {
			return factory.getCurrentSession();
		}

		@Override
		public void insert(LatestNews latestNews) {
			getSession().save(latestNews);
		}

		@Override
		public int update(LatestNews latestNews) {
			try {
				getSession().merge(latestNews);
				return 1;
			} catch (Exception e) {
				return -1;
			}
		}

		@Override
		public int delete(Integer lnNo) {
			LatestNews latestNews =getSession().get(LatestNews.class, lnNo);
			if(latestNews !=null) {
				getSession().delete(latestNews);
				return 1;
			}else {
				return -1;
			}
		}

		@Override
		public LatestNews findLatestNewsByLnNo(Integer lnNo) {
			Query<LatestNews> query = getSession().createQuery("FROM LatestNews where lnNo=:lnNo",LatestNews.class);
			query.setParameter("lnNo", lnNo);
			return query.getSingleResult();
		}

		@Override
		public List<LatestNews> getAll() {
			return getSession().createQuery("FROM LatestNews", LatestNews.class).list();
		}
	}
		

