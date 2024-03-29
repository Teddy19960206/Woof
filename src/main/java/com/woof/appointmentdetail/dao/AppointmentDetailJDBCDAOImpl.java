//package com.woof.appointmentdetail.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.woof.appointmentdetail.entity.AppointmentDetail;
//import com.woof.util.Util;
//
//public class AppointmentDetailJDBCDAOImpl implements AppointmentDetailJDBCDAO{
//	
//	private static final String INSERT_STMT = "INSERT INTO appointment_detail (pta_no, app_time, app_status, app_venue) VALUES (?, ?, ?, ?)";
//	private static final String UPDATE_STMT = "UPDATE appointment_detail SET pta_no = ?, app_time = ?, app_status = ?, app_venue = ? WHERE ad_no = ?";
//	private static final String DELETE_STMT = "DELETE FROM appointment_detail WHERE ad_no = ?";
//	private static final String FIND_BY_ADNO = "SELECT * FROM appointment_detail WHERE ad_no = ?";
//	private static final String GET_ALL = "SELECT * FROM appointment_detail";
//	
//	@Override
//	public void insert(AppointmentDetail appointmentDetailVO) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(INSERT_STMT);
//            ps.setInt(1, appointmentDetailVO.getPtaNo());
//            ps.setTimestamp(2, appointmentDetailVO.getAppTime());
//            ps.setInt(3, appointmentDetailVO.getAppStatus());
//            ps.setString(4, appointmentDetailVO.getAppVenue());
//            
//            count = ps.executeUpdate();
//
//        } catch (SQLException e) {
//        	throw new RuntimeException(e);
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
//	
//	@Override
//	public void update(AppointmentDetail appointmentDetailVO) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(UPDATE_STMT);
//            ps.setInt(1, appointmentDetailVO.getPtaNo());
//            ps.setTimestamp(2, appointmentDetailVO.getAppTime());
//            ps.setInt(3, appointmentDetailVO.getAppStatus());
//            ps.setString(4, appointmentDetailVO.getAppVenue());
//            ps.setInt(5, appointmentDetailVO.getAdNo());
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
//            System.out.println("修改成功");
//        } else {
//            System.out.println("修改失敗");
//        }
//	}
//	
//	@Override
//	public void delete(AppointmentDetail appointmentDetailVO) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(DELETE_STMT);
//            ps.setInt(1, appointmentDetailVO.getAdNo());
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
//    }
//
//	@Override
//	public AppointmentDetail findByAdNo(Integer adNo) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        AppointmentDetail appointmentDetailVO = null;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(FIND_BY_ADNO);
//            ps.setInt(1, adNo);
//            rs = ps.executeQuery();
//
//            if (rs.next()){
//            	appointmentDetailVO = new AppointmentDetail();
//            	appointmentDetailVO.setAdNo(adNo);
//            	appointmentDetailVO.setPtaNo(rs.getInt("pta_no"));
//            	appointmentDetailVO.setAppTime(rs.getTimestamp("app_time"));
//            	appointmentDetailVO.setAppStatus(rs.getInt("app_status"));
//            	appointmentDetailVO.setAppVenue(rs.getString("app_venue"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeResources(con, ps, rs);
//        }
//        return appointmentDetailVO;
//	}
//	
//	@Override
//	public List<AppointmentDetail> getAll() {
//		Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<AppointmentDetail> appointmentDetailVOList = new ArrayList<>();
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(GET_ALL);
//            rs = ps.executeQuery();
//
//            while (rs.next()){
//            	AppointmentDetail appointmentDetailVO = new AppointmentDetail();
//            	appointmentDetailVO.setAdNo(rs.getInt("ad_no"));
//            	appointmentDetailVO.setPtaNo(rs.getInt("pta_no"));
//            	appointmentDetailVO.setAppTime(rs.getTimestamp("app_time"));
//            	appointmentDetailVO.setAppStatus(rs.getInt("app_status"));
//            	appointmentDetailVO.setAppVenue(rs.getString("app_venue"));
//            	
//            	appointmentDetailVOList.add(appointmentDetailVO);
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeResources(con, ps, rs);
//        }
//
//        return appointmentDetailVOList;
//	}
//	
//	
//	public static void main(String[] args) {
//		AppointmentDetailJDBCDAO appointmentDetailDAO = new AppointmentDetailJDBCDAOImpl();
//		AppointmentDetail appointmentDetailVO = new AppointmentDetail();
//		
//		// 新增
////		appointmentDetailVO.setPtaNo(9);
////		appointmentDetailVO.setAppTime(Timestamp.valueOf("2023-11-30 12:00:00"));
////		appointmentDetailVO.setAppStatus(1);
////		appointmentDetailVO.setAppVenue("123");
////		appointmentDetailDAO.insert(appointmentDetailVO);
//		
//		// 修改
////		appointmentDetailVO.setPtaNo(5);
////		appointmentDetailVO.setAppTime(Timestamp.valueOf("2023-11-29 11:00:00"));
////		appointmentDetailVO.setAppStatus(2);
////		appointmentDetailVO.setAppVenue("525");
////		appointmentDetailVO.setAdNo(20);
////		appointmentDetailDAO.update(appointmentDetailVO);
//		
//		// 刪除
////		appointmentDetailVO.setAdNo(17);
////		appointmentDetailDAO.delete(appointmentDetailVO);
//		
//		// 查詢
////		appointmentDetailVO = appointmentDetailDAO.findByAdNo(13);
////		System.out.println(appointmentDetailVO);
//			
//		// 查詢全部
////		List<AppointmentDetailVO> appointmentDetailVOList = appointmentDetailDAO.getAll();
////		for(AppointmentDetailVO appointmentDetailVO1: appointmentDetailVOList) {
////			System.out.println(appointmentDetailVO1.getAdNo());
////			System.out.println(appointmentDetailVO1.getPtaNo());
////			System.out.println(appointmentDetailVO1.getAppTime());
////			System.out.println(appointmentDetailVO1.getAppStatus());
////			System.out.println(appointmentDetailVO1.getAppVenue());
////		}
//	}
//}