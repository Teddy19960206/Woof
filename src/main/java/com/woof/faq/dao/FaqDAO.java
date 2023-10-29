package com.woof.faq.dao;

import java.util.List;

import com.woof.faq.entity.Faq;

public interface FaqDAO {

	int insert(Faq faq);

	int update(Faq faq);

	int delete(Integer faqNo);

	Faq findByFaqNo(Integer faqNo);

	List<Faq> getAll();

	
	List<Faq> getAll(int currentPage);

	long getTotal();

}
