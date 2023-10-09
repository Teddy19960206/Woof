package com.woof.groupcourseorder.model;

import java.util.List;

public interface GroupCourseOrderDAO {
    void insert(GroupCourseOrderVO groupCourseOrderVO);

    void delete(GroupCourseOrderVO groupCourseOrderVO);

    void update(GroupCourseOrderVO groupCourseOrderVO);

    GroupCourseOrderVO findByGcoNo(Integer gcoNo);

    List<GroupCourseOrderVO> getAll();

}
