package com.woof.faq.service;

import java.util.List;

import com.woof.faq.dao.FaqDAO;
import com.woof.faq.dao.FaqDAOImpl;
import com.woof.faq.entity.Faq;
import com.woof.util.HibernateUtil;

public class FaqServiceImpl implements FaqService{

	private FaqDAO dao;
	
	public FaqServiceImpl() {
		dao = new FaqDAOImpl(HibernateUtil.getSessionFactory());
	}

	
	@Override
	public int addFaq(String faqClass, String faqTitle, String faqContent) {
		Faq faq = new Faq();
		faq.setFaqClass(faqClass);
		faq.setFaqTitle(faqTitle);
		faq.setFaqContent(faqContent);
		
		return dao.insert(faq);
	}

	@Override
	public int updateFaq(Integer faqNo, String faqClass, String faqTitle, String faqContent) {
		Faq faq = new Faq();
		faq.setFaqNo(faqNo);
		faq.setFaqClass(faqClass);
		faq.setFaqTitle(faqTitle);
		faq.setFaqContent(faqContent);
		
		return dao.update(faq);
	}

	@Override
	public int deleteFaq(Integer faqNo) {
		
		  return dao.delete(faqNo);
	}

	@Override
	public List<Faq> getAllFaq() {
		
		 return dao.getAll();
	}

	@Override
	public Faq findByFaqNo(Integer faqNo) {
		
		return dao.findByFaqNo(faqNo);
	}
	
	
	
}
