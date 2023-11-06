package com.woof.groupcourseorder.dao;

import com.woof.groupcourseorder.entity.GroupCourseOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

public class GroupCourseOrderDAOImpl implements GroupCourseOrderDAO{

    private SessionFactory factory;

    public GroupCourseOrderDAOImpl(SessionFactory factory){
        this.factory = factory;
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }

    @Override
    public int insert(GroupCourseOrder groupCourseOrder) {
        return (int) getSession().save(groupCourseOrder);
    }

    @Override
    public void update(GroupCourseOrder groupCourseOrder) {
        getSession().update(groupCourseOrder);
    }

    @Override
    public GroupCourseOrder findByGcoNo(Integer gcoNo) {
        GroupCourseOrder groupCourseOrder = getSession().get(GroupCourseOrder.class, gcoNo);
        return groupCourseOrder;
    }

    @Override
    public List<GroupCourseOrder> getAll() {
        return getSession()
                .createQuery("FROM GroupCourseOrder ", GroupCourseOrder.class)
                .list();

    }

    @Override
    public List<GroupCourseOrder> getAll(Integer groupClass, Integer status, String memNo, Integer currentPage) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<GroupCourseOrder> criteriaQuery = builder.createQuery(GroupCourseOrder.class);
        Root<GroupCourseOrder> root = criteriaQuery.from(GroupCourseOrder.class);

        List<Predicate> predicates = new ArrayList<>();

        if (groupClass != null){
            predicates.add(builder.equal(root.get("gcoNo"), groupClass));
        }

        if (status != null){
            predicates.add(builder.equal(root.get("gcoStatus") , status));
        }

        if (memNo != null){
            predicates.add(builder.equal(root.get("member").get("memName"), memNo));
        }

        int first = (currentPage - 1) * PAGE_MAX_RESULT;
        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<GroupCourseOrder> query = getSession().createQuery(criteriaQuery);

        List<GroupCourseOrder> list = query
                .setFirstResult(first)
                .setMaxResults(PAGE_MAX_RESULT)
                .getResultList();

        return list;
    }

    @Override
    public long getTotal(Integer groupClass, Integer status, String memNo) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<GroupCourseOrder> root = criteriaQuery.from(GroupCourseOrder.class);

        criteriaQuery.select(builder.count(root));

        List<Predicate> predicates = new ArrayList<>();

        if (groupClass != null){
            predicates.add(builder.equal(root.get("gcoNo"), groupClass));
        }

        if (status != null){
            predicates.add(builder.equal(root.get("gcoStatus") , status));
        }

        if (memNo != null){
            predicates.add(builder.equal(root.get("member").get("memName"), memNo));
        }

        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<Long> query = getSession().createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<GroupCourseOrder> getByDate(Integer year , Integer month) {
        String hql = "FROM GroupCourseOrder WHERE YEAR(GroupCourseOrder.gcoDate) = :year and month(GroupCourseOrder .gcoDate) = :month";
        Query query = getSession().createQuery(hql);
        query.setParameter("year" , year);
        query.setParameter("month" , month);
        List<GroupCourseOrder> resultList = query.list();

        return resultList;
    }
}
