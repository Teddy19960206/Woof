package com.woof.groupcourseorder.model;

import com.woof.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupCourseOrderDAOImpl implements GroupCourseOrderDAO{

    private static final String INSERT_STMT = "INSERT INTO group_course_order (mem_no , gcs_no , gco_date , gco_payment_method , gco_smmp ,actual_amount)" +
            "VALUES ( ? , ? , ? ,? ,?,?)";

    private  static final String UPDATE_STMT = "UPDATE group_course_order SET  mem_no = ? , gcs_no = ? , gco_date = ?," +
            " gco_payment_method = ? , gco_smmp = ? , actual_amount = ? , gco_status = ? WHERE gco_no = ?";
    private static final String DELETE_STMT = "DELETE FROM group_course_order WHERE gco_no = ? ";

    private static final String FIND_BY_GCONO = "SELECT * FROM group_course_order WHERE gco_no = ?";

    private static final String GET_ALL = "SELECT  * FROM group_course_order";


    @Override
    public void insert(GroupCourseOrderVO groupCourseOrderVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count  = 0;

        try {
            con = Util.getConnection();
            ps =  con.prepareStatement(INSERT_STMT);
            ps.setInt(1 , groupCourseOrderVO.getMemNo());
            ps.setInt(2, groupCourseOrderVO.getGcsNo());
            ps.setTimestamp(3 , groupCourseOrderVO.getGcoDate());
            ps.setInt(4 , groupCourseOrderVO.getGcoPaymentMethod());
            ps.setInt(5, groupCourseOrderVO.getGcoSmmp());
            ps.setInt(6 , groupCourseOrderVO.getActualAmount());
            count = ps.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con  , ps ,null);
        }

        if( count == 1){
            System.out.println("新增成功");
        }else{
            System.out.println("新增失敗");
        }

    }

    @Override
    public void delete(GroupCourseOrderVO groupCourseOrderVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count  = 0;

        try {
            con = Util.getConnection();
            ps =  con.prepareStatement(DELETE_STMT);
            ps.setInt(1 , groupCourseOrderVO.getGcoNo());
            count = ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con  , ps ,null);
        }

        if( count == 1){
            System.out.println("刪除成功");
        }else{
            System.out.println("刪除失敗");
        }
    }

    @Override
    public void update(GroupCourseOrderVO groupCourseOrderVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count  = 0;

        try {
            con = Util.getConnection();
            ps =  con.prepareStatement(UPDATE_STMT);
            ps.setInt(1 , groupCourseOrderVO.getMemNo());
            ps.setInt(2, groupCourseOrderVO.getGcsNo());
            ps.setTimestamp(3 , groupCourseOrderVO.getGcoDate());
            ps.setInt(4 , groupCourseOrderVO.getGcoPaymentMethod());
            ps.setInt(5, groupCourseOrderVO.getGcoSmmp());
            ps.setInt(6 , groupCourseOrderVO.getActualAmount());
            ps.setInt(7 , groupCourseOrderVO.getGcoStatus());
            ps.setInt(8, groupCourseOrderVO.getGcoNo());
            count = ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con  , ps ,null);
        }

        if( count == 1){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失敗");
        }
    }

    @Override
    public GroupCourseOrderVO findByGcoNo(Integer gcoNo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        GroupCourseOrderVO  groupCourseOrderVO = null;

        try {
            con = Util.getConnection();
            ps =  con.prepareStatement(FIND_BY_GCONO);
            ps.setInt(1, gcoNo);
            rs = ps.executeQuery();

            if (rs.next()){
                groupCourseOrderVO = new GroupCourseOrderVO();
                groupCourseOrderVO.setGcoNo(rs.getInt("gco_no"));
                groupCourseOrderVO.setMemNo(rs.getInt("mem_no"));
                groupCourseOrderVO.setGcsNo(rs.getInt("gcs_no"));
                groupCourseOrderVO.setGcoDate(rs.getTimestamp("gco_date"));
                groupCourseOrderVO.setGcoPaymentMethod(rs.getInt("gco_payment_method"));
                groupCourseOrderVO.setGcoSmmp(rs.getInt("gco_smmp"));
                groupCourseOrderVO.setActualAmount(rs.getInt("actual_amount"));
                groupCourseOrderVO.setGcoStatus(rs.getInt("gco_status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con  , ps ,rs);
        }

        return groupCourseOrderVO;
    }

    @Override
    public List<GroupCourseOrderVO> getAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GroupCourseOrderVO>  groupCourseOrderVOList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps =  con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()){
                GroupCourseOrderVO groupCourseOrderVO = new GroupCourseOrderVO();
                groupCourseOrderVO.setGcoNo(rs.getInt("gco_no"));
                groupCourseOrderVO.setMemNo(rs.getInt("mem_no"));
                groupCourseOrderVO.setGcsNo(rs.getInt("gcs_no"));
                groupCourseOrderVO.setGcoDate(rs.getTimestamp("gco_date"));
                groupCourseOrderVO.setGcoPaymentMethod(rs.getInt("gco_payment_method"));
                groupCourseOrderVO.setGcoSmmp(rs.getInt("gco_smmp"));
                groupCourseOrderVO.setActualAmount(rs.getInt("actual_amount"));
                groupCourseOrderVO.setGcoStatus(rs.getInt("gco_status"));

                groupCourseOrderVOList.add(groupCourseOrderVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con  , ps ,rs);
        }

        return groupCourseOrderVOList;
    }

    public static void main(String[] args) {
        GroupCourseOrderDAO groupCourseOrderDAO = new GroupCourseOrderDAOImpl();

        GroupCourseOrderVO groupCourseOrderVO = new GroupCourseOrderVO();

        groupCourseOrderVO.setGcoNo(3);
        groupCourseOrderVO.setMemNo(3);
        groupCourseOrderVO.setGcsNo(2);
        groupCourseOrderVO.setGcoDate(Timestamp.valueOf("2023-11-30 12:00:00"));
        groupCourseOrderVO.setGcoPaymentMethod(1);
        groupCourseOrderVO.setGcoSmmp(10);
        groupCourseOrderVO.setActualAmount(700);
        groupCourseOrderVO.setGcoStatus(2);
//        groupCourseOrderDAO.update(groupCourseOrderVO);

        System.out.println(groupCourseOrderDAO.findByGcoNo(2));
        System.out.println(groupCourseOrderDAO.getAll());

    }

}
