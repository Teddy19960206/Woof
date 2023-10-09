package com.woof.letestnews.model;

import java.util.List;

public interface LetestNewsDAO {
	void insert(LetestNewsVO letestNewsVO);

	void update(LetestNewsVO letestNewsVO);

	void delete(LetestNewsVO letestNewsVO);

	LetestNewsVO findByLnNo(Integer lnNo);

	List<LetestNewsVO> getAll();
}
