package com.woof.promotionactivity.service;

import java.util.List;

import com.woof.promotionactivity.entity.PromotionActivity;

public interface PromotionActivityService {

	PromotionActivity addPromotionActivity(PromotionActivity promotionActivity);

	PromotionActivity updatePromotionActivity(PromotionActivity promotionActivity);

	void deletePromotionActivity(Integer paNo);

	PromotionActivity findByPaNo(Integer paNo);

	List<PromotionActivity> getAllPromotionActivity();

	int getPageTotal();

}
