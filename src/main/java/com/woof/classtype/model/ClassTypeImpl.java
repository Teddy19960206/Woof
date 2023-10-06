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
    public void insert(ClassType classType) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setString(1, classType.getCtName());
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
    public void update(ClassType classType) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(UPDATE_STMT);
            ps.setString(1, classType.getCtName());
            ps.setInt(2, classType.getCtNo());
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
    public void delete(ClassType classType) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(DELETE_STMT);
            ps.setString(1, classType.getCtName());
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
    public ClassType findbyCtNo(Integer ctNo) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ClassType classType = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(FIND_BY_CTNO);
            ps.setInt(1, ctNo);
            rs = ps.executeQuery();

            if (rs.next()) {
                classType = new ClassType();
                classType.setCtNo(ctNo);
                classType.setCtName(rs.getString("ct_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return classType;
    }

    @Override
    public List<ClassType> getAll() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ClassType classType = null;

        List<ClassType> classList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                classType = new ClassType();
                classType.setCtNo(rs.getInt("ct_no"));
                classType.setCtName(rs.getString("ct_name"));

                classList.add(classType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return classList;
    }
}
