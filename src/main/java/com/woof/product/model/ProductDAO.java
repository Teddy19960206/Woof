package com.woof.product.model;

import java.util.List;

public interface ProductDAO {
    public void insert(ProductVO productVO);
    public void update(ProductVO productVO);
    public void delete(Integer prodNo);
    public ProductVO findByProdNo(Integer prodNo);
    public List<ProductVO> getAll();
}
