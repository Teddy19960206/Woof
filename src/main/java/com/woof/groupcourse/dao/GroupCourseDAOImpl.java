package com.woof.groupcourse.dao;

import com.woof.groupcourse.entity.GroupCourse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

public class GroupCourseDAOImpl implements GroupCourseDAO{

    private SessionFactory factory;

    public GroupCourseDAOImpl(SessionFactory factory){
        this.factory = factory;
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }

    @Override
    public int insert(GroupCourse groupCourse) {
        return (Integer) getSession().save(groupCourse);
    }

    @Override
    public int update(GroupCourse groupCourse) {
        try{
            getSession().merge(groupCourse);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int deletePhoto(Integer gcNo) {
        GroupCourse groupCourse = findbyGcNo(gcNo);
        groupCourse.setCoursePhoto(null);
        getSession().update(groupCourse);
        return 1;
    }

    @Override
    public GroupCourse findbyGcNo(Integer gcNo) {
        return getSession().get(GroupCourse.class,gcNo);
    }

    @Override
    public List<GroupCourse> getGroupCourseByCtNo(Integer ctNo) {
        Query query  = getSession().createQuery("FROM GroupCourse where classType.ctNo = :ctNo");
        query.setParameter("ctNo",ctNo);

        return query.list();
    }

    @Override
    public List<GroupCourse> getAll() {
        return getSession().createQuery("FROM GroupCourse" , GroupCourse.class).list();
    }

    @Override
    public List<GroupCourse> getByStatus(Integer status) {
        Query query = getSession().createQuery("FROM GroupCourse WHERE courseStatus = :status");
        query.setParameter("status" , status);

        return query.list();
    }

    @Override
    public long getTotal(Integer classType , Integer status){

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<GroupCourse> root = criteriaQuery.from(GroupCourse.class);

        criteriaQuery.select(builder.count(root));

        List<Predicate> predicates = new ArrayList<>();

        if (classType != null){
            predicates.add(builder.equal(root.get("classType").get("ctNo"),classType));
        }

        if (status != null){
            predicates.add(builder.equal(root.get("courseStatus") , status));
        }

        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<Long> query = getSession().createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    public List<GroupCourse> getAll(Integer classType , Integer status , int currentPage){

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<GroupCourse> criteriaQuery = builder.createQuery(GroupCourse.class);
        Root<GroupCourse> root = criteriaQuery.from(GroupCourse.class);

        List<Predicate> predicates = new ArrayList<>();

        if (classType != null){
            predicates.add(builder.equal(root.get("classType").get("ctNo"), classType));
        }
        if (status != null){
            predicates.add(builder.equal(root.get("courseStatus"), status));
        }

        Order order = builder.desc(root.get("gcNo"));
        criteriaQuery.orderBy(order);

        criteriaQuery.where(builder.and(predicates.toArray(predicates.toArray(new Predicate[predicates.size()]))));
        TypedQuery<GroupCourse> query = getSession().createQuery(criteriaQuery);

        int first = (currentPage -1)  * PAGE_MAX_RESULT;
        List<GroupCourse> groupCourseList = query
                .setFirstResult(first)
                .setMaxResults(PAGE_MAX_RESULT)
                .getResultList();

        return groupCourseList;
    }
}
