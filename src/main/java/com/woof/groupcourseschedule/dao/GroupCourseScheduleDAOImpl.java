package com.woof.groupcourseschedule.dao;

import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
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

public class GroupCourseScheduleDAOImpl implements GroupCourseScheduleDAO{

    private SessionFactory factory;

    public GroupCourseScheduleDAOImpl(SessionFactory factory){
        this.factory = factory;
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }

    @Override
    public int insert(GroupCourseSchedule groupCourseSchedule) {
        return (Integer) getSession().save(groupCourseSchedule);
    }

    @Override
    public int update(GroupCourseSchedule groupCourseSchedule) {
        getSession().merge(groupCourseSchedule);
        return 1;
    }

    @Override
    public void updateCount(Integer gcsNo , Integer regCount) {
        String sql = "UPDATE group_course_schedule SET regCount = ? WHERE GCS_NO = ?";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter(1, regCount + 1);
        query.setParameter(2, gcsNo);
        int result = query.executeUpdate();
    }

    @Override
    public GroupCourseSchedule findByGcsNo(Integer gcsNo) {
        return getSession().get(GroupCourseSchedule.class , gcsNo);
    }

    @Override
    public List<GroupCourseSchedule> getAll() {
        return getSession().createQuery("FROM GroupCourseSchedule" , GroupCourseSchedule.class).list();
    }

    @Override
    public List<GroupCourseSchedule> getAll(Integer classType, Integer status, Integer currentPage) {

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<GroupCourseSchedule> criteriaQuery = builder.createQuery(GroupCourseSchedule.class);
        Root<GroupCourseSchedule> root = criteriaQuery.from(GroupCourseSchedule.class);

        List<Predicate> predicates = new ArrayList<>();

        if (classType != null){
            predicates.add(builder.equal(root.get("groupCourse").get("classType").get("ctNo"), classType));
        }

        if (status != null){
            predicates.add(builder.equal(root.get("gcsStatus"),status));
        }

        int first = (currentPage -1)  * PAGE_MAX_RESULT;
        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<GroupCourseSchedule> query = getSession().createQuery(criteriaQuery);

        List<GroupCourseSchedule> list = query
                .setFirstResult(first)
                .setMaxResults(PAGE_MAX_RESULT)
                .getResultList();


        return list;
    }

    @Override
    public List<GroupCourseSchedule> getListSchedule(Integer classType , Integer status) {

        String hql = "FROM GroupCourseSchedule gcs WHERE gcs.groupCourse.classType.ctNo = :ctNo and gcs.gcsStatus = :status";

        Query<GroupCourseSchedule> query = getSession().createQuery(hql , GroupCourseSchedule.class);
        query.setParameter("ctNo" , classType);
        query.setParameter("status" , status);


        List<GroupCourseSchedule> list = query.list();


        return list;
    }

    @Override
    public List<GroupCourseSchedule> getAllbyClassType(Integer ctNo) {

//      原生 sql 無法對物件，僅可明確指定
//        String sql = "SELECT gcs.GCS_NO , gcs.GC_NO , gcs.TRAINER_NO , gcs.GCS_START , gcs.GCS_END , gcs.MIN_LIMIT ," +
//                " gcs.MAX_LIMIT , gcs.REG_COUNT , gcs.GCS_PRICE, gcs.GCS_STATUS , gcs.GCS_DELAY_REASON , " +
//                "gcs.RELATED_GCS_NO , gcs.CREATED_AT , gcs.UPDATED_AT FROM GROUP_COURSE_SCHEDULE AS gcs " +
//                "INNER JOIN GROUP_COURSE AS gc ON gcs.GC_NO = gc.GC_NO WHERE gc.CT_NO = :ctNo";
//
//        NativeQuery query = getSession().createNativeQuery(sql)
//            .setParameter("ctNo", ctNo)
//                .addEntity(GroupCourseSchedule.class);
//
//        List<GroupCourseSchedule> results = query.list();

//        HQL可以對應物件
        String hql = "select gcs FROM GroupCourseSchedule gcs JOIN gcs.groupCourse gc WHERE gc.classType = :ctNo";
        Query<GroupCourseSchedule> query = getSession().createQuery(hql, GroupCourseSchedule.class);
        query.setParameter("ctNo", ctNo);
        List<GroupCourseSchedule> results = query.list();

        return results;
    }

    @Override
    public List<GroupCourseSchedule> getOffStatus() {

        String hql = "FROM GroupCourseSchedule gcs WHERE gcs.gcsStatus = 0";
        Query<GroupCourseSchedule> query = getSession().createQuery(hql , GroupCourseSchedule.class);
        return query.list();
    }

    @Override
    public long getTotal(Integer classType, Integer status) {

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<GroupCourseSchedule> root = criteriaQuery.from(GroupCourseSchedule.class);

        criteriaQuery.select(builder.count(root));

        List<Predicate> predicates = new ArrayList<>();

        if (classType != null){
            predicates.add(builder.equal(root.get("groupCourse").get("classType").get("ctNo"), classType));
        }

        if (status != null){
            predicates.add(builder.equal(root.get("gcsStatus"),status));
        }

        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<Long> query = getSession().createQuery(criteriaQuery);

        return query.getSingleResult();
    }
}
