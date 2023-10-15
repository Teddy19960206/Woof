package com.woof.productcategory.model;

import java.util.List;

public interface ProductCategoryDAO {
    public void insert(ProductCategoryVO productCategoryVO);
    public void update(ProductCategoryVO productCategoryVO);
    public void delete(Integer prodCatNo);
    public ProductCategoryVO findByProdCatNo(Integer prodCatNo);
    public List<ProductCategoryVO> getAll();
}
