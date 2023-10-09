package com.woof.nontrainingschedule.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.woof.util.Util;

public class NonTrainingScheduleDAOImpl implements NonTrainingScheduleDAO{

	public static final String INSERT_STMT = "INSERT INTO non_training_schedule VALUES (? , ?)";
	public static final String UPDATE_STMT = "UPDATE non_training_schedule SET trainer_no = ?, nts_date = ? WHERE nts_no = ?";
	private static final String DELETE_STMT = "DELETE FROM non_training_schedule WHERE nts_no = ?";
	private static final String FIND_BY_NTSNO = "SELECT * FROM non_training_schedule WHERE nts_no = ?";
	private static final String GET_ALL = "SELECT * FROM non_training_schedule";
	
	@Override
	public void insert(NonTrainingScheduleVO nonTrainingScheduleVO) {
		Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        byte[] a = null;

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
		
	}
	@Override
	public void delete(Integer ntsNo) {
		
	}
	@Override
	public NonTrainingScheduleVO findByNtsNo(Integer ntsNo) {
		return null;
	}
	@Override
	public List<NonTrainingScheduleVO> getAll() {
		return null;
	}
	
//	public static void main(String[] args) {
//		NonTrainingScheduleDAO nonTrainingSchedule = new NonTrainingScheduleDAOImpl();
//		List<NonTrainingScheduleVO> nonTrainingScheduleVOList = nonTrainingSchedule.getAll();
//		for(NonTrainingScheduleVO nonTrainingScheduleVO: nonTrainingScheduleVOList) {
//			System.out.println(nonTrainingScheduleVO.getNtsNo());
//			System.out.println(nonTrainingScheduleVO.getTrainerNo());
//			System.out.println(nonTrainingScheduleVO.getNtsDate());
//		}
//	}	
}
