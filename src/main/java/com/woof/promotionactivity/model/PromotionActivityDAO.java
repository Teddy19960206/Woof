package com.woof.promotionactivity.model;

import java.util.List;

public interface PromotionActivityDAO {
	
	void insert (PromotionActivityVO PromotionActivityVO);
	void update (PromotionActivityVO PromotionActivityVO);
	void delete (PromotionActivityVO PromotionActivityVO);

	PromotionActivityVO findByPaNo(Integer paNo);
	List<PromotionActivityVO> getAll();
}
