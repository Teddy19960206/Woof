package com.woof.promotionproduct.model;

import java.util.List;

public interface PromotionProductDAO {
	
	void insert (PromotionProductVO promotionProductVO);

	void delete (PromotionProductVO promotionProductVO);
		
	PromotionProductVO findByProdNo(Integer prodNo);
	PromotionProductVO findByPaNo(Integer paNo);
	
	List<PromotionProductVO> getAll();
}
