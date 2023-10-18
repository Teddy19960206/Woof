package com.woof.latestnews.service;

import java.util.List;

import com.woof.latestnews.entity.LatestNews;

public interface LatestNewsService {
	LatestNews addLatestNews(LatestNews latestnews);

	LatestNews updateLatestNews(LatestNews latestnews);

	void deleteLatestNews(Integer lnNo);

	LatestNews findLatestNewsByNo(Integer lnNo);

	List<LatestNews> getAllLatestNews();
}
