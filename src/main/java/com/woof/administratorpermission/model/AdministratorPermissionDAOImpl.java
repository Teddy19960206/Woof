package com.woof.administratorpermission.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woof.cartlist.model.CartListVO;
import com.woof.util.Util;

public class AdministratorPermissionDAOImpl implements AdministratorPermissionDAO {
	private static final String INSERT_STMT = "INSERT INTO  administrator_permission (admin_no, func_no) VALUES ( ? , ? )";
	private static final String UPDATE_STMT = "UPDATE administrator_permission  SET admin_no = ?  WHERE func_no = ? ";
	private static final String DELETE_STMT = "DELETE FROM administrator_permission  WHERE  admin_no = ? and  func_no  = ?";
	private static final String FIND_ONE = "SELECT * FROM administrator_permission  WHERE admin_no = ? and  func_no  = ?";
	private static final String FIND_BY_ADMINNO = "SELECT * FROM administrator_permission WHERE admin_no = ?";
	private static final String GET_ALL = "SELECT * FROM administrator_permission ";

	@Override
	public void insert(AdministratorPermissionVO administratorPermissionVO) {
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;

		try {
			con = Util.getConnection();
			ps = con.prepareStatement(INSERT_STMT);
			ps.setInt(1, administratorPermissionVO.getAdminNo());
			ps.setInt(2, administratorPermissionVO.getFuncNo());

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
	public void update(AdministratorPermissionVO administratorPermissionVO) {
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;

		try {
			con = Util.getConnection();
			ps = con.prepareStatement(UPDATE_STMT);
			ps.setInt(1, administratorPermissionVO.getAdminNo());
			ps.setInt(2, administratorPermissionVO.getFuncNo());

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
	public void delete(AdministratorPermissionVO administratorPermissionVO) {
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;

		try {
			con = Util.getConnection();
			ps = con.prepareStatement(DELETE_STMT);
			ps.setInt(1, administratorPermissionVO.getAdminNo());
			ps.setInt(2, administratorPermissionVO.getFuncNo());

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
	public AdministratorPermissionVO find(AdministratorPermissionVO administratorPermissionVO1) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AdministratorPermissionVO administratorPermissionVO = null;

		try {
			con = Util.getConnection();
			ps = con.prepareStatement(FIND_ONE);
			ps.setInt(1, administratorPermissionVO1.getAdminNo());
			ps.setInt(2, administratorPermissionVO1.getFuncNo());
			rs = ps.executeQuery();

			if (rs.next()) {
				administratorPermissionVO = new AdministratorPermissionVO();
				administratorPermissionVO.setAdminNo(rs.getInt("admin_no"));
				administratorPermissionVO.setFuncNo(rs.getInt("func_no"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResources(con, ps, rs);
		}

		return administratorPermissionVO;
	}

	public AdministratorPermissionVO findByAdminNo(Integer adminNo) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AdministratorPermissionVO administratorPermissionVO = null;

		try {
			con = Util.getConnection();
			ps = con.prepareStatement(FIND_BY_ADMINNO);
			ps.setInt(1, adminNo);
			rs = ps.executeQuery();

			if (rs.next()) {
				administratorPermissionVO = new AdministratorPermissionVO();
				administratorPermissionVO.setAdminNo(adminNo);
				administratorPermissionVO.setFuncNo(rs.getInt("func_no"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResources(con, ps, rs);
		}

		return administratorPermissionVO;
	}

	@Override
	public List<AdministratorPermissionVO> getAll() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<AdministratorPermissionVO> administratorPermissionVOList = new ArrayList<>();

		try {
			con = Util.getConnection();
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				AdministratorPermissionVO administratorPermissionVO = new AdministratorPermissionVO();
				administratorPermissionVO.setAdminNo(rs.getInt("admin_no"));
				administratorPermissionVO.setFuncNo(rs.getInt("func_no"));

				administratorPermissionVOList.add(administratorPermissionVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeResources(con, ps, rs);
		}

		return administratorPermissionVOList;
	}

	public static void main(String[] args) {
		AdministratorPermissionDAO administratorPermissionDAO = new AdministratorPermissionDAOImpl();

		// 查詢多筆資料
		List<AdministratorPermissionVO> administratorPermissionVOList = administratorPermissionDAO.getAll();
//        
		for (AdministratorPermissionVO administratorPermissionVOList1 : administratorPermissionVOList) {
			System.out.println(administratorPermissionVOList1.getAdminNo());
			System.out.println(administratorPermissionVOList1.getFuncNo());
//     
//
		}

//    	 新增單筆資料
		AdministratorPermissionVO administratorPermission = new AdministratorPermissionVO();
		administratorPermission.setAdminNo(3);
		administratorPermission.setFuncNo(3);

//        	
		administratorPermissionDAO.insert(administratorPermission);
//     		
//    	}	

		// 修改單筆資料
//        	AdministratorPermissionVO administratorPermission = new AdministratorPermissionVO();
//        	
//        	administratorPermission.setCartAmount(1000);
//        	administratorPermission.setProdNo(1);
//        
//
//        	AdministratorPermissionDAO.update(administratorPermission);
//    }

		// 刪除單筆資料
//    		AdministratorPermissionVO administratorPermission = new AdministratorPermissionVO();	
//    		administratorPermission.setProdNo(1);
//    		administratorPermission.setMemNo(2);
//    		
//    		AdministratorPermissionDAO.delete(administratorPermission);
//    
//	}

//    	findOne的資料
//    	  AdministratorPermissionVO administratorPermission = new AdministratorPermissionVO();
//        administratorPermission.setAdminNo(2);
//        administratorPermission.setFuncNo(2);
//        System.out.println(administratorPermissionDAO.find(administratorPermission));

	}

//	   //findByAdminNo查詢資料	
//    	AdministratorPermissionVO administratorPermission = administratorPermissionDAO.findByAdminNo(2);
//		   System.out.println(administratorPermission);
//	
//		   }

}
