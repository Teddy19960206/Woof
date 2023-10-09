package com.woof.groupcoursescheduledetail.model;

import antlr.preprocessor.Preprocessor;
import com.woof.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupCourseScheduleDetailDAOImpl implements GroupCourseScheduleDetailDAO{

    private static final String INSERT_STMT = "INSERT INTO group_course_schedule_detail (gcs_no , trainer_no , class_date)" +
            "VALUES (? , ? , ?)";

    private static final String UPDATE_STMT = "UPDATE group_course_schedule_detail SET gcs_no = ? , trainer_no = ? , class_date = ? WHERE gcsd_no = ?";

    private static final String FIND_BY_GCSD = "SELECT * FROM group_course_schedule_detail WHERE gcsd_no = ?";

    private static final String GET_ALL ="SELECT * FROM group_course_schedule_detail";

    private static final String DELETE_STMT = "DELETE FROM group_course_schedule_detail WHERE gcsd_no = ?";
    @Override
    public void insert(GroupCourseScheduleDetailVO groupCourseScheduleDetailVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setInt(1 , groupCourseScheduleDetailVO.getGcsNo());
            ps.setInt(2 , groupCourseScheduleDetailVO.getTrainerNo());
            ps.setDate(3 , groupCourseScheduleDetailVO.getClassDate());
            count = ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps ,null);
        }

        if (count == 1) {
            System.out.println("新增成功");
        } else {
            System.out.println("新增失敗");
        }
    }

    @Override
    public void update(GroupCourseScheduleDetailVO groupCourseScheduleDetailVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(UPDATE_STMT);
            ps.setInt(1 , groupCourseScheduleDetailVO.getGcsNo());
            ps.setInt(2 , groupCourseScheduleDetailVO.getTrainerNo());
            ps.setDate(3 , groupCourseScheduleDetailVO.getClassDate());
            ps.setInt(4 ,groupCourseScheduleDetailVO.getGcsdNo());
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps ,null);
        }

        if (count == 1) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失敗");
        }

    }

    @Override
    public void delete(GroupCourseScheduleDetailVO groupCourseScheduleDetailVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(DELETE_STMT);
            ps.setInt(1 , groupCourseScheduleDetailVO.getGcsdNo());
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps ,null);
        }

        if (count == 1) {
            System.out.println("刪除成功");
        } else {
            System.out.println("刪除失敗");
        }
    }

    @Override
    public GroupCourseScheduleDetailVO findByGcsd(Integer gcsd) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        GroupCourseScheduleDetailVO groupCourseScheduleDetailVO = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(FIND_BY_GCSD);
            ps.setInt(1, gcsd);
            rs = ps.executeQuery();

            if (rs.next()){
                groupCourseScheduleDetailVO = new GroupCourseScheduleDetailVO();
                groupCourseScheduleDetailVO.setGcsdNo(gcsd);
                groupCourseScheduleDetailVO.setGcsNo(rs.getInt("gcs_no"));
                groupCourseScheduleDetailVO.setTrainerNo(rs.getInt("trainer_no"));
                groupCourseScheduleDetailVO.setClassDate(rs.getDate("class_date"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }
        return groupCourseScheduleDetailVO;
    }

    @Override
    public List<GroupCourseScheduleDetailVO> getAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GroupCourseScheduleDetailVO> groupCourseScheduleDetailVOList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()){
                GroupCourseScheduleDetailVO groupCourseScheduleDetailVO = new GroupCourseScheduleDetailVO();
                groupCourseScheduleDetailVO.setGcsdNo(rs.getInt("gcsd_no"));
                groupCourseScheduleDetailVO.setGcsNo(rs.getInt("gcs_no"));
                groupCourseScheduleDetailVO.setTrainerNo(rs.getInt("trainer_no"));
                groupCourseScheduleDetailVO.setClassDate(rs.getDate("class_date"));

                groupCourseScheduleDetailVOList.add(groupCourseScheduleDetailVO);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return groupCourseScheduleDetailVOList;
    }

    public static void main(String[] args) {
        GroupCourseScheduleDetailDAO groupCourseScheduleDetailDAO = new GroupCourseScheduleDetailDAOImpl();

        GroupCourseScheduleDetailVO groupCourseScheduleDetailVO = new GroupCourseScheduleDetailVO();
        groupCourseScheduleDetailVO.setGcsdNo(7);
        groupCourseScheduleDetailVO.setGcsNo(2);
//        新增時與檔期編號的訓練師相同
//        若是有人代班 再改成 代班的訓練師編號
//        groupCourseScheduleDetailVO.setTrainerNo(groupCourseScheduleDetailVO.getGcsdNo());
        groupCourseScheduleDetailVO.setTrainerNo(3);
        groupCourseScheduleDetailVO.setClassDate(Date.valueOf("2023-12-30"));

//        groupCourseScheduleDetailDAO.update(groupCourseScheduleDetailVO);

        System.out.println(groupCourseScheduleDetailDAO.findByGcsd(7));

        System.out.println(groupCourseScheduleDetailDAO.getAll());


//        groupCourseScheduleDetailDAO.delete(groupCourseScheduleDetailVO);


    }
}
