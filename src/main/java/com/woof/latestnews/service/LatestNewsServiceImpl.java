package com.woof.latestnews.service;

import java.util.List;
import org.hibernate.Session;

import com.woof.latestnews.dao.LatestNewsDAO;
import com.woof.latestnews.dao.LatestNewsDAOImpl;
import com.woof.latestnews.entity.LatestNews;
import com.woof.util.HibernateUtil;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import org.hibernate.Session;

public class LatestNewsServiceImpl implements LatestNewsService {
	
		private LatestNewsDAO dao;

		public LatestNewsServiceImpl() {
			dao = new LatestNewsDAOImpl(HibernateUtil.getSessionFactory());
		}

		@Override
		public LatestNews addLatestNews(LatestNews latestNews) {
			dao.insert(latestNews);
			return latestNews;
		}

		@Override
		public LatestNews updateLatestNews(LatestNews latestNews) {
			int i = dao.update(latestNews);
			if (i == 1) {
				return latestNews;
			}
			return null;
		}

		@Override
		public void deleteLatestNews(Integer lnNo) {
			dao.delete(lnNo);
		}

		@Override
		public LatestNews findLatestNewsByLnNo(Integer lnNo) {
			LatestNews latestNews = dao.findLatestNewsByLnNo(lnNo);
			return latestNews;
		}

		@Override
		public List<LatestNews> getAllLatestNews() {
			List<LatestNews> latestNewsList = dao.getAll();
			for(LatestNews vo : latestNewsList) {
				if(vo.getLnPhoto() != null) {
					Encoder coder = Base64.getEncoder();
					String imgStr1 = coder.encodeToString(vo.getLnPhoto());
					vo.setImgStr1(imgStr1);
				}
			}
			return latestNewsList;
		}

	}

