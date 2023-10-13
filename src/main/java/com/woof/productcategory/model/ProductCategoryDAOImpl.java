package com.woof.productcategory.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class ProductCategoryDAOImpl implements ProductCategoryDAO {
    
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(ProductCategoryVO productCategoryVO) {
        Session session = sessionFactory.getCurrentSession();
        session.save(productCategoryVO);
    }

    @Override
    public void update(ProductCategoryVO productCategoryVO) {
        Session session = sessionFactory.getCurrentSession();
        session.update(productCategoryVO);
    }

    @Override
    public void delete(Integer prodCatNo) {
        Session session = sessionFactory.getCurrentSession();
        ProductCategoryVO productCategoryVO = session.get(ProductCategoryVO.class, prodCatNo);
        if(productCategoryVO != null) {
            session.delete(productCategoryVO);
        }
    }

    @Override
    public ProductCategoryVO findByProdCatNo(Integer prodCatNo) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ProductCategoryVO.class, prodCatNo);
    }

    @Override
    public List<ProductCategoryVO> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<ProductCategoryVO> query = session.createQuery("FROM ProductCategoryVO", ProductCategoryVO.class);
        return query.getResultList();
    }
}
