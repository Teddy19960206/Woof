//package com.woof.trainer.dao;
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
//import com.woof.trainer.entity.TrainerVO;
//import com.woof.util.Util;
//
//public class TrainerDAOImpl implements TrainerDAO {
//	 private static final String INSERT_STMT = "INSERT INTO trainer ( admin_no ,experience) VALUES (? , ? )";
//
//	    private static final String UPDATE_STMT = "UPDATE trainer SET admin_no=?,experience=? WHERE trainer_no=?";
//
//	    private static final String DELETE_STMT = "DELETE FROM trainer WHERE trainer_no= ?";
//
//	    private static final String FIND_BY_ADMINNO= "SELECT * FROM trainer WHERE trainer_no = ?";
//
//	    private static final String GET_ALL = "SELECT * FROM trainer";
//
//	    @Override
//	    public void insert(TrainerVO trainerVO ) {
//
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        int count = 0;
//	        byte[] a = null;
//	        
//	        try {
//	            con = Util.getConnection();
//	            ps = con.prepareStatement(INSERT_STMT);
//	            ps.setInt(1, trainerVO.getAdminNo());
//	            ps.setString(2, trainerVO.getExperience());
//	           
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
//	    public void update(TrainerVO trainerVO) {
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        int count = 0;
//
//	        try {
//	            con = Util.getConnection();
//	            ps = con.prepareStatement(UPDATE_STMT);
//	            ps.setInt(1, trainerVO.getAdminNo());
//	            ps.setString(2, trainerVO.getExperience());
//	            ps.setInt(3, trainerVO.getTrainerNo());
//	       
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
//	    public void delete(TrainerVO trainerVO) {
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        int count  = 0;
//
//	        try {
//	            con = Util.getConnection();
//	            ps =  con.prepareStatement(DELETE_STMT);
//	            ps.setInt(1 , trainerVO.getTrainerNo());
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
//	    public TrainerVO  findByTrainerNo(Integer trainerNo){
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        ResultSet rs = null;
//
//	        TrainerVO trainerVO = null;
//
//	        try {
//	            con = Util.getConnection();
//	            ps = con.prepareStatement(FIND_BY_ADMINNO);
//	            ps.setInt(1,trainerNo);
//	            rs = ps.executeQuery();
//
//	            if (rs.next()){
//	            	trainerVO = new TrainerVO();
//	            	trainerVO.setTrainerNo(trainerNo);
//	            	trainerVO.setAdminNo(rs.getInt("admin_no"));
//	            	trainerVO.setExperience(rs.getString("experience"));
//	          
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
//	        return trainerVO;
//	    }
//
//	    @Override
//	    public List<TrainerVO> getAll() {
//
//
//	        Connection con = null;
//	        PreparedStatement ps = null;
//	        ResultSet rs = null;
//
//	        List<TrainerVO> trainerVOList = new ArrayList<>();
//
//	        try {
//	            con = Util.getConnection();
//	            ps = con.prepareStatement(GET_ALL);
//	            rs = ps.executeQuery();
//
//	            while (rs.next()){
//	            	TrainerVO trainerVO = new TrainerVO();
//	            	trainerVO.setTrainerNo(rs.getInt("trainer_No"));
//	            	trainerVO.setAdminNo(rs.getInt("admin_no"));
//	            	trainerVO.setExperience(rs.getString("experience"));
//	    
//
//	            	trainerVOList.add(trainerVO);
//	            }
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            Util.closeResources(con , ps , rs);
//	        }
//
//	        return trainerVOList;
//	    }
//
//
//	    public static void main(String[] args) throws IOException {
//	    	TrainerDAO trainerDAO = new TrainerDAOImpl();
//
//	    	TrainerVO trainerVO = new TrainerVO();
//	        
//	    	trainerVO.setTrainerNo(11);
//	    	trainerVO.setAdminNo(1);
//	    	trainerVO.setExperience("寵物大師");
//  
//     	
//
//
//
//	        trainerDAO.delete(trainerVO);
//
//	        System.out.println(trainerDAO.findByTrainerNo(1));
//	
//	        List<TrainerVO> trainerVOList = trainerDAO.getAll();
//	
//	        for (TrainerVO trainer : trainerVOList){
//	            System.out.println(trainer);
//	
//	        }
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
