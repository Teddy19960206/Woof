package com.woof.privatetrainingappointmentform.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.woof.util.Util;

public class PrivateTrainingAppointmentFormDAOImpl implements PrivateTrainingAppointmentFormDAO{
	
	public static final String INSERT_STMT = "INSERT INTO private_training_appointment_form VALUES (? , ? , ?)";
	public static final String UPDATE_STMT = "UPDATE private_training_appointment_form SET mem_no = ?, trainer_no = ?, pta_class = ? WHERE pta_no = ?";
	private static final String DELETE_STMT = "DELETE FROM private_training_appointment_form WHERE pta_no = ?";
	private static final String FIND_BY_PTANO = "SELECT * FROM private_training_appointment_form WHERE pta_no = ?";
	private static final String GET_ALL = "SELECT * FROM private_training_appointment_form";
	
	@Override
	public void insert(PrivateTrainingAppointmentFormVO privateTrainingAppointmentFormVO) {
		Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        byte[] a = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setInt(1, privateTrainingAppointmentFormVO.getMemNo());
            ps.setInt(2, privateTrainingAppointmentFormVO.getTrainerNo());
            ps.setInt(3, privateTrainingAppointmentFormVO.getPtaClass());
           

            

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
	public void update(PrivateTrainingAppointmentFormVO privateTrainingAppointmentFormVO) {
		
	}
	@Override
	public void delete(Integer ptaNo) {
		
	}
	@Override
	public PrivateTrainingAppointmentFormVO findByPtaNo(Integer ptaNo) {
		return null;
	}
	@Override
	public List<PrivateTrainingAppointmentFormVO> getAll() {
		return null;
	}
	
//	public static void main(String[] args) {
//		PrivateTrainingAppointmentFormDAO privateTrainingAppointmentForm = new PrivateTrainingAppointmentFormDAOImpl();
//		List<PrivateTrainingAppointmentFormVO> privateTrainingAppointmentFormVOList = privateTrainingAppointmentForm.getAll();
//		for(PrivateTrainingAppointmentFormVO privateTrainingAppointmentFormVO: privateTrainingAppointmentFormVOList) {
//			System.out.println(privateTrainingAppointmentFormVO.getPtaNo());
//			System.out.println(privateTrainingAppointmentFormVO.getMemNo());
//			System.out.println(privateTrainingAppointmentFormVO.getTrainerNo());
//			System.out.println(privateTrainingAppointmentFormVO.getPtaClass());
//		}
//	}
}
