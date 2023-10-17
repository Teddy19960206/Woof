//package com.woof.commentreport.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.woof.commentreport.entity.CommentReport;
//import com.woof.util.Util;
//
//public class CommentReportDAOJDBCImpl implements CommentReportJDBCDAO{
//
//	public static final String INSERT_STMT = "INSERT INTO comment_report (mem_no, trainer_no, pta_no, cr_context, cr_status, cr_date) VALUES (? , ? , ? , ? , ? , ?)";
//	public static final String UPDATE_STMT = "UPDATE comment_report SET mem_no = ?, trainer_no = ?, pta_no = ?, cr_context = ?, cr_status= ?, cr_date = ? WHERE cr_no = ?";
//	private static final String DELETE_STMT = "DELETE FROM comment_report WHERE cr_no = ?";
//	private static final String FIND_BY_CRNO = "SELECT * FROM comment_report WHERE cr_no = ?";
//	private static final String GET_ALL = "SELECT * FROM comment_report";
//	
//	@Override
//	public void insert(CommentReport commentReportVO) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(INSERT_STMT);
//            ps.setInt(1, commentReportVO.getMemNo());
//            ps.setInt(2, commentReportVO.getTrainerNo());
//            ps.setInt(3, commentReportVO.getPtaNo());
//            ps.setString(4, commentReportVO.getCrContext());
//            ps.setInt(5, commentReportVO.getCrStatus());
//            ps.setTimestamp(6, commentReportVO.getCrDate());
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
//	public void update(CommentReport commentReportVO) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(UPDATE_STMT);
//            ps.setInt(1, commentReportVO.getMemNo());
//            ps.setInt(2, commentReportVO.getTrainerNo());
//            ps.setInt(3, commentReportVO.getPtaNo());
//            ps.setString(4, commentReportVO.getCrContext());
//            ps.setInt(5, commentReportVO.getCrStatus());
//            ps.setTimestamp(6, commentReportVO.getCrDate());
//            ps.setInt(7, commentReportVO.getCrNo());
//
//            count = ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeResources(con, ps, null);
//        }
//
//
//        if (count == 1) {
//            System.out.println("修改成功");
//        } else {
//            System.out.println("修改失敗");
//        }
//	}
//	@Override
//	public void delete(CommentReport commentReportVO) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        int count = 0;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(DELETE_STMT);
//            ps.setInt(1, commentReportVO.getCrNo());
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
//	public CommentReport findByCrNo(Integer crNo) {
//		Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        CommentReport commentReportVO = null;
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(FIND_BY_CRNO);
//            ps.setInt(1, crNo);
//            rs = ps.executeQuery();
//
//            if (rs.next()){
//            	commentReportVO = new CommentReport();
//            	commentReportVO.setCrNo(crNo);
//            	commentReportVO.setMemNo(rs.getInt("mem_no"));
//            	commentReportVO.setTrainerNo(rs.getInt("trainer_no"));
//            	commentReportVO.setPtaNo(rs.getInt("pta_no"));
//            	commentReportVO.setCrContext(rs.getString("cr_context"));
//            	commentReportVO.setCrStatus(rs.getInt("cr_status"));
//            	commentReportVO.setCrDate(rs.getTimestamp("cr_date"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeResources(con, ps, rs);
//        }
//        return commentReportVO;
//	}
//	@Override
//	public List<CommentReport> getAll() {
//		Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<CommentReport> commentReportVOList = new ArrayList<>();
//
//        try {
//            con = Util.getConnection();
//            ps = con.prepareStatement(GET_ALL);
//            rs = ps.executeQuery();
//
//            while (rs.next()){
//            	CommentReport commentReportVO = new CommentReport();
//            	commentReportVO.setCrNo(rs.getInt("cr_No"));
//            	commentReportVO.setMemNo(rs.getInt("mem_no"));
//            	commentReportVO.setTrainerNo(rs.getInt("trainer_no"));
//            	commentReportVO.setPtaNo(rs.getInt("pta_no"));
//            	commentReportVO.setCrContext(rs.getString("cr_context"));
//            	commentReportVO.setCrStatus(rs.getInt("cr_status"));
//            	commentReportVO.setCrDate(rs.getTimestamp("cr_date"));
//            	
//            	commentReportVOList.add(commentReportVO);
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            Util.closeResources(con, ps, rs);
//        }
//
//        return commentReportVOList;
//	}
//	
//	public static void main(String[] args) {
////		CommentReportDAO commentReportDAO = new CommentReportDAOImpl();
////		CommentReportVO commentReportVO = new CommentReportVO();
//		
//		// 新增
////		commentReportVO.setMemNo(1);
////		commentReportVO.setTrainerNo(2);
////		commentReportVO.setPtaNo(3);
////		commentReportVO.setCrContext("馬卡巴卡");
////		commentReportVO.setCrStatus(4);
////		commentReportVO.setCrDate(Timestamp.valueOf("2023-12-25 12:00:00"));
////		commentReportDAO.insert(commentReportVO);
//		
//		// 修改
////		commentReportVO.setMemNo(2);
////		commentReportVO.setTrainerNo(3);
////		commentReportVO.setPtaNo(4);
////		commentReportVO.setCrContext("馬卡巴卡");
////		commentReportVO.setCrStatus(5);
////		commentReportVO.setCrDate(Timestamp.valueOf("2023-12-26 12:00:00"));
////		commentReportVO.setCrNo(11);		
////		commentReportDAO.update(commentReportVO);
//		
//		// 刪除
////		commentReportVO.setCrNo(11);
////		commentReportDAO.delete(commentReportVO);
//		
//		// 查詢
////		commentReportVO = commentReportDAO.findByCrNo(9);
////		System.out.println(commentReportVO);
//		
//		// 查詢全部
////		List<CommentReportVO> commentReportVOList = commentReportDAO.getAll();
////		for(CommentReportVO commentReportVO1: commentReportVOList) {
////			System.out.println(commentReportVO1.getCrNo());
////			System.out.println(commentReportVO1.getMemNo());
////			System.out.println(commentReportVO1.getTrainerNo());
////			System.out.println(commentReportVO1.getPtaNo());
////			System.out.println(commentReportVO1.getCrContext());
////			System.out.println(commentReportVO1.getCrStatus());
////			System.out.println(commentReportVO1.getCrDate());
////		}
//	}
//}
