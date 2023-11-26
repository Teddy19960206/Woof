package com.woof.latestnews.service;

import java.util.List;

import com.woof.latestnews.entity.LatestNews;

public interface LatestNewsService {
	LatestNews addLatestNews(LatestNews latestnews);

	LatestNews updateLatestNews(LatestNews latestnews);

	void deleteLatestNews(Integer lnNo);
	

	LatestNews findLatestNewsByLnNo(Integer lnNo);

	List<LatestNews> getAllLatestNews();

	byte[] getPhotoById(String lnNoStr);
}
