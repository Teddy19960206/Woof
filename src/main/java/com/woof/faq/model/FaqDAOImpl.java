package com.woof.faq.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woof.util.Util;

public class FaqDAOImpl implements FaqDAO{
	
	private static final String INSERT_STMT = "INSERT INTO faq(faq_class, faq_title, faq_content) VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE faq SET faq_class = ?, faq_title = ?, faq_content = ? WHERE faq_no = ?";
	private static final String DELETE_STMT = "DELETE FROM faq WHERE faq_no = ?";
	private static final String FIND_BY_FAQNO = "SELECT * FROM faq WHERE faq_no = ?";
	private static final String GET_ALL = "SELECT * FROM faq";

	
	@Override
    public void insert(FaqVO faqVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setString(1, faqVO.getFaqClass());
            ps.setString(2, faqVO.getFaqTitle());
            ps.setString(3, faqVO.getFaqContent());
           
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
    public void update(FaqVO faqVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(UPDATE_STMT);
            ps.setString(1, faqVO.getFaqClass());
            ps.setString(2, faqVO.getFaqTitle());
            ps.setString(3, faqVO.getFaqContent());
            ps.setInt(4, faqVO.getFaqNo());
                  
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
    public void delete(FaqVO faqVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(DELETE_STMT);
            ps.setInt(1, faqVO.getFaqNo());
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
    public FaqVO findByFaqNo(Integer faqNo) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        FaqVO faqVO = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(FIND_BY_FAQNO);
            ps.setInt(1, faqNo);
            rs = ps.executeQuery();

            if (rs.next()) {
            	faqVO = new FaqVO();
            	faqVO.setFaqNo(faqNo);
            	faqVO.setFaqClass(rs.getString("faq_class"));
            	faqVO.setFaqTitle(rs.getString("faq_title"));
            	faqVO.setFaqContent(rs.getString("faq_content"));

            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return faqVO;
    }

    @Override
    public List<FaqVO> getAll() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<FaqVO> faqVOList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
            	FaqVO faqVO = new FaqVO();
            	faqVO.setFaqNo(rs.getInt("faq_no"));
            	faqVO.setFaqClass(rs.getString("faq_class"));
            	faqVO.setFaqTitle(rs.getString("faq_title"));
            	faqVO.setFaqContent(rs.getString("faq_content"));

            	
            	faqVOList.add(faqVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return faqVOList;
    }


    public static void main(String[] args){
    	FaqDAO faqDAO = new FaqDAOImpl();
    	
    	//查詢多筆資料
//        List<FaqVO> faqVOList = faqDAO.getAll();
//        
//        for (FaqVO FaqVOList: faqVOList){
//            System.out.println(FaqVOList.getFaqNo());
//            System.out.println(FaqVOList.getFaqClass());
//            System.out.println(FaqVOList.getFaqTitle());
//            System.out.println(FaqVOList.getFaqContent());
//   
//        }

//    	 新增單筆資料
//    		FaqVO faq = new FaqVO();
//        	faq.setFaqNo(11);
//        	faq.setFaqClass("莓有錢的問題");
//        	faq.setFaqTitle("莓有錢的問題");
//        	faq.setFaqContent("莓有錢的問題");
//        	
//        	faqDAO.insert(faq);
//     		
//    	}	
        	
        // 修改單筆資料	
//        	FaqVO faq = new FaqVO();
//        	
//        	faq.setFaqClass("莓有錢的問題莓有錢的問題");
//        	faq.setFaqTitle("莓有錢的問題莓有錢的問題");
//        	faq.setFaqContent("莓有錢的問題莓有錢的問題");
//        	faq.setFaqNo(11);
//
//        	faqDAO.update(faq);
//        	
//    }
    
        // 刪除單筆資料
    
    		FaqVO faq = new FaqVO();
    		
    		faq.setFaqNo(11);
    		
    		faqDAO.delete(faq);
    
	}
    	
   	
}
