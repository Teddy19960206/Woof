package com.woof.groupcourseorder.service;

import com.woof.groupcourseorder.dao.GroupCourseOrderDAO;
import com.woof.groupcourseorder.dao.GroupCourseOrderDAOImpl;
import com.woof.groupcourseorder.entity.GroupCourseOrder;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.member.entity.Member;
import com.woof.util.HibernateUtil;

import java.util.List;

public class GroupCourseOrderServiceImpl implements GroupCourseOrderService{

    private GroupCourseOrderDAO dao;

    public GroupCourseOrderServiceImpl(){
        dao = new GroupCourseOrderDAOImpl(HibernateUtil.getSessionFactory());
    }

    @Override
    synchronized public void addOrder(Member member, GroupCourseSchedule groupCourseSchedule, Integer gcoPaymentMethod, Integer gcoSmmp, Integer actualAmount, Integer gcoStatus) {
        GroupCourseOrder groupCourseOrder = new GroupCourseOrder();
        groupCourseOrder.setMember(member);
        groupCourseOrder.setGroupCourseSchedule(groupCourseSchedule);
        groupCourseOrder.setGcoPaymentMethod(gcoPaymentMethod);
        groupCourseOrder.setGcoSmmp(gcoSmmp);
        groupCourseOrder.setActualAmount(actualAmount);
        groupCourseOrder.setGcoStatus(gcoStatus);

        dao.insert(groupCourseOrder);
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
}
