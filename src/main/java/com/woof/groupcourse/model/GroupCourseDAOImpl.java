package com.woof.groupcourse.model;

import com.woof.util.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupCourseDAOImpl implements GroupCourseDAO {

    private static final String INSERT_STMT = "INSERT INTO group_course (skill_no , class_type , course_photo , course_content) VALUES (? , ? , ? , ?)";

    private static final String UPDATE_STMT = "UPDATE group_course SET skill_no = ?, class_type = ?, course_photo = ? , course_content = ? , course_status = ? WHERE gc_no = ?";

    //private static final String DELETE_STMT = "DELETE FROM group_course WHERE gc_no = ?";

    private static final String FIND_BY_GCNO = "SELECT * FROM group_course WHERE gc_no = ?";

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
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(UPDATE_STMT);
            ps.setInt(1 , groupCourseVO.getSkillNo());
            ps.setInt(2 , groupCourseVO.getClassType());
            ps.setBytes(3 , groupCourseVO.getCoursePhoto());
            ps.setString(4 , groupCourseVO.getCourseContent());
            ps.setInt( 5 , groupCourseVO.getCourseStatus());
            ps.setInt(6, groupCourseVO.getGcNo());

            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps , null);
        }

        if (count == 1) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失敗");
        }

    }

    @Override
    public GroupCourseVO findbyGcNo(Integer gcNo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        GroupCourseVO groupCourseVO = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(FIND_BY_GCNO);
            ps.setInt(1, gcNo);
            rs = ps.executeQuery();

            if (rs.next()){
                groupCourseVO = new GroupCourseVO();
                groupCourseVO.setGcNo(gcNo);
                groupCourseVO.setSkillNo(rs.getInt("skill_no"));
                groupCourseVO.setClassType(rs.getInt("class_type"));
                groupCourseVO.setCoursePhoto(rs.getBytes("course_photo"));
                groupCourseVO.setCourseContent(rs.getString("course_content"));
                groupCourseVO.setCourseStatus(rs.getInt("course_status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps , rs);
        }

        return groupCourseVO;
    }

    @Override
    public List<GroupCourseVO> getAll() {


        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<GroupCourseVO> groupCourseVOList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()){
                GroupCourseVO groupCourseVO = new GroupCourseVO();
                groupCourseVO.setGcNo(rs.getInt("gc_no"));
                groupCourseVO.setSkillNo(rs.getInt("skill_no"));
                groupCourseVO.setClassType(rs.getInt("class_type"));
                groupCourseVO.setCoursePhoto(rs.getBytes("course_photo"));
                groupCourseVO.setCourseContent(rs.getString("course_content"));
                groupCourseVO.setCourseStatus(rs.getInt("course_status"));

                groupCourseVOList.add(groupCourseVO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps , rs);
        }

        return groupCourseVOList;
    }


    public static void main(String[] args) throws IOException {
        GroupCourseDAO groupCourseDAO = new GroupCourseDAOImpl();

        GroupCourseVO groupCourseVO = new GroupCourseVO();
        groupCourseVO.setGcNo(4);
        groupCourseVO.setSkillNo(3);
        groupCourseVO.setClassType(2);
        groupCourseVO.setCourseContent("123");
        byte[] pic = getPictureBtyeArray("C:\\Users\\trick\\OneDrive\\桌面\\FrontWeb\\images\\Carousel05.jpg");
        groupCourseVO.setCoursePhoto(pic);
        groupCourseVO.setCourseStatus(2);

//        groupCourseDAO.insert(groupCourseVO);

        System.out.println(groupCourseDAO.findbyGcNo(4));
//
//        List<GroupCourseVO> groupCourseVOList = groupCourseDAO.getAll();
//
//        for (GroupCourseVO groupCourse : groupCourseVOList){
//            System.out.println(groupCourse);
//
//        }

    }

    public static byte[] getPictureBtyeArray(String path) throws IOException {

        FileInputStream fis = new FileInputStream(path);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        return buffer;
    }
}
