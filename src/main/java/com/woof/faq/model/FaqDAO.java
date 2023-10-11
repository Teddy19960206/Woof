package com.woof.faq.model;

import java.util.List;

public interface FaqDAO {
	
	void insert (FaqVO FaqVO);
	void update (FaqVO FaqVO);
	void delete (FaqVO FaqVO);
	
	FaqVO findByFaqNo(Integer faqNo);
	List<FaqVO> getAll();
	
}
