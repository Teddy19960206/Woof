package com.woof.groupcourseorder.service;

import com.woof.groupcourseorder.dao.GroupCourseOrderDAO;
import com.woof.groupcourseorder.dao.GroupCourseOrderDAOImpl;
import com.woof.groupcourseorder.entity.GroupCourseOrder;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.member.entity.Member;
import com.woof.util.HibernateUtil;

import java.util.List;

import static com.woof.util.Constants.PAGE_MAX_RESULT;

public class GroupCourseOrderServiceImpl implements GroupCourseOrderService{

    private GroupCourseOrderDAO dao;

    public GroupCourseOrderServiceImpl(){
        dao = new GroupCourseOrderDAOImpl(HibernateUtil.getSessionFactory());
    }

    @Override
    synchronized public int addOrder(Member member, GroupCourseSchedule groupCourseSchedule, Integer gcoPaymentMethod, Integer gcoSmmp, Integer actualAmount, Integer gcoStatus) {
        GroupCourseOrder groupCourseOrder = new GroupCourseOrder();
        groupCourseOrder.setMember(member);
        groupCourseOrder.setGroupCourseSchedule(groupCourseSchedule);
        groupCourseOrder.setGcoPaymentMethod(gcoPaymentMethod);
        groupCourseOrder.setGcoSmmp(gcoSmmp);
        groupCourseOrder.setActualAmount(actualAmount);
        groupCourseOrder.setGcoStatus(gcoStatus);

        return dao.insert(groupCourseOrder);
    }

    @Override
    public void modify(Integer gcoNo, Member member, GroupCourseSchedule groupCourseSchedule, Integer gcoPaymentMethod, Integer gcoSmmp, Integer actualAmount, Integer gcoStatus) {
        GroupCourseOrder groupCourseOrder = new GroupCourseOrder();
        groupCourseOrder.setGcoNo(gcoNo);
        groupCourseOrder.setMember(member);
        groupCourseOrder.setGroupCourseSchedule(groupCourseSchedule);
        groupCourseOrder.setGcoPaymentMethod(gcoPaymentMethod);
        groupCourseOrder.setGcoSmmp(gcoSmmp);
        groupCourseOrder.setActualAmount(actualAmount);
        groupCourseOrder.setGcoStatus(gcoStatus);

        dao.update(groupCourseOrder);
    }

    @Override
    public GroupCourseOrder getOneOrder(Integer gcoNo) {
        return dao.findByGcoNo(gcoNo);
    }

    @Override
    public List<GroupCourseOrder> getAll() {
        return dao.getAll();
    }

    @Override
    public List<GroupCourseOrder> getByGroupSchedule() {
        return null;
    }

    public List<GroupCourseOrder> getOrderByDate(Integer year , Integer month){
        return dao.getByDate(year , month);
    }

    @Override
    public List<GroupCourseOrder> getAll(Integer groupClass, Integer status, String memNo, Integer currentPage) {
        return dao.getAll(groupClass , status ,memNo ,currentPage);
    }

    @Override
    public int getPageTotal(Integer groupClass, Integer status, String memNo) {
        long total = dao.getTotal(groupClass , status , memNo );

        int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));

        return pageQty;
    }
}
