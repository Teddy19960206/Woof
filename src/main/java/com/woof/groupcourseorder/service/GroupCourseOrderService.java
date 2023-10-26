package com.woof.groupcourseorder.service;

import com.woof.groupcourseorder.entity.GroupCourseOrder;
import com.woof.groupcourseschedule.entity.GroupCourseSchedule;
import com.woof.member.entity.Member;

import java.util.List;

public interface GroupCourseOrderService {

    void addOrder(Member member , GroupCourseSchedule groupCourseSchedule , Integer gcoPaymentMethod , Integer gcoSmmp , Integer actualAmount , Integer gcoStatus);

    void modify(Integer gcoNo , Member member , GroupCourseSchedule groupCourseSchedule , Integer gcoPaymentMethod , Integer gcoSmmp , Integer actualAmount , Integer gcoStatus);

    GroupCourseOrder getOneOrder(Integer gcoNo);
    List<GroupCourseOrder> getAll();

    List<GroupCourseOrder> getByGroupSchedule();



}
