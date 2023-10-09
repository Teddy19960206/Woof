package com.woof.groupcourseschedule.model;

import com.woof.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupCourseScheduleDAOImpl implements GroupCourseScheduleDAO {

    private static final String INSERT_STMT = "INSERT INTO group_course_schedule " +
            "( gc_no , trainer_no  , gcs_start , gcs_end , min_limit , max_limit  , gcs_price)" +
            "VALUES (? , ? , ? , ? , ? , ? ,? )";

    private static final String UPDATE_STMT = "UPDATE group_course_schedule " +
            "SET gc_no = ?, trainer_no = ? , gcs_start = ?, gcs_end = ?, min_limit = ?, max_limit = ? ,count = ? ," +
            " gcs_price = ? , gcs_status = ? WHERE gcs_no = ?";

    private static final String FIND_BY_GCSNO = "SELECT * FROM group_course_schedule WHERE GCS_NO =?";


    private static final String GET_ALL = "SELECT  * FROM group_course_schedule";
    @Override
    public void insert(GroupCourseScheduleVO groupCourseScheduleVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setInt(1,groupCourseScheduleVO.getGcNo());
            ps.setInt(2,groupCourseScheduleVO.getTrainerNo());
            ps.setDate(3,groupCourseScheduleVO.getGcsStart());
            ps.setDate(4,groupCourseScheduleVO.getGcsEnd());
            ps.setInt(5,groupCourseScheduleVO.getMinLimit());
            ps.setInt(6,groupCourseScheduleVO.getMaxLimit());
            ps.setInt(7,groupCourseScheduleVO.getGcsPrice());

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
    public void update(GroupCourseScheduleVO groupCourseScheduleVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(UPDATE_STMT);
            ps.setInt(1 , groupCourseScheduleVO.getGcNo());
            ps.setInt(2, groupCourseScheduleVO.getTrainerNo());
            ps.setDate(3, groupCourseScheduleVO.getGcsStart());
            ps.setDate(4, groupCourseScheduleVO.getGcsStart());
            ps.setInt(5, groupCourseScheduleVO.getMinLimit());
            ps.setInt(6, groupCourseScheduleVO.getMaxLimit());
            ps.setInt(7, groupCourseScheduleVO.getCount());
            ps.setInt(8, groupCourseScheduleVO.getGcsPrice());
            ps.setInt(9, groupCourseScheduleVO.getGcsStatus());
            ps.setInt(10 ,groupCourseScheduleVO.getGcsNo());
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
    public GroupCourseScheduleVO findByGcsNo(Integer gcsNo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        GroupCourseScheduleVO groupCourseScheduleVO = null;

        try {
            con =  Util.getConnection();
            ps = con.prepareStatement(FIND_BY_GCSNO);
            ps.setInt(1,gcsNo);
            rs = ps.executeQuery();

            if (rs.next()){
                groupCourseScheduleVO = new GroupCourseScheduleVO();
                groupCourseScheduleVO.setGcsNo(gcsNo);
                groupCourseScheduleVO.setGcNo(rs.getInt("gc_no"));
                groupCourseScheduleVO.setTrainerNo(rs.getInt("trainer_no"));
                groupCourseScheduleVO.setGcsStart(rs.getDate("gcs_start"));
                groupCourseScheduleVO.setGcsEnd(rs.getDate("gcs_end"));
                groupCourseScheduleVO.setMinLimit(rs.getInt("min_limit"));
                groupCourseScheduleVO.setMaxLimit(rs.getInt("max_limit"));
                groupCourseScheduleVO.setCount(rs.getInt("count"));
                groupCourseScheduleVO.setGcsPrice(rs.getInt("gcs_price"));
                groupCourseScheduleVO.setGcsStatus(rs.getInt("gcs_status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps , rs);
        }

        return groupCourseScheduleVO;
    }

    @Override
    public List<GroupCourseScheduleVO> getAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GroupCourseScheduleVO> groupCourseScheduleVOList = new ArrayList<>();

        try {
            con =  Util.getConnection();
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()){
                GroupCourseScheduleVO groupCourseScheduleVO = new GroupCourseScheduleVO();
                groupCourseScheduleVO.setGcsNo(rs.getInt("gcs_no"));
                groupCourseScheduleVO.setGcNo(rs.getInt("gc_no"));
                groupCourseScheduleVO.setTrainerNo(rs.getInt("trainer_no"));
                groupCourseScheduleVO.setGcsStart(rs.getDate("gcs_start"));
                groupCourseScheduleVO.setGcsEnd(rs.getDate("gcs_end"));
                groupCourseScheduleVO.setMinLimit(rs.getInt("min_limit"));
                groupCourseScheduleVO.setMaxLimit(rs.getInt("max_limit"));
                groupCourseScheduleVO.setCount(rs.getInt("count"));
                groupCourseScheduleVO.setGcsPrice(rs.getInt("gcs_price"));
                groupCourseScheduleVO.setGcsStatus(rs.getInt("gcs_status"));

                groupCourseScheduleVOList.add(groupCourseScheduleVO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps , rs);
        }

        return groupCourseScheduleVOList;
    }

    public static void main(String[] args) {
        GroupCourseScheduleDAO groupCourseScheduleDAO = new GroupCourseScheduleDAOImpl();

        GroupCourseScheduleVO groupCourseScheduleVO = new GroupCourseScheduleVO();

        groupCourseScheduleVO.setGcsNo(4);
        groupCourseScheduleVO.setGcNo(3);
        groupCourseScheduleVO.setTrainerNo(5);
        groupCourseScheduleVO.setGcsStart(Date.valueOf("2023-11-11"));
        groupCourseScheduleVO.setGcsEnd(Date.valueOf("2023-11-30"));
        groupCourseScheduleVO.setMinLimit(5);
        groupCourseScheduleVO.setMaxLimit(10);
        groupCourseScheduleVO.setCount(2);
        groupCourseScheduleVO.setGcsPrice(8000);
        groupCourseScheduleVO.setGcsStatus(1);


//        groupCourseScheduleDAO.update(groupCourseScheduleVO);

//        System.out.println(groupCourseScheduleDAO.findByGcsNo(3));

        System.out.println(groupCourseScheduleDAO.getAll());
    }
}
