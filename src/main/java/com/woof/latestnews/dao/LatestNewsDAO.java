package com.woof.latestnews.dao;

import java.util.List;


import com.woof.latestnews.entity.LatestNews;

public interface LatestNewsDAO {
	void insert(LatestNews latestNews);

	int update(LatestNews latestNews);

	int delete(Integer lnNo);

	List<LatestNews> getAll();

	LatestNews findLatestNewsByLnNo(Integer lnNo);
}
