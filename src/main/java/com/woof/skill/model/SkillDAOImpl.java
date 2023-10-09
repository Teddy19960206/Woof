package com.woof.skill.model;

import com.woof.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillDAOImpl implements SkillDAO{

    private static final String INSERT_STMT = "INSERT INTO skill (skill_name) VALUES ( ? )";

    private static final String UPDATE_STMT = "UPDATE skill SET skill_name = ? WHERE skill_no = ?";

    private static final String DELETE_STMT = "DELETE FROM skill WHERE skill_no = ?";

    private static final String FIND_BY_SKILLNO = "SELECT * FROM skill WHERE skill_no = ?";

    private static final String GET_ALL = "SELECT * FROM skill";
    @Override
    public void insert(SkillVO skillVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setString(1, skillVO.getSkillName());
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
    public void update(SkillVO skillVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(UPDATE_STMT);
            ps.setString(1, skillVO.getSkillName());
            ps.setInt(2, skillVO.getSkillNo());
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
    public void delete(SkillVO skillVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(DELETE_STMT);
            ps.setInt(1, skillVO.getSkillNo());
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
    public SkillVO findbySkillNo(Integer skillNo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SkillVO skillVO = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(FIND_BY_SKILLNO);
            ps.setInt(1, skillNo);
            rs = ps.executeQuery();

            if (rs.next()) {
                skillVO = new SkillVO();
                skillVO.setSkillNo(skillNo);
                skillVO.setSkillName(rs.getString("skill_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return skillVO;
    }

    @Override
    public List<SkillVO> getAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<SkillVO> skillList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                SkillVO skillVO = new SkillVO();
                skillVO.setSkillNo(rs.getInt("skill_no"));
                skillVO.setSkillName(rs.getString("skill_name"));

                skillList.add(skillVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return skillList;
    }

    public static void main(String[] args){
        SkillDAO skillDAO = new SkillDAOImpl();
        List<SkillVO> skillVOList = skillDAO.getAll();
        for (SkillVO skillVO: skillVOList){
            System.out.println(skillVO.getSkillNo());
            System.out.println(skillVO.getSkillName());
        }

        SkillVO skillVO = new SkillVO();
        skillVO.setSkillNo(14);
        skillVO.setSkillName("1231");

//        skillDAO.insert(skillVO);

//        skillDAO.update(skillVO);

//        skillDAO.delete(skillVO);



//        System.out.println(skillDAO.findbySkillNo(1).getSkillName());
    }
}
