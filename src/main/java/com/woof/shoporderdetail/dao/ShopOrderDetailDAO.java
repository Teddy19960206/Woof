package com.woof.shoporderdetail.dao;

import java.util.List;
import java.util.Map;
import com.woof.shoporderdetail.entity.ShopOrderDetail;

public interface ShopOrderDetailDAO {
	int insert(ShopOrderDetail shopOrderDetail);

	int update(ShopOrderDetail shopOrderDetail);

	int delete(ShopOrderDetail.ShopOrderDetailPK id);

	ShopOrderDetail findById(ShopOrderDetail.ShopOrderDetailPK id);

	List<ShopOrderDetail> getAll();

	List<ShopOrderDetail> getByCompositeQuery(Map<String, String> map);

	List<ShopOrderDetail> getAll(int currentPage);

	long getTotal();
}