package com.woof.latestnews.dao;

import java.util.List;
import com.woof.latestnews.entity.LatestNews;

public interface LatestNewsDAO {
	 int insert(LatestNews latestNews);

	 int update(LatestNews latestNews);

	 int delete(Integer lnNo);

	 LatestNews findByLatestNewsNo(Integer lnNo);

	 List<LatestNews> getAll();
}
//void insert(LatestNewsVO letestNewsVO);
//
//void update(LatestNewsVO letestNewsVO);
//
//void delete(LatestNewsVO letestNewsVO);
//
//LatestNewsVO findByLnNo(Integer lnNo);
//
//List<LatestNewsVO> getAll();