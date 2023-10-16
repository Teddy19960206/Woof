//package com.woof.administrator.dao;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.woof.administrator.entity.AdministratorVO;
//import com.woof.groupcourseorder.model.GroupCourseOrderVO;
//import com.woof.util.Util;
//
//public class AdministratorDAOImpl implements AdministratorDAO {
//	 private static final String INSERT_STMT = "INSERT INTO administrator ( admin_name , admin_gender , admin_photo,admin_email,admin_password,admin_tel,admin_address,admin_bd,emergency_contactname,emergency_contactel,admin_hd,admin_rd) VALUES (? , ? , ? , ?,?,?,?,?,?,?,?,?)";
//
//	    private static final String UPDATE_STMT = "UPDATE administrator SET admin_name=?,admin_gender=?,admin_photo=?,admin_email=?,admin_password=?,admin_tel=?,admin_address=?,admin_bd=?,emergency_contactname=?,emergency_contactel=?,admin_hd=?,admin_rd=?,admin_status=? WHERE admin_no=?";
//
//	    private static final String DELETE_STMT = "DELETE FROM administrator WHERE admin_no= ?";
//
//	    private static final String FIND_BY_ADMINNO= "SELECT * FROM administrator WHERE admin_no = ?";
//
//	    private static final String GET_ALL = "SELECT * FROM administrator";
//
//	    @Override
//	    public void insert(AdministratorVO administratorVO ) {
//
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        int count = 0;
//	        byte[] a = null;
//	        
//	        try {
//	            con = Util.getConnection();
//	            ps = con.prepareStatement(INSERT_STMT);
//	            ps.setString(1, administratorVO.getAdminName());
//	            ps.setString(2, administratorVO.getAdminGender());
//	            ps.setBytes(3, administratorVO.getAdminPhoto());
//	            ps.setString(4, administratorVO.getAdminEmail());
//	            ps.setString(5,administratorVO.getAdminPassword());
//	            ps.setString(6,administratorVO.getAdminTel());
//	            ps.setString(7,administratorVO.getAdminAddress());
//	            ps.setDate(8,administratorVO.getAdminBd());
//	            ps.setString(9,administratorVO.getEmergencyContactName());
//	            ps.setInt(10,administratorVO.getEmergencyContactel());
//	            ps.setDate(11,administratorVO.getAdminHd());
//	            ps.setDate(12,administratorVO.getAdminRd());
//	            
//
//	            count = ps.executeUpdate();
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            Util.closeResources(con, ps, null);
//	        }
//
//
//	        if (count == 1) {
//	            System.out.println("新增成功");
//	        } else {
//	            System.out.println("新增失敗");
//	        }
//
//	    }
//
//	    @Override
//	    public void update(AdministratorVO administratorVO) {
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        int count = 0;
//
//	        try {
//	            con = Util.getConnection();
//	            ps = con.prepareStatement(UPDATE_STMT);
//	            ps.setString(1, administratorVO.getAdminName());
//	            ps.setString(2, administratorVO.getAdminGender());
//	            ps.setBytes(3, administratorVO.getAdminPhoto());
//	            ps.setString(4, administratorVO.getAdminEmail());
//	            ps.setString(5,administratorVO.getAdminPassword());
//	            ps.setString(6,administratorVO.getAdminTel());
//	            ps.setString(7,administratorVO.getAdminAddress());
//	            ps.setDate(8,administratorVO.getAdminBd());
//	            ps.setString(9,administratorVO.getEmergencyContactName());
//	            ps.setInt(10,administratorVO.getEmergencyContactel());
//	            ps.setDate(11,administratorVO.getAdminHd());
//	            ps.setDate(12,administratorVO.getAdminRd());
//	            ps.setInt(13,administratorVO.getAdminStatus());
//	            ps.setInt(14,administratorVO.getAdminNo());
//
//	            count = ps.executeUpdate();
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            Util.closeResources(con , ps , null);
//	        }
//
//	        if (count == 1) {
//	            System.out.println("修改成功");
//	        } else {
//	            System.out.println("修改失敗");
//	        }
//
//	    }
//
//	    public void delete(AdministratorVO administratorVO) {
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        int count  = 0;
//
//	        try {
//	            con = Util.getConnection();
//	            ps =  con.prepareStatement(DELETE_STMT);
//	            ps.setInt(1 , administratorVO.getAdminNo());
//	            count = ps.executeUpdate();
//
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            Util.closeResources(con  , ps ,null);
//	        }
//
//	        if( count == 1){
//	            System.out.println("刪除成功");
//	        }else{
//	            System.out.println("刪除失敗");
//	        }
//	    }
//
//	    
//	    @Override
//	    public AdministratorVO  findbyAdminNo(Integer adminNo){
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        ResultSet rs = null;
//
//	        AdministratorVO administratorVO = null;
//
//	        try {
//	            con = Util.getConnection();
//	            ps = con.prepareStatement(FIND_BY_ADMINNO);
//	            ps.setInt(1,adminNo);
//	            rs = ps.executeQuery();
//
//	            if (rs.next()){
//	            	administratorVO = new AdministratorVO();
//	            	administratorVO.setAdminNo(adminNo);
//	            	administratorVO.setAdminName(rs.getString("admin_name"));
//	            	administratorVO.setAdminGender(rs.getString("admin_gender"));
//	            	administratorVO.setAdminPhoto(rs.getBytes("admin_photo"));
//	            	administratorVO.setAdminEmail(rs.getString("admin_email"));
//	            	administratorVO.setAdminPassword(rs.getString("admin_password"));
//	            	administratorVO.setAdminTel(rs.getString("admin_tel"));
//	            	administratorVO.setAdminAddress(rs.getString("admin_address"));
//	            	administratorVO.setAdminBd(rs.getDate("admin_bd"));
//	            	administratorVO.setEmergencyContactName(rs.getString("emergency_contactname"));
//	            	administratorVO.setEmergencyContactel(rs.getInt("emergency_contactel"));
//	            	administratorVO.setAdminHd(rs.getDate("admin_hd"));
//	            	administratorVO.setAdminRd(rs.getDate("admin_rd"));
//	            	administratorVO.setAdminStatus(rs.getInt("admin_status"));
//	            	
//	            	
//	            	
//	            }
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            Util.closeResources(con , ps , rs);
//	        }
//
//	        return administratorVO;
//	    }
//
//	    @Override
//	    public List<AdministratorVO> getAll() {
//
//
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        ResultSet rs = null;
//
//	        List<AdministratorVO> administratorVOList = new ArrayList<>();
//
//	        try {
//	            con = Util.getConnection();
//	            ps = con.prepareStatement(GET_ALL);
//	            rs = ps.executeQuery();
//
//	            while (rs.next()){
//	            	AdministratorVO administratorVO = new AdministratorVO();
//	            	administratorVO.setAdminNo(rs.getInt("admin_no"));
//	            	administratorVO.setAdminName(rs.getString("admin_name"));
//	            	administratorVO.setAdminGender(rs.getString("admin_gender"));
//	            	administratorVO.setAdminPhoto(rs.getBytes("admin_photo"));
//	            	administratorVO.setAdminEmail(rs.getString("admin_email"));
//	            	administratorVO.setAdminPassword(rs.getString("admin_password"));
//	            	administratorVO.setAdminTel(rs.getString("admin_tel"));
//	            	administratorVO.setAdminAddress(rs.getString("admin_address"));
//	            	administratorVO.setAdminBd(rs.getDate("admin_bd"));
//	            	administratorVO.setEmergencyContactName(rs.getString("emergency_contactname"));
//	            	administratorVO.setEmergencyContactel(rs.getInt("emergency_contactel"));
//	            	administratorVO.setAdminHd(rs.getDate("admin_hd"));
//	            	administratorVO.setAdminRd(rs.getDate("admin_rd"));
//	            	administratorVO.setAdminStatus(rs.getInt("admin_status"));
//
//	            	administratorVOList.add(administratorVO);
//	            }
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            Util.closeResources(con , ps , rs);
//	        }
//
//	        return administratorVOList;
//	    }
//
//
//	    public static void main(String[] args) throws IOException {
//	    	AdministratorDAO administratorDAO = new AdministratorDAOImpl();
//
//	        AdministratorVO administratorVO = new AdministratorVO();
//	        
//	        administratorVO.setAdminNo(13);
//        	administratorVO.setAdminName("管理員12");
//        	administratorVO.setAdminGender("女");
//        	 byte[] pic = getPictureBtyeArray("C:\\Users\\T14 Gen 3\\Desktop\\吉\\123.jpg");
//        	administratorVO.setAdminPhoto(pic);
//        	administratorVO.setAdminEmail("123456621@gmail.com");
//        	administratorVO.setAdminPassword("12345622");
//        	administratorVO.setAdminTel("09xxxxxxx");
//        	administratorVO.setAdminAddress("51");
//        	administratorVO.setAdminBd(Date.valueOf("2023-11-11"));
//        	administratorVO.setEmergencyContactName("緊急聯絡人20");
//        	administratorVO.setEmergencyContactel(5);
//        	administratorVO.setAdminHd(Date.valueOf("2023-11-11"));
//        	administratorVO.setAdminRd(Date.valueOf("2023-11-11"));
//        	administratorVO.setAdminStatus(11);
//        	
//
//
//
////	        administratorDAO.delete(administratorVO);
//
////	        System.out.println(administratorDAO.findbyAdminNo(1));
//	
////	        List<AdministratorVO> administratorVOList = administratorDAO.getAll();
//	
////	        for (AdministratorVO administrator : administratorVOList){
////	            System.out.println(administrator);
//	
////	        }
//
//	    }
//
//	    public static byte[] getPictureBtyeArray(String path) throws IOException {
//
//	        FileInputStream fis = new FileInputStream(path);
//	        byte[] buffer = new byte[fis.available()];
//	        fis.read(buffer);
//	        fis.close();
//	        return buffer;
//	    }
//}
