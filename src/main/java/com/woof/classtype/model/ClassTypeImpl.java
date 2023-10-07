package com.woof.classtype.model;

import com.woof.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassTypeImpl implements ClassTypeDAO {

    private static final String INSERT_STMT = "INSERT INTO class_type VALUES ( ? )";

    private static final String UPDATE_STMT = "UPDATE class_type SET ct_name = ? WHERE ct_no = ?";

    private static final String DELETE_STMT = "DELETE FROM class_type WHERE ct_no = ?";

    private static final String FIND_BY_CTNO = "SELECT * FROM class_type WHERE ct_no = ?";

    private static final String GET_ALL = "SELECT * FROM class_type";


    @Override
    public void insert(ClassTypeVO classTypeVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setString(1, classTypeVO.getCtName());
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
    public void update(ClassTypeVO classTypeVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(UPDATE_STMT);
            ps.setString(1, classTypeVO.getCtName());
            ps.setInt(2, classTypeVO.getCtNo());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, null);
        }

        if (count == 1) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失敗");
        }


    }

    @Override
    public void delete(ClassTypeVO classTypeVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(DELETE_STMT);
            ps.setString(1, classTypeVO.getCtName());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, null);
        }

        if (count == 1) {
            System.out.println("刪除成功");
        } else {
            System.out.println("刪除失敗");
        }

    }

    @Override
    public ClassTypeVO findbyCtNo(Integer ctNo) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ClassTypeVO classTypeVO = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(FIND_BY_CTNO);
            ps.setInt(1, ctNo);
            rs = ps.executeQuery();

            if (rs.next()) {
                classTypeVO = new ClassTypeVO();
                classTypeVO.setCtNo(ctNo);
                classTypeVO.setCtName(rs.getString("ct_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return classTypeVO;
    }

    @Override
    public List<ClassTypeVO> getAll() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ClassTypeVO classTypeVO = null;

        List<ClassTypeVO> classList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                classTypeVO = new ClassTypeVO();
                classTypeVO.setCtNo(rs.getInt("ct_no"));
                classTypeVO.setCtName(rs.getString("ct_name"));

                classList.add(classTypeVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return classList;
    }
}
