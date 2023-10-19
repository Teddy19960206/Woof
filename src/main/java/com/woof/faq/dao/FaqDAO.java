package com.woof.faq.dao;

import java.util.List;

import com.woof.faq.entity.Faq;

public interface FaqDAO {
	
	int insert (Faq faq);
	int update (Faq faq);
	int delete (Faq faq);
	
	Faq findByFaqNo(Integer faqNo);
	List<Faq> getAll();
	
}
