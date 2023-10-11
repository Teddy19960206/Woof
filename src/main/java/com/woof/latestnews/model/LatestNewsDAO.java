package com.woof.latestnews.model;

import java.util.List;

public interface LatestNewsDAO {
	void insert(LatestNewsVO letestNewsVO);

	void update(LatestNewsVO letestNewsVO);

	void delete(LatestNewsVO letestNewsVO);

	LatestNewsVO findByLnNo(Integer lnNo);

	List<LatestNewsVO> getAll();
}
