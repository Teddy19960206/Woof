package com.woof.latestnews.dao;

import java.util.List;


import com.woof.latestnews.entity.LatestNewsVO;

public interface LatestNewsDAO {
//	void insert(LatestNewsVO letestNewsVO);
//
//	void update(LatestNewsVO letestNewsVO);
//
//	void delete(LatestNewsVO letestNewsVO);
//
//	LatestNewsVO findByLnNo(Integer lnNo);
//
//	List<LatestNewsVO> getAll();
	int insert(LatestNewsVO latestNewsVO);

	 int update(LatestNewsVO latestNewsVO);

	 int delete(Integer lnNo);

	 LatestNewsVO getById(Integer lnNo);

	 List<LatestNewsVO> getAll();

	 long getTotal();
}
