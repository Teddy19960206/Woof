package com.woof.faq.service;

import java.util.List;

import com.woof.faq.entity.Faq;

public interface FaqService {

	int addFaq(String faqClass, String faqTitle, String faqContent);

	int updateFaq(String faqClass, String faqTitle, String faqContent);

	int deleteFaq(Integer faqNo);

	List<Faq> getAllFaq();

	Faq findByFaqNo(Integer faqNo);

}
