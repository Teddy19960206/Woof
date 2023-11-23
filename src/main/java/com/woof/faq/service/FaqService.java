package com.woof.faq.service;

import java.util.List;

import com.woof.faq.entity.Faq;
import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;

public interface FaqService {

	int addFaq(String faqClass, String faqTitle, String faqContent);

	int updateFaq(Integer faqNo, String faqClass, String faqTitle, String faqContent);

	int deleteFaq(Integer faqNo);

	List<Faq> getAllFaq();

	Faq findByFaqNo(Integer faqNo);

	List<Faq> getAllFaq(int currentPage);

	int getPageTotal();

	
}
