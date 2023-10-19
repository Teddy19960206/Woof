package com.woof.product.service;

import java.util.List;
import java.util.Map;

import com.woof.product.entity.Product;

public interface ProductService {

	Product addProduct(Product product);

	Product updateProduct(Product product);

	void deleteProduct(Integer prodNo);

	Product getProductByProdNo(Integer prodNo);

	List<Product> getAllProducts(int currentPage);

	int getPageTotal();

	List<Product> getProductsByCompositeQuery(Map<String, String> map);
}