package com.woof.productcategory.dao;

import java.util.List;

import com.woof.productcategory.entity.ProductCategory;

public interface ProductCategoryDAO {
    public void insert(ProductCategory productCategoryVO);
    public void update(ProductCategory productCategoryVO);
    public void delete(Integer prodCatNo);
    public ProductCategory findByProdCatNo(Integer prodCatNo);
    public List<ProductCategory> getAll();
}
