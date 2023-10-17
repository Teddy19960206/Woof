//package com.woof.privatetrainingappointmentform.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.woof.privatetrainingappointmentform.entity.PrivateTrainingAppointmentForm;
//import com.woof.util.Util;
//
//public class PrivateTrainingAppointmentFormJDBCDAOImpl implements PrivateTrainingAppointmentFormJDBCDAO{
//	
//	public static final String INSERT_STMT = "INSERT INTO private_training_appointment_form (mem_no , trainer_no , pta_class) VALUES (? , ? , ?)";
//	public static final String UPDATE_STMT = "UPDATE private_training_appointment_form SET mem_no = ?, trainer_no = ?, pta_class = ? WHERE pta_no = ?";
//	private static final String DELETE_STMT = "DELETE FROM private_training_appointment_form WHERE pta_no = ?";
//	private static final String FIND_BY_PTANO = "SELECT * FROM private_training_appointment_form WHERE pta_no = ?";
//	private static final String GET_ALL = "SELECT * FROM private_training_appointment_form";
//	
//	@Override
//	public void insert(PrivateTrainingAppointmentForm privateTrainingAppointmentFormVO) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(INSERT_STMT);
//            ps.setInt(1, privateTrainingAppointmentFormVO.getMemNo());
//            ps.setInt(2, privateTrainingAppointmentFormVO.getTrainerNo());
//            ps.setInt(3, privateTrainingAppointmentFormVO.getPtaClass());   
//
//            count = ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeResources(con, ps, null);
//        }
//
//        if (count == 1) {
//            System.out.println("新增成功");
//        } else {
//            System.out.println("新增失敗");
//        }		
//	}
//	@Override
//	public void update(PrivateTrainingAppointmentForm privateTrainingAppointmentFormVO) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(UPDATE_STMT);
//            ps.setInt(1, privateTrainingAppointmentFormVO.getMemNo());
//            ps.setInt(2, privateTrainingAppointmentFormVO.getTrainerNo());
//            ps.setInt(3, privateTrainingAppointmentFormVO.getPtaClass());   
//            ps.setInt(4, privateTrainingAppointmentFormVO.getPtaNo());   
//
//            count = ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeResources(con, ps, null);
//        }
//
//        if (count == 1) {
//            System.out.println("修改成功");
//        } else {
//            System.out.println("修改失敗");
//        }		
//	}
//	@Override
//	public void delete(PrivateTrainingAppointmentForm privateTrainingAppointmentFormVO) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(DELETE_STMT);
//            ps.setInt(1, privateTrainingAppointmentFormVO.getPtaNo());
//            
//            count = ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeResources(con , ps ,null);
//        }
//
//        if (count == 1) {
//            System.out.println("刪除成功");
//        } else {
//            System.out.println("刪除失敗");
//        }
//	}
//	@Override
//	public PrivateTrainingAppointmentForm findByPtaNo(Integer ptaNo) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        PrivateTrainingAppointmentForm privateTrainingAppointmentFormVO = null;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(FIND_BY_PTANO);
//            ps.setInt(1, ptaNo);
//            rs = ps.executeQuery();
//
//            if (rs.next()){
//            	privateTrainingAppointmentFormVO = new PrivateTrainingAppointmentForm();
//            	privateTrainingAppointmentFormVO.setPtaNo(ptaNo);
//            	privateTrainingAppointmentFormVO.setMemNo(rs.getInt("mem_no"));
//            	privateTrainingAppointmentFormVO.setTrainerNo(rs.getInt("trainer_no"));
//            	privateTrainingAppointmentFormVO.setPtaClass(rs.getInt("pta_class"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeResources(con, ps, rs);
//        }
//        return privateTrainingAppointmentFormVO;
//	}
//	@Override
//	public List<PrivateTrainingAppointmentForm> getAll() {
//		Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<PrivateTrainingAppointmentForm> privateTrainingAppointmentFormVOList = new ArrayList<>();
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(GET_ALL);
//            rs = ps.executeQuery();
//
//            while (rs.next()){
//            	PrivateTrainingAppointmentForm privateTrainingAppointmentFormVO = new PrivateTrainingAppointmentForm();
//            	privateTrainingAppointmentFormVO.setPtaNo(rs.getInt("pta_no"));
//            	privateTrainingAppointmentFormVO.setMemNo(rs.getInt("mem_no"));
//            	privateTrainingAppointmentFormVO.setTrainerNo(rs.getInt("trainer_no"));
//            	privateTrainingAppointmentFormVO.setPtaClass(rs.getInt("pta_class"));
//            	
//            	privateTrainingAppointmentFormVOList.add(privateTrainingAppointmentFormVO);
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeResources(con, ps, rs);
//        }
//
//        return privateTrainingAppointmentFormVOList;
//	}
//	
//	public static void main(String[] args) {
////		PrivateTrainingAppointmentFormDAO privateTrainingAppointmentFormDAO = new PrivateTrainingAppointmentFormDAOImpl();
////		PrivateTrainingAppointmentFormVO privateTrainingAppointmentFormVO = new PrivateTrainingAppointmentFormVO();
//		
//		// 新增
////		privateTrainingAppointmentFormVO.setMemNo(2);
////		privateTrainingAppointmentFormVO.setTrainerNo(4);
////		privateTrainingAppointmentFormVO.setPtaClass(3);
////		privateTrainingAppointmentFormDAO.insert(privateTrainingAppointmentFormVO);
//		
//		// 修改
////		privateTrainingAppointmentFormVO.setMemNo(3);
////		privateTrainingAppointmentFormVO.setTrainerNo(5);
////		privateTrainingAppointmentFormVO.setPtaClass(6);
////		privateTrainingAppointmentFormVO.setPtaNo(11);
////		privateTrainingAppointmentFormDAO.update(privateTrainingAppointmentFormVO);
//		
//		// 刪除
////		privateTrainingAppointmentFormVO.setPtaNo(11);
////		privateTrainingAppointmentFormDAO.delete(privateTrainingAppointmentFormVO);
//		
//		// 查詢
////		privateTrainingAppointmentFormVO = privateTrainingAppointmentFormDAO.findByPtaNo(9);
////		System.out.println(privateTrainingAppointmentFormVO);
//		
//		// 查詢全部
////		List<PrivateTrainingAppointmentFormVO> privateTrainingAppointmentFormVOList = privateTrainingAppointmentFormDAO.getAll();
////		for(PrivateTrainingAppointmentFormVO privateTrainingAppointmentFormVO1: privateTrainingAppointmentFormVOList) {
////			System.out.println(privateTrainingAppointmentFormVO1.getPtaNo());
////			System.out.println(privateTrainingAppointmentFormVO1.getMemNo());
////			System.out.println(privateTrainingAppointmentFormVO1.getTrainerNo());
////			System.out.println(privateTrainingAppointmentFormVO1.getPtaClass());
////		}
//	}
//}
