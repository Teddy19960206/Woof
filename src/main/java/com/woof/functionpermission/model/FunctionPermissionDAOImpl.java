package com.woof.functionpermission.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woof.util.Util;

public class FunctionPermissionDAOImpl implements FunctionPermissionDAO {
	private static final String INSERT_STMT = "INSERT INTO function_permission (func_name) VALUES (?)";
	private static final String UPDATE_STMT = "UPDATE function_permission SET func_name = ? WHERE func_no = ?";
	private static final String DELETE_STMT = "DELETE FROM function_permission WHERE func_no = ?";
	private static final String FIND_BY_FUNCNO = "SELECT * FROM function_permission WHERE func_no = ?";
	private static final String GET_ALL = "SELECT * FROM function_permission";

	@Override
	public void insert(FunctionPermissionVO functionPermissionVO) {
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			con = Util.getConnection();
			ps = con.prepareStatement(INSERT_STMT);
			ps.setInt(1, functionPermissionVO.getFuncName());

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
	public void update(FunctionPermissionVO functionPermissionVO) {
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			con = Util.getConnection();
			ps = con.prepareStatement(UPDATE_STMT);
			ps.setInt(1, functionPermissionVO.getFuncName());
			ps.setInt(2, functionPermissionVO.getFuncNo());

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
	public void delete(FunctionPermissionVO functionPermissionVO) {
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			con = Util.getConnection();
			ps = con.prepareStatement(DELETE_STMT);
			ps.setInt(1, functionPermissionVO.getFuncNo());
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
	public FunctionPermissionVO findBy(Integer funcNo) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		FunctionPermissionVO functionPermissionVO = null;

		try {
			con = Util.getConnection();
			ps = con.prepareStatement(FIND_BY_FUNCNO);
			ps.setInt(1, funcNo);
			rs = ps.executeQuery();

			if (rs.next()) {
				functionPermissionVO = new FunctionPermissionVO();
				functionPermissionVO.setFuncNo(rs.getInt("func_no"));
				functionPermissionVO.setFuncName(rs.getInt("func_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResources(con, ps, rs);
		}

		return functionPermissionVO;
	}

	@Override
	public List<FunctionPermissionVO> getAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<FunctionPermissionVO> functionPermissionList = new ArrayList<>();

		try {
			con = Util.getConnection();
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				FunctionPermissionVO functionPermissionVO = new FunctionPermissionVO();
				functionPermissionVO.setFuncNo(rs.getInt("func_no"));
				functionPermissionVO.setFuncName(rs.getInt("func_name"));

				functionPermissionList.add(functionPermissionVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResources(con, ps, rs);
		}

		return functionPermissionList;
	}
}