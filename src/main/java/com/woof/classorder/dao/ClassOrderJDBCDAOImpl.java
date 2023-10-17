//package com.woof.classorder.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.woof.classorder.entity.ClassOrder;
//import com.woof.util.Util;
//
//public class ClassOrderJDBCDAOImpl implements ClassOrderJDBCDAO{
//	
//	public static final String INSERT_STMT = "INSERT INTO class_order (mem_no, co_bc, co_payment_method, co_smmp, co_time, co_status, co_ct, actual_amount) VALUES (? , ? , ? , ? , ? , ? , ? , ? )";
//	public static final String UPDATE_STMT = "UPDATE class_order SET mem_no = ?, co_bc = ?, co_payment_method = ?, co_smmp = ?, co_time = ?, co_status = ?, co_ct = ?, actual_amount = ? WHERE co_no = ?";
//	private static final String DELETE_STMT = "DELETE FROM class_order WHERE co_no = ?";
//	private static final String FIND_BY_CONO = "SELECT * FROM class_order WHERE co_no = ?";
//	private static final String GET_ALL = "SELECT * FROM class_order";
//	
//	@Override
//	public void insert(ClassOrder classOrderVO) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(INSERT_STMT);
//            ps.setInt(1, classOrderVO.getMemNo());
//            ps.setInt(2, classOrderVO.getCoBc());
//            ps.setInt(3, classOrderVO.getCoPaymentMethod());
//            ps.setInt(4, classOrderVO.getCoSmmp());
//            ps.setTimestamp(5, classOrderVO.getCoTime());
//            ps.setInt(6, classOrderVO.getCoStatus());
//            ps.setTimestamp(7, classOrderVO.getCoCt());
//            ps.setInt(8, classOrderVO.getActualAmount());
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
//	public void update(ClassOrder classOrderVO) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(UPDATE_STMT);
//            ps.setInt(1, classOrderVO.getMemNo());
//            ps.setInt(2, classOrderVO.getCoBc());
//            ps.setInt(3, classOrderVO.getCoPaymentMethod());
//            ps.setInt(4, classOrderVO.getCoSmmp());
//            ps.setTimestamp(5, classOrderVO.getCoTime());
//            ps.setInt(6, classOrderVO.getCoStatus());
//            ps.setTimestamp(7, classOrderVO.getCoCt());
//            ps.setInt(8, classOrderVO.getActualAmount());
//            ps.setInt(9, classOrderVO.getCoNo());
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
//	public void delete(ClassOrder classOrderVO) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(DELETE_STMT);
//            ps.setInt(1, classOrderVO.getCoNo());
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
//            System.out.println("刪除成功");
//        } else {
//            System.out.println("刪除失敗");
//        }
//	}
//	@Override
//	public ClassOrder findByCoNo(Integer coNo) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        ClassOrder classOrderVO = null;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(FIND_BY_CONO);
//            ps.setInt(1, coNo);
//            rs = ps.executeQuery();
//
//            if (rs.next()){
//            	classOrderVO = new ClassOrder();
//            	classOrderVO.setCoNo(coNo);
//            	classOrderVO.setMemNo(rs.getInt("mem_no"));
//            	classOrderVO.setCoBc(rs.getInt("co_bc"));
//            	classOrderVO.setCoPaymentMethod(rs.getInt("co_payment_method"));
//            	classOrderVO.setCoSmmp(rs.getInt("co_smmp"));
//            	classOrderVO.setCoTime(rs.getTimestamp("co_time"));
//            	classOrderVO.setCoStatus(rs.getInt("co_status"));
//            	classOrderVO.setCoCt(rs.getTimestamp("co_ct"));
//            	classOrderVO.setActualAmount(rs.getInt("actual_amount"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeResources(con, ps, rs);
//        }
//        return classOrderVO;
//	}
//	@Override
//	public List<ClassOrder> getAll() {
//		Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<ClassOrder> classOrderVOList = new ArrayList<>();
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(GET_ALL);
//            rs = ps.executeQuery();
//
//            while (rs.next()){
//            	ClassOrder classOrderVO = new ClassOrder();
//            	classOrderVO.setCoNo(rs.getInt("co_No"));
//            	classOrderVO.setMemNo(rs.getInt("mem_no"));
//            	classOrderVO.setCoBc(rs.getInt("co_bc"));
//            	classOrderVO.setCoPaymentMethod(rs.getInt("co_payment_method"));
//            	classOrderVO.setCoSmmp(rs.getInt("co_smmp"));
//            	classOrderVO.setCoTime(rs.getTimestamp("co_time"));
//            	classOrderVO.setCoStatus(rs.getInt("co_status"));
//            	classOrderVO.setCoCt(rs.getTimestamp("co_ct"));
//            	classOrderVO.setActualAmount(rs.getInt("actual_amount"));
//            	
//            	classOrderVOList.add(classOrderVO);
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeResources(con, ps, rs);
//        }
//
//        return classOrderVOList;
//	}
//
//	
//	public static void main(String[] args) {
////		ClassOrderDAO classOrderDAO = new ClassOrderDAOImpl();
////		ClassOrderVO classOrderVO = new ClassOrderVO();
//		
//		// 新增
////		classOrderVO.setMemNo(2);
////    	classOrderVO.setCoBc(8);
////    	classOrderVO.setCoPaymentMethod(1);
////    	classOrderVO.setCoSmmp(0);
////    	classOrderVO.setCoTime(Timestamp.valueOf("2023-10-10 11:00:00"));
////    	classOrderVO.setCoStatus(2);
////    	classOrderVO.setCoCt(Timestamp.valueOf("2023-10-12 13:00:00"));
////    	classOrderVO.setActualAmount(2000);
////    	classOrderDAO.insert(classOrderVO);
//		
//		// 修改
////    	classOrderVO.setMemNo(3);
////    	classOrderVO.setCoBc(4);
////    	classOrderVO.setCoPaymentMethod(0);
////    	classOrderVO.setCoSmmp(1);
////    	classOrderVO.setCoTime(Timestamp.valueOf("2023-10-11 11:00:00"));
////    	classOrderVO.setCoStatus(2);
////    	classOrderVO.setCoCt(Timestamp.valueOf("2023-10-14 13:00:00"));
////    	classOrderVO.setActualAmount(2000);
////    	classOrderVO.setCoNo(11);    	
////    	classOrderDAO.update(classOrderVO);
//    	
//    	// 刪除
////    	classOrderVO.setCoNo(11);
////    	classOrderDAO.delete(classOrderVO);
//		
//		// 查詢
////		classOrderVO = classOrderDAO.findByCoNo(1);
////		System.out.println(classOrderVO);
//    	
//		// 查詢全部
////		List<ClassOrderVO> classOrderVOList = classOrderDAO.getAll();
////		for(ClassOrderVO classOrderVO1: classOrderVOList) {
////			System.out.println(classOrderVO1.getCoNo());
////			System.out.println(classOrderVO1.getMemNo());
////			System.out.println(classOrderVO1.getCoBc());
////			System.out.println(classOrderVO1.getCoPaymentMethod());
////			System.out.println(classOrderVO1.getCoSmmp());
////			System.out.println(classOrderVO1.getCoTime());
////			System.out.println(classOrderVO1.getCoStatus());
////			System.out.println(classOrderVO1.getCoCt());
////			System.out.println(classOrderVO1.getActualAmount());
////		}
//	}
//
//}
