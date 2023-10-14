package com.woof.promotionactivity.dao;

import java.util.List;
import java.util.Map;

import com.woof.promotionactivity.entity.PromotionActivity;

public interface PromotionActivityDAO {
	
	int insert (PromotionActivity PromotionActivity);
	int update (PromotionActivity PromotionActivity);
	int delete (Integer paNo);

	PromotionActivity findByPaNo(Integer paNo);
	
	List<PromotionActivity> getAll();
	
	List<PromotionActivity> getByCompositeQuery(Map<String, String> map);
	
	List<PromotionActivity> getAll(int currentPage);
	
	long getTotal();

}
