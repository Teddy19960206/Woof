package com.woof.groupcourseorder.service;

import com.woof.groupcourseorder.entity.GroupCourseOrder;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.member.entity.Member;

import java.util.List;

public interface GroupCourseOrderService {

    int addOrder(Member member , GroupCourseSchedule groupCourseSchedule , Integer gcoPaymentMethod , Integer gcoSmmp , Integer actualAmount , Integer gcoStatus);
    void modify(Integer gcoNo , Member member , GroupCourseSchedule groupCourseSchedule , Integer gcoPaymentMethod , Integer gcoSmmp , Integer actualAmount , Integer gcoStatus);
    void modifyOfGcoNo(GroupCourseOrder groupCourseOrder ,GroupCourseSchedule groupCourseSchedule);
    void modify(Integer gcoNo , Integer status);
    void refund(Integer gcoNo);
    void finishOrder(Integer gcsNo);
    void modifyAllOrderByGcsNo(Integer gcsNo);
    GroupCourseOrder getOneOrder(Integer gcoNo);
    List<GroupCourseOrder> getAll();
    List<GroupCourseOrder> getByGroupSchedule();
    List<GroupCourseOrder> getOrderByDate(Integer year , Integer month);
    List<GroupCourseOrder> getAll(Integer groupClass , Integer status , String memNo , Integer currentPage);
    List<GroupCourseOrder> getOrderBySchedule(Integer scheduleNo);
    List<GroupCourseOrder> getAllBySchedule(Integer gcsNo);
    int getPageTotal(Integer groupClass , Integer status , String memNo);

}
