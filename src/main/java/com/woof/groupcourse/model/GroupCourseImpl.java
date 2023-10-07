package com.woof.groupcourse.model;

import com.woof.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class GroupCourseImpl implements GroupCourseDAO {

    private static final String INSERT_STMT = "INSERT INTO group_course VALUES (? , ? , ? , ? , ?)";

    private static final String UPDATE_STMT = "UPDATE group_course SET skill_no = ?, class_type = ?, course_photo = ? , course_content = ? , course_status = ? WHERE gc_no = ?";

    private static final String DELETE_STMT = "DELETE FROM group_course WHERE gc_no = ?";

    private static final String FIND_BY_CTNO = "SELECT * FROM group_course WHERE ct_no = ?";

    private static final String GET_ALL = "SELECT * FROM group_course";

    @Override
    public void insert(GroupCourseVO groupCourseVO) {

        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        byte[] a = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setInt(1, groupCourseVO.getSkillNo());
            ps.setInt(2, groupCourseVO.getClassType());
            ps.setBytes(3, groupCourseVO.getCoursePhoto());
            ps.setString(4, groupCourseVO.getCourseContent());
            ps.setInt(5, groupCourseVO.getCourseStatus()) ;

            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, null);
        }


        if (count == 1) {
            System.out.println("新增成功");
        } else {
            System.out.println("新增失敗");
        }

    }

    @Override
    public void update(GroupCourseVO groupCourseVO) {

    }

    @Override
    public void delete(GroupCourseVO groupCourseVO) {

    }

    @Override
    public GroupCourseVO findbyCtNo(Integer ctNo) {
        return null;
    }

    @Override
    public List<GroupCourseVO> getAll() {
        return null;
    }
}
