package com.woof.latestnews.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.woof.latestnews.entity.LatestNews;
import java.util.List;

public class LatestNewsDAOImpl implements LatestNewsDAO {
	private SessionFactory factory;

	public LatestNewsDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(LatestNews latestNews) {
		return (Integer) getSession().save(latestNews);
	}

	@Override
	public int update(LatestNews latestNews) {
		try {
			getSession().update(latestNews);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer lnNo) {
		LatestNews latestNews = getSession().get(LatestNews.class, lnNo);
		if (latestNews != null) {
			getSession().delete(latestNews);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public LatestNews findByLatestNewsNo(Integer lnNo) {
		return getSession().get(LatestNews.class, lnNo);
	}

	@Override
	public List<LatestNews> getAll() {
		return getSession().createQuery("FROM LatestNews", LatestNews.class).list();
	}
}
