package com.woof.skillslist.dao;

import com.woof.skillslist.entity.SkillsList;
import com.woof.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillsListDAOImpl implements SkillsListDAO{

    private static final String INSERT_STMT = "INSERT INTO skills_list (trainer_no , skill_no) VALUES ( ? , ?)";

    private static final String DELETE_STMT = "DELETE FROM skills_list WHERE  trainer_no = ? and skill_no = ?";

    private static final String FIND_ONE = "SELECT * FROM skills_list WHERE trainer_no = ? and skill_no = ?";

    private static final String FIND_BY_TRAINERNO = "SELECT * FROM skills_list WHERE trainer_no = ?";

    private static final String GET_ALL = "SELECT * FROM skills_list";
    @Override
    public void insert(SkillsList skillsList) {
        Connection con = null;
        PreparedStatement ps = null;
        int count  = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setInt(1 , skillsList.getTrainerNo());
            ps.setInt(2 , skillsList.getSkillNo());
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Util.closeResources(con, ps , null);
        }

        if (count == 1) {
            System.out.println("新增成功");
        } else {
            System.out.println("新增失敗");
        }

    }

    @Override
    public void delete(SkillsList skillsList) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(DELETE_STMT);
            ps.setInt(1, skillsList.getTrainerNo());
            ps.setInt(2, skillsList.getSkillNo());
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps , null);
        }

        if (count == 1) {
            System.out.println("刪除成功");
        } else {
            System.out.println("刪除失敗");
        }

    }

    @Override
    public SkillsList find(SkillsList skillsListVO) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SkillsList skillsList = null;

        try {
            con  = Util.getConnection();
            ps = con.prepareStatement(FIND_ONE);
            ps.setInt(1, skillsListVO.getTrainerNo());
            ps.setInt(2, skillsListVO.getSkillNo());
            rs = ps.executeQuery();

            if (rs.next()){
                skillsList = new SkillsList();
                skillsList.setTrainerNo(rs.getInt("trainer_no"));
                skillsList.setSkillNo(rs.getInt("skill_no"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps , rs);
        }

        return skillsList;
    }

    @Override
    public List<SkillsList> findbyTrainerNo(Integer TrainerNo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;



        List<SkillsList> skillsList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(FIND_BY_TRAINERNO);
            ps.setInt(1 , TrainerNo);
            rs = ps.executeQuery();
            while (rs.next()){
                SkillsList skillsListVO = new SkillsList();
                skillsListVO.setTrainerNo(rs.getInt("trainer_no"));
                skillsListVO.setSkillNo(rs.getInt("skill_no"));

                skillsList.add(skillsListVO);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps , rs);
        }


        return skillsList;
    }

    @Override
    public List<SkillsList> getAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        List<SkillsList> skillsListVOList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()){
                SkillsList skillsList = new SkillsList();
                skillsList.setTrainerNo(rs.getInt("trainer_no"));
                skillsList.setSkillNo(rs.getInt("skill_no"));

                skillsListVOList.add(skillsList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps , rs);
        }

        return skillsListVOList;
    }

    public static void main(String[] args) {
        SkillsListDAO skillsListDAO = new SkillsListDAOImpl();

        SkillsList skillsList = new SkillsList();
        skillsList.setSkillNo(3);
        skillsList.setTrainerNo(3);

//        System.out.println(skillsListDAO.find(skillsListVO).getSkillNo());
//        System.out.println(skillsListDAO.find(skillsListVO).getTrainerNo());


//        List<SkillsListVO> skillsListVOList = skillsListDAO.findbyTrainerNo(3);

        List<SkillsList> skillsListVOList = skillsListDAO.getAll();

        for (SkillsList skillsVO : skillsListVOList){
            System.out.println(skillsVO.getTrainerNo());
            System.out.println(skillsVO.getSkillNo());
        }

    }
}
