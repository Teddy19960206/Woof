package com.woof.latestnews.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.woof.util.Util;

public class LatestNewsDAOImpl  implements LatestNewsDAO {
	 private static final String INSERT_STMT = "INSERT INTO latest_news (  ln_title , ln_content,ln_photo,ln_time) VALUES (? , ? , ? , ?)";

	    private static final String UPDATE_STMT = "UPDATE latest_news SET ln_title=?,ln_content=?,ln_photo=? ,ln_time=? WHERE ln_no=?";

	    private static final String DELETE_STMT = "DELETE FROM latest_news WHERE ln_no= ?";

	    private static final String FIND_BY_ADMINNO= "SELECT * FROM latest_news WHERE ln_no = ?";

	    private static final String GET_ALL = "SELECT * FROM latest_news";

	    @Override
	    public void insert(LatestNewsVO latestnewsVO ) {

	        Connection con = null;
	        PreparedStatement ps = null;
	        int count = 0;
	        byte[] a = null;
	        
	        try {
	            con = Util.getConnection();
	            ps = con.prepareStatement(INSERT_STMT);
	            ps.setString(1, latestnewsVO.getLnTitle());
	            ps.setString(2, latestnewsVO.getLnContent());
	            ps.setBytes(3, latestnewsVO.getLnPhoto());
	            ps.setTimestamp(4, latestnewsVO.getLnTime());
	         
	            

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
	    public void update(LatestNewsVO latestnewsVO) {
	        Connection con = null;
	        PreparedStatement ps = null;
	        int count = 0;

	        try {
	            con = Util.getConnection();
	            ps = con.prepareStatement(UPDATE_STMT);
	            ps.setString(1, latestnewsVO.getLnTitle());
	            ps.setString(2, latestnewsVO.getLnContent());
	            ps.setBytes(3, latestnewsVO.getLnPhoto());
	            ps.setTimestamp(4, latestnewsVO.getLnTime());
	            ps.setInt(5,latestnewsVO.getLnNo());
	         

	            count = ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            Util.closeResources(con , ps , null);
	        }

	        if (count == 1) {
	            System.out.println("修改成功");
	        } else {
	            System.out.println("修改失敗");
	        }

	    }

	    public void delete(LatestNewsVO latestnewsVO) {
	        Connection con = null;
	        PreparedStatement ps = null;
	        int count  = 0;

	        try {
	            con = Util.getConnection();
	            ps =  con.prepareStatement(DELETE_STMT);
	            ps.setInt(1 , latestnewsVO.getLnNo());
	            count = ps.executeUpdate();


	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            Util.closeResources(con  , ps ,null);
	        }

	        if( count == 1){
	            System.out.println("刪除成功");
	        }else{
	            System.out.println("刪除失敗");
	        }
	    }

	    
	    @Override
	    public LatestNewsVO  findByLnNo(Integer lnNo){
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        LatestNewsVO latestnewsVO = null;

	        try {
	            con = Util.getConnection();
	            ps = con.prepareStatement(FIND_BY_ADMINNO);
	            ps.setInt(1,lnNo);
	            rs = ps.executeQuery();

	            if (rs.next()){
	            	latestnewsVO = new LatestNewsVO();
	            	latestnewsVO.setLnNo(lnNo);
	            	latestnewsVO.setLnTitle(rs.getString("ln_title"));
	            	latestnewsVO.setLnContent(rs.getString("ln_content"));
	            	latestnewsVO.setLnPhoto(rs.getBytes("ln_photo"));
	            	latestnewsVO.setLnTime(rs.getTimestamp("ln_time"));
	       
	            	
	            	
	            	
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            Util.closeResources(con , ps , rs);
	        }

	        return latestnewsVO;
	    }

	    @Override
	    public List<LatestNewsVO> getAll() {


	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        List<LatestNewsVO> latestnewsVOList = new ArrayList<>();

	        try {
	            con = Util.getConnection();
	            ps = con.prepareStatement(GET_ALL);
	            rs = ps.executeQuery();

	            while (rs.next()){
	            	LatestNewsVO latestnewsVO = new LatestNewsVO();
	            	latestnewsVO.setLnNo(rs.getInt("ln_no"));
	            	latestnewsVO.setLnTitle(rs.getString("ln_title"));
	            	latestnewsVO.setLnContent(rs.getString("ln_content"));
	            	latestnewsVO.setLnPhoto(rs.getBytes("ln_photo"));
	            	latestnewsVO.setLnTime(rs.getTimestamp("ln_time"));
	            	

	            	latestnewsVOList.add(latestnewsVO);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            Util.closeResources(con , ps , rs);
	        }

	        return latestnewsVOList;
	    }


	    public static void main(String[] args) throws IOException {
	    	LatestNewsDAO latestnewsDAO = new LatestNewsDAOImpl();

	    	LatestNewsVO latestnewsVO = new LatestNewsVO();
	        
	    	latestnewsVO.setLnNo(20);
	    	latestnewsVO.setLnTitle("1232");
	    	latestnewsVO.setLnContent("安安");
     	 byte[] pic = getPictureBtyeArray("C:\\Users\\T14 Gen 3\\Desktop\\吉\\123.jpg");
     	latestnewsVO.setLnPhoto(pic);
     	latestnewsVO.setLnTime(Timestamp.valueOf("2023-11-30 12:00:00"));
  
     	



	        latestnewsDAO.update(latestnewsVO);

	        System.out.println(latestnewsDAO.findByLnNo(1));
	
	        List<LatestNewsVO> latestnewsVOList = latestnewsDAO.getAll();
	
	        for (LatestNewsVO latestnews : latestnewsVOList){
	            System.out.println(latestnews);
	
	        }

	    }

	    public static byte[] getPictureBtyeArray(String path) throws IOException {

	        FileInputStream fis = new FileInputStream(path);
	        byte[] buffer = new byte[fis.available()];
	        fis.read(buffer);
	        fis.close();
	        return buffer;
	    }
}
