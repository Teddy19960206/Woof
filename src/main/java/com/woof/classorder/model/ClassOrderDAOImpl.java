package com.woof.classorder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.woof.util.Util;

public class ClassOrderDAOImpl implements ClassOrderDAO{
	
	public static final String INSERT_STMT = "INSERT INTO class_order VALUES (? , ? , ? , ? , ? , ? , ? , ? )";
	public static final String UPDATE_STMT = "UPDATE class_order SET mem_no = ?, co_bc = ?, co_payment_method = ?, co_smmp = ?, co_time = ?, co_status = ?, co_ct = ?, actual_amount = ? WHERE co_no = ?";
	private static final String DELETE_STMT = "DELETE FROM class_order WHERE co_no = ?";
	private static final String FIND_BY_CONO = "SELECT * FROM class_order WHERE co_no = ?";
	private static final String GET_ALL = "SELECT * FROM class_order";
	
	@Override
	public void insert(ClassOrderVO classOrderVO) {
		Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        byte[] a = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setInt(1, classOrderVO.getMemNo());
            ps.setInt(2, classOrderVO.getCoBc());
            ps.setInt(3, classOrderVO.getCoPaymentMethod());
            ps.setInt(4, classOrderVO.getCoSmmp());
            ps.setTimestamp(5, classOrderVO.getCoTime());
            ps.setInt(6, classOrderVO.getCoStatus());
            ps.setTimestamp(7, classOrderVO.getCoCt());
            ps.setInt(8, classOrderVO.getActualAmount());
            

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
	public void update(ClassOrderVO classOrderVO) {
		
	}
	@Override
	public void delete(Integer coNo) {
		
	}
	@Override
	public ClassOrderVO findByCoNo(Integer coNo) {
		return null;
	}
	@Override
	public List<ClassOrderVO> getAll() {
		return null;
	}
	
//	public static void main(String[] args) {
//		ClassOrderDAO classOrder = new ClassOrderDAOImpl();
//		List<ClassOrderVO> classOrderVOList = classOrder.getAll();
//		for(ClassOrderVO classOrderVO: classOrderVOList) {
//			System.out.println(classOrderVO.getCoNo());
//			System.out.println(classOrderVO.getMemNo());
//			System.out.println(classOrderVO.getCoBc());
//			System.out.println(classOrderVO.getCoPaymentMethod());
//			System.out.println(classOrderVO.getCoSmmp());
//			System.out.println(classOrderVO.getCoTime());
//			System.out.println(classOrderVO.getCoStatus());
//			System.out.println(classOrderVO.getCoCt());
//			System.out.println(classOrderVO.getActualAmount());
//		}
//	}

}
