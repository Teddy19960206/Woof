package com.woof.appointmentdetail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.woof.util.Util;

public class AppointmentDetailDAOImpl implements AppointmentDetailDAO{
	
	private static final String INSERT_STMT = "INSERT INTO appointment_detail VALUES (? , ? , ? , ?)";
	private static final String UPDATE_STMT = "UPDATE appointment_detail SET pta_no = ?, app_time = ?, app_status = ?, app_venue = ? WHERE ad_no = ?";
	private static final String DELETE_STMT = "DELETE FROM appointment_detail WHERE ad_no = ?";
	private static final String FIND_BY_ADNO = "SELECT * FROM appointment_detail WHERE ad_no = ?";
	private static final String GET_ALL = "SELECT * FROM appointment_detail";
	
	@Override
	public void insert(AppointmentDetailVO appointmentDetailVO) {
		Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setInt(1, appointmentDetailVO.getPtaNo());
            ps.setTimestamp(2, appointmentDetailVO.getAppTime());
            ps.setInt(3, appointmentDetailVO.getAppStatus());
            ps.setString(4, appointmentDetailVO.getAppVenue());
            

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
	public void update(AppointmentDetailVO appointmentDetailVO) {
		Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(UPDATE_STMT);
            ps.setInt(1, appointmentDetailVO.getPtaNo());
            ps.setTimestamp(2, appointmentDetailVO.getAppTime());
            ps.setInt(3, appointmentDetailVO.getAppStatus());
            ps.setString(4, appointmentDetailVO.getAppVenue());
            ps.setInt(5, appointmentDetailVO.getAdNo());
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps ,null);
        }

        if (count == 1) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失敗");
        }
	}
	@Override
	public void delete(AppointmentDetailVO appointmentDetailVO) {
		Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(DELETE_STMT);
            ps.setInt(1, appointmentDetailVO.getAdNo());
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps ,null);
        }

        if (count == 1) {
            System.out.println("刪除成功");
        } else {
            System.out.println("刪除失敗");
        }
    }

	@Override
	public AppointmentDetailVO findByAdNo(Integer adNo) {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AppointmentDetailVO appointmentDetailVO = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(FIND_BY_ADNO);
            ps.setInt(1, adNo);
            rs = ps.executeQuery();

            if (rs.next()){
            	appointmentDetailVO = new AppointmentDetailVO();
            	appointmentDetailVO.setAdNo(adNo);
            	appointmentDetailVO.setPtaNo(rs.getInt("pta_no"));
            	appointmentDetailVO.setAppTime(rs.getTimestamp("app_time"));
            	appointmentDetailVO.setAppStatus(rs.getInt("app_status"));
            	appointmentDetailVO.setAppVenue(rs.getString("app_venue"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }
        return appointmentDetailVO;
	}
	@Override
	public List<AppointmentDetailVO> getAll() {
		Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AppointmentDetailVO> appointmentDetailVOList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()){
            	AppointmentDetailVO appointmentDetailVO = new AppointmentDetailVO();
            	appointmentDetailVO.setAdNo(rs.getInt("ad_no"));
            	appointmentDetailVO.setPtaNo(rs.getInt("pta_no"));
            	appointmentDetailVO.setAppTime(rs.getTimestamp("app_time"));
            	appointmentDetailVO.setAppStatus(rs.getInt("app_status"));
            	appointmentDetailVO.setAppVenue(rs.getString("app_venue"));
            	
            	appointmentDetailVOList.add(appointmentDetailVO);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return appointmentDetailVOList;
	}
	
	public static void main(String[] args) {
		AppointmentDetailDAO appointmentDetailDAO = new AppointmentDetailDAOImpl();
		AppointmentDetailVO appointmentDetailVO = new AppointmentDetailVO();
		
		appointmentDetailVO.setAdNo(11);
		appointmentDetailVO.setPtaNo(11);
		appointmentDetailVO.setAppTime(Timestamp.valueOf("2023-11-30 12:00:00"));
		appointmentDetailVO.setAppStatus(1);
		appointmentDetailVO.setAppVenue("123");
		appointmentDetailDAO.insert(appointmentDetailVO);
		
		

//		List<AppointmentDetailVO> appointmentDetailVOList = appointmentDetailDAO.getAll();
//		for(AppointmentDetailVO appointmentDetailVO: appointmentDetailVOList) {
//			System.out.println(appointmentDetailVO.getAdNo());
//			System.out.println(appointmentDetailVO.getPtaNo());
//			System.out.println(appointmentDetailVO.getAppTime());
//			System.out.println(appointmentDetailVO.getAppStatus());
//			System.out.println(appointmentDetailVO.getAppVenue());
//		}
	}
}
