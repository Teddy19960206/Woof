package com.woof.product.service;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.woof.product.dao.ProductDAO;
import com.woof.product.dao.ProductDAOImpl;
import com.woof.product.entity.Product;
import com.woof.util.HibernateUtil;

public class ProductServiceImpl implements ProductService {

	private ProductDAO dao;

	public ProductServiceImpl() {
		dao = new ProductDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public Product addProduct(Product product) {
		int id = dao.insert(product);
		product.setProdNo(id);
		return product;
	}

	@Override
	public Product updateProduct(Product product) {
		dao.update(product);
		return product;
	}

	@Override
	public void deleteProduct(Integer prodNo) {
		dao.delete(prodNo);
	}

	@Override
	public Product getProductByProdNo(Integer prodNo) {
		return dao.findByProdNo(prodNo);
	}

	@Override
	public List<Product> getAllProducts(int currentPage) {
		return dao.getAll(currentPage);
	}

	@Override
	public List<Product> getProductsByCompositeQuery(Map<String, String> map) {
		Map<String, String> query = new HashMap<>();
		for (Map.Entry<String, String> row : map.entrySet()) {
			String key = row.getKey();
			if ("action".equals(key)) {
				continue;
			}
			String value = row.getValue();
			if (value == null || value.isEmpty()) {
				continue;
			}
			query.put(key, value);
		}
		return dao.getByCompositeQuery(query);
	}

	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		int pageQty = (int) (total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}
}
