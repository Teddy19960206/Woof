package com.woof.cartlist.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.woof.util.Util;

public class CartListDAOImpl implements CartListDAO {

	private static final String INSERT_STMT = "INSERT INTO cart_list (prod_no, mem_no, cart_amount) VALUES ( ? , ? , ?)";
	private static final String UPDATE_STMT = "UPDATE cart_list SET cart_amount = ?  WHERE prod_no = ? and mem_no = ?";
	private static final String DELETE_STMT = "DELETE FROM cart_list WHERE  prod_no = ? and mem_no = ?";
	private static final String FIND_ONE = "SELECT * FROM cart_list WHERE prod_no = ? and mem_no = ?";
	private static final String FIND_BY_PRODNO = "SELECT * FROM cart_list WHERE prod_no = ?";
	private static final String FIND_BY_MEMNO = "SELECT * FROM cart_list WHERE mem_no = ?";
	private static final String GET_ALL = "SELECT * FROM cart_list";
	
	
	@Override
    public void insert(CartListVO cartListVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(INSERT_STMT);
            ps.setInt(1, cartListVO.getProdNo());
            ps.setInt(2, cartListVO.getMemNo());
            ps.setInt(3, cartListVO.getCartAmount());

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
	    public void update(CartListVO cartListVO) {
	        Connection con = null;
	        PreparedStatement ps = null;
	        int count = 0;

	        try {
	            con = Util.getConnection();
	            ps = con.prepareStatement(UPDATE_STMT);
	            ps.setInt(1, cartListVO.getCartAmount());
	            ps.setInt(2, cartListVO.getProdNo());
	            ps.setInt(3, cartListVO.getMemNo());

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
    public void delete(CartListVO cartListVO) {
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(DELETE_STMT);
            ps.setInt(1, cartListVO.getProdNo());
            ps.setInt(2, cartListVO.getMemNo());
            
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
    public CartListVO findOne(CartListVO cartListVO1) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CartListVO cartListVO = null;

        try {
            con  = Util.getConnection();
            ps = con.prepareStatement(FIND_ONE);
            ps.setInt(1, cartListVO1.getProdNo());
            ps.setInt(2, cartListVO1.getMemNo());
            rs = ps.executeQuery();

            if (rs.next()){
            	cartListVO = new CartListVO();
            	cartListVO.setProdNo(rs.getInt("prod_no"));
            	cartListVO.setMemNo(rs.getInt("mem_no"));
            	cartListVO.setCartAmount(rs.getInt("cart_amount"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con , ps , rs);
        }

        return cartListVO;
    }
    
    
    @Override
    public CartListVO findByProdNo(Integer prodNo) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CartListVO cartListVO = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(FIND_BY_PRODNO);
            ps.setInt(1, prodNo);
            rs = ps.executeQuery();

            if (rs.next()) {
            	cartListVO = new CartListVO();
            	cartListVO.setProdNo(prodNo);
            	cartListVO.setMemNo(rs.getInt("mem_no"));
            	cartListVO.setCartAmount(rs.getInt("cart_amount"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return cartListVO;
    }

    @Override
    public CartListVO findByMemNo(Integer memNo) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CartListVO cartListVO = null;

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(FIND_BY_MEMNO);
            ps.setInt(1, memNo);
            rs = ps.executeQuery();

            if (rs.next()) {
            	cartListVO = new CartListVO();
            	cartListVO.setMemNo(memNo);
            	cartListVO.setProdNo(rs.getInt("prod_no"));
            	cartListVO.setCartAmount(rs.getInt("cart_amount"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return cartListVO;
    }
    
    @Override
    public List<CartListVO> getAll() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<CartListVO> cartListVOList = new ArrayList<>();

        try {
            con = Util.getConnection();
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
            	CartListVO cartListVO = new CartListVO();
            	cartListVO.setProdNo(rs.getInt("prod_no"));
            	cartListVO.setMemNo(rs.getInt("mem_no"));
            	cartListVO.setCartAmount(rs.getInt("cart_amount"));
            	
            	cartListVOList.add(cartListVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.closeResources(con, ps, rs);
        }

        return cartListVOList;
    }


    public static void main(String[] args){
    	CartListDAO cartListDAO = new CartListDAOImpl();
    	
    	//查詢多筆資料
//        List<CartListVO> cartListVOList = cartListDAO.getAll();
//        
//        for (CartListVO CartListVOList: cartListVOList){
//            System.out.println(CartListVOList.getProdNo());
//            System.out.println(CartListVOList.getMemNo());
//            System.out.println(CartListVOList.getCartAmount());
//
//        }

//    	 新增單筆資料
//        	CartListVO cartList = new CartListVO();
//        	cartList.setProdNo(1);
//        	cartList.setMemNo(2);
//        	cartList.setCartAmount(11);
//        	
//        	cartListDAO.insert(cartList);
//     		
//    	}	
        	
        // 修改單筆資料	
//        	CartListVO cartList = new CartListVO();
//        	
//        	cartList.setCartAmount(1000);
//        	cartList.setProdNo(1);
//        	cartList.setMemNo(2);
//
//        	cartListDAO.update(cartList);
//    }
    
        // 刪除單筆資料
//    		CartListVO cartList = new CartListVO();	
//    		cartList.setProdNo(1);
//    		cartList.setMemNo(2);
//    		
//    		cartListDAO.delete(cartList);
//    
//	}
    	
    	//findOne的資料
//    	  CartListVO cartList = new CartListVO();
//        cartList.setProdNo(1);
//        cartList.setMemNo(1);
//        System.out.println(cartListDAO.findOne(cartList));
//
//    }
    	
    	//findByProdNo查詢資料	
//    		CartListVO cartList = cartListDAO.findByProdNo(1);
//    		System.out.println(cartList);
//
//    		}

	   //findByMemNo查詢資料	
		   CartListVO cartList = cartListDAO.findByMemNo(2);
		   System.out.println(cartList);
	
		   }

}
