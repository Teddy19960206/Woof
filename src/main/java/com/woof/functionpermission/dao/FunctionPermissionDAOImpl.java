package com.woof.functionpermission.dao;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.woof.functionpermission.entity.FunctionPermission;

public class FunctionPermissionDAOImpl implements FunctionPermissionDAO {
	private SessionFactory factory;

	public FunctionPermissionDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(FunctionPermission functionPermission) {
		return (Integer) getSession().save(functionPermission);
	}

	@Override
	public int update(FunctionPermission functionPermission) {
		try {
			getSession().update(functionPermission);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer funcNo) {
		FunctionPermission functionPermission = getSession().get(FunctionPermission.class, funcNo);
		if (functionPermission != null) {
			getSession().delete(functionPermission);
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public FunctionPermission findByFuncNo(Integer funcNo) {
		return getSession().get(FunctionPermission.class, funcNo);
	}

	@Override
	public List<FunctionPermission> getAll() {
		return getSession().createQuery("FROM FunctionPermission", FunctionPermission.class).list();
	}

}

//package com.woof.functionpermission.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.woof.functionpermission.entity.FunctionPermissionVO;
//import com.woof.util.Util;
//
//public class FunctionPermissionDAOImpl implements FunctionPermissionDAO {
//	private static final String INSERT_STMT = "INSERT INTO function_permission (func_name) VALUES (?)";
//	private static final String UPDATE_STMT = "UPDATE function_permission SET func_name = ? WHERE func_no = ?";
//	private static final String DELETE_STMT = "DELETE FROM function_permission WHERE func_no = ?";
//	private static final String FIND_BY_FUNCNO = "SELECT * FROM function_permission WHERE func_no = ?";
//	private static final String GET_ALL = "SELECT * FROM function_permission";
//
//	@Override
//	public void insert(FunctionPermissionVO functionPermissionVO) {
//		Connection con = null;
//		PreparedStatement ps = null;
//		int count = 0;
//		try {
//			con = Util.getConnection();
//			ps = con.prepareStatement(INSERT_STMT);
//			ps.setInt(1, functionPermissionVO.getFuncName());
//
//			count = ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			Util.closeResources(con, ps, null);
//		}
//
//		if (count == 1) {
//			System.out.println("新增成功");
//		} else {
//			System.out.println("新增失敗");
//		}
//	}
//
//	@Override
//	public void update(FunctionPermissionVO functionPermissionVO) {
//		Connection con = null;
//		PreparedStatement ps = null;
//		int count = 0;
//		try {
//			con = Util.getConnection();
//			ps = con.prepareStatement(UPDATE_STMT);
//			ps.setInt(1, functionPermissionVO.getFuncName());
//			ps.setInt(2, functionPermissionVO.getFuncNo());
//
//			count = ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			Util.closeResources(con, ps, null);
//		}
//
//		if (count == 1) {
//			System.out.println("修改成功");
//		} else {
//			System.out.println("修改失敗");
//		}
//	}
//
//	@Override
//	public void delete(FunctionPermissionVO functionPermissionVO) {
//		Connection con = null;
//		PreparedStatement ps = null;
//		int count = 0;
//		try {
//			con = Util.getConnection();
//			ps = con.prepareStatement(DELETE_STMT);
//			ps.setInt(1, functionPermissionVO.getFuncNo());
//			count = ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			Util.closeResources(con, ps, null);
//		}
//
//		if (count == 1) {
//			System.out.println("刪除成功");
//		} else {
//			System.out.println("刪除失敗");
//		}
//	}
//
//	@Override
//	public FunctionPermissionVO findBy(Integer funcNo) {
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		FunctionPermissionVO functionPermissionVO = null;
//
//		try {
//			con = Util.getConnection();
//			ps = con.prepareStatement(FIND_BY_FUNCNO);
//			ps.setInt(1, funcNo);
//			rs = ps.executeQuery();
//
//			if (rs.next()) {
//				functionPermissionVO = new FunctionPermissionVO();
//				functionPermissionVO.setFuncNo(rs.getInt("func_no"));
//				functionPermissionVO.setFuncName(rs.getInt("func_name"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			Util.closeResources(con, ps, rs);
//		}
//
//		return functionPermissionVO;
//	}
//
//	@Override
//	public List<FunctionPermissionVO> getAll() {
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		List<FunctionPermissionVO> functionPermissionList = new ArrayList<>();
//
//		try {
//			con = Util.getConnection();
//			ps = con.prepareStatement(GET_ALL);
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				FunctionPermissionVO functionPermissionVO = new FunctionPermissionVO();
//				functionPermissionVO.setFuncNo(rs.getInt("func_no"));
//				functionPermissionVO.setFuncName(rs.getInt("func_name"));
//
//				functionPermissionList.add(functionPermissionVO);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			Util.closeResources(con, ps, rs);
//		}
//
//		return functionPermissionList;
//	}
//
//	public static void main(String[] args) {
////		FunctionPermissionDAO dao = new FunctionPermissionDAOImpl();
//
////		// 新增
////		FunctionPermissionVO functionPermission1 = new FunctionPermissionVO();
////		functionPermission1.setFuncName(5); // 假設的數值
////		dao.insert(functionPermission1);
//
////		// 修改
////		FunctionPermissionVO functionPermission2 = new FunctionPermissionVO();
////		functionPermission2.setFuncName(2); // 假設的數值
////		functionPermission2.setFuncNo(5); // 假設的數值
////		dao.update(functionPermission2);
////
////		// 刪除
////		FunctionPermissionVO functionPermission3 = new FunctionPermissionVO();
////		functionPermission3.setFuncNo(17); // 假設的數值
////		dao.delete(functionPermission3);
////
////		// 查詢單筆
////		FunctionPermissionVO functionPermission4 = dao.findBy(13); // 假設的數值
////		System.out.println(functionPermission4.getFuncNo());
////		System.out.println(functionPermission4.getFuncName());
////
////		// 查詢全部
////		List<FunctionPermissionVO> list = dao.getAll();
////		for (FunctionPermissionVO aFunctionPermission : list) {
////			System.out.println(aFunctionPermission.getFuncNo() + ",");
////			System.out.println(aFunctionPermission.getFuncName());
////			System.out.println();
////		}
//	}
//}