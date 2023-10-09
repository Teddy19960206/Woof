package com.woof.appointmentdetail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.woof.util.Util;

public class AppointmentDetailDAOImpl implements AppointmentDetailDAO{
	
	public static final String INSERT_STMT = "INSERT INTO appointment_detail VALUES (? , ? , ? , ? , ?)";
	public static final String UPDATE_STMT = "UPDATE appointment_detail SET pta_no = ?, app_time = ?, app_status = ?, app_venue = ? WHERE ad_no = ?";
	private static final String DELETE_STMT = "DELETE FROM appointment_detail WHERE ad_no = ?";
	private static final String FIND_BY_ADNO = "SELECT * FROM appointment_detail WHERE ad_no = ?";
	private static final String GET_ALL = "SELECT * FROM appointment_detail";
	
	@Override
	public void insert(AppointmentDetailVO appointmentDetailVO) {
		Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        byte[] a = null;

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
		
	}
	@Override
	public void delete(Integer adNo) {
		
	}
	@Override
	public AppointmentDetailVO findByAdNo(Integer adNo) {
		return null;
	}
	@Override
	public List<AppointmentDetailVO> getAll() {
		return null;
	}
	
//	public static void main(String[] args) {
//		AppointmentDetailDAO appointmentDetail = new AppointmentDetailDAOImpl();
//		List<AppointmentDetailVO> appointmentDetailVOList = appointmentDetail.getAll();
//		for(AppointmentDetailVO appointmentDetailVO: appointmentDetailVOList) {
//			System.out.println(appointmentDetailVO.getAdNo());
//			System.out.println(appointmentDetailVO.getPtaNo());
//			System.out.println(appointmentDetailVO.getAppTime());
//			System.out.println(appointmentDetailVO.getAppStatus());
//			System.out.println(appointmentDetailVO.getAppVenue());
//		}
//	}
}
