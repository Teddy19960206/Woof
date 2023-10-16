package com.woof.productcategory.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.woof.productcategory.entity.ProductCategory;

public class ProductCategoryDAOImpl implements ProductCategoryDAO {
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(ProductCategory productCategoryVO) {
        Session session = sessionFactory.getCurrentSession();
        session.save(productCategoryVO);
    }

    @Override
    public void update(ProductCategory productCategoryVO) {
        Session session = sessionFactory.getCurrentSession();
        session.update(productCategoryVO);
    }

    @Override
    public void delete(Integer prodCatNo) {
        Session session = sessionFactory.getCurrentSession();
        ProductCategory productCategoryVO = session.get(ProductCategory.class, prodCatNo);
        if(productCategoryVO != null) {
            session.delete(productCategoryVO);
        }
    }

    @Override
    public ProductCategory findByProdCatNo(Integer prodCatNo) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ProductCategory.class, prodCatNo);
    }

    @Override
    public List<ProductCategory> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<ProductCategory> query = session.createQuery("FROM ProductCategoryVO", ProductCategory.class);
        return query.getResultList();
    }
}
