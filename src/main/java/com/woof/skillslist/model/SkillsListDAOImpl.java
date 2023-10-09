package com.woof.skillslist.model;

import com.woof.skill.model.SkillVO;
import com.woof.util.Util;
import oracle.jdbc.proxy.annotation.Pre;

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
    public void insert(SkillsListVO skillsListVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count  = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setInt(1 , skillsListVO.getTrainerNo());
            ps.setInt(2 , skillsListVO.getSkillNo());
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
    public void delete(SkillsListVO skillsListVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(DELETE_STMT);
            ps.setInt(1,skillsListVO.getTrainerNo());
            ps.setInt(2, skillsListVO.getSkillNo());
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
    public SkillsListVO find(SkillsListVO skillsListVO) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SkillsListVO skillsList = null;

        try {
            con  = Util.getConnection();
            ps = con.prepareStatement(FIND_ONE);
            ps.setInt(1, skillsListVO.getTrainerNo());
            ps.setInt(2, skillsListVO.getSkillNo());
            rs = ps.executeQuery();

            if (rs.next()){
                skillsList = new SkillsListVO();
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
    public List<SkillsListVO> findbyTrainerNo(Integer TrainerNo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;



        List<SkillsListVO> skillsList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(FIND_BY_TRAINERNO);
            ps.setInt(1 , TrainerNo);
            rs = ps.executeQuery();
            while (rs.next()){
                SkillsListVO skillsListVO = new SkillsListVO();
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
    public List<SkillsListVO> getAll() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        List<SkillsListVO> skillsListVOList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()){
                SkillsListVO skillsListVO = new SkillsListVO();
                skillsListVO.setTrainerNo(rs.getInt("trainer_no"));
                skillsListVO.setSkillNo(rs.getInt("skill_no"));

                skillsListVOList.add(skillsListVO);
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

        SkillsListVO skillsListVO = new SkillsListVO();
        skillsListVO.setSkillNo(3);
        skillsListVO.setTrainerNo(3);

//        System.out.println(skillsListDAO.find(skillsListVO).getSkillNo());
//        System.out.println(skillsListDAO.find(skillsListVO).getTrainerNo());


//        List<SkillsListVO> skillsListVOList = skillsListDAO.findbyTrainerNo(3);

        List<SkillsListVO> skillsListVOList = skillsListDAO.getAll();

        for (SkillsListVO skillsVO : skillsListVOList){
            System.out.println(skillsVO.getTrainerNo());
            System.out.println(skillsVO.getSkillNo());
        }

    }
}
