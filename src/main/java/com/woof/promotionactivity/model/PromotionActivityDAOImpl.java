package com.woof.promotionactivity.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

import com.woof.util.Util;

public class PromotionActivityDAOImpl implements PromotionActivityDAO{
	
	private static final String INSERT_STMT = "INSERT INTO promotion_activity(pa_name, pa_discount, pa_content, pa_start, pa_end, pa_status) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE promotion_activity SET pa_name = ?, pa_discount = ?, pa_content = ?, pa_start = ?, pa_end = ?, pa_status = ? WHERE pa_no = ?";
	private static final String DELETE_STMT = "DELETE FROM promotion_activity WHERE pa_no = ?";
	private static final String FIND_BY_PANO = "SELECT * FROM promotion_activity WHERE pa_no = ?";
	private static final String GET_ALL = "SELECT * FROM promotion_activity";


    @Override
    public void insert(PromotionActivityVO promotionActivityVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setString(1, promotionActivityVO.getPaName());
            ps.setBigDecimal(2, promotionActivityVO.getPaDiscount());
            ps.setString(3, promotionActivityVO.getPaContent());
            ps.setTimestamp(4, promotionActivityVO.getPaStart());
            ps.setTimestamp(5, promotionActivityVO.getPaEnd());
            ps.setBoolean(6, promotionActivityVO.getPaStatus());
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
    public void update(PromotionActivityVO promotionActivityVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(UPDATE_STMT);
            ps.setString(1, promotionActivityVO.getPaName());
            ps.setBigDecimal(2, promotionActivityVO.getPaDiscount());
            ps.setString(3, promotionActivityVO.getPaContent());
            ps.setTimestamp(4, promotionActivityVO.getPaStart());
            ps.setTimestamp(5, promotionActivityVO.getPaEnd());
            ps.setBoolean(6, promotionActivityVO.getPaStatus());
            ps.setInt(7, promotionActivityVO.getPaNo());
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
    public void delete(PromotionActivityVO promotionActivityVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(DELETE_STMT);
            ps.setInt(1, promotionActivityVO.getPaNo());
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
    public PromotionActivityVO findByPaNo(Integer paNo) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PromotionActivityVO promotionActivityVO = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(FIND_BY_PANO);
            ps.setInt(1, paNo);
            rs = ps.executeQuery();

            if (rs.next()) {
            	promotionActivityVO = new PromotionActivityVO();
            	promotionActivityVO.setPaNo(paNo);
            	promotionActivityVO.setPaName(rs.getString("pa_name"));
            	promotionActivityVO.setPaDiscount(rs.getBigDecimal("pa_discount"));
            	promotionActivityVO.setPaContent(rs.getString("pa_content"));
            	promotionActivityVO.setPaStart(rs.getTimestamp("pa_start"));
            	promotionActivityVO.setPaEnd(rs.getTimestamp("pa_end"));
            	promotionActivityVO.setPaStatus(rs.getBoolean("pa_status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return promotionActivityVO;
    }

    @Override
    public List<PromotionActivityVO> getAll() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PromotionActivityVO> promotionActivityVOList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
            	PromotionActivityVO promotionActivityVO = new PromotionActivityVO();
            	promotionActivityVO.setPaNo(rs.getInt("pa_no"));
            	promotionActivityVO.setPaName(rs.getString("pa_name"));
            	promotionActivityVO.setPaDiscount(rs.getBigDecimal("pa_discount"));
            	promotionActivityVO.setPaContent(rs.getString("pa_content"));
            	promotionActivityVO.setPaStart(rs.getTimestamp("pa_start"));
            	promotionActivityVO.setPaEnd(rs.getTimestamp("pa_end"));
            	promotionActivityVO.setPaStatus(rs.getBoolean("pa_status"));
            	
            	promotionActivityVOList.add(promotionActivityVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return promotionActivityVOList;
    }


    public static void main(String[] args){
    	PromotionActivityDAO promotionActivityDAO = new PromotionActivityDAOImpl();
    	
    	//查詢多筆資料
//        List<PromotionActivityVO> promotionActivityVOList = promotionActivityDAO.getAll();
        
//        for (PromotionActivityVO PromotionActivityVOList: promotionActivityVOList){
//            System.out.println(PromotionActivityVOList.getPaNo());
//            System.out.println(PromotionActivityVOList.getPaName());
//            System.out.println(PromotionActivityVOList.getPaDiscount());
//            System.out.println(PromotionActivityVOList.getPaContent());
//            System.out.println(PromotionActivityVOList.getPaStart());
//            System.out.println(PromotionActivityVOList.getPaEnd());
//            System.out.println(PromotionActivityVOList.getPaStatus());
//        }

//    	 新增單筆資料
//        	PromotionActivityVO promotionActivity = new PromotionActivityVO();
//        	promotionActivity.setPaName("雙十國慶1010");
//        	promotionActivity.setPaDiscount(new BigDecimal(0.10));
//        	promotionActivity.setPaContent("雙十國慶1010活動，所有商品全面1折");
//        	promotionActivity.setPaStart(Timestamp.valueOf("2023-10-10 10:10:10"));
//        	promotionActivity.setPaEnd(Timestamp.valueOf("2023-10-30 10:30:30"));
//        	promotionActivity.setPaStatus(true);
//        	
//        	promotionActivityDAO.insert(promotionActivity);
//     		
//    	}	
        	
        // 修改單筆資料	
//        	PromotionActivityVO promotionActivity = new PromotionActivityVO();
//        	
//        	promotionActivity.setPaName("修改1010");
//        	promotionActivity.setPaDiscount(new BigDecimal(0.25));
//        	promotionActivity.setPaContent("1010活動，所有商品全面25折");
//        	promotionActivity.setPaStart(Timestamp.valueOf("2025-10-10 10:10:10"));
//        	promotionActivity.setPaEnd(Timestamp.valueOf("2025-10-30 10:30:30"));
//        	promotionActivity.setPaStatus(false);
//        	promotionActivity.setPaNo(11);
//
//        	promotionActivityDAO.update(promotionActivity);
//    }
    
        // 刪除單筆資料
    
//    		PromotionActivityVO promotionActivity = new PromotionActivityVO();
//    		
//    		promotionActivity.setPaNo(11);
//    		
//    		promotionActivityDAO.delete(promotionActivity);
//    
//	}
    	
    	//查詢單筆資料
    	  		
    		PromotionActivityVO promotionActivity = promotionActivityDAO.findByPaNo(1);

    		System.out.println(promotionActivity);
    		
//    		if (promotionActivity != null) {
//    		    System.out.println("找到促銷活動:");
//    		    System.out.println("活動名稱: " + promotionActivity.getPaName());
//    		    System.out.println("折扣: " + promotionActivity.getPaDiscount());
//    		    System.out.println("活動內容: " + promotionActivity.getPaContent());
//    		    System.out.println("活動開始時間: " + promotionActivity.getPaStart());
//    		    System.out.println("活動開始結束: " + promotionActivity.getPaEnd());
//    		    System.out.println("活動狀態: " + promotionActivity.getPaStatus());
//    		    
//    		} else {
//    		    System.out.println("未找到指定編號的促銷活動");
//    		}

    	
	}
}
