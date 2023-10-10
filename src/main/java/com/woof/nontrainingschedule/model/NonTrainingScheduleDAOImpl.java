package com.woof.nontrainingschedule.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.woof.util.Util;

public class NonTrainingScheduleDAOImpl implements NonTrainingScheduleDAO{

	public static final String INSERT_STMT = "INSERT INTO non_training_schedule (trainer_no , nts_date) VALUES (? , ?)";
	public static final String UPDATE_STMT = "UPDATE non_training_schedule SET trainer_no = ?, nts_date = ? WHERE nts_no = ?";
	private static final String DELETE_STMT = "DELETE FROM non_training_schedule WHERE nts_no = ?";
	private static final String FIND_BY_NTSNO = "SELECT * FROM non_training_schedule WHERE nts_no = ?";
	private static final String GET_ALL = "SELECT * FROM non_training_schedule";
	
	@Override
	public void insert(NonTrainingScheduleVO nonTrainingScheduleVO) {
		Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setInt(1, nonTrainingScheduleVO.getTrainerNo());
            ps.setDate(2, nonTrainingScheduleVO.getNtsDate());

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
	public void update(NonTrainingScheduleVO nonTrainingScheduleVO) {
		Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(UPDATE_STMT);
            ps.setInt(1, nonTrainingScheduleVO.getTrainerNo());
            ps.setDate(2, nonTrainingScheduleVO.getNtsDate());
            ps.setInt(3, nonTrainingScheduleVO.getNtsNo());

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
	public void delete(NonTrainingScheduleVO nonTrainingScheduleVO) {
		Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(DELETE_STMT);
            ps.setInt(1, nonTrainingScheduleVO.getNtsNo());

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
	public NonTrainingScheduleVO findByNtsNo(Integer ntsNo) {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        NonTrainingScheduleVO nonTrainingScheduleVO = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(FIND_BY_NTSNO);
            ps.setInt(1, ntsNo);
            rs = ps.executeQuery();

            if (rs.next()){
            	nonTrainingScheduleVO = new NonTrainingScheduleVO();
            	nonTrainingScheduleVO.setNtsNo(ntsNo);
            	nonTrainingScheduleVO.setTrainerNo(rs.getInt("trainer_no"));
            	nonTrainingScheduleVO.setNtsDate(rs.getDate("nts_date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }
        return nonTrainingScheduleVO;
	}
	@Override
	public List<NonTrainingScheduleVO> getAll() {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<NonTrainingScheduleVO> nonTrainingScheduleVOList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()){
            	NonTrainingScheduleVO nonTrainingScheduleVO = new NonTrainingScheduleVO();
            	nonTrainingScheduleVO.setNtsNo(rs.getInt("nts_no"));
            	nonTrainingScheduleVO.setTrainerNo(rs.getInt("trainer_no"));
            	nonTrainingScheduleVO.setNtsDate(rs.getDate("nts_date"));
            	
            	nonTrainingScheduleVOList.add(nonTrainingScheduleVO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return nonTrainingScheduleVOList;
	}
	
	public static void main(String[] args) {
//		NonTrainingScheduleDAO nonTrainingScheduleDAO = new NonTrainingScheduleDAOImpl();
//		NonTrainingScheduleVO nonTrainingScheduleVO = new NonTrainingScheduleVO();
		
		// 新增
//		nonTrainingScheduleVO.setTrainerNo(2);
//		nonTrainingScheduleVO.setNtsDate(Date.valueOf("2023-09-10"));
//		nonTrainingScheduleDAO.insert(nonTrainingScheduleVO);
		
		// 修改
//		nonTrainingScheduleVO.setTrainerNo(3);
//		nonTrainingScheduleVO.setNtsDate(Date.valueOf("2023-10-10"));
//		nonTrainingScheduleVO.setNtsNo(11);
//		nonTrainingScheduleDAO.update(nonTrainingScheduleVO);
		
		// 刪除
//		nonTrainingScheduleVO.setNtsNo(11);
//		nonTrainingScheduleDAO.delete(nonTrainingScheduleVO);
		
		// 查詢
//		nonTrainingScheduleVO = nonTrainingScheduleDAO.findByNtsNo(9);
//		System.out.println(nonTrainingScheduleVO);
		
//		List<NonTrainingScheduleVO> nonTrainingScheduleVOList = nonTrainingSchedule.getAll();
//		for(NonTrainingScheduleVO nonTrainingScheduleVO: nonTrainingScheduleVOList) {
//			System.out.println(nonTrainingScheduleVO.getNtsNo());
//			System.out.println(nonTrainingScheduleVO.getTrainerNo());
//			System.out.println(nonTrainingScheduleVO.getNtsDate());
//		}
	}	
}
